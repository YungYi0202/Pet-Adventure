package objects;

import model.Prop;
import utils.ImageStateUtils;
import java.awt.*;
import java.lang.*;
import model.Sprite;
import java.awt.image.BufferedImage;
import pet.Pet;
import state.DoublePoint;

public class DoubleCan extends Prop {
    private BufferedImage image;
    public DoubleCan(){
        this.image = ImageStateUtils.getImage("assets/can/can_3.png"); 
        int width = image.getWidth();
        int height = image.getHeight();
        setShape(new Dimension(width, height), new Dimension(0, 0), new Dimension(width, height));
    }
    
    public void render(Graphics g){
        Rectangle range = this.getRange();
        g.drawImage(this.image, range.x, range.y, range.width, range.height, null);
    }

    public void collideWith(Sprite sprite){
        if(sprite instanceof Pet){
	    Pet p = (Pet) sprite;
	    p.addProps("DoublePoint");
	    world.removeSprite(this);
        }
    }
}
