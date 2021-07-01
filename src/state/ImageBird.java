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
 
public class ImageBird extends ImageState{
    public ImageBird(){
        this.totalDuration = 0;
        this.defaultAnimLength = defaultAnimLength;
        frames = new ArrayList<ImageFrame>();
        for(int i = 1; i <= 6; ++i){
            String path = "assets/bird/bird_" + i + ".png";
            addFrame(ImageStateUtils.getImage(path), 8);
        }
    }
}
