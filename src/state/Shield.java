package state;
import pet.Pet;
import java.lang.*;
import java.awt.*;
public class Shield extends PropState{
    private String petName;
    public Shield(String petName) {
	this.petName = petName;	
	this.is = new ImageShield(this.petName,false);
	remainTime = 1000;
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
        return new Rectangle( s.getX()-60, s.getY()+20, (int)s.getRange().getWidth()/2, (int)s.getRange().getHeight()/3*2); // ?
    }
}