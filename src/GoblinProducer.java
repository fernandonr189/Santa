public class GoblinProducer extends Thread{

    private Buffer buffer;

    public GoblinProducer(Buffer _buffer) {
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
           Goblin newGoblin = new Goblin(names[(int) (Math.random() * 4)]);
           buffer.produceGoblin(newGoblin);
        }
    }
}
