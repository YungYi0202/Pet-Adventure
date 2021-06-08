package model;

//原本取名為Map，想想覺得還要記錄道具等等東西，所以叫關卡Stage

//複製world的import，可改
import java.awt.*;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;

import views.GameView;


//陳咏誼
//TODO: Sort By x

public abstract class Stage {
    private Image background; //目前的作法background是在GameView裡畫的，作法待更新
    private List<Position> positionList = new CopyOnWriteArrayList<Position>();
    private int index = 0;
    private int speed = 20;
    private int jumpFactor = 20; //TODO: 找到最適合的jumpFactor，20是亂設的default

    public void setBackground(Image background){
        this.background = background;
    }
    public void setSpeed(int speed){
        this.speed = speed;
    }
    public void setJumpFactor(int jumpFactor){
        this.jumpFactor = jumpFactor;
    }
    public Image getBackground(){return this.background;}
    public int getSpeed(){return this.speed;}
    public int getJumpFactor(){return this.jumpFactor;}


    //呼叫時要直接new 一個 sprite
    void addSprite(int x, int y, Sprite sprite){
        positionList.add( new Position(x, y, sprite) );
    }

    List<Sprite> getNewSprites(int cur_abs_x){
        List<Sprite> newSprites = new CopyOnWriteArrayList<Sprite>();
        while(index < positionList.size() && positionList.get(index).getX() < cur_abs_x){
            Position p = positionList.get(index);
            Sprite s = p.getSprite();
            
            //把x的絕對位置轉換為相對位置
            s.setLocation(new Point( GameView.WIDTH - (cur_abs_x - p.getX()) , p.getY() ));
            
            returnList.add(s);
            index++;
        }
        return newSprites;
    }
}