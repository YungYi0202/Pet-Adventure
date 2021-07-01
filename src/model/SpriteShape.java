package model;

import java.awt.*;

/**
 * @author - Yung-Yi Chen, Andy Young
 */

public class SpriteShape {
    public Dimension size;
    public Dimension bodyOffset;
    public Dimension bodySize;

    public SpriteShape(Dimension size,
                       Dimension bodyOffset, Dimension bodySize) {
        this.size = size;
        this.bodyOffset = bodyOffset;
        this.bodySize = bodySize;
    }
    public void setSize(Dimension size){
        this.size = size;
    }
    public void setBodyOffset(Dimension bodyOffset){
        this.bodyOffset = bodyOffset;
    }
    public void setBodySize(Dimension bodySize){
        this.bodySize = bodySize;
    }
}

