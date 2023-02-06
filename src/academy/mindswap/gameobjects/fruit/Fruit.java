package academy.mindswap.gameobjects.fruit;

import academy.mindswap.field.Position;

public class Fruit {

    private final Position position;
    int fruitPoints;

    public Fruit(Position position){
       this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }
    public int getFruitPoints() {
        return fruitPoints;
    }

    public void setFruitPoints(int fruitPoints) {
        this.fruitPoints = fruitPoints;
    }
}
