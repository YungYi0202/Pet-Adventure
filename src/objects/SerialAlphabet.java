package objects;

import model.Sprite;
import utils.ImageStateUtils;
import java.awt.*;
import java.lang.*;
import model.Sprite;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;

import pet.Pet;

public class SerialAlphabet extends Sprite{
    //TODO: 等陳奕瑄給圖
    private int bonus = 300;
    private List<Alphabet> list = new ArrayList<Alphabet>();
    public Point absLocation;

    public SerialAlphabet(String ... alphas){
        for(String alpha: alphas){
            list.add(new Alphabet(alpha, this));
        }
        absLocation = new Point(800,100);
    }

    public Alphabet get(int index){return list.get(index);}
    public List<Alphabet> getAlphabetList(){ return list; }
    public Boolean isLastAlphabet(Alphabet A){ return ( list.get(list.size() - 1) == A ); }
    public int getBonus(){ return bonus; }

    public void render(Graphics g){
        //TODO: draw at right-up corner
        // for(Alphabet alpha: list){
        //     if(alpha.isCollected()){
        //         //TODO: draw 實心的照片

        //     }else{
        //         //TODO: draw 半透明的照片
        //     }
        // }
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