package state;

public class Run extends State {
    public State getNext(Sprite s) {
	if (s.getVy() != 0)
	    return new Jump();
	return this;
    }
    public State getCollide() {
	return new UnstoppableJump();
    }
}
