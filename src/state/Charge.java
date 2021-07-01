package state;

import pet.Pet;

import java.lang.*;
import java.awt.*;

public class Charge extends PropState{
    private String petName;
    private int ChargeSpeed = 40;
    public Charge(String petName) {
	this.petName = petName;	
	this.is = new ImageCharge(this.petName,false);
	remainTime = 100;
    }
    @Override
    public PropState getNext(Pet s) {
	is.update();
    remainTime--;
	if(remainTime <= 0){
        return null;
    }
    s.setNowSpeed(ChargeSpeed);
	return this;
    }
    
    @Override
    public Rectangle getPropRange(Pet s){
        return new Rectangle( s.getX()-60, s.getY()+20, (int)s.getRange().getWidth()/2, (int)s.getRange().getHeight()/3*2);
    }
}
