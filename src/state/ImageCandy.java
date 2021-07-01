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

public class ImageCandy extends ImageState{
    public ImageCandy(){
        totalDuration = 0;
        this.defaultAnimLength = defaultAnimLength;
        frames = new ArrayList<ImageFrame>();
        String path = "../../assert/candy.png";
        frames.add( ImageStateUtils.getImage(path), i);
    }
}