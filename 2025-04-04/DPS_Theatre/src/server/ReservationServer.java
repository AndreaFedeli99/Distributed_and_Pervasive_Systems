package server;

import util.Reservations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ReservationServer {
    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ServerSocket welcomeSocket = null;
        int availableTickets = -1;
        int serverPort = -1;
        String errorMessage = "";

        while (serverPort < 0) {
            try {
                System.out.println("Please enter the server port...");
                serverPort = Integer.parseInt(in.readLine());
            } catch (NumberFormatException exc) {
                errorMessage = "Please insert a valid number!";
            }

            if (errorMessage.isEmpty() && serverPort < 0) {
                errorMessage = "Please insert a valid number!";
            }

            if (!errorMessage.isEmpty()) {
                System.out.println(errorMessage);
            }
        }

        while (availableTickets < 0) {
            try {
                System.out.println("How many tickets are available?");
                availableTickets = Integer.parseInt(in.readLine());
            } catch (NumberFormatException exc) {
                errorMessage = "Please insert a valid number!";
            }

            if (errorMessage.isEmpty() && availableTickets < 0) {
                errorMessage = "Please insert a valid number!";
            }

            if (!errorMessage.isEmpty()) {
                System.out.println(errorMessage);
            }
        }

        Reservations r = new Reservations(availableTickets);
        welcomeSocket = new ServerSocket(serverPort);

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();

            System.out.println("Connection established " + connectionSocket.getInetAddress() + ":" + connectionSocket.getPort());
            ReservationThread t = new ReservationThread(r, connectionSocket);

            t.start();
        }
    }
}
