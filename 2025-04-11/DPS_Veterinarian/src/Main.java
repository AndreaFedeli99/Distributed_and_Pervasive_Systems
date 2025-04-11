public class Main {
    public static void main(String[] args) {
        Room r = new Room();

        Animal cat1 = new Animal(1, r);
        Animal cat2 = new Animal(1, r);

        Animal dog1 = new Animal(2, r);
        Animal dog2 = new Animal(2, r);
        Animal dog3 = new Animal(2, r);
        Animal dog4 = new Animal(2, r);

        cat1.start();
        cat2.start();

        dog1.start();
        dog2.start();
        dog3.start();
        dog4.start();
    }
}
