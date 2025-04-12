import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Room room = new Room();

        Random r = new Random();
        final int animalNumber = 11;
        int catIndex = 0;
        int dogIndex = 0;
        ArrayList<Animal> animals = new ArrayList<Animal>();

        for (int i = 0; i < animalNumber; i++) {
            if (r.nextBoolean())
                animals.add(new Animal("cat", catIndex++, room));
            else
                animals.add(new Animal("dog", dogIndex++, room));
        }

        for (Animal a : animals)
            a.start();
    }
}
