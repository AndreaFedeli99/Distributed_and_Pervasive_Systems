package ChatRoom;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class MessageBroadcaster extends Thread {
    public MessageBroadcaster(MessagePool pool) {
        this.clients = new ArrayList<>();
        this.pool = pool;
    }

    public synchronized void addClient(DataOutputStream client) {
        this.clients.add(client);
    }

    public synchronized void removeClient(DataOutputStream client) {
        this.clients.remove(client);
    }

    public void run() {
        String message = null;
        while(true) {
            try {
                message = this.pool.readMessage();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (message != null) {
                for (DataOutputStream client : clients) {
                    try {
                        client.writeBytes(message + "\n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    private final ArrayList<DataOutputStream> clients;
    private final MessagePool pool;
}
