package objects;

import model.Prop;
import model.Sprite;
import pet.Pet;
import state.Charge;
import utils.ImageStateUtils;

import java.awt.*;
import java.lang.*;
import java.awt.image.BufferedImage;

public class ChargeCan extends Prop{
    private BufferedImage image;
    public ChargeCan(){
        this.image = ImageStateUtils.getImage("assets/can/can_1.png");
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
        p.addProps("ChargeCan");
        world.removeSprite(this);
        }
    }
}
