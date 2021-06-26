package stage;

import model.Stage;
import model.Position;
import model.Sprite;
import objects.Ground;

/**
 * @author - Yung-Yi Chen
 */
public class StageEasy1 extends Stage{
    public StageEasy1(){
        setSpeed(5);
        //TODO: 把該有的Sprite加進去
        for(int i = 0 ; i < 50; i++){
            addSprite(i * 1000 , this.getFirstFloorY() , new Ground());
        }
        
    }
}
