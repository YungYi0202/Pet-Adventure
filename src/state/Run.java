package state;
import pet.Pet;
public class Run extends State {
    private String petName;
    public Run(String petName) {
    this.petName = petName;
	this.is = new ImageRun(this.petName);
    }
    public State getNext(Pet s) {
	is.update();
    //System.out.println(s.getLocation().y);
	if (s.getVy() < 0){
	    return new Jump(this.petName);
    }
	return this;
    }
    public State getCollide() {
	return this;
    }
}
