package ChatRoom;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ToServerThread extends  Thread {
    public ToServerThread(Socket socket, MessagePool pool) throws IOException {
        this.socket = socket;
        this.pool = pool;
        this.out = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        String message = null;
        while (true) {
            try {
                message = this.pool.readMessage();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (message != null) {
                try {
                    this.out.writeBytes(socket.getLocalSocketAddress() + "::" + message + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private Socket socket;
    private final MessagePool pool;
    private final DataOutputStream out;
}
