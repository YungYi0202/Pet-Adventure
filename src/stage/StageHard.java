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


public class StageHard extends Stage{
    public StageHard(){
        setSpeed(25);
        //TODO: 把該有的Sprite加進去
	
	addFarground(0, 0, new Farground("assets/farground/farground_1.png"));
	
        //serialAlphabet的用法
        SerialAlphabet serial = new SerialAlphabet("F", "O", "O1", "P");
        addSprite(serial.absLocation, serial);
	addSpriteToFirstFloor(4500, serial.get(0));
        addSpriteToSecondFloor(8000, serial.get(1));
        addSpriteToSecondFloor(12000, serial.get(2));
        addSpriteToFirstFloor(15500, serial.get(3));

	addSpriteToFirstFloor(1000, new Hydrant());
	addSpriteToFirstFloor(2000, new Hydrant());
	addSpriteToFirstFloor(3200, new Hydrant());
	addSpriteToFirstFloor(5000, new Hydrant());
	addSpriteToFirstFloor(6000, new Hydrant());
	addSpriteToFirstFloor(12000, new Hydrant());
	addSpriteToFirstFloor(14200, new Hydrant());
	addSpriteToFirstFloor(20000, new Hydrant());
	addSpriteToFirstFloor(20800, new Hydrant());

	addSpriteToFirstFloor(3500, new Candy());
	addSpriteToSecondFloor(6000, new Candy());
	addSpriteToFirstFloor(7000, new Candy());
	addSpriteToFirstFloor(9000, new Candy());
	addSpriteToSecondFloor(12500, new Candy());

	addSpriteToFirstFloor(14000, new ChargeCan());
	addSprite(5200, this.getSecondFloorY() - 380, new Candy());
	addSprite(4000, this.getSecondFloorY(), new SecondFloor(1));
	addSprite(5000, this.getSecondFloorY(), new SecondFloor(3));
	addSprite(14000, this.getSecondFloorY(), new SecondFloor(1));
	addSprite(15000, this.getSecondFloorY(), new SecondFloor(2));
	addSprite(16000, this.getSecondFloorY(), new SecondFloor(2));
	addSprite(17000, this.getSecondFloorY(), new SecondFloor(3));

	
        for(int i = 0 ; i < 22; i++){
            addSprite(i * 1000 , this.getFirstFloorY() , new Ground());
	    //Bird b = new Bird();
	    //addSprite((i+1) * 1000, getFirstFloorY() - b.getBodySize().height - 120, b);
            //addSprite((i+1) * 1000 , (int)(GameView.HEIGHT * 0.2) , new Candy());
            //addSprite((i+1) * 1000 , (int)(GameView.HEIGHT * 0.5) , new Hydrant());

        }
        // Leyna
        setBackground( ImageStateUtils.getImage("assets/background/background_1.png") );
        // setBackground( new ImageStateUtils().getImage("assets/background/background_1.png") );
        // Cathy
        sortByX();
        
    }
}
