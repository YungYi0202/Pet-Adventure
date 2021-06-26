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
    public ImageSlide(){
        this.totalDuration = 0;
        this.defaultAnimLength = defaultAnimLength;
        frames = new ArrayList<ImageFrame>();
        for(int i = 13; i <= 17; ++i){
            String path = "assets/puppy/slide/puppy_" + i + ".png";
            addFrame(new ImageStateUtils().getImage(path), 5);
        }
    }
}
