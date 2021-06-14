/////    大改中先別看
public class Pet_state_control(){
    public static tick = 0;
    private State state;
    private ImageState fly_image = new ImageFly();
    public enum Event {
        RUN, Jump, Stop, UnstoppableFly, UnstoppableRun
    }
    public Pet_state_control(State nowstate){
        this.state = nowstate;
    }
    public void tigger(){
        fly_image.start();
        this.state.getNext(s);
    }
    public void update( boolean colliding,int velocity){

        if(colliding){
            this.state.getCollide();
        }
        else{
            this.state.getNext(s); // 傳入 pet s
        }
        
    }

}