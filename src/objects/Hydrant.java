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

/**
 * @author - Yung-Yi Chen
 */

public class Hydrant extends Prop{
    //TODO: 等陳奕瑄給圖
    private BufferedImage image = null;
    private int hpDamage = 50;
    public Hydrant(){
        //this.image = new ImageStateUtils().getImage("assets/hydrant/hydrant.png");
        this.image = ImageStateUtils.getImage("assets/hydrant/hydrant.png");
        int width = image.getWidth();
        int height = image.getHeight();
        setShape(new Dimension(width, height), new Dimension(0, 0), new Dimension(width, height));
    }
    
    
    public void render(Graphics g){
        Rectangle range = this.getRange();
        g.drawImage(this.image, range.x, range.y, range.width, range.height, null);
    }

    public void collideWith(Sprite sprite){
        if(sprite instanceof Pet ){
            Pet pet = (Pet)sprite;
            if( pet.getState() instanceof Unstoppable) return;
            pet.costHp(hpDamage);
            //TODO: 確認彭光湞有寫對應的State
            if(!(pet.getState() instanceof Dead)){
                pet.setState(new Stop(pet,pet.getState(),pet.petName)); 
            }
        }
    }

    /*
    public int getScore(){return 0;}
    public int getHpDamage(){return 50;}
    public State getStateEffect(Sprite collideSprite){
        //TODO: 等彭光湞寫對應的State
        return null;
    }
    public boolean canBeStoredInPropBox(){return false;}
    */
}