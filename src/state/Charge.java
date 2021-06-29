package state;
import pet.Pet;
import java.lang.*;
import java.awt.*;
public class Charge extends PropState{
    private String petName;
    private int ChargeSpeed = 40;
    public Charge(String petName) {
	this.petName = petName;	
	this.is = new ImageCharge(this.petName);
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
}
