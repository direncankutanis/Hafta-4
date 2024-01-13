import java.util.Random;

public class Snake extends Obstacle {
    public Snake() {
        super("Yılan", 4, randomDamage(), 12, 0);

    }

    public static int randomDamage() {
        Random r = new Random();
        int randomDamage = r.nextInt(4) +3;
        return randomDamage;
    }


}