package academy.mindswap.gameobjects.snake;

import academy.mindswap.field.Position;

import java.util.Iterator;
import java.util.LinkedList;

public class Snake {
    private final static int SNAKE_INITIAL_SIZE = 6;
    private  Iterator iterator;
    private Direction idleDirection;
    private boolean alive = true;
    private LinkedList<Position> fullSnake = new LinkedList<>();

    public Snake(){

        idleDirection = Direction.LEFT;
        for(int i = 0; i < SNAKE_INITIAL_SIZE; i++){
            int y = 12;
            int x = 50;
            fullSnake.add( i, new Position(y, x +i));
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
        System.out.println(getSnakeSize());
    }

    public void move(Direction direction) {
    boolean notGoingBack = false;
        switch (direction) {
            case UP:
                if (idleDirection == Direction.DOWN){notGoingBack = true; break;}
                getFullSnake().addFirst(new Position(getHead().getRow(), getHead().getCol()));
                getFullSnake().pollLast();
                getFullSnake().get(0).setRow(-1);
                break;
            case DOWN:
                if (idleDirection == Direction.UP){notGoingBack = true; break;}
                getFullSnake().addFirst(new Position(getHead().getRow(), getHead().getCol()));
                getFullSnake().pollLast();
                getFullSnake().get(0).setRow(1);
                break;
            case RIGHT:
                if (idleDirection == Direction.LEFT){notGoingBack = true; break;}
                getFullSnake().addFirst(new Position(getHead().getRow(), getHead().getCol()));
                getFullSnake().pollLast();
                getFullSnake().get(0).setCol(1);
                break;
            case LEFT:
                if (idleDirection == Direction.RIGHT){notGoingBack = true; break;}
                getFullSnake().addFirst(new Position(getHead().getRow(), getHead().getCol()));
                getFullSnake().pollLast();
                getFullSnake().get(0).setCol(-1);
                break;
        }
        if (!notGoingBack) {idleDirection = direction;}
       /* switch (direction) {
            case UP -> {getFullSnake().addFirst(new Position(getHead().getRow(), getHead().getCol())); getFullSnake().pollLast();  getFullSnake().get(0).setRow(-1);}
            case DOWN -> {getFullSnake().addFirst(new Position(getHead().getRow(), getHead().getCol())); getFullSnake().pollLast(); getFullSnake().get(0).setRow(1);}
            case RIGHT -> {getFullSnake().addFirst(new Position(getHead().getRow(), getHead().getCol())); getFullSnake().pollLast(); getFullSnake().get(0).setCol(1);}
            case LEFT -> {getFullSnake().addFirst(new Position(getHead().getRow(), getHead().getCol())); getFullSnake().pollLast(); getFullSnake().get(0).setCol(-1);}
        }*/
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

    public Position getTail() {
        return fullSnake.getLast();
    }

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

