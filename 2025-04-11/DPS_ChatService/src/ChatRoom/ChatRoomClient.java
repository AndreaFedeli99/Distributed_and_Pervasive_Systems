package ChatRoom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ChatRoomClient {
    public static void main(String[] args) throws IOException {
        MessagePool pool = new MessagePool();
        KeyboardThread keyboardIn = new KeyboardThread(pool);

        Socket socket = new Socket("localhost", 9876);
        ToServerThread out = new ToServerThread(socket, pool);
        System.out.println(":- Local socket info: " + socket.getLocalSocketAddress() + "\n");

        keyboardIn.start();
        out.start();

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String message = null;
        while (true) {
            message = input.readLine();
            String[] messageContent = message.split("::");
            if (!messageContent[0].equals(socket.getLocalSocketAddress().toString()))
                System.out.println("FROM CHATROOM: " + messageContent[1]);
        }
    }
}
