package Simple;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        MessagePool pool = new MessagePool();

        KeyboardThread kt = new KeyboardThread(pool);
        kt.start();

        Socket clientSocket = new Socket("localhost", 9876);

        ToOtherUserThread otherUser = new ToOtherUserThread(clientSocket, pool);
        otherUser.start();

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        while (true) {
            System.out.println("FROM SERVER: " + inFromUser.readLine());
        }
    }
}
