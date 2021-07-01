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

public class TutorialPage{
    private final Game game;
    private final JPanel panel;

    private JButton skipButton = new JButton("Skip (S)");
    private JLabel title =  new JLabel("Tutorial", SwingConstants.CENTER);
    private JLabel restTime = new JLabel("Close in 5 seconds", SwingConstants.CENTER);
    private JLabel line1 = new JLabel("* Press UP to jump", SwingConstants.LEFT);
    private JLabel line2 = new JLabel("* Press DOWN to slide", SwingConstants.LEFT);
    private JLabel line3 = new JLabel("* Press LEFT to use prop", SwingConstants.LEFT);
    private JLabel line4 = new JLabel("* Press S to pause the game", SwingConstants.LEFT);

    // private JPanel loadingWindow = new JPanel();
    
    public TutorialPage(Game game, JPanel panel){
        this.game = game;
        this.panel = panel;

        title.setFocusable(false);
        title.setBounds(350, 100, 300, 120);
        title.setFont(new Font("Utopia", Font.BOLD, 50));

        restTime.setFocusable(false);
        restTime.setBounds(250, 380, 500, 100);
        restTime.setFont(new Font("Utopia", Font.BOLD, 30));
        

        skipButton.setFocusable(false);
        skipButton.setUI(new StyledButtonUI(255,182,25));
        skipButton.addActionListener(new SkipButtonListener(this));
        skipButton.setBounds(400, 470, 200, 80);
        skipButton.setFont(new Font("Utopia", Font.BOLD, 40));

        line1.setFocusable(false);
        line1.setBounds(250, 200, 500, 50);
        line1.setFont(new Font("Utopia", Font.BOLD, 30));

        line2.setFocusable(false);
        line2.setBounds(250, 250, 500, 50);
        line2.setFont(new Font("Utopia", Font.BOLD, 30));

        line3.setFocusable(false);
        line3.setBounds(250, 300, 500, 50);
        line3.setFont(new Font("Utopia", Font.BOLD, 30));

        line4.setFocusable(false);
        line4.setBounds(250, 350, 500, 50);
        line4.setFont(new Font("Utopia", Font.BOLD, 30));

    }

    public void loadToPanel(int time){
        panel.add(title);
        panel.add(skipButton);
        restTime.setText("Close in " + String.valueOf(time) + " seconds" );
        panel.add(restTime);
        panel.add(line1);
        panel.add(line2);
        panel.add(line3);
        panel.add(line4);
        
    }
    public void removeFromPanel(){
        panel.remove(title);
        panel.remove(skipButton);
        panel.remove(restTime);
        panel.remove(line1);
        panel.remove(line2);
        panel.remove(line3);
        panel.remove(line4);
    }
    
    public void gameResume(){
        this.game.resume();
    }
    
}

class SkipButtonListener implements  ActionListener{
    private TutorialPage page;
    public SkipButtonListener(TutorialPage page){
        this.page = page;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        page.gameResume();
    }
}
