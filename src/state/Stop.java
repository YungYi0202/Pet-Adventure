package state;
import pet.Pet;
public class Stop extends State implements Unstoppable {
    private int remainTime;
    public Stop(int n) {
	remainTime = n;
    }
    public Stop() {
	remainTime = 10;    //??
    }
    public int getRemainTime() {
	return remainTime;
    }
    public State getNext(Pet s) {
	is.update();
	if (--remainTime <= 0)
	    return new Run();
	
	// s.setVx(0);     not sure. 可能是什麼 getWorld() 再 set 之類的?
	return this;
    }
    public State getCollide() {
	return this;
    }
}
