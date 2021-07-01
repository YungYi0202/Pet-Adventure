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
 
public class ImageOwner extends ImageState{
    public ImageOwner(){
        this.totalDuration = 0;
        this.defaultAnimLength = defaultAnimLength;
        frames = new ArrayList<ImageFrame>();
        for(int i = 1; i <= 5; ++i){
            String path = "assets/owner/owner_" + i + ".png";
            
            // frames.add(new ImageFrame(new ImageStateUtils().getImage(path), 1));
            // addFrame(new ImageStateUtils().getImage(path), 5);
            addFrame(ImageStateUtils.resize(ImageStateUtils.getImage(path), 0.8), 8);
        }
    }
}
