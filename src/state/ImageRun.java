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
    public ImageRun(){
        this.totalDuration = 0;
        this.defaultAnimLength = defaultAnimLength;
        frames = new ArrayList<ImageFrame>();
        String path = "..../assert/puppyRun/puppy_";
        for(int i = 0; i < 5; ++i){
            path = path + i + ".png";
            frames.add(new ImageStateUtils().getImage(path), i);
        }
    }
}