package state;

import pet.Pet;

import java.lang.*;
import java.awt.*;

public class Stop extends State implements Unstoppable {
    private String petName;
    public Stop(Pet s, State nowstate,String petName) {
	if(nowstate instanceof Slide){
		s.decreaseLocationY(s.getSlideY());
	}
	s.setVy(0);
	this.petName = petName;
	this.is = new ImageStop(this.petName);
	remainTime = 60;
    }
    public int getRemainTime() {
	return remainTime;
    }
    public State getNext(Pet s) {
	is.update();
	if (--remainTime <= 0)
	    return new UnstoppableRun(this.petName);
	return this;
    }
    public State getCollide() {
	return this;
    }
}
