package pet;

import model.CollisionHandler;
import model.Sprite;

import java.awt.*;

//楊鈞安

public class PetCollisionHandler implements CollisionHandler {
    @Override
    public void handle(Sprite from, Sprite to) { // 目前只處理Pet撞障礙物的情況
        if (from instanceof Pet) {
            from.collideWith(to);
<<<<<<< HEAD
=======
            to.collideWith(from);
>>>>>>> 68d83dfee110a15e432c2b7d02a2c6fd785aaf61
        }
    }
}