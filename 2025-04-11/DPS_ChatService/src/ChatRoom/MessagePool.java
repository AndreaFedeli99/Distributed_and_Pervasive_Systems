package ChatRoom;

import java.util.ArrayList;

public class MessagePool {
    public MessagePool() {
        this.messages = new ArrayList<>();
    }

    public synchronized void writeMessage(String message) {
        messages.add(message);
        notify();
    }

    public synchronized String readMessage() throws InterruptedException {
        while (messages.isEmpty()) {
            wait();
        }

        return this.messages.remove(0);
    }

    private final ArrayList<String> messages;
}
