public class Room {
    public Room() {
        dogsNumber = 0;
        catsNumber = 0;
    }

    public synchronized void enterRoom(Animal a) throws InterruptedException {
        while (checkCondition(a)) {
            System.out.printf("%s is waiting (%d cats - %d dogs)\n", a.toString(), catsNumber, dogsNumber);
            wait();
        }

        if (a.type.equals("cat"))
            catsNumber++;
        else
            dogsNumber++;
        System.out.printf("%s enters the room (%d cats - %d dogs)\n", a.toString(), catsNumber, dogsNumber);
    }

    public synchronized void exitRoom(Animal a) {
        if (a.type.equals("cat"))
            catsNumber--;
        else
            dogsNumber--;
        System.out.printf("%s exits the room (%d cats - %d dogs)\n", a.toString(), catsNumber, dogsNumber);
        notifyAll();
    }

    private synchronized boolean checkCondition(Animal a) {
        if (a.type.equals("cat"))
            return (dogsNumber > 0 || catsNumber > 0);
        else
            return (catsNumber > 0 || dogsNumber >= 3);
    }

    private int dogsNumber;
    private int catsNumber;
}
