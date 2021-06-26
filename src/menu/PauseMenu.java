package menu;

import controller.Game;

import java.awt.*;
import java.awt.event.*;
import views.GameView;
import javax.swing.*;
//import javax.swing.JOptionPane;

/**
 * @author - Yung-Yi Chen
 */

public class PauseMenu{
    private final Game game;
    private final JPanel panel;

    private JButton exitButton = new JButton("Exit");
    private JButton resumeButton = new JButton("Continue");
    
    public PauseMenu(Game game, JPanel panel){
        this.game = game;
        this.panel = panel;

        exitButton.setFocusable(false);
        exitButton.addActionListener(new ExitButtonListener(this));

        resumeButton.setFocusable(false);
        resumeButton.addActionListener(new ResumeButtonListener(this));
    }
    public void loadToPanel(){
        panel.add(exitButton);
        panel.add(resumeButton);
        //System.out.printf("loadToPanel\n");
    }
    public void removeFromPanel(){
        panel.remove(exitButton);
        panel.remove(resumeButton);
    }

    public void gameExit(){
        //panel.add(loadingWindow);
        this.game.exit();
    }

    public void gameResume(){
        this.game.resume();
    }

}

class ExitButtonListener implements  ActionListener{
    private PauseMenu menu;
    public ExitButtonListener(PauseMenu m){
        this.menu = m;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        menu.gameExit();
    }
}

class ResumeButtonListener implements  ActionListener{
    private PauseMenu menu;
    public ResumeButtonListener(PauseMenu m){
        this.menu = m;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        menu.gameResume();
    }
}

