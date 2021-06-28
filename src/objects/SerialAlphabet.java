package objects;

import model.Sprite;
import utils.ImageStateUtils;
import java.awt.*;
import java.lang.*;
import model.Sprite;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;
import views.GameView;

import pet.Pet;

/**
 * @author - Yung-Yi Chen
 */

public class SerialAlphabet extends Sprite{
    //TODO: 等陳奕瑄給圖
    private int bonus = 300;
    private List<Alphabet> list = new ArrayList<Alphabet>();
    public Point absLocation;
    double shrinkRate = 0.5;
    float opacityRate = (float)0.5;
    int interval = 10;
    int margin = 90;
    int Y = 100;

    public SerialAlphabet(String ... alphas){
        //int [] alphabetCount = new int[52];
        double widthToRight = 0;
        for(String alpha: alphas){
            //int index = (int)(alpha.charAt(0) - 'a');
            //list.add(new Alphabet(alpha + String.valueOf( alphabetCount[index] ) , this));
            list.add(new Alphabet(alpha  , this));
            widthToRight += list.get(list.size() - 1).getImageWidth() * shrinkRate + interval;
            //alphabetCount[index]++;
        }
        absLocation = new Point( GameView.WIDTH - (int)widthToRight - margin , Y);
        //setShape(new Dimension(width, height), new Dimension(0, 0), new Dimension(width, height));
    }

    public Alphabet get(int index){return list.get(index);}
    public List<Alphabet> getAlphabetList(){ return list; }
    public Boolean isLastAlphabet(Alphabet A){ return ( list.get(list.size() - 1) == A ); }
    public int getBonus(){ return bonus; }

    public void render(Graphics g){
        //TODO: draw at right-up corner
        int width = 0;
        for(Alphabet alpha: list){
            BufferedImage img = ImageStateUtils.resize(alpha.getImage(), shrinkRate);
            if(alpha.isCollected()){
                //draw 實心的照片
                g.drawImage(img, absLocation.x + width, Y, img.getWidth(), img.getHeight(), null);
            }else{
                //半透明的照片
                img = ImageStateUtils.opacity(img, opacityRate);
                g.drawImage(img, absLocation.x + width, Y, img.getWidth(), img.getHeight(), null);
            }
            width += img.getWidth() + interval;
        }
    }

    public Boolean isAllCollected(){
        for(Alphabet alpha: list){
            if(alpha.isCollected() == false) return false; 
        }
        return true;
    }

    public void collideWith(Sprite sprite){
    }
    public void update(){

    }

    

}