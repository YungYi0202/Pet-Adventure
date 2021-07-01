package objects;

import model.Prop;
import utils.ImageStateUtils;
import java.awt.*;
import java.lang.*;
import model.Sprite;
import java.awt.image.BufferedImage;
import pet.Pet;
import state.Shield;
public class ShieldCan extends Prop{
    //private int chargeRemainTime;
    private BufferedImage image;
    public ShieldCan(){
        //this.image = new ImageStateUtils().getImage("assets/candy/candy.png"); // 待改
        this.image = ImageStateUtils.getImage("assets/can/can_2.png"); // 待改
        //this.chargeRemainTime = 200; // 可改成用傳入的
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
        p.addProps("Shield");
        world.removeSprite(this);
        }
    }
}