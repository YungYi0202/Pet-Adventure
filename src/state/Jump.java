package state;

public class Jump extends State {
    public Jump() {
	this.is = new ImageJump();
    }
    public State getNext(Pet s) {
	if (s.getVy() == 0)
	    return new Run();
	return this;
    }
    public State getCollide() {
	return this;
    }
}
