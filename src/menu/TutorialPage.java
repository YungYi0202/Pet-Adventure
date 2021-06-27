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
    
    // private JPanel loadingWindow = new JPanel();
    
    public TutorialPage(Game game, JPanel panel){
        this.game = game;
        this.panel = panel;

        title.setFocusable(false);
        title.setBounds(350, 150, 300, 120);
        title.setFont(new Font("Utopia", Font.BOLD, 50));

        restTime.setFocusable(false);
        restTime.setBounds(250, 300, 500, 100);
        restTime.setFont(new Font("Utopia", Font.BOLD, 30));
        

        skipButton.setFocusable(false);
        skipButton.setUI(new StyledButtonUI(255,182,25));
        skipButton.addActionListener(new SkipButtonListener(this));
        skipButton.setBounds(400, 400, 200, 100);
        skipButton.setFont(new Font("Utopia", Font.BOLD, 40));

    }

    public void loadToPanel(int time){
        panel.add(title);
        panel.add(skipButton);
        restTime.setText("Close in " + String.valueOf(time) + " seconds" );
        panel.add(restTime);
        
    }
    public void removeFromPanel(){
        panel.remove(title);
        panel.remove(skipButton);
        panel.remove(restTime);
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
