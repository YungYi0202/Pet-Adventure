package model;

import pet.HealthPointBar;
import media.AudioPlayer;

import java.awt.*;

/**
 * @author - andyyoung
 */
public abstract class HealthPointSprite extends Sprite {
    public static final String AUDIO_DIE = "Die";

    protected HealthPointBar hpBar;

    public HealthPointSprite(int hp) {
        this.hpBar = new HealthPointBar(hp);
        hpBar.setOwner(this);
    }

    @Override
    public void onDamaged(int damage) {
        hpBar.onDamaged(damage);
        if (hpBar.isDead()) {
            world.removeSprite(this);  ///// remove sprite 的實作
            AudioPlayer.playSounds(AUDIO_DIE);
        }
    }

    @Override
    public void render(Graphics g) {
        hpBar.render(g);
    }
}
