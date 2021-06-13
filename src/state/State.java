package state;
import model.Sprite;

public abstract class State {
    public abstract State getNext(Sprite s);
    public abstract State getCollide();
}

