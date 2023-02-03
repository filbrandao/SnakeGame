package academy.mindswap.field;

import java.util.Objects;

public class Position {
    private int y = 12;
    private int x = 50;
    public Position(int y, int x){
        this.y += y;
        this.x += x;
    }

    public int getCol() {
        return x;
    }

    public int getRow() {
        return y;
    }

    public void setRow(int y) {
        this.y += y;
    }

    public void setCol(int x) {
        this.x += x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return y == position.y && x == position.x;
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x);
    }
}
