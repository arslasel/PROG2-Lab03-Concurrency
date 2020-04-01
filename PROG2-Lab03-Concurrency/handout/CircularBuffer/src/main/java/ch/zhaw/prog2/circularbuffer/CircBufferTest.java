package ch.zhaw.prog2.circularbuffer;

public class CircBufferTest {
    public static void main(String[] args) {
        final int capacity = 5; // Number of buffer items
        final int prodCount = 5; // Number of producer threads
        final int consCount = 2; // Number of consumer threads
        final int maxProdTime = 200; // max. production time for one item
        final int maxConsTime = 5000; // max. consumption time for one item

        try {
            Buffer<String> buffer = new CircularBuffer<>(
                String.class, capacity);

            Consumer[] consumers = new Consumer[consCount];
            for (int i = 0; i < consCount; i++) {
                consumers[i] = new Consumer("Consumer_" + i, buffer,
                    maxConsTime);
                consumers[i].start();
            }
            Producer[] producers = new Producer[prodCount];
            for (int i = 0; i < prodCount; i++) {
                producers[i] = new Producer("Producer_" + i, buffer,
                    maxProdTime);
                producers[i].start();
            }

            while (true) {
                // buffer.printBufferSlots();
                buffer.printBufferContent();
                Thread.sleep(1000);
            }
        } catch (Exception logOrIgnore) {
            System.out.println(logOrIgnore.getMessage());
        }
    }

    private static class Producer extends Thread {
        // ToDo: Add required instance variables
        private Buffer producerFillBuffer;
        private int prodTime;
        private String threadName = "";

        public Producer(String name, Buffer<String> buffer, int prodTime) {
            super(name);
            // ToDo implement Constructor
            threadName = name;
            this.producerFillBuffer = buffer;
            this.prodTime = prodTime;
        }

        @Override
        public void run() {
            // ToDo: Continuously produce counting Strings in prodTime interval
            while (true) {
                try {
                    double random = (Math.random() * prodTime);
                    sleep((int)random);
                    producerFillBuffer.put(threadName + " Time to produce -> " + random);
                    System.out.println("! produced !");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Consumer extends Thread {
        // ToDo: Add required instance variables
        private Buffer consumerReadBuffer;
        private int consTime;

        public Consumer(String name, Buffer<String> buffer, int consTime) {
            super(name);
            // ToDo implement Constructor
            this.consumerReadBuffer = buffer;
            this.consTime = consTime;
        }

        @Override
        public void run() {
            // ToDo: Continuously consume Strings in prodTime intervall
            while (true) {
                try {
                    consumerReadBuffer.get();
                    sleep((int) (Math.random() * consTime));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /*
     * Theoretische  Aufgabe 2.3
     *
     * Benützt man für macProdTIme und maxConsTime die gleiche zeit, dann schwankt die a Anzahl der Elemente nur minimal,
     * sollte ca. gleich bleiben aber ist abhängig vom randomfaktor.
     *
     * Wenn man die Bedingung maxProdTime < maxConsTime hat, inkrementiert die Anzahl Elemente im Buffer bis zum Maximum.
     * Ist die Bedingung maxProdTime > maxConsTime, so wird die Anzahl 0 sein.
     * Überschuss an Produktion und Konsum wird durch GuardedCircularBuffer gewährleistet.
     *
     * Mein Programm verwendet NotifyAll, weil in dieser Zeit die anderen Thread keine Aufgaben / Prozesse erfüllen müssen.
     * Natürlich kann man auch Notify verwenden, aber in diesem Programm, spielt es keine Rolle.
     *
     * */
}
