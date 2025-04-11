import java.util.Random;

public class Animal extends Thread {

    public Animal (int type, Room room) {
        this.room = room;
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

    private Room room;
    public int type;
}
