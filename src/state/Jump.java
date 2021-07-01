package state;

import pet.Pet;

public class Jump extends State {
    private String petName;
    public Jump(String petName) {
    this.petName = petName;
	this.is = new ImageJump(petName);
    }
    public State getNext(Pet s) {
	is.update();
	return this;
    }
    public State getCollide() {
	return this;
    }
}
