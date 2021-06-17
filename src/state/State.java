package state;
import model.Sprite;

public abstract class State {
    public abstract State getNext(Pet s);
    public abstract State getCollide();
    protected ImageState is;
    public void setImage()
    public Image getImage() {
	return is.getImage();
    }
}

