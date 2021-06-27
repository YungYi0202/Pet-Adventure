package stage;

import model.Stage;
import model.Position;
import model.Sprite;
import objects.Ground;
import objects.Candy;
import objects.Hydrant;
import views.GameView;
//Leyna
import utils.ImageStateUtils;
import java.awt.image.BufferedImage;

/**
 * @author - Yung-Yi Chen
 */
public class StageEasy1 extends Stage{
    public StageEasy1(){
        setSpeed(10);
        //TODO: 把該有的Sprite加進去
        for(int i = 0 ; i < 50; i++){
            addSprite(i * 1000 , this.getFirstFloorY() , new Ground());
            addSprite((i+1) * 1000 , (int)(GameView.HEIGHT * 0.2) , new Candy());
            addSprite((i+1) * 1000 , (int)(GameView.HEIGHT * 0.5) , new Hydrant());
        }
        // Leyna
        this.background = new ImageStateUtils().getImage("assets/background/background.png");

    }
}
