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
	addSprite(pos - 200 + 30, getFirstFloorY() - 300, new Candy());
	addSprite(pos       + 30, getFirstFloorY() - 380, new Candy());
	addSprite(pos + 200 + 30, getFirstFloorY() - 300, new Candy());
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
	//Bird b = new Bird();
	//addSprite((i+1) * 1000, getFirstFloorY() - b.getBodySize().height - 120, b);
	/*
	addSpriteToFirstFloor(4500, serial.get(0));
        addSpriteToSecondFloor(8000, serial.get(1));
        addSpriteToSecondFloor(12000, serial.get(2));
        addSpriteToFirstFloor(15500, serial.get(3));
	*/

	for (int i = 0; i < 10; ++i) 
	    addSprite(i * 1000, getFirstFloorY(), new Ground());
	addHydrantPattern(2000);
	for (int i = 0; i < 3; ++i)
	    addSpriteToFirstFloor(2400 + 200 * i, new Candy());
	addSprite(3000, getFirstFloorY(), new Hole(1));
	addSprite(3800, getFirstFloorY(), new Hole(3));
	addSecondFloor(2700, 2);
	//	for (int i = 0; i < 10; ++i)
	//    addSprite(4600 + i * 1000, getFirstFloorY(), new Ground());

        // Leyna
        setBackground( ImageStateUtils.getImage("assets/background/background_1.png") );
        // Cathy
        sortByX();
        
    }
}
