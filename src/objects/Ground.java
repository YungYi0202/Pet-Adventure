package objects;
import model.Sprite;
import state.*;
import java.awt.*;
import utils.ImageStateUtils;
import java.awt.image.BufferedImage;
import pet.Pet;
import java.lang.*;
// author = qpoiPeng
public class Ground extends Sprite {
    private BufferedImage image;
    boolean isRemoved = false;
    public Ground(){
	//this.image = new ImageStateUtils().getImage("../../assets/floor/floor.png");
	this.image = new ImageStateUtils().getImage("assets/floor/floor.png");
	setShape(new Dimension(image.getWidth(), image.getHeight()), new Dimension(0, 0), new Dimension(image.getWidth(), image.getHeight()));
    }
    @Override
    public void collideWith(Sprite s) {
	Pet p = null;
	if (s instanceof Pet)
	    p = (Pet) s;
	if (p.getState() instanceof Unstoppable)
	    p.setState(new UnstoppableRun());
	else
	    p.setState(new Run());
	p.setVy(0);
    }
    @Override
    public void update() {
	    // this.location.move(-this.getWorld().getSpeed(), 0);
        
        //Point loc = this.getLocation();
        //loc.x -= this.getWorld().getSpeed();
        //this.setLocation(loc);
        this.decreaseLocationX(this.getWorld().getSpeed());
    }
    @Override
    public Dimension getBodyOffset() {
	    return new Dimension(0, 0);
    }
    @Override
    public Dimension getBodySize() {
	return new Dimension(image.getWidth(), image.getHeight());                // 20?
    }
    public void render(Graphics g) {
    if (isRemoved == false) {
        Rectangle range = this.getRange();
        g.drawImage(this.image, range.x, range.y, range.width, range.height, null);
    }
    }
}
