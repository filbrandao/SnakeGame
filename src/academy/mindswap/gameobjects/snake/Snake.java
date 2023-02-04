package academy.mindswap.gameobjects.snake;

import academy.mindswap.field.Field;
import academy.mindswap.field.Position;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class Snake{

    private int x = 12;
    private int y = 50;
    private LinkedList<Position> fullSnake = new LinkedList<>();
    private Iterator iterator;
    private final static int SNAKE_INITIAL_SIZE = 6;
    private Direction idleDirection;
    private boolean alive = true;
    public Snake(){
        this.idleDirection = Direction.LEFT;
        for(int i = 0; i < SNAKE_INITIAL_SIZE; i++){
            fullSnake.add(i, new Position(y, x+i));
        }
        this.iterator = fullSnake.iterator();
    }
    public void increaseSize() {
        switch (idleDirection){
            case UP -> getFullSnake().add(getSnakeSize(), new Position(getFullSnake().getLast().getRow(), getFullSnake().getLast().getCol()-1));
            case DOWN -> getFullSnake().add(getSnakeSize(), new Position(getFullSnake().getLast().getRow(), getFullSnake().getLast().getCol()+1));
            case LEFT -> getFullSnake().add(getSnakeSize(), new Position(getFullSnake().getLast().getRow()+1, getFullSnake().getLast().getCol()));
            case RIGHT -> getFullSnake().add(getSnakeSize(), new Position(getFullSnake().getLast().getRow()-1, getFullSnake().getLast().getCol()));
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
        this.alive = false;
    }

    public boolean isAlive() {
        return this.alive;
    }

    public Position getHead() {
        return this.getFullSnake().getFirst();
    }

    public void setHead() {

    }

    public Position getTail() {
        return fullSnake.getLast();
    }
    public void setTail() {}

    public LinkedList<Position> getFullSnake(){
        return fullSnake;
    }

    public int getSnakeSize() {
        return getFullSnake().size();
    }

    public Iterator getIterator() {
        return iterator;
    }

    public void setIterator(Iterator iterator) {
        this.iterator = iterator;
    }
}

