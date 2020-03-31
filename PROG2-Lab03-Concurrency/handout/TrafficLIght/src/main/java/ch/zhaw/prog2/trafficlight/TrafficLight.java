package ch.zhaw.prog2.trafficlight;

class TrafficLight {
    private boolean red;

    public TrafficLight() {
        red = true;
    }

    public synchronized void passby() {
        // ToDo: wait as long the light is red
        if (red) {
            try {
                wait(); //Thread is waiting till another Thread calls notify.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            notify(); // Thread calls notify, waiting thread is able to move again.
        }
    }

    public synchronized void switchToRed() {
        // ToDo: set light to red
        red = true;
    }

    public synchronized void switchToGreen() {
        // Todo: set light to green
        // waiting cars can now pass by
        red = false;
    }
}
