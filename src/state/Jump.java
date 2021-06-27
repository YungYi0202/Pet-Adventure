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
	//if (s.getVy() == 0) // 楊鈞安改掉了
	    //return new Run();
    //System.out.printf("speed : %d\n",s.getVy());
	return this;
    }
    public State getCollide() {
	return this;
    }
}
