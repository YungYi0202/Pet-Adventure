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
 
public class ImageSlide extends ImageState{
    public ImageSlide(String petName){
        this.totalDuration = 0;
        this.defaultAnimLength = defaultAnimLength;
        frames = new ArrayList<ImageFrame>();
        int[] duration = {5,5,15,5,5};
        for(int i = 13; i <= 17; ++i){
            String path = "assets/"+ petName +"/slide/"+ petName + "_" + i + ".png";            
            addFrame(new ImageStateUtils().getImage(path), duration[i - 13]);
        }
    }
}
