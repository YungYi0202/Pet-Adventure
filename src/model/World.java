package model;

import java.awt.*;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import pet.Pet;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;

import views.GameView;

//陳咏誼

public class World {
    private final List<Sprite> sprites = new CopyOnWriteArrayList<Sprite>();
    private final CollisionHandler collisionHandler;
    
    //以下是陳咏誼加的，可改
    private Stage stage;
    private int cur_abs_x = GameView.WIDTH;
    private List<Pet> players = new CopyOnWriteArrayList<Pet>();;


    public World(CollisionHandler collisionHandler, Stage stage ,Sprite... sprites) {
        this.collisionHandler = collisionHandler;
        addSprites(sprites);
        this.stage = stage;
        setPlayers(sprites);
        addSprites(stage.getNewSprites(cur_abs_x));
        // 1P掌握視窗速度
        this.players.get(0).setSpeed(this.stage.getSpeed());
    }

    public void setPlayers(Sprite... sprites){
        for(Sprite sprite: sprites){
            if( sprite instanceof Pet){
                players.add( (Pet)sprite );
            }
        }
    }

    public void getSpeed(){return this.players.get(0).getSpeed();}
    public void getJumpFactor(){return this.stage.getJumpFactor();}

    public void update() {
        for (Sprite sprite : sprites) {
            sprite.update();
            //把沒入螢幕範圍、應該要消失的sprite拿掉（例如糖果被吃掉）
            if(sprite.isOutOfWindow()){
                removeSprite(sprite);
            }
        }

        //Collision Detection
        //不確定有沒有錯
        for(Pet from: players){
            Rectangle body = from.getBody();
            for (Sprite to : sprites) { 
                if (to != from && body.intersects(to.getBody())) {
                    collisionHandler.handle(from, to);
                }
            }
        }

        // 加入新Sprites
        addSprites(stage.getNewSprites(cur_abs_x));
    }

    public void addSprites(List<Sprite> newSprites) {
        for(Sprite sprite: newSprites){
            addSprite(sprite);
        }
    }

    public void addSprites(Sprite... sprites) {
        stream(sprites).forEach(this::addSprite);
    }

    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
        sprite.setWorld(this);
    }

    public void removeSprite(Sprite sprite) {
        sprites.remove(sprite);
        sprite.setWorld(null);
    }

    public Collection<Sprite> getSprites(Rectangle area) {
        return sprites.stream()
                .filter(s -> area.intersects(s.getBody()))
                .collect(toSet());
    }

    public List<Sprite> getSprites() {
        return sprites;
    }

    // Actually, directly couple your model with the class "java.awt.Graphics" is not a good design
    // If you want to decouple them, create an interface that encapsulates the variation of the Graphics.
    public void render(Graphics g) {
        for (Sprite sprite : sprites) {
            sprite.render(g);
        }
    }
}
