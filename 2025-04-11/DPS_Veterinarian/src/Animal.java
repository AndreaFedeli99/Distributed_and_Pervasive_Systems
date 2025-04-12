import java.util.Random;

public class Animal extends Thread {

    public Animal (String type, int id, Room room) {
        this.room = room;
        this.id = id;
        this.type = type;
    }

    @Override
    public void run() {
        int seconds = new Random().nextInt(10);
        try {
            room.enterRoom(this);
            Thread.sleep((long)seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        room.exitRoom(this);
    }

    @Override
    public String toString() {
        return this.type + " " + id;
    }

    private final Room room;
    private final int id;
    public String type;
}
