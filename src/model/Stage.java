package model;

//原本取名為Map，想想覺得還要記錄道具等等東西，所以叫關卡Stage

//複製world的import，可改
import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Comparator;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;

import views.GameView;


/**
 * @author - Yung-Yi Chen
 */
//TODO: Sort By (x + offset.width)=imageX

public abstract class Stage {
    //leyna changed to public
    private Image background; //目前的作法background是在GameView裡畫的，作法待更新
    private List<Position> positionList = new CopyOnWriteArrayList<Position>();
    private PositionComparater comparator = new PositionComparater();
    private int index = 0;
    private int speed = 20;

    public void setBackground(Image background){
        this.background = background;
    }
    public void setSpeed(int speed){
        this.speed = speed;
    }
    
    //public Image getBackground(){return this.background;}
    public void backgroundRender(Graphics g){
        g.drawImage(this.background, 0, 0, GameView.WIDTH, GameView.HEIGHT, null);
    }

    public int getSpeed(){return this.speed;}
   
    //呼叫時要直接new 一個 sprite
    public void addSprite(int x, int y, Sprite sprite){
        positionList.add( new Position(x, y, sprite) );
    }
    public void addSprite(Point posi, Sprite sprite){
        positionList.add( new Position(posi, sprite) );
    }

    public void addSpriteToFirstFloor(int x, Sprite sprite){
        positionList.add( new Position(x, getFirstFloorY() - sprite.getBodySize().height , sprite) );
    }
    public void addSpriteToSecondFloor(int x, Sprite sprite){
        positionList.add( new Position(x, getSecondFloorY() - sprite.getBodySize().height , sprite) );
    }

    List<Sprite> getNewSprites(int cur_abs_x){
        List<Sprite> newSprites = new CopyOnWriteArrayList<Sprite>();
        while(index < positionList.size() && positionList.get(index).getImageX() < cur_abs_x){
            Position p = positionList.get(index);
            Sprite s = p.getSprite();
            
            //把x的絕對位置轉換為相對位置
            s.setLocation(new Point( GameView.WIDTH - (cur_abs_x - p.getX()) , p.getY() ));
            
            newSprites.add(s);
            index++;
        }
        // if(newSprites.size() > 0){
        //     System.out.printf("getNewSprites: sprites list size = %d\n", newSprites.size());
        // }

        return newSprites;
    }

    public int getFirstFloorY(){return (int)(GameView.HEIGHT * 0.75); }
    public int getSecondFloorY(){return (int)(GameView.HEIGHT * 0.4); }

    public void sortByX(){
        Collections.sort(positionList, comparator);
    }
}

class PositionComparater implements Comparator<Position> {
  @Override
  public int compare(Position A, Position B) {
    int startComparison = compare(A.getX(), B.getX());
    return startComparison;
  }
  private static int compare(int a, int b) {
    return a < b ? -1
         : a > b ? 1
         : 0;
  }
}