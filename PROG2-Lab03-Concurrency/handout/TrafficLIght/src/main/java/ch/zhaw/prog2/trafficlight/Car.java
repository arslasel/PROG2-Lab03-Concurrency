package ch.zhaw.prog2.trafficlight;

class Car extends Thread {
    private TrafficLight[] trafficLights;
    private int pos;

    public Car(String name, TrafficLight[] trafficLights) {
        super(name);
        this.trafficLights = trafficLights;
        pos = 0; // start at first light
        start();
    }

    public synchronized int position() {
        return pos;
    }

    private void gotoNextLight() {
        // ToDo: Helper method to move car to next light
        pos++; // Position increment to next position.
    }

    @Override
    public void run() {
        while (true) {
            // ToDo: drive endlessly through all lights
            try {
                sleep((int) (Math.random() * 500)); // Thread sleeps.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
