package state;
import pet.Pet;
public class UnstoppableSlide extends State implements Unstoppable {
    //private int remainTime;
	private String petName;
    public UnstoppableSlide(int n,String petName) {
	this.petName = petName;
	this.is = new ImageSlide(this.petName); //?
	remainTime = n;
    }
    public UnstoppableSlide(String petName) {
	this.petName = petName;	
	this.is = new ImageSlide(this.petName); //?
	remainTime = 300;
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