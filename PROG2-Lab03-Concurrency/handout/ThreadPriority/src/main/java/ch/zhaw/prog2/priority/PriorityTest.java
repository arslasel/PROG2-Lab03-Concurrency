package ch.zhaw.prog2.priority;


public class PriorityTest {

    public static void main(String[] args) throws InterruptedException {
        int numthreads = 30;
        SimpleThread[] threads = new SimpleThread[numthreads];
        int[] priorities = new int[] { 1, 1, 1 }; // { 1, 1, 1 }
        // start threads
        for (int i = 0; i < numthreads; i++) {
            threads[i] = new SimpleThread(""+i);
            threads[i].setPriority(priorities[i % priorities.length]);
            threads[i].start();
        }
        // wait for some time
        Thread.sleep(2000);
        // terminate all threads
        for (SimpleThread thread : threads) {
            // ToDo: Replace the deprecated stop-Method with a proper way to stop the thread.
            thread.terminate(); // new function with a volatile boolean.
            thread.stop(); // old function, up tp now has to be ignored.
        }
        // print results
        for (SimpleThread thread : threads) {
            System.out.println(thread.getName() +
                " : " + thread.getPriority() +
                " = " + thread.count );
        }
        System.out.println("main exits " + Thread.currentThread().toString());
    }

    private static class SimpleThread extends Thread {
        private volatile boolean running = true;
        long count = 0;

        public SimpleThread(String str) { super(str); }

        @Override
        public void run() {
            while (running) {
                count++;
                Thread.yield();
            }
        }

        public void terminate(){
            running = false;
            this.interrupt();
        }
    }

}
