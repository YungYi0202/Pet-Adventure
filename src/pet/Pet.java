package pet;

import state.ImageRenderer;
import state.State;
import state.AnimState;
import model.HealthPointSprite;
import model.SpriteShape; 
import model.World;
import java.awt.*;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

//還有其他要import的記得import

//楊鈞安

public class Pet extends HealthPointSprite {
    //private int time_tick = 0;
    private int score = 0;
    private int damage = 100;
    private int Pet_HP;
    private int jump_velocity;
    private State nowstate;
    private int speed; // x方向, normal = 10
    private int nowVy = 0;
    private final Pet_state_control controler;
    public Pet(int Pet_HP,int jump_velocity){  // 已改成直接傳入
        /* shape = (size , body_offset, body_size) 
        ** size = 圖片的大小
        ** body_offset = 身體的左上角
        ** body_size = 身體的長寬範圍
        */
        this.Pet_HP = Pet_HP;
        this.jump_velocity = jump_velocity;
        this.shape = setShape(new Dimension(146, 176),
                new Dimension(33, 38), new Dimension(66, 105) ); /// shape can be revise
        State running = new Run();
        this.nowstate = running; 
        controler = new Pet_state_control(this.nowstate);
    }
 
    /// 取用任何 pet 資訊的地方
    public int getVy(){  
        return this.nowVy;  // Vy 我會在state_control 中修改
    }
    public void setScore(int score){
        this.score = score;
    }
    public int getScore(){
        return this.score;
    }
    public int getHp(){
        return this.Pet_HP;
    }
    public void costHp(int amount){ //傳入要增減的血量
        this.hp -= amount;
    }
    public void setState(State a){
        this.nowstate = a;
    }
    public State getState(){
        return this.nowstate;
    }
    public void setSpeed(int speed){
        this.speed = speed;
    }
    public int getSpeed(){  //x方向 
        return this.speed;
    }
    ////////////////
    /// 
    public void jump(){
        this.nowVy = jump_velocity;
    }
    public void Vy_update(){
        if((this.nowstate instanceof Jump || this.nowstate instanceof UnstoppableFly) && this.nowVy >= 0){
            if(this.nowVy > 9.8){
                this.nowVy -= 9.8;
            }
            else{
                this.nowVy = 0;
            }
        }
    }
    @Override 
    public void update(){ 
        Vy_update();
        this.nowstate = controler.update(this);
        this.speed = controler.update_speed(this.speed);
    }
    @Override
    public void render(Graphics g) {  
        super.render(g); // healthbar render 
    }
    @Override
    public Rectangle getRange() {
        return new Rectangle(this.getLocation(), shape.size);
    }
    @Override
    public Dimension getBodyOffset() {
        return shape.bodyOffset;
    }
    @Override
    public Dimension getBodySize() {
        return shape.bodySize;
    }
}

