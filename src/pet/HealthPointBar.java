package pet;

import model.Sprite;
import views.GameView;

import java.awt.*;
import java.lang.*;

/**
 * @author - Andy young 
 */

public class HealthPointBar extends Sprite {
    private final int maxHp;
    private Sprite owner;
    private int hp;

    public HealthPointBar(int hp) {
        this.maxHp = this.hp = hp;
    }

    public void setOwner(Sprite owner) {
        this.owner = owner;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void onDamaged(int damage) {
        this.hp = Math.max(hp - damage, 0);
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g) {
        Rectangle range = getRange();
        int width = (int) (hp * GameView.WIDTH/5*3 / maxHp);
        g.setColor(Color.BLACK);
        g.fillRect(range.x, range.y, (int) GameView.WIDTH/5*3, range.height);
        g.setColor(Color.RED); // 血是green
        g.fillRect(range.x, range.y, width, range.height);
    }
    
    @Override
    public void collideWith(Sprite sprite){
        return;
    }
    @Override
    public Rectangle getRange() { /// Todo 位置可能要改 
        return new Rectangle(GameView.WIDTH/10 - 20 , GameView.HEIGHT/16, GameView.WIDTH/5*2, 20);
    } 

    @Override
    public Dimension getBodyOffset() {
        return new Dimension();
    }

    @Override
    public Dimension getBodySize() {
        return new Dimension();
    }

    public boolean isDead() {
        return hp <= 0;
    }
}
