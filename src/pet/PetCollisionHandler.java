package pet;

import model.CollisionHandler;
import model.Sprite;

import java.awt.*;

/**
 * @author - Andy young 
 */

public class PetCollisionHandler implements CollisionHandler {
    @Override
    public void handle(Sprite from, Sprite to) { // 目前只處理Pet撞障礙物的情況
        to.collideWith(from);
        from.collideWith(to);
    }
}