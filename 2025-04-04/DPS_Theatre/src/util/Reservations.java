package util;

import java.util.ArrayList;

public class Reservations {
    public Reservations(int availableTickets) {
        this.ticketsNumber = availableTickets;
    }

    public synchronized int availableTickets() throws InterruptedException {
        Thread.sleep(10000);
        return this.ticketsNumber > 0 ? this.ticketsNumber : 0;
    }

    public int ticketsNumber;
}
