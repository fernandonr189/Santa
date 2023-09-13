public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(18, 9);
        GoblinProducer goblinProducer = new GoblinProducer(buffer);
        DeerProducer deerProducer = new DeerProducer(buffer);
        goblinProducer.start();
        deerProducer.start();
    }
}