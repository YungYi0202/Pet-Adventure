package state;

import pet.Pet;

public class UnstoppableJump extends State implements Unstoppable {
    private String petName;
    public UnstoppableJump(int n,String petName) {
    this.petName = petName;
    this.is = new ImageJump(petName);
	remainTime = n;
    }
    public UnstoppableJump(String petName) {
    this.petName = petName;
    this.is = new ImageJump(petName);
	remainTime = 100;
    }
    public State getNext(Pet s) {
	is.update();
	if (--remainTime <= 0)
	    return new Jump(this.petName);
	return this;	    
    }
    public State getCollide() {
	return this;
    }
}
