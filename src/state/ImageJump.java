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

public class ImageJump extends ImageState{
    public ImageJump(){
        totalDuration = 0;
        this.defaultAnimLength = defaultAnimLength;
        frames = new ArrayList<ImageFrame>();
        // add frames
        for(int i = 6; i < 12; ++i){
            // String path = "../../assets/puppy/jump/puppy_" + i + ".png";
            String path = "assets/puppy/jump/puppy_" + i + ".png";
            frames.add(new ImageFrame(new ImageStateUtils().getImage(path), i));
        }
    }
}