package stage;

import model.Stage;
import model.Position;
import model.Sprite;
import objects.*;
import views.GameView;
//Leyna
import utils.ImageStateUtils;
import java.awt.image.BufferedImage;


public class StageHard extends Stage{
    private void addHydrantPattern(int pos) {
	addSpriteToFirstFloor(pos, new Hydrant());
	addSprite(pos - 250 + 30, getFirstFloorY() - 300, new Candy());
	addSprite(pos       + 30, getFirstFloorY() - 380, new Candy());
	addSprite(pos + 250 + 30, getFirstFloorY() - 300, new Candy());
    }
    private void addSecondFloor(int start, int count) {
	addSprite(start, getSecondFloorY(), new SecondFloor(1));
	for (int i = 1; i < count - 1; ++i)
	    addSprite(start + i * 1000, getSecondFloorY(), new SecondFloor(2));
	addSprite(start + (count - 1) * 1000, getSecondFloorY(), new SecondFloor(3));
    }
    
    public StageHard(){
        setSpeed(25);
        //TODO: 把該有的Sprite加進去
	
	addFarground(0, 0, new Farground("assets/farground/farground_2.png"));
	
        //serialAlphabet的用法
        SerialAlphabet serial = new SerialAlphabet("F", "O", "O1", "P");
        addSprite(serial.absLocation, serial);
	
	Bird b = new Bird();
	//addSprite((i+1) * 1000, getFirstFloorY() - b.getBodySize().height - 120, b);
	/*
	addSpriteToFirstFloor(4500, serial.get(0));
        addSpriteToSecondFloor(8000, serial.get(1));
        addSpriteToSecondFloor(12000, serial.get(2));
        addSpriteToFirstFloor(15500, serial.get(3));
	*/

	for (int i = 0; i < 60; ++i) 
	    addSprite(i * 1000, getFirstFloorY(), new Ground());
	addHydrantPattern(2000);
	for (int i = 0; i < 3; ++i)
	    addSpriteToFirstFloor(2400 + 200 * i, new Candy());
	addSprite(4000, getFirstFloorY(), new Hole(1));
	addSprite(4200, getFirstFloorY(), new Hole(2));
	addSprite(5000, getFirstFloorY(), new Hole(3));
	addSecondFloor(3700, 2);

	addSpriteToFirstFloor(7000, new DoubleCan());
	for (int i = 0; i < 8; ++i)
	    addSpriteToFirstFloor(7500 + 300 * i, new Hydrant());
	addSpriteToFirstFloor(6200, serial.get(0));
	addSpriteToFirstFloor(6000, new Candy());
	addSpriteToFirstFloor(6400, new Candy());
			      
	addSecondFloor(6600, 4);
	addSprite(8400, getSecondFloorY() - b.getBodySize().height - 120, new Bird());
	addSprite(8600, getSecondFloorY() - b.getBodySize().height - 180, new Bird());
	addSprite(8800, getSecondFloorY() - b.getBodySize().height - 240, new Bird());

	addSprite(10350, getSecondFloorY() - 300, new Candy());
	addSprite(10600, getSecondFloorY() - 380, new Candy());
	addSprite(10850, getSecondFloorY() - 300, new Candy());

	for (int i = 0; i < 10; ++i)
	    addSpriteToFirstFloor(11200 + 200 * i, new Candy());
	addSecondFloor(11500, 2);
	addSpriteToSecondFloor(11800, new ChargeCan());
	addSprite(15000, getSecondFloorY() - 380, serial.get(1));

	addSpriteToFirstFloor(17000, new Hydrant());
	addSprite(17020, getSecondFloorY() - 360, new Bird());
	addSprite(17800, getFirstFloorY() - b.getBodySize().height - 120, new Bird());
	addSprite(18000, getFirstFloorY() - b.getBodySize().height - 180, new Bird());
	addSprite(18200, getFirstFloorY() - b.getBodySize().height - 240, new Bird());
        // Leyna
        setBackground( ImageStateUtils.getImage("assets/background/background_1.png") );
        // Cathy
        sortByX();
        
    }
}
