package ch.zhaw.prog2.bridge;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Controls the traffic passing the bridge
 */
public class TrafficController {

    private boolean bridgeOccupied = false;
    private int waitingTime = 1000;

    private ReentrantLock mutex = new ReentrantLock();
    private Condition leftToRight = mutex.newCondition();
    private Condition rightToLeft = mutex.newCondition();


    /* Called when a car wants to enter the bridge form the left side */
    public void enterLeft() {
        mutex.lock();
        while(bridgeOccupied) {
            try {
                rightToLeft.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        bridgeOccupied = true;
        mutex.unlock();
    }

    /* Called when a wants to enter the bridge form the right side */
    public void enterRight() {
        mutex.lock();
        while (bridgeOccupied){
            try{
                rightToLeft.await();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        bridgeOccupied = true;
        mutex.unlock();
    }

    /* Called when the car leaves the bridge on the left side */
    public void leaveLeft() {
        mutex.lock();
        bridgeOccupied = false;
        if(mutex.hasWaiters(leftToRight)){
            leftToRight.signal();
        } else {
            rightToLeft.signal();
        }
        mutex.unlock();
    }

    /* Called when the car leaves the bridge on the right side */
    public void leaveRight() {
        mutex.lock();
        bridgeOccupied = false;
        if(mutex.hasWaiters(rightToLeft)){
            rightToLeft.signal();
        } else {
            leftToRight.signal();
        }
        mutex.unlock();
    }
}
