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
    private static final int LENGTH = 42;
    private void addHydrantPattern(int pos) {
	addSpriteToFirstFloor(pos, new Hydrant());
	addSprite(pos - 250 + 30, getFirstFloorY() - 300, new Candy());
	addSprite(pos       + 30, getFirstFloorY() - 380, new Candy());
	addSprite(pos + 250 + 30, getFirstFloorY() - 300, new Candy());
    }
    private void addMiddlePattern(int pos) {
	addSpriteToFirstFloor(pos, new Hydrant());
	addSprite(pos + 20, getSecondFloorY() - 360, new Bird());
	addSprite(pos + 30, getFirstFloorY() - 380, new Candy());
    }
    private void addSecondFloor(int start, int count) {
	addSprite(start, getSecondFloorY(), new SecondFloor(1));
	for (int i = 1; i < count - 1; ++i)
	    addSprite(start + i * 1000, getSecondFloorY(), new SecondFloor(2));
	addSprite(start + (count - 1) * 1000, getSecondFloorY(), new SecondFloor(3));
    }
    
    public StageHard(){
        setSpeed(25);
	
	addFarground(0, 0, new Farground("assets/farground/farground_2.png"));
	addFarground(25000, 0, new Farground("assets/farground/farground_2.png"));
	
        SerialAlphabet serial = new SerialAlphabet("F", "O", "O1", "P");
        addSprite(serial.absLocation, serial);
	
	Bird b = new Bird();

	for (int i = 0; i < LENGTH; ++i) 
	    addSprite(i * 1000, getFirstFloorY(), new Ground());

	for (int i = 0; i < 3; ++i)
	    addSpriteToFirstFloor(1200 + 200 * i, new Candy());	
	addHydrantPattern(2000);
	for (int i = 0; i < 3; ++i)
	    addSpriteToFirstFloor(2400 + 200 * i, new Candy());
	addSprite(4000, getFirstFloorY(), new Hole(1));
	addSprite(4200, getFirstFloorY(), new Hole(2));
	addSprite(5000, getFirstFloorY(), new Hole(3));
	addSecondFloor(3700, 2);
	for (int i = 0; i < 5; ++i)
	    addSpriteToSecondFloor(4500 + 200 * i, new Candy());

	addSpriteToFirstFloor(7000, new DoubleCan());
	for (int i = 0; i < 8; ++i)
	    addSpriteToFirstFloor(7500 + 300 * i, new Hydrant());
	addSpriteToFirstFloor(6200, serial.get(0));
	addSpriteToFirstFloor(6000, new Candy());
	addSpriteToFirstFloor(6400, new Candy());
			      
	addSecondFloor(6600, 4);
	for (int i = 1; i < 8; ++i)
	    addSpriteToSecondFloor(7000 + 200 * i, new Candy());
	
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

	addMiddlePattern(17000);
	addSprite(17800, getFirstFloorY() - b.getBodySize().height - 120, new Bird());
	addSprite(18000, getFirstFloorY() - b.getBodySize().height - 180, new Bird());
	addSprite(18200, getFirstFloorY() - b.getBodySize().height - 240, new Bird());
	addMiddlePattern(19200);
	addSecondFloor(20500, 2);
	addSpriteToFirstFloor(20800, new ShieldCan());
	addSprite(21400, getFirstFloorY(), new Hole(1));
	addSprite(21600, getFirstFloorY(), new Hole(2));
	addSprite(22400, getFirstFloorY(), new Hole(2));
	addSprite(23200, getFirstFloorY(), new Hole(2));
	addSprite(24000, getFirstFloorY(), new Hole(3));
	addSprite(21550, getSecondFloorY() - 300, new Candy());
	addSprite(21800, getSecondFloorY() - 380, new Candy());
	addSprite(22050, getSecondFloorY() - 300, new Candy());
	addSprite(22550, getSecondFloorY() - 300, new Candy());
	addSprite(22800, getSecondFloorY() - 380, new Candy());
	addSprite(23050, getSecondFloorY() - 300, new Candy());
	addSecondFloor(23100, 2);

	addSprite(24000, getSecondFloorY() - b.getBodySize().height - 120, new Bird());
	addSprite(24200, getSecondFloorY() - b.getBodySize().height - 180, new Bird());
	addSprite(24400, getSecondFloorY() - b.getBodySize().height - 240, new Bird());

	addHydrantPattern(25400);
	addSpriteToFirstFloor(25900, new Candy());
	addHydrantPattern(26400);
	addSecondFloor(27200, 4);
	addSprite(27800, getFirstFloorY() - b.getBodySize().height - 120, new Bird());
	addSprite(28100, getFirstFloorY() - b.getBodySize().height - 120, new Bird());
	addSpriteToFirstFloor(28400, serial.get(2));
	addSprite(28800, getFirstFloorY(), new Hole(1));
	addSprite(29000, getFirstFloorY(), new Hole(2));
	addSprite(29800, getFirstFloorY(), new Hole(3));
	for (int i = 0; i < 6; ++i)
	    addSpriteToSecondFloor(29000 + 200 * i, new Candy());

	addSpriteToFirstFloor(32000, new ShieldCan());
	for (int i = 0; i < 11; ++i)
	    if (i == 5)
		addSpriteToFirstFloor(32400 + 200 * i, new Bird());
	    else
		addSpriteToFirstFloor(32400 + 200 * i, new Candy());
	addMiddlePattern(34400);
	addSecondFloor(35600, 2);
	addSpriteToFirstFloor(35500, new Hydrant());
	addSpriteToFirstFloor(36000, new DoubleCan());
	addSpriteToFirstFloor(36500, serial.get(3));
	for (int i = 0; i < 5; ++i)
	    addSpriteToSecondFloor(36800 + 200 * i, new Candy());
	
	
	for (int i = 0; i < 10; ++i)
	    addSpriteToFirstFloor(39000 + 200 * i, new Candy());

	addSpriteToFirstFloor(41700, new Owner());
	    
	
        // Leyna
        setBackground( ImageStateUtils.getImage("assets/background/background_2.png") );
        // Cathy
        sortByX();
        
    }
}
