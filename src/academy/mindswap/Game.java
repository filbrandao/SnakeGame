package academy.mindswap;

import academy.mindswap.field.Field;
import academy.mindswap.field.Sound;
import academy.mindswap.gameobjects.fruit.Fruit;
import academy.mindswap.gameobjects.snake.Direction;
import academy.mindswap.gameobjects.snake.Snake;
import com.googlecode.lanterna.input.Key;
import academy.mindswap.field.Position;
import com.googlecode.lanterna.terminal.Terminal;



public class Game {

    private final Snake snake;
    private Fruit fruit;
    private final int delay;
    Sound sound = new Sound();


    public Game(int cols, int rows, int delay) {
        Field.init(cols, rows);
        snake = new Snake();
        this.delay = delay;
    }

    public void start() throws InterruptedException {

        generateFruit();
        //sound.getSoundLoop();
        sound.init();
        sound.playSound(SoundFiles.GAME_LOOP);

        while (snake.isAlive()) {
            Thread.sleep(delay);
            Field.clearTail(snake);
            moveSnake();
            checkCollisions();
            Field.drawSnake(snake);
            //if (!sound.getSoundLoopVar().isRunning()) sound.getSoundLoop();

        }
        try {
            int waitTime = 1;
            Thread.sleep(waitTime * 700);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
       // sound.getSoundLoopVar().stop();
        //Field.gameOverScreenAlpha();
        Field.gameOverScreenBeta();

    }

    private void generateFruit() {
        //RANDOM DE 1 ATÉ À WIDTH e HEIGHT DA FIELD
        int randomX = (int) (Math.random() * ((Field.getHeight() -2) - 2) + 1) + 2;
        int randomY = (int) (Math.random() * ((Field.getWidth() -2) - 2) + 1) + 2;


        //TEM DE SER UMA POSIÇÃO DIFERENTE DE TODO O CORPO DA SNAKE
       for (int i = 0; i < snake.getSnakeSize(); i++) {
            if (randomX == snake.getFullSnake().get(i).getCol() && randomY == snake.getFullSnake().get(i).getRow()) {
                generateFruit();
                return;
            }
        }
       switch ((int) (Math.random() * (10 - 1 + 1) + 1)) {
           case 1, 2, 3, 4, 5:
               fruit = new Fruit(new Position(randomX, randomY));
               fruit.setFruitPoints(1);
               Field.drawFruit(fruit, Terminal.Color.YELLOW);
               break;
           case 6, 7:
               fruit = new Fruit(new Position(randomX, randomY));
               fruit.setFruitPoints(2);
               Field.drawFruit(fruit, Terminal.Color.BLUE);
               break;
           case 8, 9:
               fruit = new Fruit(new Position(randomX, randomY));
               fruit.setFruitPoints(3);
               Field.drawFruit(fruit, Terminal.Color.RED);
               break;
           case 10:
               fruit = new Fruit(new Position(randomX, randomY));
               fruit.setFruitPoints(5);
               Field.drawFruit(fruit, Terminal.Color.CYAN);
               break;
        }

    }

    private void moveSnake() {

        Key k = Field.readInput();

        if (k != null) {
            switch (k.getKind()) {
                case ArrowUp:
                    snake.move(Direction.UP);
                    return;

                case ArrowDown:
                    snake.move(Direction.DOWN);
                    return;

                case ArrowLeft:
                    snake.move(Direction.LEFT);
                    return;

                case ArrowRight:
                    snake.move(Direction.RIGHT);
                    return;
            }
        }
        snake.move();
    }

    private void checkCollisions() {

        //VERIFICA SE A HEAD DA SNAKE BATEU EM ALGUMA PAREDE
        if (snake.getHead().getCol() == 0) snake.die();
        if (snake.getHead().getRow() == 0) snake.die();
        if (snake.getHead().getRow() == Field.getHeight()) snake.die();
        if (snake.getHead().getCol() == Field.getWidth()) snake.die();

        //VERIFICA SE A HEAD DA SNAKE TEM A MESMA POSIÇÃO DA FRUTA
        if(snake.getHead().equals(fruit.getPosition())){
            snake.increaseSize();
           // sound.getSoundClip("resources/soundEffects/mixkit-retro-game-notification-212.wav");
            sound.init();
            sound.playSound(SoundFiles.GAME_LOOP);
            Field.setFruitCatched(fruit.getFruitPoints());
            generateFruit();
        }

        //VERIFICA SE A SNAKE BATEU NELA PRÓPRIA
        for (int i = 3; i < snake.getSnakeSize(); i++) {
            if (snake.getHead().equals(snake.getFullSnake().get(i))) {
                snake.die();
                break;
            }
        }
        //System.out.println(snake.getHead().getCol() + " " + snake.getHead().getRow() + " " + fruit.getPosition().getCol() + " " + fruit.getPosition().getRow());
    }
}
