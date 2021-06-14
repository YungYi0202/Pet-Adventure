package objects;

import model.Prop;

public class Candy extends Prop{
    //TODO: 等陳奕瑄給圖
    Image image = null;
    boolean isRemoved = false;
    public Candy(){
        //TODO: 等陳奕瑄圖出來之後要設定
        //不知道要不要用new Dimension
        setShape(Dimension(), Dimension(), Dimension());
    }
    public int getScore(){return 30;}
    public int getHpDamage(){return 0;}
    public State getStateEffect(Sprite collideSprite){return null;}
    public boolean canBeStoredInPropBox(){return false;}
    public boolean canBeRemoved(){return this.isRemoved;}

    //TODO: 不知道要不要直接寫到Prop裡
    public void render(Graphics g){
        if(isRemoved == false){
            Rectangle range = this.getRange()
            g.drawImage(this.image, range.x, range.y, range.width, range.height, null);
        }
    }

    public void collideWith(Sprite sprite){
        if(sprite instanceof Pet){
            this.isRemoved = true;
        }
    }
}