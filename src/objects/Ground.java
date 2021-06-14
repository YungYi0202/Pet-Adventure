package objects;
import model.Sprite;
import awt.*;

// author = ?
public class Ground extends Sprite {
    private int width;
    public Ground(int x, int y, int width) {
	    this.location = new Point(x, y);
	    this.width = width;
    }
    @Override
    public void collideWith(Sprite s) {
	    s.setVy(0);
    }
    @Override
    public void update() {
	    this.location.move(-this.getWorld.getSpeed(), 0);
    }
    @Override
    public Dimension getBodyOffset() {
	    return new Dimension(0, 0);
    }
    @Override
    public Dimension getBodySize() {
	    return new Dimension(width, 20);                // 20?
    }
}
