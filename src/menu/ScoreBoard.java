package menu;

import controller.Game;

import java.awt.*;
import java.awt.event.*;
import views.GameView;
import javax.swing.*;
//import javax.swing.plaf.multi.MultiComboBoxUI;
//import javax.swing.JOptionPane;

/**
 * @author - Yung-Yi Chen
 */

public class ScoreBoard{
    private final Game game;
    private final JPanel panel;

    private JLabel scoreText = new JLabel("score: ", SwingConstants.LEFT);
    private JLabel scoreValue = new JLabel("0", SwingConstants.RIGHT);
    
    public ScoreBoard(Game game, JPanel panel){
        this.game = game;
        this.panel = panel;

        scoreText.setFocusable(false);
        scoreText.setBounds(720, 10, 100, 100);
        scoreText.setFont(new Font("Utopia", Font.BOLD, 20));
        
        scoreValue.setFocusable(false);
        scoreValue.setBounds(820, 10, 100, 100);
        scoreValue.setFont(new Font("Utopia", Font.BOLD, 20));

    }

    public void loadToPanel(int scoreVal){
        panel.add(scoreText);
        scoreValue.setText(String.valueOf(scoreVal) + "");
        panel.add(scoreValue);
    }
    public void removeFromPanel(){
        panel.remove(scoreText);
        panel.remove(scoreValue);
    }
    
}


