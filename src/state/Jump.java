package state;

public class Jump extends State {
    public State getNext(Sprite s) {
	if (s.getVy() == 0)
	    return new Run();
	return this;
    }
    public State getCollide() {
	return new UnstoppableJump();
    }
}
