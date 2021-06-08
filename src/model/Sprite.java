package model;

import java.awt.*;

//楊鈞安、陳咏誼

public abstract class Sprite {
    protected World world;
    protected Point location = new Point();

    public abstract void update();

    public abstract void render(Graphics g);

    public abstract void onDamaged(Rectangle damageArea, int damage);

    //陳咏誼加的，讓PetCollisionHandler可以用
    public abstract void collideWith(Sprite sprite);

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public int getX() {
        return getRange().x;
    }

    public int getY() {
        return getRange().y;
    }

    public abstract Rectangle getRange();

    public abstract Dimension getBodyOffset();

    public abstract Dimension getBodySize();


    public Rectangle getBody() {
        return getArea(getBodyOffset(), getBodySize());
    }

    public Rectangle getArea(Dimension offset, Dimension size) {
        //TODO: 還沒看懂，要請楊鈞安確認

        return new Rectangle(new Point(offset.width + location.x,
                offset.height + location.y), size);
    }

    public boolean isAlive() {
        return world != null;
    }
}
