package state;
import pet.Pet;
import java.awt.Image;
public abstract class State {
    public abstract State getNext(Pet s);
    public abstract State getCollide();
    protected ImageState is;
    public Image getImage() {
	return is.getImage();
    }
}

