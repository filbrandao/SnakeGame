package academy.mindswap.gameobjects.snake;

import academy.mindswap.field.Position;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Snake {

    private int x;
    private int y;

    LinkedList<Position> fullSnake = new LinkedList<>();
    private final static int SNAKE_INITIAL_SIZE = 3;
    private Direction direction;
    private boolean alive = true;
    public void increaseSize() {

    }

    public Snake(){
        this.y = 12;
        this.x = 50;
        getFullSnake();

    }

    public void move(Direction direction) {

    }

    public void move(){
        move(direction);
    }

    public void die() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public Position getHead() {

        return fullSnake.getFirst();
    }

    public Position getTail() {

        return fullSnake.getLast();
    }

    public LinkedList<Position> getFullSnake(){
        for(int i = 0; i < SNAKE_INITIAL_SIZE; i++){
           fullSnake.add( i, new Position(y, x+i));

        }

         /*Position initialPosition = new Position();
        int [] position = {initialPosition.getCol(), initialPosition.getRow()};*/

        return fullSnake;
    }

    public int getSnakeSize() {
        return 0;
    }
}

