package ch.zhaw.prog2.circularbuffer;

public class GuardedCircularBuffer<T> extends CircularBuffer<T> implements Buffer<T> {
    private int waitingTime = 100;

    public GuardedCircularBuffer(Class<T> clazz, int bufferSize) {
        super(clazz, bufferSize);
    }

    @Override
    public synchronized boolean put(T element) {
        while (this.full()) {
            try {
                wait(waitingTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notifyAll();
        return super.put(element);
    }

    @Override
    public synchronized T get() {
        while (this.empty()) {
            try {
                wait(waitingTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notifyAll();
        return super.get();
    }
}
