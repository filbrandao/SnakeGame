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
    private Direction idleDirection;
    private boolean alive = true;
    public void increaseSize() {

    }
    public Snake(){
        idleDirection = Direction.LEFT;
        for(int i = 0; i < SNAKE_INITIAL_SIZE; i++){
            fullSnake.add( i, new Position(y, x+i));
        }
    }

    public void move(Direction direction) {
        idleDirection = direction;
       /* switch (direction) {
            case UP -> {getHead().setRow(-1); Field.clearTail(this); System.out.println(getHead().getRow() + " " +  getHead().getCol());}
            case DOWN -> {getHead().setRow(1); Field.clearTail(this); System.out.println(getHead().getRow() + " " + getHead().getCol());}
            case RIGHT -> {getHead().setCol(1); Field.clearTail(this); System.out.println(getHead().getRow() + " " + getHead().getCol());}
            case LEFT -> {getHead().setCol(-1); Field.clearTail(this); System.out.println(getHead().getRow() + " " + getHead().getCol());}
        }*/
        switch (direction) {
            case UP -> {fullSnake.get(0).setRow(-1);}
            case DOWN -> {getFullSnake().get(0).setRow(1);}
            case RIGHT -> {getFullSnake().get(0).setCol(1);}
            case LEFT -> {getFullSnake().get(0).setCol(-1);}

        }
    }

    public void move(){
        move(idleDirection);
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
    public void setTails() {}

    public LinkedList<Position> getFullSnake(){
        return fullSnake;
    }

    public int getSnakeSize() {
        return 0;
    }
}

