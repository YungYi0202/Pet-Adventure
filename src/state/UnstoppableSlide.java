package state;
import pet.Pet;
import java.lang.*;
import java.awt.*;
public class UnstoppableSlide extends State implements Unstoppable {
    //private int remainTime;
	private int slideRemainTime;
	private String petName;
    public UnstoppableSlide(int n,String petName) {
	this.petName = petName;
	this.is = new ImageSlide(this.petName); //?
	remainTime = n;
    }
    public UnstoppableSlide(String petName) {
	this.petName = petName;	
	this.is = new ImageSlide(this.petName); //?
	remainTime = 100;
    }
    public State getNext(Pet s) {
	is.update();
	slideRemainTime++;
	remainTime--;
    if(slideRemainTime >= 35 && remainTime > 0){
        s.setLocation(new Point( s.getLocation().x , s.normalY - s.getVy()));
        return new UnstoppableRun(remainTime ,this.petName);
    }
	else if(slideRemainTime >= 35){
		s.setLocation(new Point( s.getLocation().x , s.normalY - s.getVy()));
        return new Run(this.petName);
	}
	else if(remainTime <= 0)
	    return new Slide(this.petName);
	return this;
    }
    public State getCollide() {
	return this;
    }
}
