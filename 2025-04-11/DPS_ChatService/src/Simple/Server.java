package Simple;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        MessagePool pool = new MessagePool();

        KeyboardThread kt = new KeyboardThread(pool);
        kt.start();

        ServerSocket serverSocket = new ServerSocket(9876);
        Socket clientSocket = serverSocket.accept();

        ToOtherUserThread otherUser = new ToOtherUserThread(clientSocket, pool);
        otherUser.start();

        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        while (true) {
            System.out.println("FROM CLIENT: " + inFromClient.readLine());
        }
    }
}