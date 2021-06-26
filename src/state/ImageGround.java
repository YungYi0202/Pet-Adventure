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

public class ImageGround extends ImageState{
    public ImageGround(){
        totalDuration = 0;
        this.defaultAnimLength = defaultAnimLength;
        frames = new ArrayList<ImageFrame>();
        for(int i = 1; i <=2; ++i){
            String path = "assert/ground/ground_" + i + ".png";
            addFrame(new ImageStateUtils().getImage(path), 5);
        }
    }
    BufferedImage getGround(int i) {
        if (frames.size() == 0) {
            return null;
        }
        else {
            return getFrame(i).image;
        }
    }
}