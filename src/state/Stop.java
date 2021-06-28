package state;
import pet.Pet;
public class Stop extends State implements Unstoppable {
    //private int remainTime;
    private String petName;
    //public Stop(int n) {
    //this.is = new ImageStop(petName);
	//remainTime = n;
    //}
    public Stop(String petName) {
    this.petName = petName;
    this.is = new ImageStop(this.petName);
	remainTime = 60;    //??
    }
    public int getRemainTime() {
	return remainTime;
    }
    public State getNext(Pet s) {
	is.update();
	if (--remainTime <= 0)
	    return new UnstoppableRun(this.petName);
	
	// s.setVx(0);     not sure. 可能是什麼 getWorld() 再 set 之類的?
	return this;
    }
    public State getCollide() {
	return this;
    }
}
