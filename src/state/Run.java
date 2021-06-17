package state;

public class Run extends State {
    public Run() {
	this.is = new ImageRun();
    }
    public State getNext(Pet s) {
	if (s.getVy() != 0)
	    return new Jump();
	return this;
    }
    public State getCollide() {
	return new UnstoppableRun();
    }
}
