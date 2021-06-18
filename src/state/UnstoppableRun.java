package state;

public class UnstoppableRun extends State implements Unstoppable {
    private int remainTime;
    public UnstoppableRun(int n) {
	remainTime = n;
    }
    public UnstoppableRun() {
	remainTime = 10;
    }
    public int getRemainTime() {
	return remainTime;
    }
    public State getNext(Pet s) {
	if (--remainTime <= 0 && s.getVy() == 0)
	    return new Run();
	if (remainTime <= 0)
	    return new Jump();
	if (s.getVy() != 0)
	    return new UnstoppableJump(remainTime);
	return this;
    }
    public State getCollide() {
	return this;
    }
}
