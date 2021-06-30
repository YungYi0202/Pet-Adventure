package model;

import pet.HealthPointBar;
import media.AudioPlayer;

import java.awt.*;

/**
 * @author - andyyoung
 */
public abstract class HealthPointSprite extends Sprite {
    public int count = 0;
    protected HealthPointBar hpBar;

    public HealthPointSprite(int hp) {
        this.hpBar = new HealthPointBar(hp);
        hpBar.setOwner(this);
    }

    //@Override
    public void onDamaged(int damage) {
        hpBar.onDamaged(damage);
        if (hpBar.isDead() && count == 0) {
            //world.removeSprite(this);  ///// remove 本身 的實作

            // AudioPlayer.playSounds(AUDIO_DIE);
            count = 1;
        }
    }

    @Override
    public void render(Graphics g) {
        hpBar.render(g);
    }
}
