///楊鈞安 6/16
package pet;
import state.State;
import state.*;
import java.lang.*;

public class PetStateControl{
    //private int lower_speed = 5; // 用前面的速度
    private int stopping_speed = 0;
    private State state;
    public PetStateControl(State nowstate){
        this.state = nowstate;
    }
    public State update(Pet s,State nowstate){  
        //this.state = nowstate;
        this.state = nowstate.getNext(s); // 傳入 pet s
        return this.state;
    }
    public int update_speed(int speed){
        if(this.state instanceof Stop){
            return this.stopping_speed;
        }
        else if(this.state instanceof Unstoppable){
            return speed/3;
        }
        else{
            return speed;
        }
    }

}
