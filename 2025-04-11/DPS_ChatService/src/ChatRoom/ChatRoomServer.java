package ChatRoom;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatRoomServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9876);

        MessagePool pool = new MessagePool();

        MessageBroadcaster broadcaster = new MessageBroadcaster(pool);
        broadcaster.start();

        while (true) {
            System.out.println("Waiting for connection...");
            Socket socket = serverSocket.accept();

            System.out.println(socket.getRemoteSocketAddress() + " connected!");
            ServerWriterThread writer = new ServerWriterThread(socket, pool, broadcaster);
            writer.start();
        }
    }
}
