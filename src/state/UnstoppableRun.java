package state;

public class UnstoppableRun extends State implements Unstoppable {
    private int remainTime;
    UnstoppableRun(int n) {
	remainTime = n;
    }
    UnstoppableRun() {
	remainTime = 10;
    }
    public int getRemainTime() {
	return remainTime;
    }
    public State getNext(Sprite s) {
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
