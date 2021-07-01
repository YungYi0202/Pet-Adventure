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
 
public class ImageCharge extends ImageState{
    public ImageCharge(String petName , boolean proplist){
        this.totalDuration = 0;
        this.defaultAnimLength = defaultAnimLength;
        frames = new ArrayList<ImageFrame>();
        String path = "";
        if(proplist == true){
            path = "assets/can/can_1.png";
        }
        else{
            path = "assets/skill/skill_1.png";
        }
        frames.add(new ImageFrame(new ImageStateUtils().getImage(path), 1));
    }
}