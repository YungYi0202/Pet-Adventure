package objects;

import model.Prop;
import utils.ImageStateUtils;
import java.awt.*;
import java.lang.*;
import model.Sprite;
import java.awt.image.BufferedImage;
import pet.Pet;
 
public class Alphabet extends Prop{
    //TODO: 等陳奕瑄給圖
    private BufferedImage image;
    private int score = 500;
    private static ImageStateUtils utils = new ImageStateUtils();
    public Alphabet(int i){
        
        this.image = utils.getImage("assets/alphabet/alphabet_" + i + ".png");
        // this.image = utils.resize(image, 0.5);
        int width = image.getWidth();
        int height = image.getHeight();
        //System.out.printf("Alphabet: width:%d\n height:%d\n", width, height);
        setShape(new Dimension(width, height), new Dimension(0, 0), new Dimension(width, height));
    }
    
    public void render(Graphics g){
        //只有(isRemoved == false) 才會呼叫到這個函式
        Rectangle range = this.getRange();
        g.drawImage(this.image, range.x, range.y, range.width, range.height, null);
        
    }

    public void collideWith(Sprite sprite){
        if(sprite instanceof Pet){
            //this.isRemoved = true;
            ((Pet)sprite).addScoreWithRender(this.score);
            world.removeSprite(this);
        }
    }

    /*
    public int getScore(){return 30;}
    public int getHpDamage(){return 0;}
    public State getStateEffect(Sprite collideSprite){return null;}
    public boolean canBeStoredInPropBox(){return false;}
    */
}