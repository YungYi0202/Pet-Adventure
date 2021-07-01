package stage;

import model.Stage;
import model.Position;
import model.Sprite;
import objects.Ground;
import objects.Hole;
import objects.SecondFloor;
import objects.Candy;
import objects.Hydrant;
import objects.Bird;
import objects.Owner;
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
public class StageEasy extends Stage{
    public StageEasy(){
        setSpeed(15);
        //TODO: 把該有的Sprite加進去
	
	addFarground(0, 0, new Farground("assets/farground/farground_1.png"));
	
        //serialAlphabet的用法
        SerialAlphabet serial = new SerialAlphabet("P", "E", "T");
        addSprite(serial.absLocation, serial);
		addSpriteToFirstFloor(4500, serial.get(0));
        addSpriteToSecondFloor(8000, serial.get(1));
        addSpriteToSecondFloor(15500, serial.get(2));
        // addSpriteToFirstFloor(15500, serial.get(3));
	

	addSprite(1330, this.getFirstFloorY() - 300, new Candy());
	addSprite(1530, this.getFirstFloorY() - 380, new Candy());
	addSprite(1730, this.getFirstFloorY() - 300, new Candy());

	addSpriteToFirstFloor(1500, new Hydrant());
	for (int i = 0; i < 3; ++i)
	    addSpriteToFirstFloor(2300 + 200 * i, new Candy());
	addSprite(3030, this.getFirstFloorY() - 300, new Candy());
	addSprite(3230, this.getFirstFloorY() - 380, new Candy());
	addSprite(3430, this.getFirstFloorY() - 300, new Candy());
	addSpriteToFirstFloor(3200, new Hydrant());
	addSpriteToFirstFloor(5000, new Hydrant());
	addSpriteToFirstFloor(6000, new Hydrant());
	addSpriteToFirstFloor(12000, new Hydrant());
	addSpriteToFirstFloor(14200, new Hydrant());
	addSpriteToFirstFloor(20000, new Hydrant());
	addSpriteToFirstFloor(20800, new Hydrant());

	for (int i = 0; i < 3; ++i)
	    addSpriteToFirstFloor(3500 + 200 * i, new Candy());
	for (int i = 0; i < 3; ++i)
	    addSpriteToSecondFloor(5600 + i * 200, new Candy());
	addSpriteToFirstFloor(7000, new Candy());
	for (int i = 0; i < 6; ++i)
	    addSpriteToFirstFloor(8800 + 200 * i, new Candy());
	addSprite(10000, this.getFirstFloorY() - 300, new Candy());
	addSprite(10200, this.getFirstFloorY() - 380, new Candy());
	addSprite(10400, this.getFirstFloorY() - 300, new Candy());
	for (int i = 0; i < 5; ++i)
	    addSpriteToFirstFloor(14400 + 200 * i, new Candy());
	addSprite(5000, this.getSecondFloorY() - 300, new Candy());
	addSprite(5200, this.getSecondFloorY() - 380, new Candy());
	addSprite(5400, this.getSecondFloorY() - 300, new Candy());

	addSpriteToFirstFloor(13950, new ChargeCan());
	addSprite(4000, this.getSecondFloorY(), new SecondFloor(1));
	addSprite(5000, this.getSecondFloorY(), new SecondFloor(3));
	addSprite(14000, this.getSecondFloorY(), new SecondFloor(1));
	addSprite(15000, this.getSecondFloorY(), new SecondFloor(2));
	addSprite(16000, this.getSecondFloorY(), new SecondFloor(2));
	addSprite(17000, this.getSecondFloorY(), new SecondFloor(3));

	addSpriteToFirstFloor(21700, new Owner());
        for(int i = 0 ; i < 22; i++){
	    addSprite(i * 1000 , this.getFirstFloorY() , new Ground());
        }
        // Leyna
        setBackground( ImageStateUtils.getImage("assets/background/background_1.png") );
        // Cathy
        sortByX();
        
    }
}
