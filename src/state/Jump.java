package state;
import pet.Pet;
public class Jump extends State {
    public Jump() {
	this.is = new ImageJump();
    }
    public State getNext(Pet s) {
	is.update();
	//if (s.getVy() == 0) // 楊鈞安改掉了
	    //return new Run();
	return this;
    }
    public State getCollide() {
	return this;
    }
}
