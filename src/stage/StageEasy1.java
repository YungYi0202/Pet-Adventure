package stage;

import model.Stage;
import model.Position;
import model.Sprite;
import objects.Ground;
import objects.SecondFloor;
import objects.Candy;
import objects.Hydrant;
import objects.Bird;
import objects.Alphabet;
import objects.SerialAlphabet;
import objects.Farground;
import objects.ChargeCan;
import views.GameView;
//Leyna
import utils.ImageStateUtils;
import java.awt.image.BufferedImage;

/**
 * @author - Yung-Yi Chen
 */
public class StageEasy1 extends Stage{
    public StageEasy1(){
        setSpeed(15);
        //TODO: 把該有的Sprite加進去
	
	    addFarground(0, 0, new Farground("assets/farground/farground_1.png"));
	
        //serialAlphabet的用法
        SerialAlphabet serial = new SerialAlphabet("F", "O", "O1", "P");
        addSprite(serial.absLocation, serial);
        addSpriteToFirstFloor(2500, serial.get(0));
        addSpriteToFirstFloor(3500, serial.get(1));
        addSpriteToFirstFloor(4500, serial.get(2));
        addSpriteToFirstFloor(5500, serial.get(3));


        //addSpriteToFirstFloor(2500, new Alphabet("F"));

        for(int i = 0 ; i < 50; i++){
            addSprite(i * 1000 , this.getFirstFloorY() , new Ground());
	    //Bird b = new Bird();
	    //addSprite((i+1) * 1000, getFirstFloorY() - b.getBodySize().height - 120, b);
            //addSprite((i+1) * 1000 , (int)(GameView.HEIGHT * 0.2) , new Candy());
           addSprite((i+1) * 700 , (int)(GameView.HEIGHT * 0.5) , new Hydrant());

        }
	    // 測試加到 2 樓, Peng

        addSprite( 1000 , this.getSecondFloorY() , new SecondFloor(1));
        addSprite( 1800 , this.getSecondFloorY() , new SecondFloor(2));
        addSprite( 2600 , this.getSecondFloorY() , new SecondFloor(3));
        addSprite( 3000 , (int)(GameView.HEIGHT * 0.5) , new ChargeCan());

        // Leyna
        setBackground( ImageStateUtils.getImage("assets/background/background_1.png") );
        // setBackground( new ImageStateUtils().getImage("assets/background/background_1.png") );
        // Cathy
        sortByX();
        
    }
}
