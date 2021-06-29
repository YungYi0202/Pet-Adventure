package model;

import java.awt.*;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import pet.Pet;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;

import views.GameView;

/**
 * @author - Yung-Yi Chen
 */

public class World {
    private final List<Sprite> sprites = new CopyOnWriteArrayList<Sprite>();//exclusive of players
    private final List<Sprite> fargrounds = new CopyOnWriteArrayList<Sprite>();
    private final CollisionHandler collisionHandler;
    
    //以下是陳咏誼加的，可改
    private Stage stage;
    private int cur_abs_x = GameView.WIDTH;
    //TODO:根據stage 設定結束
    private int end_abs_x;
    private List<Pet> players = new CopyOnWriteArrayList<Pet>();
    private Boolean gameOver = false;
    private String result = "WIN";

    public World(CollisionHandler collisionHandler, Stage stage ,Sprite... players) {
        this.collisionHandler = collisionHandler;
        this.stage = stage;
        for(Sprite player: players){
            player.setLocation(new Point( (int)(GameView.WIDTH * 0.1), stage.getFirstFloorY() - player.getBodySize().height ));
        }
        //addSprites(players);
        this.end_abs_x = stage.getEndAbsX();
        setPlayers(players);
        addSprites(stage.getNewSprites(cur_abs_x));
	
	    addFargrounds(stage.getNewFargrounds(cur_abs_x));  // Peng
	
	    //System.out.printf("World: sprites list size = %d\n", sprites.size());
        // 1P掌握視窗速度
        this.players.get(0).setSpeed(this.stage.getSpeed());
    }

    public void setPlayers(Sprite... spritesP){
        for(Sprite sprite: spritesP){
            if( sprite instanceof Pet){
                players.add( (Pet)sprite );
            }
        }
    }

    public int getSpeed(){return this.players.get(0).getSpeed();}

    public void update() {
        // Result is LOSE
        if(players.get(0).isDead()){
            this.result = "LOSE";
            this.gameOver = true;
            return;
        }

        cur_abs_x += this.getSpeed();

        // Result is WIN
        // if(players.get(0).arriveEnd()){
        //     this.gameOver = true;
        //     return;
        // }
        if(cur_abs_x + GameView.WIDTH >= end_abs_x){
            //TODO: 楊鈞安要改，到結尾
            players.get(0).setNowSpeed(0);
        }

        for(Pet player: players){
            player.update();
        }

	    // Peng
        for (Sprite fg : fargrounds) {
            fg.update();
            //把沒入螢幕範圍、應該要消失的sprite拿掉（例如糖果被吃掉）
            if(fg.isOutOfWindow()){
                removeFarground(fg);
            }
        }

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
	    addFargrounds(stage.getNewFargrounds(cur_abs_x));
    }

    // Peng
    public void addFargrounds(List<Sprite> newFg) {
	for (Sprite fg : newFg)
	    addFarground(fg);
    }
    
    public void addSprites(List<Sprite> newSprites) {
        for(Sprite sprite: newSprites){
            addSprite(sprite);
        }
    }

    public void addSprites(Sprite... sprites) {
        stream(sprites).forEach(this::addSprite);
    }

    // Peng
    public void addFarground(Sprite fg) {
	fargrounds.add(fg);
	fg.setWorld(this);
    }

    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
        sprite.setWorld(this);
    }

    public void removeSprite(Sprite sprite) {
        sprites.remove(sprite);
        sprite.setWorld(null);
    }

    public void removeFarground(Sprite fg) {
	fargrounds.remove(fg);
	fg.setWorld(null);
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
        stage.backgroundRender(g);
        //Peng
        for (Sprite fg : fargrounds) {
            fg.render(g);
        }
        for (Sprite sprite : sprites) {
            sprite.render(g);
        }
        for (Pet player: players){
            player.render(g);
        }
    }

    public Boolean isGameOver(){return this.gameOver;}
    public String getResult(){return this.result;}
}
