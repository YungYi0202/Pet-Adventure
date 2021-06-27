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
	this.image = new ImageStateUtils().getImage("assets/ground/ground_1.png");
	setShape(new Dimension(image.getWidth(), image.getHeight()), new Dimension(0, 0), new Dimension(image.getWidth(), image.getHeight()));
    }
    @Override
    public void collideWith(Sprite s) {
    System.out.println("dealing");
	Pet p = null;
	if (s instanceof Pet)
	    p = (Pet) s;
	if (p.getState() instanceof Unstoppable)
	    p.setState(new UnstoppableRun());
    else if(p.getState() instanceof Jump && p.getVy() > 0)
	    p.setState(new Run(p.petName));
    else if(p.getState() instanceof Run || p.getState() instanceof Slide)
        p.decreaseLocationY(p.getVy());
        //p.backToNormalLocation();
	if (p.getVy() > 0) // 楊鈞安改了這，getnormalVy 是一開始跳上去的速度的反方向，因為你原本寫 0
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
