package state;

import pet.Pet;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class State {
    public abstract State getNext(Pet s);
    public abstract State getCollide();
    protected ImageState is;
    public int remainTime;
    public ImageState getImageState(){
        return this.is;
    }
    public int getRemainTime() {
	return remainTime;
    }
    public BufferedImage getImage() {
	return is.getImage();
    }
}
