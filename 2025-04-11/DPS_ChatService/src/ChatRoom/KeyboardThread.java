package ChatRoom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KeyboardThread extends Thread {

    public KeyboardThread(MessagePool pool) {
        this.msgPool = pool;
        input = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        String message = null;
        while (true) {
            try {
                message = this.input.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (message != null) {
                this.msgPool.writeMessage(message);
            }
        }
    }

    private final MessagePool msgPool;
    private final BufferedReader input;
}
