package pet;
import model.Sprite;
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

    /*public void onDamaged(Rectangle damageArea, int damage) {
        this.hp = Math.max(hp - damage, 0);
    }*/
    public void onDamaged(int damage) {
        this.hp = Math.max(hp - damage, 0);
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g) {
        System.out.println("healthbar render");
        Rectangle range = getRange();
        int width = (int) (hp * owner.getRange().getWidth() / maxHp);
        g.setColor(Color.RED);
        g.fillRect(range.x, range.y, (int) owner.getRange().getWidth(), range.height);
        g.setColor(Color.GREEN); // 血是green
        g.fillRect(range.x, range.y, width, range.height);
    }
    
    @Override
    public void collideWith(Sprite sprite){
        return;
    }
    @Override
    public Rectangle getRange() { /// Todo 位置可能要改 
        return new Rectangle(owner.getX(), owner.getY() - 15, (int) owner.getRange().getWidth(), 10);
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
