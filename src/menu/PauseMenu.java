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

    private JButton exitButton = new JButton("Exit (E)");
    private JButton resumeButton = new JButton("Continue (S)");
    public static Color backgroundColor = new Color(234,239,240);
    private JLabel title =  new JLabel("PAUSE", SwingConstants.CENTER);

    public PauseMenu(Game game, JPanel panel){
        this.game = game;
        this.panel = panel;

        title.setFocusable(false);
        title.setBounds(350, 150, 300, 120);
        title.setFont(new Font("Utopia", Font.BOLD, 50));

        exitButton.setFocusable(false);
        exitButton.addActionListener(new ExitButtonListener(this));
        
        exitButton.setBounds(200, 350, 250, 100);
        exitButton.setFont(new Font("Utopia", Font.BOLD, 30));
        exitButton.setUI(new StyledButtonUI(255,127,127));
        
        resumeButton.setFocusable(false);
        resumeButton.addActionListener(new ResumeButtonListener(this));
        
        resumeButton.setBounds(550, 350, 250, 100);
        resumeButton.setFont(new Font("Utopia", Font.BOLD, 30));
        resumeButton.setUI(new StyledButtonUI(255,182,25));

    }
    public void loadToPanel(){
        panel.add(title);
        panel.add(exitButton);
        panel.add(resumeButton);
        System.out.printf("PauseMenu: loadToPanel\n");
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

