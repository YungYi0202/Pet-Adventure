package objects;

import model.Sprite;
import state.*;
import pet.Pet;
import utils.ImageStateUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.*;

/**
 * @author - qpoiPeng
 */

public class SecondFloor extends Sprite {
    private BufferedImage image;

    // n 代表第幾張，起點用 1 中間用 2 終點用 3。
    public SecondFloor(int n){
	this.image = ImageStateUtils.getImage("assets/secondfloor/secondfloor_" + n + ".png");
	setShape(new Dimension(image.getWidth(), image.getHeight()), new Dimension(0, 0), new Dimension(image.getWidth(), 1));
    }
    @Override
    public void collideWith(Sprite s) {
        Pet p = null;
        if (s instanceof Pet)
            p = (Pet) s;
        if (p.getState() instanceof UnstoppableJump && p.getVy() > 0)
            p.setState( new UnstoppableRun(p.getState().remainTime, p, p.petName) );
        else if(p.getState() instanceof Jump && p.getVy() > 0)
            p.setState(new Run(p.petName));
        else if( !(p.getState() instanceof Jump || p.getState() instanceof UnstoppableJump))
            p.decreaseLocationY(p.getVy());
        if (p.getVy() > 0)
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
	return new Dimension(image.getWidth(), image.getHeight());       
    }
    public void render(Graphics g) {
        Rectangle range = this.getRange();
        g.drawImage(this.image, range.x, range.y, range.width, range.height, null);
    }
}
