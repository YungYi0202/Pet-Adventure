package state;
import pet.Pet;
import java.lang.*;
import java.awt.*;
public class Slide extends State {
    private int remainTime;
    private String petName;
    public Slide(String petName) {
    this.remainTime = 0;
    this.petName = petName;
	this.is = new ImageSlide(petName);
    }
    public State getNext(Pet s) {
	is.update();
    //System.out.println(s.getLocation().y);
    remainTime++;
    if(remainTime >= 30){
        s.setLocation(new Point( s.getLocation().x , s.normalY - s.getVy()));

        return new Run(this.petName);
    }
	return this;
    }
    public State getCollide() {
	return this;
    }
}