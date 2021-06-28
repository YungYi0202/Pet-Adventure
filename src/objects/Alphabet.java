package objects;

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
 
public class Alphabet extends Prop{
    //TODO: 等陳奕瑄給圖
    private Boolean collected;
    private SerialAlphabet serial;
    private BufferedImage image;
    private int score = 100;

    public Alphabet(String alpha, SerialAlphabet s){
        this.collected = false;
        this.serial = s;
        this.image = ImageStateUtils.getImage("assets/alphabet/" + alpha + ".png");
        //this.image = utils.resize(image, 50, 80);

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
            this.collected = true;
            //System.out.printf("serial.isLastAlphabet(this): %b , serial.isAllCollected():%b\n", serial.isLastAlphabet(this) , serial.isAllCollected());
            if(serial.isLastAlphabet(this) && serial.isAllCollected() ){
                ((Pet)sprite).addScoreWithRender( this.score + serial.getBonus() );
            }else{
                ((Pet)sprite).addScoreWithRender( this.score );
            }
            world.removeSprite(this);
        }
    }

    public BufferedImage getImage(){return image;}
    public int getImageWidth(){return image.getWidth();}
    public Boolean isCollected(){return this.collected;}

}