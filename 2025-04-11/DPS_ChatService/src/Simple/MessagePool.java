package Simple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MessagePool {
    public MessagePool() {
        this.messages = new ArrayList<>();
    }

    public synchronized void sendMessage(String msg) throws Exception {
        this.messages.add(msg);
        notifyAll();
    }

    public synchronized String readMessage() throws Exception {
        while (this.messages.isEmpty())
            wait();

        return this.messages.remove(0);
    }

    private ArrayList<String> messages;
    private Random rand = new Random();
}
