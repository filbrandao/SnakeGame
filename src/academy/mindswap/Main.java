package academy.mindswap;

import javax.sound.sampled.LineUnavailableException;

public class Main {

    public static void main(String[] args) {
       Game game = new Game(80, 30, 100);
        try {

            game.start();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
