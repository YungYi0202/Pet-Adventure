package objects;
import model.Sprite;
import state.*;
import awt.*;
import utils.ImageStateUtils;
// author = qpoiPeng
public class Ground extends Sprite {
    private BufferedImage image;
    boolean isRemoved = false;
    public Ground() {
	this.image = new ImageStateUtils().getImage("../../assets/floor/floor.png");
	setShape(new Dimension(image.getWidth(), image.getHeight()), new Dimension(0, 0), new Dimension(image.getWidth(), image.getHeight()));
    }
    @Override
    public void collideWith(Sprite s) {
	if (s.getState() instanceof Unstoppable)
	    s.setState(new UnstoppableRun());
	else
	    s.setState(new Run());
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
    public void render(Graphics g) {
	if (isRemoved == false) {
	    Rectangle range = this.getRange();
	    g.drawImage(this.image, range.x, range.y, range.width, range.height, null);
	}
    }
}
