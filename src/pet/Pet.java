package pet;

import state.ImageRenderer;
import state.State;
import state.AnimState;
import model.HealthPointSprite;
import model.SpriteShape; 

import java.awt.*;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

//還有其他要import的記得import

//楊鈞安

public class Pet extends HealthPointSprite {
    //public static final int Pet_HP = 500;
    private int Pet_HP;
    private int jump_velocity;// 
    private Spriteshape shape;
    public static int now_velocity;

    public Pet(int location_X,int location_Y, int Pet_HP){
        this.location_X = location_X;
        this.location_Y = location_Y;
        this.Pet_HP = Pet_HP;
        /* shape = (size , body_offset, body_size) 
        ** size = 圖片的大小
        ** body_offset = 身體的左上角
        ** body_size = 身體的長寬範圍
        */
        shape = new Spriteshape(new Dimension(146, 176),
                new Dimension(33, 38), new Dimension(66, 105)); /// shape can be revise
        ImageRenderer imageRenderer = new Pet_Image_renderer(this);
        //State fly = new Fly()...
        //State slide = 
        /*State idle = new WaitingPerFrame(4,
                new Idle(imageStatesFromFolder("assets/idle", imageRenderer)));
        State walking = new WaitingPerFrame(2,
                new Walking(this, imageStatesFromFolder("assets/walking", imageRenderer)));
        State attacking = new WaitingPerFrame(3,
                new Attacking(this, fsm, imageStatesFromFolder("assets/attack", imageRenderer)));

        fsm.setInitialState(idle);
        fsm.addTransition(from(idle).when(WALK).to(walking));
        fsm.addTransition(from(walking).when(STOP).to(idle));
        fsm.addTransition(from(idle).when(ATTACK).to(attacking));
        fsm.addTransition(from(walking).when(ATTACK).to(attacking)); */
    }

    // healthPointbar 已經寫好了(onDamage)
    // while velocity == -jump_velocity , trigger RUN

    /*public int getVy(){  //?
        return now_velocity
    }*/

    @Override
    public void render(Graphics g) {  
        super.render(g); // healthbar render 
    }
    @Override
    public void collideWith(){} 
    // collide with
    @Override
    public Point getLocation() {
        return location;
    }
    @Override
    public Rectangle getRange() {
        return new Rectangle(location, shape.size);
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

