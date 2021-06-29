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
 
public class ImageBird extends ImageState{
    public ImageBird(){
        this.totalDuration = 0;
        this.defaultAnimLength = defaultAnimLength;
        frames = new ArrayList<ImageFrame>();
        for(int i = 1; i <= 6; ++i){
            String path = "assets/bird/bird_" + i + ".png";
            
            // frames.add(new ImageFrame(new ImageStateUtils().getImage(path), 1));
            // addFrame(new ImageStateUtils().getImage(path), 5);
            addFrame(ImageStateUtils.getImage(path), 8);
        }
    }
}
