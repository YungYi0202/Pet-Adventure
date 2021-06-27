package state;
import pet.Pet;
public class UnstoppableJump extends State implements Unstoppable {
    private int remainTime;
    private String petName;
    public UnstoppableJump(int n,String petName) {
    this.petName = petName;
	remainTime = n;
    }
    public UnstoppableJump() {
	remainTime = 100;
    }
    public int getRemainTime() {
	return remainTime;
    }
    public State getNext(Pet s) {
	is.update();
	if (--remainTime <= 0)
	    return new Jump(this.petName);
	if (s.getVy() == 0)
	    return new UnstoppableRun(remainTime,this.petName);
	return this;	    
    }
    public State getCollide() {
	return this;
    }
}
