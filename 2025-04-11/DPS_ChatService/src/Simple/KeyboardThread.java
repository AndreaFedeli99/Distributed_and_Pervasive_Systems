package Simple;

import java.io.*;

public class KeyboardThread extends Thread {
    public KeyboardThread(MessagePool pool) throws Exception {
        this.input = new BufferedReader(new InputStreamReader(System.in));
        this.msgPool = pool;
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.msgPool.sendMessage(this.input.readLine());
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
    }

    private BufferedReader input;
    private MessagePool msgPool;
}
