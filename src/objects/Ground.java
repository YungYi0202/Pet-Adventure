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
    public Ground(){
	//this.image = new ImageStateUtils().getImage("../../assets/floor/floor.png");
	this.image = new ImageStateUtils().getImage("assets/floor/floor.png");
    this.image = new ImageStateUtils().resize(this.image, image.getWidth() / 2, image.getHeight() / 2);
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
	if (p.getVy() != 0) // 楊鈞安改了這，getnormalVy 是一開始跳上去的速度的反方向，因為你原本寫 0
        p.setVy(0);
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
	return new Dimension(image.getWidth(), image.getHeight());                // 20?
    }
    public void render(Graphics g) {
        Rectangle range = this.getRange();
        g.drawImage(this.image, range.x, range.y, range.width, range.height, null);
    }
}
