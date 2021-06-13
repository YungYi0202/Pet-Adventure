package state;

public class Run extends State {
    public State getNext(Sprite s) {
	if (s.getVy() != 0)
	    return new Fly();
	return this;
    }
    public State getCollide() {
	return new UnstoppableRun();
    }
}
