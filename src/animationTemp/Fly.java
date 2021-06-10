import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import utility.Resource;

public class Fly extends AnimState{
    public ImageState(){
        totalDuration = 0;
        this.defaultAnimLength = defaultAnimLength;
        frames = new ArrayList<AnimFrame>();
        // add frames
        // frames.add(new Resource().getResourceImage("../images/Cactus-1.png"), 1);
    }
}