package state;
import pet.Pet;
import java.lang.*;
import java.awt.*;
public class DoublePoint extends PropState{
    private String petName;
    public DoublePoint(String petName) {
	this.petName = petName;	
	//this.is = new ImageCharge(this.petName);
	remainTime = 300;
    }
    @Override
    public PropState getNext(Pet s) {
	//is.update();
    remainTime--;
	if(remainTime <= 0){
        return null;
    }
    //s.setNowSpeed(ChargeSpeed);
	return this;
    }
    
    @Override
    public Rectangle getPropRange(Pet s){
        return null;
        //return new Rectangle( s.getX()-60, s.getY()+20, (int)s.getRange().getWidth()/2, (int)s.getRange().getHeight()/3*2);
    }
}