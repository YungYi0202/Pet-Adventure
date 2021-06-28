package state;

public class Charge extends PropState {
    private String petName;
    public Charge(String petName) {
	this.petName = petName;	
	this.is = new ImageRun(this.petName);
	remainTime = 120;
    }
}
