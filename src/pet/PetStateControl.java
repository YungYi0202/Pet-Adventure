package pet;

import state.State;
import state.*;

import java.lang.*;

/**
 * @author - Andy young 
 */

public class PetStateControl{
    private int lowerSpeedTime = 5;
    private int stopping_speed = 0;
    private int increasingSpeed = 0;
    private State state;
    public PetStateControl(State nowstate){
        this.state = nowstate;
    }
    public State update(Pet s,State nowstate){  
        this.state = nowstate.getNext(s); // 傳入 pet s
        return this.state;
    }
    public int update_speed(Pet s, int speed){
        if(this.state instanceof Stop){
            return this.stopping_speed;
        }
        else if(this.state instanceof Unstoppable){ // 撞到
            if(increasingSpeed < speed){
                if(lowerSpeedTime > 0){
                    lowerSpeedTime--;
                    increasingSpeed = speed/3;
                }
                else{
                    increasingSpeed += 1;
                }
            }
            return increasingSpeed;
        }
        else{
            lowerSpeedTime = 5;
            increasingSpeed = 0;
            return speed;
        }
    }
    public PropState propStateUpdate(Pet s, PropState nowPropState){
        if(nowPropState != null){
            return nowPropState.getNext(s);
        }
        return null;
    }

}
