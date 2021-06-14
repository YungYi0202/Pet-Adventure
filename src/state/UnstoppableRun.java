package state;

public class UnstoppableRun extends State {
    private int remainTime;
    UnstoppableRun(int n) {
	remainTime = n;
    }
    UnstoppableRun() {
	remainTime = 10;
    }
    public State getNext(Pet s) {
	if (--remainTime <= 0 && s.getVy() == 0)
	    return new Run();
	if (remainTime <= 0)
	    return new Fly();
	if (s.getVy() != 0)
	    return new UnstoppableFly(remainTime);
	return this;
    }
    public State getCollide() {
	return this;
    }
}
