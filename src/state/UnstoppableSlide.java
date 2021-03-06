package state;

import pet.Pet;

import java.lang.*;
import java.awt.*;

public class UnstoppableSlide extends State implements Unstoppable {
    private int slideRemainTime;
    private String petName;
    public UnstoppableSlide(int n,String petName,boolean doubleSlide) {
	this.petName = petName;
	this.is = new ImageSlide(this.petName,doubleSlide); 
	remainTime = n;
    }
    public UnstoppableSlide(String petName,boolean doubleSlide) {
	this.petName = petName;	
	this.is = new ImageSlide(this.petName,doubleSlide); 
	remainTime = 100;
    }
    public State getNext(Pet s) {
	is.update();
	slideRemainTime++;
	remainTime--;
	if(slideRemainTime >= 35 && remainTime > 0){
	    s.decreaseLocationY(s.getSlideY());
	    return new UnstoppableRun(remainTime, s,this.petName);
	}
	else if(slideRemainTime >= 35){
	    s.decreaseLocationY(s.getSlideY());
	    return new Run(this.petName);
	}
	else if(remainTime <= 0)
	    return new Slide(this.petName,true);
	return this;
    }
    public State getCollide() {
	return this;
    }
}
