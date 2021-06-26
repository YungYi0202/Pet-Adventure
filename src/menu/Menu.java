package menu;

import java.awt.*;
import javax.swing.*;
import views.GameView;


public class Menu{
    public void render(Graphics g){
        
        Font fnt0 = new Font("ariel", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.black);
        //g.drawString("Menu Page", (GameView.WIDTH/2) - GameView.WIDTH/10, GameView.HEIGHT/2);
        g.drawString("Press S to Start/Pause", (GameView.WIDTH/2) - GameView.WIDTH/4, GameView.HEIGHT/2);
        
    }
}