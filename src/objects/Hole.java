package objects;
import model.Sprite;
import state.*;
import java.awt.*;
import utils.ImageStateUtils;
import java.awt.image.BufferedImage;
import pet.Pet;
import java.lang.*;

// author = qpoiPeng
public class Hole extends Sprite {
    private BufferedImage image;
    private static int cur = -1;
    public Hole(int n) {
	this.image = ImageStateUtils.getImage("assets/hole/hole_" + n + ".png");
	if (n == 1)
	    setShape(new Dimension(image.getWidth(), image.getHeight()), new Dimension(800, 0),
		     new Dimension(image.getWidth() - 800, image.getHeight()));	
    }
    @Override
    public void collideWith(Sprite s) {
	Pet p = null;
	if (s instanceof Pet) {
	    p = (Pet) s;
	    if (cur == -1)
		cur = 0;
	    if (cur != -1 && cur < 20) {
		p.increaseLocationY(2);
		++cur;
	    }
		
	    p.costHp(99999);
	}	
    }
    @Override
    public void update() {
        this.decreaseLocationX(this.getWorld().getSpeed());
    }
    @Override
    public Dimension getBodyOffset() {
	    return new Dimension(0, 0);
    }
    @Override
    public Dimension getBodySize() {
	return new Dimension(image.getWidth(), image.getHeight());       
    }
    public void render(Graphics g) {
        Rectangle range = this.getRange();
        g.drawImage(this.image, range.x, range.y, range.width, range.height, null);
    }
}
