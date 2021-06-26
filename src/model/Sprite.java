package model;

import java.awt.*;

/**
 * @author - Yung-Yi Chen, Andy Young
 */

import model.SpriteShape; 
//import views.GameView;

public abstract class Sprite {
    protected World world;
    protected Point location = new Point();
    protected SpriteShape shape = new SpriteShape(new Dimension(), new Dimension(), new Dimension() ); 

    public abstract void update();

    public abstract void render(Graphics g);

    //public abstract void onDamaged(Rectangle damageArea, int damage);

    //陳咏誼加的，讓PetCollisionHandler可以用
    public abstract void collideWith(Sprite sprite);
    

    public World getWorld() {
        return world;
    }

    //被加進World的時候，會被World設好
    public void setWorld(World world) {
        this.world = world;
    }

    
    public Point getLocation() {
        return location;
    }

    //從Stage用getNewSprites加進world之後會被設定好
    public void setLocation(Point location) {
        this.location = location;
    }

    public void decreaseLocationX(int x){
        //System.out.printf("Before: Ground Location: (x: %d, y: %d) speed:%d\n", location.x, location.y, x);
        this.location.move(this.location.x - x, this.location.y);
        //System.out.printf("After: Ground Location: (x: %d, y: %d) speed:%d\n", location.x, location.y, x);
    }
    /// 楊鈞安 adding 
    public void increaseLocationY(int y){
        //System.out.printf("Before: Ground Location: (x: %d, y: %d) speed:%d\n", location.x, location.y, x);
        this.location.move(this.location.x, this.location.y + y);
        //System.out.printf("After: Ground Location: (x: %d, y: %d) speed:%d\n", location.x, location.y, x);
    }
    public void decreaseLocationY(int y){
        //System.out.printf("Before: Ground Location: (x: %d, y: %d) speed:%d\n", location.x, location.y, x);
        this.location.move(this.location.x, this.location.y - y);
        //System.out.printf("After: Ground Location: (x: %d, y: %d) speed:%d\n", location.x, location.y, x);
    }

    public int getX() {
        return getRange().x;
    }

    public int getY() {
        return getRange().y;
    }

    //陳咏誼 2021/06/14新增
    public void setShape(Dimension size, Dimension bodyOffset, Dimension bodySize){
        this.shape.setSize(size);
        this.shape.setBodyOffset(bodyOffset);
        this.shape.setBodySize(bodySize);
    }

    public SpriteShape getShape(){return this.shape;}

    public Rectangle getRange() {
        return new Rectangle(this.location, this.shape.size);
    }

    public Dimension getBodyOffset() {
        return this.shape.bodyOffset;
    }
    public Dimension getBodySize() {
        return this.shape.bodySize;
    }
    public boolean isOutOfWindow(){
        if(this.getX() + this.getBodyOffset().width + this.getBodySize().width < 0) return true;
        return false;
    }
    
    //陳咏誼 2021/06/14新增 end

    public Rectangle getBody() {
        return getArea(getBodyOffset(), getBodySize());
    }
    
    // 楊鈞安 2021/06/13 目前不需要用到
    // public int getbody_right(){
    //     bodyoffset = getBodyOffset();
    //     bodysize = getBodySize();
    //     return bodyoffset.width + location.x + bodysize.width;
    // }
    // public int getbody_bottom(){
    //     bodyoffset = getBodyOffset();
    //     bodysize = getBodySize();
    //     return location.y + bodyoffset.height + bodysize.height;
    // }
    // public int getbody_head(){
    //     bodyoffset = getBodyOffset();
    //     bodysize = getBodySize();
    //     return location.y + bodyoffset.height;
    // }
    // public int getbody_left(){
    //     bodyoffset = getBodyOffset();
    //     bodysize = getBodySize();
    //     return location.x + bodyoffset.width;
    // }

    public Rectangle getArea(Dimension offset, Dimension bodysize) {
        //TODO: 還沒看懂，要請楊鈞安確認

        return new Rectangle(new Point(offset.width + location.x,  
                offset.height + location.y), bodysize);
    }

    public boolean isAlive() {
        return world != null;
    }
}
