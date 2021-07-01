package objects;

import state.Stop;
import state.Unstoppable;
import state.Dead;
import state.ImageOwner;
import state.ImageState;
import model.Prop;
import pet.Pet;
import utils.ImageStateUtils;
import model.Sprite;

import java.lang.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author - Yung-Yi Chen
 */

public class Owner extends Sprite{
    private ImageState is;
    public Owner(){
	is = new ImageOwner();
        int width = is.getImage().getWidth();
        int height = is.getImage().getHeight();
        setShape(new Dimension(width, height), new Dimension(0, 0), new Dimension(width, height));
    }

    @Override
    public void update(){
        this.decreaseLocationX(this.getWorld().getSpeed());
	is.update();
    }

    
    public void render(Graphics g){
        Rectangle range = this.getRange();
	BufferedImage bi = is.getImage();
        g.drawImage(bi, range.x, range.y, range.width, range.height, null);
    }

    public void collideWith(Sprite sprite){
	
    }

}
