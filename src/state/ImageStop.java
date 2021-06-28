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
 
public class ImageStop extends ImageState{
    public ImageStop(String petName){
        this.totalDuration = 0;
        this.defaultAnimLength = defaultAnimLength;
        frames = new ArrayList<ImageFrame>();
        //for(int i = 14; i <= 16; ++i){
        String path = "assets/"+ petName +"/run/"+ petName + "_" + 1 + ".png";
            
        // frames.add(new ImageFrame(new ImageStateUtils().getImage(path), 1));
        frames.add(new ImageFrame(ImageStateUtils.getImage(path), 1));
            //addFrame(new ImageStateUtils().getImage(path), 3);
        //}
    }
}