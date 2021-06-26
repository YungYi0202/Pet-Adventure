package state;
import pet.Pet;
public class Run extends State {
    public Run() {
	this.is = new ImageRun();
    }
    public State getNext(Pet s) {
	is.update();
    System.out.printf("position : %d\n",s.getLocation().y);
    System.out.println(s.getVy());
	if (s.getVy() != 0){
        System.out.println("no I didin,t");
	    return new Jump();
    }
	return this;
    }
    public State getCollide() {
	return this;
    }
}
