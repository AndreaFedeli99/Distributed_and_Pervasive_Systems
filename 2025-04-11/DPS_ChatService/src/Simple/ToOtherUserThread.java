package Simple;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ToOtherUserThread extends Thread {
    public ToOtherUserThread(Socket socket, MessagePool pool) throws IOException {
        this.outToServer = new DataOutputStream(socket.getOutputStream());
        this.pool = pool;
    }

    @Override
    public void run() {
        String msg;
        while (true) {
            try {
                msg = pool.readMessage();
                outToServer.writeBytes(msg + "\n");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private MessagePool pool;
    private DataOutputStream outToServer;
}
