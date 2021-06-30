package state;
import pet.Pet;
import java.lang.*;
public class Dead extends State {
    private String petName;
    public Dead(String petName) {
    this.remainTime = 200;
	this.petName = petName;
	this.is = new ImageDead(this.petName);
    }
    public State getNext(Pet s) {
	is.update();
    remainTime--;
    if(remainTime <= 0){
        s.set_isDead();
    }
	return this;
    }
    public State getCollide() {
	return this;
    }
}