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
	    setShape(new Dimension(image.getWidth(), image.getHeight()), new Dimension((int)(image.getWidth() * 0.5), 0),
		     new Dimension((int)(image.getWidth() * 0.5), image.getHeight()));	
	else if (n == 2)
	    setShape(new Dimension(image.getWidth(), image.getHeight()), new Dimension(0, 0),
		     new Dimension(0, 0));	
	else if (n == 3)
	    setShape(new Dimension(image.getWidth(), image.getHeight()), new Dimension(0, 0),
		     new Dimension((int)(image.getWidth() * 0.5), image.getHeight()));	
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
    public void render(Graphics g) {
        Rectangle range = this.getRange();
        g.drawImage(this.image, range.x, range.y, range.width, range.height, null);
    }
}
