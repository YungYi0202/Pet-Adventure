package objects;

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

public class Candy extends Prop{
    private BufferedImage image;
    private int score = 30;
    public Candy(){
        this.image = ImageStateUtils.getImage("assets/candy/candy.png");
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
            ((Pet)sprite).addScoreWithRender(this.score);
            world.removeSprite(this);
        }
    }
}