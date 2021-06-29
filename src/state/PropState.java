package state;
import pet.Pet;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class PropState extends State {
    /* Though this class implements all abstract method, this is a abstract class */
    //public BufferedImage getImage() {
	//return is.getImage();
    //}
    public PropState getNext(Pet s) {
        return null; 
    }
    public State getCollide() {
        return null;
    }
    public Rectangle getPropRange(Pet s){
        return null;
    }
	//if (is != null)
	    //is.update();
	//return this;
}
