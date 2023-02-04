package academy.mindswap;

import academy.mindswap.field.Field;
import academy.mindswap.gameobjects.fruit.Fruit;
import academy.mindswap.gameobjects.snake.Direction;
import academy.mindswap.gameobjects.snake.Snake;
import com.googlecode.lanterna.input.Key;


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

        // generateFruit(); // uncomment when it's time to introduce fruits

        while (true) {
            Thread.sleep(delay);
            Field.clearTail(snake);
            //Field.clearHead(snake);
            moveSnake();
            checkCollisions();
            Field.drawSnake(snake);
        }
    }

    private void generateFruit() {
        //RANDOM DE 1 ATÉ À WIDTH e HEIGHT DA FIELD
        int randomX = (int) (Math.random() * Field.getWidth()) + 1;
        int randomY = (int) (Math.random() * Field.getHeight()) + 1;

        //TEM DE SER UMA POSIÇÃO DIFERENTE DE TODO O CORPO DA SNAKE
        for (int i = 0; i < snake.getSnakeSize(); i++) {
            if (randomX == snake.getFullSnake().get(i).getCol() && randomY == snake.getFullSnake().get(i).getRow()) {
                generateFruit();
                return;
            }
        }
        fruit = new Fruit(randomX, randomY);
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
        if(snake.getHead().getCol() == 0 || snake.getHead().getRow() == 0 || snake.getHead().getRow() == Field.getWidth() || snake.getHead().getCol() == Field.getHeight()){
            snake.die();
        }

        //VERIFICA SE A HEAD DA SNAKE TEM A MESMA POSIÇÃO DA FRUTA
        if(snake.getHead() == fruit.getPosition()){
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
        int index = 0;
        while(snake.getIterator().hasNext()){
            if (index > 3 && snake.getHead().equals(snake.getIterator().next())) {
                snake.die();
                break;
            }
            index++;
        }
    }
}
