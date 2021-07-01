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
    public int normalY;
    protected SpriteShape shape = new SpriteShape(new Dimension(), new Dimension(), new Dimension() ); 

    public abstract void update();

    public abstract void render(Graphics g);

    //陳咏誼加的，讓PetCollisionHandler可以用
    public abstract void collideWith(Sprite sprite);
    

    public World getWorld() {
        return world;
    }

    //被加進World的時候，會被World設好
    public void setWorld(World world) {
        this.world = world;
    }

    public void setnormalY(){
        this.normalY = this.location.y;
    }
    public void backToNormalLocation(){
        this.location.move(this.location.x, this.normalY);
    }

    public Point getLocation() {
        return location;
    }

    //從Stage用getNewSprites加進world之後會被設定好
    public void setLocation(Point location) {
        this.location = location;
    }
    public void increaseLocationX(int x){
        this.location.move(this.location.x + x, this.location.y);
    }
    public void decreaseLocationX(int x){
        this.location.move(this.location.x - x, this.location.y);
    }
    /// 楊鈞安 adding 
    public void increaseLocationY(int y){
        this.location.move(this.location.x, this.location.y + y);
    }
    public void decreaseLocationY(int y){
        this.location.move(this.location.x, this.location.y - y);
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
    
    public Rectangle getArea(Dimension offset, Dimension bodysize) {
        return new Rectangle(new Point(offset.width + location.x,  
                offset.height + location.y), bodysize);
    }

}
