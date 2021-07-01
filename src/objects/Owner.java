package objects;

import state.Stop;
import state.Unstoppable;
import state.Dead;
import model.Prop;
import utils.ImageStateUtils;
import java.awt.*;
import java.lang.*;
import model.Sprite;
import java.awt.image.BufferedImage;
import pet.Pet;
import state.ImageState;
import state.ImageOwner;
/**
 * @author - Yung-Yi Chen
 */

public class Owner extends Sprite{
    //TODO: 等陳奕瑄給圖
    private ImageState is;
    //    private BufferedImage image = null;
    public Owner(){
	is = new ImageOwner();
        int width = is.getImage().getWidth();
        int height = is.getImage().getHeight();
        setShape(new Dimension(width, height), new Dimension(0, 0), new Dimension(width, height));
    }

    @Override
    public void update(){
        this.decreaseLocationX(this.getWorld().getSpeed());
	is.update();
    }

    
    public void render(Graphics g){
        Rectangle range = this.getRange();
	BufferedImage bi = is.getImage();
        g.drawImage(bi, range.x, range.y, range.width, range.height, null);
    }

    public void collideWith(Sprite sprite){
	
    }

}
