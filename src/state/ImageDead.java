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
 
public class ImageDead extends ImageState{
    public ImageDead(String petName){
        this.totalDuration = 0;
        this.defaultAnimLength = defaultAnimLength;
        frames = new ArrayList<ImageFrame>();
        int[] duration = {30, 30, 140};
        for(int i = 19; i <= 21; ++i){
            String path = "assets/"+ petName +"/dead/"+ petName +"_" + i + ".png";
            //frames.add(new ImageFrame(new ImageStateUtils().getImage(path), duration[i - 19]));
            addFrame(ImageStateUtils.getImage(path), duration[i - 19]);
        }
    }
}