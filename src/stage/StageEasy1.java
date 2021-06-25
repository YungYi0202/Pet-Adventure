package stage;

import model.Stage;
import model.Position;
import model.Sprite;
import objects.Ground;

//陳咏誼
public class StageEasy1 extends Stage{
    public StageEasy1(){
        setSpeed(1);
        //TODO: 把該有的Sprite加進去
        for(int i = 0 ; i < 100; i++){
            addSprite(i * 1000 , this.getFirstFloorY() , new Ground());
        }
        
    }
}
