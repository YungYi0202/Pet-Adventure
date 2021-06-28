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
    public String petName;
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
    private int speedRemainTime;
    private int scoreRender;
    private int scoreRenderRemainTime = 0;
    private boolean isdead;
    private boolean ending;

    public Pet(int Pet_HP,int jump_velocity,String petName){  // 已改成直接傳入
        super(Pet_HP); // 創建 Healthpointbar
        this.petName = petName;
        this.Pet_HP = Pet_HP;
        this.jump_velocity = jump_velocity;
        this.isdead = false;
        this.ending = false;
        State running = new Run(this.petName);
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
    public void addScoreWithRender(int amount){
        this.score += amount;
        this.scoreRenderRemainTime = 40;
        this.scoreRender = amount;
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
    public void setNowSpeed(int speed){
        //this.normalSpeed = speed;
        this.nowSpeed = speed;
    }
    public int getSpeed(){  //x方向 
        return this.nowSpeed;
    }
    public int getNormalSpeed(){
        return this.normalSpeed;
    }
    public void setSpeedAndRemainTime(int speed, int remainTime){
        // this.normalSpeed = speed;
        this.nowSpeed = speed;
        this.speedRemainTime = remainTime;
    }
    public void setPropState(){

    }
    public void set_isDead(){
        this.isdead = true;
    }
    public boolean isDead(){
        return this.isdead;
    }
    public void setEnding(){
        this.ending = true;
    }
    public boolean arriveEnd(){
        return this.ending;
    }
    public void runToEnd(){
        this.nowstate = new RunToEnd(this,this.petName);
    }
    ///////
    public boolean toEnd(){
        if(getLocation().x >= GameView.WIDTH/10*7){
            return true;
        }
        return false;
    }
    public void jump(){  
        if(this.nowstate instanceof Run || this.nowstate instanceof UnstoppableRun){
            this.nowVy = -jump_velocity;
        }
    }
    public void slide(){ 
        if(this.nowstate instanceof Run){
            setnormalY();
            this.nowstate = new Slide(this.petName);
            this.increaseLocationY(50); // 稍微下降
        }
        else if(this.nowstate instanceof Slide){
            this.nowstate = new Slide(this.petName);
        }
        else if(this.nowstate instanceof UnstoppableRun){
            setnormalY();
            this.nowstate = new UnstoppableSlide(this.nowstate.remainTime , this.petName);
            this.increaseLocationY(50);
        } 
        else if(this.nowstate instanceof UnstoppableSlide){
            this.nowstate = new UnstoppableSlide(this.nowstate.remainTime , this.petName);
        }
    }
    public void Vy_update(){ // gravity
        this.nowVy += this.gravity;
    }
    @Override 
    public void update(){ 
        if(this.nowstate instanceof RunToEnd){
            Vy_update();
            this.increaseLocationY(this.nowVy);
            this.nowstate = controller.update(this,this.nowstate); 
            //this.increaseLocationX(this.normalSpeed);

        }
        else if(this.Pet_HP <= 0){
            Vy_update();
            this.increaseLocationY(this.nowVy);
            if( !(this.nowstate instanceof Dead)){
                //System.out.println("herer ff");
                //System.out.println(this.nowstate);
                this.nowstate = new Dead(this.petName);
            }
            this.nowSpeed = 0;
            this.nowstate = controller.update(this,this.nowstate); 
            /// control menu
        }
        else{
            Vy_update();
            this.nowstate = controller.update(this,this.nowstate); 
            this.increaseLocationY(this.nowVy);
            this.nowSpeed = controller.update_speed(this.normalSpeed);
        }
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
        // draw pet
        Rectangle range = this.getRange();
        this.image = this.nowstate.getImage();
        setShape(new Dimension(image.getWidth(), image.getHeight()), new Dimension(0, 0), new Dimension(image.getWidth(), image.getHeight()));
        g.drawImage(this.image, range.x, range.y, range.width, range.height, null);

        // score render
        if(scoreRenderRemainTime > 0){
            g.setFont(fnt0);
            g.setColor(Color.black); 
            g.drawString("+" + String.valueOf(scoreRender), range.x + range.width/2 - 10 , range.y - 20);
            scoreRenderRemainTime--;
        }
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

/* shape = (size , body_offset, body_size) 
        ** size = 圖片的大小
        ** body_offset = 身體的左上角
        ** body_size = 身體的長寬範圍
        */
