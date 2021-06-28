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

public class ImageHydrant extends ImageState{
    public ImageHydrant(){
        totalDuration = 0;
        this.defaultAnimLength = defaultAnimLength;
        frames = new ArrayList<ImageFrame>();
        String path = "../../assert/hydrant.png";
        frames.add(ImageStateUtils.getImage(path), i);
        // frames.add(new ImageStateUtils().getImage(path), i);
    }
}