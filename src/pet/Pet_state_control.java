///楊鈞安 6/16
public class Pet_state_control(){
    private int lower_speed = 50;
    private State state;
    public Pet_state_control(State nowstate){
        this.state = nowstate;
    }
    public State update(Pet s){  //also update speed
        this.state = this.state.getNext(s); // 傳入 pet s
        return this.state;
    }
    public int update_speed(int speed){
        if(this.state instanceof UnstoppableRun || this.state instanceof UnstoppableFly){
            return this.lower_speed;
        }
        else{
            return speed;
        }
    }

}