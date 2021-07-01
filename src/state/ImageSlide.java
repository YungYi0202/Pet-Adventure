package state;

import utils.ImageStateUtils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
 
public class ImageSlide extends ImageState{
    public ImageSlide(String petName,boolean doubleSlide){
        this.totalDuration = 0;
        this.defaultAnimLength = defaultAnimLength;
        frames = new ArrayList<ImageFrame>();
        if(doubleSlide){
            int[] duration = {21,7,7};
            for(int i = 15; i >= 13; --i){
                String path = "assets/"+ petName +"/slide/"+ petName + "_" + i + ".png";            
                addFrame(ImageStateUtils.getImage(path), duration[ 15-i ]);
            }
        }
        else{
            int[] duration = {6,6,11,6,6};
            for(int i = 13; i <= 15; ++i){
                String path = "assets/"+ petName +"/slide/"+ petName + "_" + i + ".png";            
                addFrame(ImageStateUtils.getImage(path), duration[i - 13]);
            }
            String path = "assets/"+ petName +"/slide/"+ petName + "_" + 14 + ".png";            
            addFrame(ImageStateUtils.getImage(path), duration[1]);
            path = "assets/"+ petName +"/slide/"+ petName + "_" + 13 + ".png";            
            addFrame(ImageStateUtils.getImage(path), duration[0]);
        }
        
    }
}
