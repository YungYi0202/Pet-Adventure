public class UnstoppableFly extends State {
    private int remainTime;
    UnstoppableFly(int n) {
	remainTime = n;
    }
    UnstoppableFly() {
	remainTime = 10;
    }
    public State getNext(Sprite s) {
	if (--remainTime <= 0 && s.getVy() != 0)
	    return new Fly();
	if (remainTime <= 0)
	    return new Run();
	if (s.getVy() == 0)
	    return new UnstoppableRun(remainTime);
	return this;	    
    }
    public State getCollide() {
	return this;
    }
}
