package model;

//陳咏誼
public class Position {
    public Position(int x, int y, Sprite sprite){
        this.abs_posi = new Point(x, y);
        this.sprite = sprite;
    }
    private Point abs_posi;
    private Sprite sprite;
    public int getX(){
        return (int)abs_posi.getX();
    }
    public int getY(){
        return (int)abs_posi.getY();
    }
    public Sprite getSprite(){
        return sprite;
    }
}