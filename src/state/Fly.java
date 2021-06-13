package state;

public class Fly extends State {
    public State getNext(Sprite s) {
	if (s.getVy() == 0)
	    return new Run();
	return this;
    }
    public State getCollide() {
	return new UnstoppableFly();
    }
}
