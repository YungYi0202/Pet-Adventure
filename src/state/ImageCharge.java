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
 
public class ImageCharge extends ImageState{
    public ImageCharge(String petName , boolean proplist){
        this.totalDuration = 0;
        this.defaultAnimLength = defaultAnimLength;
        frames = new ArrayList<ImageFrame>();
        //for(int i = 6; i <= 12; ++i){
            //String path = "assets/"+ petName +"/jump/"+ petName +"_" + i + ".png";
        String path = "";
        if(proplist == true){
            path = "src/can/can_1.png";
        }
        else{
            path = "assets/unstop/unstop.png";
        }
        
        frames.add(new ImageFrame(new ImageStateUtils().getImage(path), 1));
            // addFrame(new ImageStateUtils().getImage(path), 5);
            //addFrame(ImageStateUtils.getImage(path), 5);
        //}
    }
}