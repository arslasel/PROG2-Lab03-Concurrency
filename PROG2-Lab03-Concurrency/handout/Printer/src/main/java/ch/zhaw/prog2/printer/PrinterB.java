package ch.zhaw.prog2.printer;

import com.sun.jdi.ThreadReference;

public class PrinterB {

    // test program
    public static void main(String[] arg) {
        PrinterRunnable a = new PrinterRunnable('.', 0);
        PrinterRunnable b = new PrinterRunnable('*', 0);
        Thread t1 = new Thread(a, "PrinterA");
        Thread t2 = new Thread(b, "PrinterB");
        t1.start();
        t2.start();
    }

    static class PrinterRunnable implements Runnable{

        char symbol;
        int sleepTime;
        private Thread thread = Thread.currentThread();

        public PrinterRunnable(char symbol, int sleepTime) {
            this.symbol = symbol;
            this.sleepTime = sleepTime;
        }

        @Override
        public void run() {
            if (Thread.currentThread().equals(thread)) return;

            System.out.println(Thread.currentThread().getName() + " run started...");
            for (int i = 1; i < 100; i++) {
                System.out.print(symbol);
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println('\n' + Thread.currentThread().getName() + " run ended.");
        }

    }
}
