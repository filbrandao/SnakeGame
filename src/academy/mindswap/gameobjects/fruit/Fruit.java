package academy.mindswap.gameobjects.fruit;

import academy.mindswap.field.Position;

public class Fruit {

 private final Position position;

    public Fruit(Position position){
       this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }
}
