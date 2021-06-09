package state;
import 

public abstract class State {
    public abstract State getNext(Sprite s);
    public abstract State getCollide();
}
