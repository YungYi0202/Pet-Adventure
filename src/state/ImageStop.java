package state;

import utils.ImageStateUtils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author - Leyna
 */
 
public class ImageStop extends ImageState{
    public ImageStop(String petName){
        this.totalDuration = 0;
        this.defaultAnimLength = defaultAnimLength;
        frames = new ArrayList<ImageFrame>();
        String path = "assets/"+ petName +"/stop/"+ petName + "_" + 18 + ".png";
            
        frames.add(new ImageFrame(ImageStateUtils.getImage(path), 1));
    }
}