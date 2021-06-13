package state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import util.ImageStateUtils;

/**
 * @author - Leyna
 */
public 
public class ImageFly extends ImageState{
    public ImageState(){
        totalDuration = 0;
        this.defaultAnimLength = defaultAnimLength;
        frames = new ArrayList<ImageFrame>();
        // add frames
        // frames.add(new ImageStateUtils().getImage("../images/Cactus-1.png"), 1);
    }
}