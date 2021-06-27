package state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import utils.ImageStateUtils;

/**
 * @author - Leyna
 */
 
public class ImageRun extends ImageState{
    public ImageRun(String petName){
        this.totalDuration = 0;
        this.defaultAnimLength = defaultAnimLength;
        frames = new ArrayList<ImageFrame>();
        for(int i = 1; i <= 5; ++i){
            String path = "assets/"+ petName +"/run/"+ petName +"_" + i + ".png";
            
            // frames.add(new ImageFrame(new ImageStateUtils().getImage(path), 1));
            addFrame(new ImageStateUtils().getImage(path), 5);
        }
    }
}