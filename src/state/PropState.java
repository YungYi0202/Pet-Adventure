package state;

import pet.Pet;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class PropState extends State {
    public PropState getNext(Pet s) {
        return null; 
    }
    public State getCollide() {
        return null;
    }
    public Rectangle getPropRange(Pet s){
        return null;
    }
}
