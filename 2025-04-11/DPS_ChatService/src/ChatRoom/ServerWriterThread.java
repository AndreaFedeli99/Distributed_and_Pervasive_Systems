package ChatRoom;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerWriterThread extends Thread {
    public ServerWriterThread(Socket socket, MessagePool pool, MessageBroadcaster broadcaster) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.pool = pool;
        this.broadcaster = broadcaster;
    }

    @Override
    public void run() {
        String message = null;
        try {
            this.broadcaster.addClient(new DataOutputStream(socket.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            try {
                message = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (message != null) {
                this.pool.writeMessage(message);
            }
        }
    }

    private final Socket socket;
    private final BufferedReader in;
    private final MessagePool pool;
    private final MessageBroadcaster broadcaster;
}
