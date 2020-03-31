package ch.zhaw.prog2.circularbuffer;

public class CircBufferTest {
    public static void main(String[] args) {
        final int capacity = 15; // Number of buffer items
        final int prodCount = 3; // Number of producer threads
        final int consCount = 3; // Number of consumer threads
        final int maxProdTime = 5000; // max. production time for one item
        final int maxConsTime = 3000; // max. consumption time for one item

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

        public Producer(String name, Buffer<String> buffer, int prodTime) {
            super(name);
            // ToDo implement Constructor
            this.producerFillBuffer = buffer;
            this.prodTime = prodTime;
        }

        @Override
        public void run() {
            // ToDo: Continuously produce counting Strings in prodTime interval
            try {
                producerFillBuffer.put("ich heisse Selim");
                sleep((int) (Math.random() * prodTime));
            } catch (InterruptedException e){
                e.printStackTrace();
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
            try{
                consumerReadBuffer.get();
                sleep((int)(Math.random() * consTime));
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

}
