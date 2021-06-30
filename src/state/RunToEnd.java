package state;
import pet.Pet;
import java.lang.*;
import java.awt.*;
public class RunToEnd extends State{

    private String petName;
    public RunToEnd(Pet s,String petName) {
        if(s.getState() instanceof Slide || s.getState() instanceof UnstoppableSlide){
	        //s.setLocation(new Point( s.getLocation().x , s.normalY - s.getVy()));
            s.setLocation(new Point( s.getLocation().x , s.getLocation().y - s.getVy()-50));
	    }
        remainTime = 1000;
	    this.petName = petName;
	    this.is = new ImageRun(this.petName);
    }
    public int getRemainTime() {
	return remainTime;
    }
    public State getNext(Pet s) {
	is.update();
    remainTime--;
	if(s.toEnd() && this.is instanceof ImageRun){
        this.is = new ImageEnd(this.petName);
        remainTime = 100;
    }
    if(this.is instanceof ImageRun){
        s.increaseLocationX(s.getNormalSpeed());
    }
    if(remainTime <= 0){
        s.setEnding();
    }
	return this;
    }
    public State getCollide() {
	return this;
    }
}