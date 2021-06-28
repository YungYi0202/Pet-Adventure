package state;
import pet.Pet;
public class UnstoppableRun extends State implements Unstoppable {
    //private int remainTime;
	private String petName;
    public UnstoppableRun(int n,String petName) {
	this.petName = petName;
	this.is = new ImageRun(this.petName);
	remainTime = n;
    }
    public UnstoppableRun(String petName) {
	this.petName = petName;	
	this.is = new ImageRun(this.petName);
	remainTime = 60;
    }
    public int getRemainTime() {
	return remainTime;
    }
    public State getNext(Pet s) {
	is.update();
	if (--remainTime <= 0 && s.getVy() >= 0)
	    return new Run(this.petName);
	if (remainTime <= 0)
	    return new Jump(this.petName);
	if (s.getVy() < 0)
	    return new UnstoppableJump(remainTime,this.petName);
	return this;
    }
    public State getCollide() {
	return this;
    }
}
