package academy.mindswap;

import academy.mindswap.field.Field;
import academy.mindswap.gameobjects.fruit.Fruit;
import academy.mindswap.gameobjects.snake.Direction;
import academy.mindswap.gameobjects.snake.Snake;
import com.googlecode.lanterna.input.Key;
import academy.mindswap.field.Position;

import java.util.Iterator;
import java.util.Random;


public class Game {

    private Snake snake;
    private Fruit fruit;
    private int delay;

    public Game(int cols, int rows, int delay) {
        Field.init(cols, rows);
        snake = new Snake();
        this.delay = delay;
    }

    public void start() throws InterruptedException {

         generateFruit();

        while (snake.isAlive()) {
            Thread.sleep(delay);
            Field.clearTail(snake);
            moveSnake();
            checkCollisions();
            Field.drawSnake(snake);
        }
        gameOver();
    }

    private void gameOver() {
        // TODO: 04/02/23  
    }

    private void generateFruit() {
        //RANDOM DE 1 ATÉ À WIDTH e HEIGHT DA FIELD
        Random random = new Random();
        int randomX = (int) (Math.random() * ((Field.getHeight() -2) - 2) + 1) + 2;
        int randomY = (int) (Math.random() * ((Field.getWidth() -2) - 2) + 1) + 2;
        //int randomX = 23;
        //int randomY = 98;

        //TEM DE SER UMA POSIÇÃO DIFERENTE DE TODO O CORPO DA SNAKE
       for (int i = 0; i < snake.getSnakeSize(); i++) {
            if (randomX == snake.getFullSnake().get(i).getCol() && randomY == snake.getFullSnake().get(i).getRow()) {
                generateFruit();
                return;
            }
        }
        fruit = new Fruit(new Position(randomX, randomY));
        Field.drawFruit(fruit);
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

    private boolean checkCollisions() {

        //VERIFICA SE A HEAD DA SNAKE BATEU EM ALGUMA PAREDE
       if (snake.getHead().getCol() == 0) snake.die();
       if (snake.getHead().getRow() == 0) snake.die();
       if (snake.getHead().getRow() == Field.getHeight()) snake.die();
       if (snake.getHead().getCol() == Field.getWidth()) snake.die();

        //VERIFICA SE A HEAD DA SNAKE TEM A MESMA POSIÇÃO DA FRUTA
        if(snake.getHead().equals(fruit.getPosition())){
            snake.increaseSize();
            generateFruit();
        }

        /*for (Object position: snake.getFullSnake()){
            if(fruit.getPosition().equals(position)){
                snake.die();
                return;
            }
        }*/

        //VERIFICA SE A SNAKE BATEU NELA PRÓPRIA
        for (int i = 3; i < snake.getSnakeSize(); i++) {
            if (snake.getHead().equals(snake.getFullSnake().get(i))) {
                snake.die();
                break;
            }
        }

        //System.out.println(Field.getWidth());
        //System.out.println(Field.getHeight());
        //System.out.println(snake.getHead().getCol() + " " + snake.getHead().getRow() + " " + fruit.getPosition().getCol() + " " + fruit.getPosition().getRow());
        return snake.isAlive();
    }
}
