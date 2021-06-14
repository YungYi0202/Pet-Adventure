package objects;

import model.Prop;

public class Hydrant extends Prop{
    //TODO: 等陳奕瑄給圖
    Image image = null;
    public Candy(){
        //TODO: 等陳奕瑄圖出來之後要設定
        //不知道要不要用new Dimension
        setShape(Dimension(), Dimension(), Dimension());
    }
    public int getScore(){return 0;}
    public int getHpDamage(){return 50;}
    public State getStateEffect(Sprite collideSprite){
        //TODO: 等彭光湞寫對應的State
        return null;
    }
    public boolean canBeStoredInPropBox(){return false;}
    public boolean canBeRemoved(){return false;}

    //TODO: 不知道要不要直接寫到Prop裡
    public void render(Graphics g){
        Rectangle range = this.getRange()
        g.drawImage(this.image, range.x, range.y, range.width, range.height, null);
    }

    public void collideWith(Sprite sprite){
        //什麼都不用做
    }
}