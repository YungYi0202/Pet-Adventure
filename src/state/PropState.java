package state;
import pet.Pet;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class PropState extends State {
    /* Though this class implements all abstract method, this is a abstract class */
    public State getNext(Pet s) {
	if (--remainTime <= 0)
	    return null;
    }
    public State getCollide() {
	if (is != null)
	    is.update();
	return this;
    }
}
