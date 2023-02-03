package academy.mindswap.gameobjects.snake;

import academy.mindswap.field.Field;
import academy.mindswap.field.Position;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Snake {

    private int x;
    private int y;

    LinkedList<Position> fullSnake = new LinkedList<>();
    private final static int SNAKE_INITIAL_SIZE = 6;
    private Direction direction;
    private boolean alive = true;
    public void increaseSize() {

    }
    public Snake(){
       //direction = Direction.UP;
       getFullSnake();
    }

    public void move(Direction direction) {
        switch (direction) {
            case UP -> {getHead().setRow(-1); Field.clearTail(this);}
            case DOWN -> {getHead().setRow(1); Field.clearTail(this);}
            case RIGHT -> {getHead().setCol(1); Field.clearTail(this);}
            case LEFT -> {getHead().setCol(-1); Field.clearTail(this);}
        }
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

    public void setHead() {

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

