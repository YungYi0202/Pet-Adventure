///楊鈞安 6/16
package pet;
import state.State;
import state.*;

public class PetStateControl{
    private int lower_speed = 5; // 用前面的速度
    private int stopping_speed = 0;
    private State state;
    public PetStateControl(State nowstate){
        this.state = nowstate;
    }
    public State update(Pet s){  
        this.state = this.state.getNext(s); // 傳入 pet s
        return this.state;
    }
    public int update_speed(int speed){
        if(this.state instanceof Unstoppable){
            return this.lower_speed;
        }
        else if(this.state instanceof Stop){
            return this.stopping_speed;
        }
        else{
            return speed;
        }
    }

}