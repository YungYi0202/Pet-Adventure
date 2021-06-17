package state;

public class UnstoppableJump extends State implements Unstoppable {
    private int remainTime;
    public UnstoppableJump(int n) {
	remainTime = n;
    }
    public UnstoppableJump() {
	remainTime = 10;
    }
    public int getRemainTime() {
	return remainTime;
    }
    public State getNext(Sprite s) {
	if (--remainTime <= 0)
	    return new Jump();
	if (s.getVy() == 0)
	    return new UnstoppableRun(remainTime);
	return this;	    
    }
    public State getCollide() {
	return this;
    }
}
