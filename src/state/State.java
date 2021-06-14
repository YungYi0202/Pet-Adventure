package state;
import model.Sprite;

public abstract class State {
    public abstract State getNext(Pet s);
    public abstract State getCollide();
}

