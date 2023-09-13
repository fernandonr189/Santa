public class DeerProducer extends Thread{
    private Buffer buffer;

    public DeerProducer(Buffer _buffer) {
        this.buffer = _buffer;
    }

    private final String[] names = new String[] {
            "Jose",
            "Pedro",
            "Juan",
            "Fernando"
    };

    public void run() {
        while(true) {
            try {
                sleep((long) (Math.random() * (2000) + 1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Deer newDeer = new Deer(names[(int) (Math.random() * 4)]);
            buffer.produceDeer(newDeer);
        }
    }
}
