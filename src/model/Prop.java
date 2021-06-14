package model;

import java.awt.*;
import state.State;

// 陳咏誼

//Prop的定義是：碰到之後會改變Pet的血量、State、道具箱等等
public abstract class Prop extends Sprite {
    // 回傳該Prop對應的分數
    public abstract int getScore();
    // return 0 if it doesn't cause Hp damage，若return value < 0 表示為補血道具
    public abstract int getHpDamage();
    // 回傳該Prop會讓觸碰到的東西變成什麼State，如果不會改變則回傳null
    public abstract State getStateEffect(Sprite collideSprite);
    // 回傳該Prop是否是可以被加進道具箱
    public abstract boolean canBeStoredInPropBox();

    //TODO: 不知道可不可以直接這樣
    public void update(){
        //道具的x都要減少speed
        Point loc = this.getLocation();
        loc.x -= this.getWorld().getSpeed();
        this.setLocation(loc);
    }

}
