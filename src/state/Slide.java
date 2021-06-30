package state;
import pet.Pet;
import java.lang.*;
import java.awt.*;
public class Slide extends State {
    private int remainTime;
    private String petName;
    public Slide(String petName,boolean doubleSlide) {
	this.remainTime = 35;
	this.petName = petName;
	this.is = new ImageSlide(petName,doubleSlide);
    }
    public State getNext(Pet s) {
	is.update();
	if(--remainTime <= 0){
	    s.decreaseLocationY(s.getSlideY());
	    return new Run(this.petName);
	}
	return this;
    }
    public State getCollide() {
	return this;
    }
}
