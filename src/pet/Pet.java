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
import java.util.*;
import views.GameView;
//還有其他要import的記得import

//楊鈞安

public class Pet extends HealthPointSprite {
    //private int time_tick = 0;
    private int score = 0;
    private int damage = 100;
    private int Pet_HP;
    private int jump_velocity;
    private State nowstate;
    private int normalSpeed; // x方向, normal = 100
    private int nowSpeed;
    private int nowVy = 0;
    private int gravity = 2;
    private final PetStateControl controller;
    private BufferedImage image;
    public ArrayList<String> propList = new ArrayList<String>();
    private int bag_volume = 2;
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
        controller = new PetStateControl(this.nowstate);
    }
    
    /// 取用任何 pet 資訊的地方 (給別人用的)
    public void addProps(String prop){
        if(propList.size() < this.bag_volume){
            propList.add(prop);
        }
    }
    public int getnormalVy(){
        return this.jump_velocity;
    }
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
        this.normalSpeed = speed;
        this.nowSpeed = speed;
    }
    public int getSpeed(){  //x方向 
        return this.nowSpeed;
    }
    /////

    public void jump(){
        if(this.nowstate instanceof Run){
            System.out.println("call jump");
            this.nowVy = -jump_velocity;
        }
    }
    public void Vy_update(){ // gravity
        if( this.nowstate instanceof Jump || this.nowstate instanceof UnstoppableJump ){
            this.nowVy += this.gravity;
            
        }
    }
    @Override 
    public void update(){ 
        Vy_update();
        this.nowstate = controller.update(this,this.nowstate);
        System.out.println(this.nowstate); 
        this.increaseLocationY(this.nowVy);
        //System.out.println(this.nowVy);
        this.nowSpeed = controller.update_speed(this.normalSpeed);
    }

    @Override
    public void render(Graphics g){

        super.render(g); // healthbar render
        ///// score render
        Font fnt0 = new Font("ariel", Font.BOLD, 20);
        g.setFont(fnt0);
        g.setColor(Color.black); 
        String show_score = "score: " + String.valueOf(this.score);
        g.drawString(show_score, (GameView.WIDTH/10*8), GameView.HEIGHT/12);
        /////
        Rectangle range = this.getRange();
        this.image = this.nowstate.getImage();
        setShape(new Dimension(image.getWidth(), image.getHeight()), new Dimension(0, 0), new Dimension(image.getWidth(), image.getHeight()));
        g.drawImage(this.image, range.x, range.y, range.width, range.height, null);


    }
    private void delay(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

