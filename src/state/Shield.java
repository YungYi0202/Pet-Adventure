package state;

import pet.Pet;

import java.lang.*;
import java.awt.*;

public class Shield extends PropState implements Unstoppable{
    private String petName;
    public Shield(String petName) {
	this.petName = petName;	
	this.is = new ImageShield(this.petName,false);
	remainTime = 500;
    }
    @Override
    public PropState getNext(Pet s) {
	is.update();
    remainTime--;
	if(remainTime <= 0){
        return null;
    }
	return this;
    }
    
    @Override
    public Rectangle getPropRange(Pet s){
        return new Rectangle( s.getX()-35, s.getY()-40, (int)s.getRange().getWidth()/5*7, (int)s.getRange().getHeight()/5*7); // ?
    }
}