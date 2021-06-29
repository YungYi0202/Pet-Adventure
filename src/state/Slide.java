package state;
import pet.Pet;
import java.lang.*;
import java.awt.*;
public class Slide extends State {
    private int remainTime;
    private String petName;
    public Slide(String petName) {
	this.remainTime = 35;
	this.petName = petName;
	this.is = new ImageSlide(petName);
    }
    public State getNext(Pet s) {
	is.update();
	if(--remainTime <= 0){
	    //s.setLocation(new Point( s.getLocation().x , s.normalY - s.getVy()));
		//s.setLocation(new Point( s.getLocation().x , s.getLocation().y - s.getVy()-50));
	    s.decreaseLocationY(50);
	    return new Run(this.petName);
	}
	return this;
    }
    public State getCollide() {
	return this;
    }
}
