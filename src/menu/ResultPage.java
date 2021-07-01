package menu;

import controller.Game;
import views.GameView;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author - Yung-Yi Chen
 */

public class ResultPage{
    private final Game game;
    private final JPanel panel;

    private JButton backButton = new JButton("Back To Menu (S)");
    private JLabel title =  new JLabel("You Win", SwingConstants.CENTER);
    private JLabel score = new JLabel("Score: 0", SwingConstants.CENTER);
    private JLabel restTime = new JLabel("Close in 5 seconds", SwingConstants.CENTER);
    
    public ResultPage(Game game, JPanel panel){
        this.game = game;
        this.panel = panel;

        title.setFocusable(false);
        title.setBounds(350, 100, 300, 120);
        title.setFont(new Font("Utopia", Font.BOLD, 50));

        score.setFocusable(false);
        score.setBounds(200, 230, 600, 120);
        score.setFont(new Font("Utopia", Font.BOLD, 60));

        restTime.setFocusable(false);
        restTime.setBounds(250, 370, 500, 100);
        restTime.setFont(new Font("Utopia", Font.BOLD, 30));
        
        backButton.setFocusable(false);
        backButton.setUI(new StyledButtonUI(255,182,25));
        backButton.addActionListener(new BackButtonListener(this));
        backButton.setBounds(300, 470, 400, 80);
        backButton.setFont(new Font("Utopia", Font.BOLD, 40));

    }
    // result is "WIN" or "LOSE"
    public void loadToPanel(String result, int time, int scoreVal){
        title.setText("You " + result + " !");
        restTime.setText("Close in " + String.valueOf(time) + " seconds" );
        score.setText("Score: "+ String.valueOf(scoreVal));
        panel.add(title);
        panel.add(score);
        panel.add(restTime);
        panel.add(backButton);
    }

    public void removeFromPanel(){
        panel.remove(title);
        panel.remove(score);
        panel.remove(restTime);
        panel.remove(backButton);
    }
    
    public void gameExit(){
        this.game.exit();
    }
    
}

class BackButtonListener implements  ActionListener{
    private ResultPage page;
    public BackButtonListener(ResultPage page){
        this.page = page;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        page.gameExit();
    }
}
