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
 
public class ImageDoublePoint extends ImageState{
    public ImageDoublePoint(String petName , boolean proplist){
        this.totalDuration = 0;
        this.defaultAnimLength = defaultAnimLength;
        frames = new ArrayList<ImageFrame>();
        String path = "";
        if(proplist == true){
            path = "assets/can/can_3.png";
        }
        else{
            path = "assets/skill/skill_3.png"; 
        }
        frames.add(new ImageFrame(new ImageStateUtils().getImage(path), 1));
    }
}