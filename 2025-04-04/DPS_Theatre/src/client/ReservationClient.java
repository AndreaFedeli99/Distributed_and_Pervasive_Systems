package client;

import java.io.*;
import java.net.Socket;

public class ReservationClient {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream outFromServer = null;
        BufferedReader inFromServer = null;
        Socket clientSocket = null;
        String errorMessage = "";
        String serverAddress = "";
        int serverPort = -1;
        String requestedTickets;
        String acquiredTickets;

        while (serverAddress.isEmpty()) {
            System.out.println("Please enter the server address...");
            serverAddress = in.readLine();

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

            clientSocket = new Socket(serverAddress, serverPort);
        }

        outFromServer = new DataOutputStream(clientSocket.getOutputStream());
        inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        System.out.println("How many tickets would you like to reserve?");
        requestedTickets = in.readLine();

        outFromServer.writeBytes(requestedTickets + "\n");
        acquiredTickets = inFromServer.readLine();

        if (acquiredTickets.equals(requestedTickets)) {
            System.out.println("Reservation has been acquired!");
        }
        else{
            System.out.println("Tickets unavailable!");
        }
    }
}
