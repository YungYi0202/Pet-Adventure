package objects;

import model.Prop;
//TODO: 確認彭光湞有寫對應的State
import state.Stop;

public class Hydrant extends Prop{
    //TODO: 等陳奕瑄給圖
    private Image image = null;
    private int hpDamage = 50;
    public Hydrant(){
        //TODO: 等陳奕瑄圖出來之後要設定
        //不知道要不要用new Dimension
        setShape(Dimension(), Dimension(), Dimension());
    }
    
    public boolean canBeRemoved(){return false;}
    
    public void render(Graphics g){
        Rectangle range = this.getRange()
        g.drawImage(this.image, range.x, range.y, range.width, range.height, null);
    }

    public void collideWith(Sprite sprite){
        if(sprite instanceof Pet){
            Pet pet = (Pet)sprite;
            pet.costHp(hpDamage);
            //TODO: 確認彭光湞有寫對應的State
            pet.setState(new Stop());
        }
    }

    /*
    public int getScore(){return 0;}
    public int getHpDamage(){return 50;}
    public State getStateEffect(Sprite collideSprite){
        //TODO: 等彭光湞寫對應的State
        return null;
    }
    public boolean canBeStoredInPropBox(){return false;}
    */
}