package state;
import pet.Pet;
import java.lang.*;
public class Dead extends State {
    private String petName;
    public Dead(String petName) {
    this.remainTime = 100;
	this.petName = petName;
	this.is = new ImageDead(this.petName);
    }
    public State getNext(Pet s) {
	is.update();
    remainTime--;
    if(remainTime <= 0){
        s.set_isDead();
    }
	//System.out.println(s.getLocation().y);
	//if (s.getVy() < 0){
	    //return new Jump(this.petName);
    //}
    //System.out.println("wrong");
	return this;
    }
    public State getCollide() {
	return this;
    }
}