package model;

import pet.Pet;
import state.RunToEnd;
import objects.Ground;
import views.GameView;

import java.awt.*;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;

/**
 * @author - Yung-Yi Chen
 */

public class World {
    private final List<Sprite> sprites = new CopyOnWriteArrayList<Sprite>();//exclusive of players
    private final List<Sprite> fargrounds = new CopyOnWriteArrayList<Sprite>();
    private final CollisionHandler collisionHandler;
    
    //陳咏誼
    private Stage stage;
    private int cur_abs_x = GameView.WIDTH;
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
        this.end_abs_x = stage.getEndAbsX();
        setPlayers(players);
        addSprites(stage.getNewSprites(cur_abs_x));
	
	    addFargrounds(stage.getNewFargrounds(cur_abs_x));  // Peng
	
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
        if(players.get(0).arriveEnd()){
            this.gameOver = true;
            return;
        }
        if(cur_abs_x + GameView.WIDTH >= end_abs_x){
            if( !(players.get(0).getState() instanceof RunToEnd)){
                players.get(0).setNowSpeed(0);
                players.get(0).runToEnd();
            }
            
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

    public void render(Graphics g) {
        stage.backgroundRender(g);
        //Peng
        for (Sprite fg : fargrounds) {
            fg.render(g);
        }
	for (Sprite ground : sprites) {
	    if (ground instanceof Ground)
		ground.render(g);
	}
        for (Sprite sprite : sprites) {
	    if (!(sprite instanceof Ground))
		sprite.render(g);
        }
        for (Pet player: players){
            player.render(g);
        }
    }

    public Boolean isGameOver(){return this.gameOver;}
    public String getResult(){return this.result;}
    public int getScore(){return players.get(0).getScore();}
}
