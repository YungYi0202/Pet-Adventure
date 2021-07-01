package objects;

import state.Stop;
import state.Unstoppable;
import state.Dead;
import model.Prop;
import utils.ImageStateUtils;
import java.awt.*;
import java.lang.*;
import model.Sprite;
import java.awt.image.BufferedImage;
import pet.Pet;
import state.ImageState;
import state.ImageBird;

/**
 * @author - Yung-Yi Chen
 */

public class Bird extends Prop{
    private ImageState is;
    private int hpDamage = 180;
    public Bird(){
	is = new ImageBird();
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
        if(sprite instanceof Pet ){
            Pet pet = (Pet)sprite;
            if( pet.getState() instanceof Unstoppable) return;
            if(!(pet.getNowPropState() instanceof Unstoppable)){
                pet.costHp(hpDamage);
            }
            if(!(pet.getState() instanceof Dead)){
                pet.setState(new Stop(pet,pet.getState(),pet.petName)); 
            }
        }
    }
}
