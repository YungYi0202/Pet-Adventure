package state;
import pet.Pet;
import java.lang.*;
import java.awt.*;
import views.GameView;
public class RunToEnd extends State{
    private int check = 0;
    private String petName;
    public RunToEnd(Pet s,String petName) {
        if(s.getState() instanceof Slide || s.getState() instanceof UnstoppableSlide){
            s.decreaseLocationY(s.getSlideY());
	    }
        remainTime = 1000;
	    this.petName = petName;
	    this.is = new ImageRun(this.petName);
        s.setGravity(20);
    }
    public int getRemainTime() {
	return remainTime;
    }
    public State getNext(Pet s) {
        
    remainTime--;
    if(s.getLocation().x < GameView.WIDTH/13*7-10){
        is.update();
        s.increaseLocationX(s.getNormalSpeed());
    }
	
	if(s.toEnd() && this.is instanceof ImageRun){ // revise in here
        this.is = new ImageEnd(this.petName);
        s.decreaseLocationY(s.getVy());
        s.setGravity(2);
    }
    
    if(s.getLocation().x >= GameView.WIDTH/13*7-10 && this.is instanceof ImageEnd && this.check == 0){
        this.check = 1;
        remainTime = 60;
        s.setGravity(0);
        s.setVy(0);
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