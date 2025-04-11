public class Room {
    public Room() {
        dogsNumber = 0;
        catsNumber = 0;
    }

    public synchronized void enterRoom(Animal a) throws InterruptedException {
        while ((a.type == 1 && (dogsNumber > 0 || catsNumber > 0)) || (a.type == 2 && (catsNumber > 0)) || (a.type == 2 && dogsNumber >= 3)) {
            if (a.type == 1) {
                System.out.println("A CAT IS WAITING...");
            }
            else {
                System.out.println("A DOG IS WAITING...");
            }
            System.out.println("Number of cats: " + catsNumber);
            System.out.println("Number of dogs: " + dogsNumber);
            wait();
        }

        if (a.type == 1) {
            catsNumber++;
            System.out.println("A CAT ENTERS THE ROOM...");
        } else {
            dogsNumber++;
            System.out.println("A DOG ENTERS THE ROOM...");
        }
        System.out.println("Number of cats: " + catsNumber);
        System.out.println("Number of dogs: " + dogsNumber);
    }

    public synchronized void exitRoom(Animal a) {
        if (a.type == 1) {
            catsNumber--;
            System.out.println("A CAT EXITS THE ROOM...");
        }
        if (a.type == 2) {
            System.out.println("A DOG EXITS THE ROOM...");
            dogsNumber--;
        }

        System.out.println("Number of cats: " + catsNumber);
        System.out.println("Number of dogs: " + dogsNumber);
        notifyAll();
    }

    private int dogsNumber;
    private int catsNumber;
}
