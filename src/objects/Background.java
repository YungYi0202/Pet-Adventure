package objects;
import model.Sprite;
import state.*;
import java.awt.*;
import utils.ImageStateUtils;
import java.awt.image.BufferedImage;
import pet.Pet;
import java.lang.*;
// author = qpoiPeng
public class Background extends Sprite {
    private BufferedImage image;
    // 透過路徑決定要用哪張
    public Background(String path){
	this.image = ImageStateUtils.getImage(path);   
	setShape(new Dimension(image.getWidth(), image.getHeight()), new Dimension(0, 0), new Dimension(image.getWidth(), image.getHeight()));
    }
    @Override
    public void collideWith(Sprite s) {}
    @Override
    public void update() {
        this.decreaseLocationX(this.getWorld().getSpeed() / 2);
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
