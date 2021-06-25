package pet;
import state.*;
import state.ImageRenderer;
import state.State;
import model.Sprite;
import model.HealthPointSprite;
import model.SpriteShape; 
import model.World;
import java.awt.*;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import utils.ImageStateUtils;
import java.awt.image.BufferedImage;
import java.lang.*;
//還有其他要import的記得import

//楊鈞安

public class Pet extends HealthPointSprite {
    //private int time_tick = 0;
    private int score = 0;
    private int damage = 100;
    private int Pet_HP;
    private int jump_velocity;
    private State nowstate;
    private int speed; // x方向, normal = 100
    private int nowVy = 0;
    private final PetStateControl controller;
    private BufferedImage image;
    public Pet(int Pet_HP,int jump_velocity){  // 已改成直接傳入
        super(Pet_HP); // 創建 Healthpointbar
        /* shape = (size , body_offset, body_size) 
        ** size = 圖片的大小
        ** body_offset = 身體的左上角
        ** body_size = 身體的長寬範圍
        */
        this.Pet_HP = Pet_HP;
        this.jump_velocity = jump_velocity;
        State running = new Run();
        this.nowstate = running;
        this.image = this.nowstate.getImage(); 
        setShape(new Dimension(image.getWidth(), image.getHeight()), new Dimension(0, 0), new Dimension(image.getWidth(), image.getHeight()));
        //setShape(new Dimension(146, 176),
                //new Dimension(33, 38), new Dimension(66, 105) ); /// shape can be revise
        controller = new PetStateControl(this.nowstate);
    }
    
    /// 取用任何 pet 資訊的地方
    public int getVy(){  
        return this.nowVy;  // Vy 我會在state_control 中修改
    }
    public void setVy(int num){
        this.nowVy = num;
    }
    public void setScore(int score){
        this.score = score;
    }
    public int getScore(){
        return this.score;
    }
    public void addScore(int amount){
        this.score += amount;
    }
    public int getHp(){
        return this.Pet_HP;
    }
    public void costHp(int amount){ //傳入要增減的血量
        this.Pet_HP -= amount;
        onDamaged(amount);
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

    public void jump(){
        this.nowVy = jump_velocity;
    }
    public void Vy_update(){
        if((this.nowstate instanceof Jump || this.nowstate instanceof UnstoppableJump) && this.nowVy >= 0){
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
        System.out.println("pet update");
        Vy_update();
        this.nowstate = controller.update(this);
        this.speed = controller.update_speed(this.speed);
    }
    @Override
    public void render(Graphics g) {  
<<<<<<< HEAD
        System.out.println("do pet render");
=======
>>>>>>> ecad6a2d001ad4c74ad7b0997a85972b3cc5c8c3
        super.render(g); // healthbar render 
        //if (isRemoved == false) {
        //System.out.println("do pet render");
        Rectangle range = this.getRange();
        this.image = this.nowstate.getImage();
        setShape(new Dimension(image.getWidth(), image.getHeight()), new Dimension(0, 0), new Dimension(image.getWidth(), image.getHeight()));
        g.drawImage(this.image, range.x, range.y, range.width, range.height, null);
        //}
    }
    @Override
    public void collideWith(Sprite sprite){
        return;
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

