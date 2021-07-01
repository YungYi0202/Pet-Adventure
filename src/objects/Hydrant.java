package objects;

import state.Stop;
import state.Unstoppable;
import state.Dead;
import model.Prop;
import model.Sprite;
import pet.Pet;
import utils.ImageStateUtils;

import java.awt.*;
import java.lang.*;
import java.awt.image.BufferedImage;

/**
 * @author - Yung-Yi Chen
 */

public class Hydrant extends Prop{
    private BufferedImage image = null;
    private int hpDamage = 125;
    public Hydrant(){
        this.image = ImageStateUtils.getImage("assets/hydrant/hydrant.png");
        int width = image.getWidth();
        int height = image.getHeight();
        setShape(new Dimension(width, height), new Dimension(0, 50), new Dimension(width, height - 50));
    }
    
    
    public void render(Graphics g){
        Rectangle range = this.getRange();
        g.drawImage(this.image, range.x, range.y, range.width, range.height, null);
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
