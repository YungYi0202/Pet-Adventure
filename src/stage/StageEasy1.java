package stage;

import model.Stage;
import model.Position;
import model.Sprite;
import objects.Ground;
import objects.Candy;
import objects.Hydrant;
import objects.Alphabet;
import objects.SerialAlphabet;
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
        
        //serialAlphabet的用法
        SerialAlphabet serial = new SerialAlphabet("F", "o", "o", "P");
        addSprite(serial.absLocation, serial);
        addSpriteToFirstFloor(2500, serial.get(0));
        addSpriteToFirstFloor(3500, serial.get(1));
        addSpriteToFirstFloor(4500, serial.get(2));
        addSpriteToFirstFloor(5500, serial.get(3));

        //addSpriteToFirstFloor(2500, new Alphabet("F"));

        for(int i = 0 ; i < 50; i++){
            addSprite(i * 1000 , this.getFirstFloorY() , new Ground());
            addSprite((i+1) * 1000 , (int)(GameView.HEIGHT * 0.2) , new Candy());
            addSprite((i+1) * 1000 , (int)(GameView.HEIGHT * 0.5) , new Hydrant());
        }
        // Leyna
        setBackground( new ImageStateUtils().getImage("assets/background/background.png") );
        // Cathy
        sortByX();
        
    }
}
