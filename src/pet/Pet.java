package pet;

import state.*;
import state.State;
import state.PropState;
import state.ImageEnd;
import model.Sprite;
import model.HealthPointSprite;
import model.SpriteShape; 
import model.World;
import utils.ImageStateUtils;
import views.GameView;
import state.ImageCharge;
import media.AudioPlayer;
import menu.Menu;

import java.awt.*;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.awt.image.BufferedImage;
import java.lang.*;
import java.util.*;

/**
 * @author - Andy young 
 */

public class Pet extends HealthPointSprite {
    private int score = 0;
    private int damage = 100;
    private int Pet_HP;
    public String petName;
    private int jump_velocity;
    private State nowstate;
    private PropState nowPropState = null;
    private int normalSpeed; // x方向, normal = 100
    private int nowSpeed;
    private int nowVy = 0;
    private int gravity = 2;
    private final PetStateControl controller;
    private BufferedImage image;
    private BufferedImage propimage;
    public ArrayList<String> propList = new ArrayList<String>();
    private int bag_volume = 2;
    private int speedRemainTime;
    private int scoreRender;
    private int scoreRenderRemainTime = 0;
    private boolean isdead;
    private boolean ending;
    private int slideDecreaseY = 70;
    private int endJump = 0;
    private Font fnt0 = new Font("ariel", Font.BOLD, 30);
    public static final String AUDIO_ADDSCORE = "addScore";
    public static final String AUDIO_ADDPROP = "addProp";
    public static final String AUDIO_USEPROP = "useProp";
    public static final String AUDIO_COSTHP = "costHp";
    public static final String AUDIO_PUPPYDIE = "puppyDie";
    public static final String AUDIO_KITTENDIE = "kittenDie";

    public Pet(int Pet_HP,int jump_velocity,String petName){  // 已改成直接傳入
        super(Pet_HP); // 創建 Healthpointbar
        this.petName = petName;
        this.Pet_HP = Pet_HP;
        this.jump_velocity = jump_velocity;
        this.isdead = false;
        this.ending = false;
        State running = new Run(this.petName);
        this.nowstate = running;
        this.nowPropState = null;
        this.image = this.nowstate.getImage(); 
        setShape(new Dimension(image.getWidth(), image.getHeight()), new Dimension(0, 0), new Dimension(image.getWidth(), image.getHeight()));
        controller = new PetStateControl(this.nowstate);
    }
    
    /// 取用任何 pet 資訊的地方 (給別人用的)
    public void setGravity(int amount){
        this.gravity = amount;
    }
    public int getSlideY(){
        return this.slideDecreaseY;
    }
    public void addProps(String prop){
        AudioPlayer.playSounds(AUDIO_ADDPROP);
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
        if(amount > 0) AudioPlayer.playSounds(AUDIO_ADDSCORE);
    }
    public void addScoreWithRender(int amount){
        if(this.nowPropState instanceof DoublePoint){
            addScore(amount*2);
            this.scoreRender = amount*2;
        }
        else{
            addScore(amount);
            this.scoreRender = amount;
        }
        this.scoreRenderRemainTime = 40;
    }
    public int getHp(){
        return this.Pet_HP;
    }
    public void costHp(int amount){ //傳入要增減的血量
        if(amount > 0 && nowstate instanceof Dead == false) AudioPlayer.playSounds(AUDIO_COSTHP);
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
    public void setPropState(PropState state){
        this.nowPropState = state;
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
    public PropState getNowPropState(){
        return this.nowPropState;
    }

    public boolean toEnd(){
        if(getLocation().x >= GameView.WIDTH/2-70){ 
            return true;
        }
        return false;
    }
    public void useProp(){
        // revise 
        if(propList.size() > 0 && !(this.nowstate instanceof RunToEnd)){
            AudioPlayer.playSounds(AUDIO_USEPROP);
            String nowProp = propList.get(0);
            propList.remove(0);
            if(nowProp.equals("ChargeCan")){
                this.nowPropState = new Charge(this.petName);
                if(this.nowstate instanceof Slide || this.nowstate instanceof UnstoppableSlide){
                    decreaseLocationY(getSlideY());
                }
                this.nowstate = new UnstoppableRun(150,this,this.petName);
            }
            if(nowProp.equals("DoublePoint")){
                this.nowPropState = new DoublePoint(this.petName);
            }
            if(nowProp.equals("Shield")){
                this.nowPropState = new Shield(this.petName);
            }
        }
    }
    public void jump(){  
        if(this.nowstate instanceof Run || this.nowstate instanceof UnstoppableRun){
            this.nowVy = -jump_velocity;
        }
    }
    public void slide(){ 
        if(this.nowstate instanceof Run){
            this.nowstate = new Slide(this.petName,false);
            this.increaseLocationY(getSlideY()); // 稍微下降
        }
        else if(this.nowstate instanceof Slide){
            this.nowstate = new Slide(this.petName,true);
        }
        else if(this.nowstate instanceof UnstoppableRun){
            this.nowstate = new UnstoppableSlide(this.nowstate.remainTime , this.petName,false);
            this.increaseLocationY(getSlideY());
        } 
        else if(this.nowstate instanceof UnstoppableSlide){
            this.nowstate = new UnstoppableSlide(this.nowstate.remainTime , this.petName,true);
        }
    }
    public void Vy_update(){ // gravity
        this.nowVy += this.gravity;
    }
    @Override 
    public void update(){ 
        if(this.nowstate instanceof RunToEnd){
            if(this.nowstate.getImageState() instanceof ImageEnd && this.endJump == 0){
                this.nowVy = -jump_velocity+5;
                this.endJump = 1;
            }
            Vy_update();
            this.increaseLocationY(this.nowVy);
            this.nowstate = controller.update(this,this.nowstate);
            this.nowPropState = null; 
        }
        else if(this.Pet_HP <= 0){
            Vy_update();
            this.increaseLocationY(this.nowVy);
            if( !(this.nowstate instanceof Dead)){

                setLocation(new Point(getLocation().x , getLocation().y + 45 ));
                this.nowstate = new Dead(this.petName);
                if(petName == Menu.PUPPY) AudioPlayer.playSounds(AUDIO_PUPPYDIE);
                if(petName == Menu.KITTEN) AudioPlayer.playSounds(AUDIO_KITTENDIE);
            }
            this.nowSpeed = 0;
            this.nowstate = controller.update(this,this.nowstate); 
            this.nowPropState = null;
        }
        else{
            Vy_update();
            this.nowstate = controller.update(this,this.nowstate); 
            this.increaseLocationY(this.nowVy);
            this.nowSpeed = controller.update_speed(this,this.normalSpeed);
            if(this.nowstate instanceof Stop && this.nowPropState instanceof Shield){
                this.nowPropState = null;
            }
            else{
                this.nowPropState = controller.propStateUpdate(this,this.nowPropState);
            }
            
        }
    }

    @Override
    public void render(Graphics g){

        super.render(g); // healthbar render
        // draw pet
        Rectangle range = this.getRange();
        this.image = this.nowstate.getImage();
        if(this.nowstate instanceof Unstoppable && !(this.nowstate instanceof Stop)){
            float opacityRate = (float)0.5;
            this.image = ImageStateUtils.opacity(this.image, opacityRate);
        }
        setShape(new Dimension(image.getWidth(), image.getHeight()), new Dimension(image.getWidth() / 2, 0), new Dimension(image.getWidth() / 2, image.getHeight()));
        g.drawImage(this.image, range.x, range.y, range.width, range.height, null);

        // draw prop
        if(this.nowPropState != null){
            Rectangle proprange = this.nowPropState.getPropRange(this);
            //Rectangle proprange = new Rectangle( getX()-60, getY()+20, (int)getRange().getWidth()/2, (int)getRange().getHeight()/3*2);
            if(proprange != null){
                this.propimage = this.nowPropState.getImage();
                g.drawImage(this.propimage, proprange.x, proprange.y, proprange.width, proprange.height, null);
            } 
        }
        // proplist render
        for(int i = 0;i < this.propList.size();i++){
            ImageState Prop = null;
            if(propList.get(i).equals("ChargeCan")){
                Prop = new ImageCharge(this.petName,true);
            }
            else if(propList.get(i).equals("DoublePoint")){
                Prop = new ImageDoublePoint(this.petName,true);
            }
            else if(propList.get(i).equals("Shield")){
                Prop = new ImageShield(this.petName,true);
            }
            if(Prop != null){
                Rectangle image_range = new Rectangle( GameView.WIDTH/12 + 100*i, GameView.HEIGHT/10, 70, 55);
                BufferedImage image_prop = Prop.getImage();
                g.drawImage(image_prop, image_range.x, image_range.y, image_range.width, image_range.height, null);
            }
        }

        // score render
        if(scoreRenderRemainTime > 0){
            g.setFont(fnt0);
            g.setColor(Color.black); 
            g.drawString("+" + String.valueOf(scoreRender), range.x + range.width/2 - 10 , range.y - 20);
            scoreRenderRemainTime--;
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
