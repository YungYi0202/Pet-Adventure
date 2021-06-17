package objects;

import model.Prop;

public class Candy extends Prop{
    //TODO: 等陳奕瑄給圖
    private Image image = null;
    private boolean isRemoved = false;
    private int score = 30;
    public Candy(){
        //TODO: 等陳奕瑄圖出來之後要設定
        //不知道要不要用new Dimension
        setShape(Dimension(), Dimension(), Dimension());
    }
    
    public boolean canBeRemoved(){return this.isRemoved;}

    public void render(Graphics g){
        //只有(isRemoved == false) 才會呼叫到這個函式
        Rectangle range = this.getRange()
        g.drawImage(this.image, range.x, range.y, range.width, range.height, null);
        
    }

    public void collideWith(Sprite sprite){
        if(sprite instanceof Pet){
            this.isRemoved = true;
            ((Pet)sprite).addScore(this.score);
        }
    }

    /*
    public int getScore(){return 30;}
    public int getHpDamage(){return 0;}
    public State getStateEffect(Sprite collideSprite){return null;}
    public boolean canBeStoredInPropBox(){return false;}
    */
}