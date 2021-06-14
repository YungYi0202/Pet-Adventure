package pet;

import model.CollisionHandler;
import model.Sprite;

import java.awt.*;

//楊鈞安

public class PetCollisionHandler implements CollisionHandler {
    @Override
    public void handle(Sprite from, Sprite to) {
        if (from instanceof Pet) {
            //TODO: can use collide with
            from.collideWith(to);
            to.collideWith(from);
        }
    }
}