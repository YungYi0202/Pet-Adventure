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
    public ImageJump(String petName){
        totalDuration = 0;
        this.defaultAnimLength = defaultAnimLength;
        frames = new ArrayList<ImageFrame>();
        int[] duration = {3, 6, 6, 6, 6, 6, 5};
        // add frames
        for(int i = 6; i <= 12; ++i){
            // String path = "../../assets/puppy/jump/puppy_" + i + ".png";
            String path = "assets/"+petName+"/jump/"+petName+"_" + i + ".png";
            addFrame(new ImageStateUtils().getImage(path), duration[i - 6]);
        }
    }
}
