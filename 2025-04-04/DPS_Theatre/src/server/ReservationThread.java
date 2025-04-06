package server;

import util.Reservations;

import java.io.*;
import java.net.Socket;

public class ReservationThread extends Thread {
    public ReservationThread(Reservations r, Socket client) throws IOException {
        this.reservations = r;
        this.clientSocket = client;
        this.serverInput = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.serverOutput = new DataOutputStream(client.getOutputStream());
    }

    @Override
    public void run() {
        int ticketAcquired = 0;
        int requestedTickets = 0;

        try {
            requestedTickets = Integer.parseInt(this.serverInput.readLine());
        } catch (Exception exc) {
            System.err.println(exc.toString());
        }

        try {
            if (this.reservations.availableTickets() >= requestedTickets) {
                ticketAcquired = requestedTickets;
                synchronized (reservations) {
                    reservations.ticketsNumber -= requestedTickets;
                }
            }
        } catch (Exception exc) {
            System.err.println(exc.toString());
        }


        try {
            serverOutput.writeBytes(ticketAcquired + "\n");
        } catch (IOException exc) {
            System.err.println(exc.toString());
        }

        try {
            clientSocket.close();
        } catch (IOException exc) {
            System.err.println(exc.toString());
        }
    }

    private final Reservations reservations;
    private final BufferedReader serverInput;
    private final DataOutputStream serverOutput;
    private final Socket clientSocket;
}
