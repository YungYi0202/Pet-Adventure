package menu;

import controller.Game;

import java.awt.*;
import java.awt.event.*;
import views.GameView;
import javax.swing.*;

/**
 * @author - Yung-Yi Chen
 */

public class Menu{
    private final Game game;

    private String[] petChoices = { "Dog","Cat"};
    private JComboBox<String> petMenu = new JComboBox<String>(petChoices);
    private int defaultPetIndex = 0;
    private String selectedPet = petChoices[defaultPetIndex];

    private JButton playButton = new JButton("Play");
    
    private String[] levelChoices = { "Easy","Medium", "Hard" };
    private JComboBox<String> levelMenu = new JComboBox<String>(levelChoices);
    private int defaultLevelIndex = 0;
    private String selectedLevel = levelChoices[defaultLevelIndex];
    
    
    public Menu(Game game){
        this.game = game;

        petMenu.setSelectedIndex(defaultPetIndex);
        petMenu.setVisible(true);
        petMenu.addActionListener(new PetMenuListener(this));
        petMenu.setFocusable(false);

        playButton.setFocusable(false);
        playButton.setUI(new StyledButtonUI());
        playButton.addActionListener(new PlayButtonListener(this));

        levelMenu.setSelectedIndex(defaultLevelIndex);
        levelMenu.setVisible(true);
        levelMenu.addActionListener(new LevelMenuListener(this));
        levelMenu.setFocusable(false);

    }
    public void loadToPanel(JPanel panel){
        panel.add(petMenu);
        panel.add(playButton);
        panel.add(levelMenu);
        
        //System.out.printf("loadToPanel\n");
    }
    public void removeFromPanel(JPanel panel){
        panel.removeAll();
        

    }
    public void setSelectedLevel(String s){
        System.out.printf("setSelectedLevel: %s\n", s);
        this.selectedLevel = s;
    }

    public void setSelectedPet(String s){
        System.out.printf("setSelectedPet: %s\n", s);
        this.selectedPet = s;
    }

    public void gameResume(){
        this.game.resume();
    }

    // public void render(Graphics g){
    //     Font fnt0 = new Font("ariel", Font.BOLD, 50);
    //     g.setFont(fnt0);
    //     g.setColor(Color.black);
    //     //g.drawString("Menu Page", (GameView.WIDTH/2) - GameView.WIDTH/10, GameView.HEIGHT/2);
    //     g.drawString("Press S to Start/Pause", (GameView.WIDTH/2) - GameView.WIDTH/4, GameView.HEIGHT/2);
    // }
}

class PlayButtonListener implements  ActionListener{
    private Menu menu;
    public PlayButtonListener(Menu m){
        this.menu = m;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.printf( "playButton is clicked\n" );
        menu.gameResume();
    }
}

class LevelMenuListener implements  ActionListener{
    private Menu menu;
    public LevelMenuListener(Menu m){
        this.menu = m;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        //System.out.println( e.getModifiers() );
        JComboBox comboBox = (JComboBox)e.getSource();
        menu.setSelectedLevel((String)comboBox.getSelectedItem());
        //System.out.println( comboBox.getSelectedItem() );
    }
}

class PetMenuListener implements  ActionListener{
    private Menu menu;
    public PetMenuListener(Menu m){
        this.menu = m;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        //System.out.println( e.getModifiers() );
        JComboBox comboBox = (JComboBox)e.getSource();
        menu.setSelectedPet((String)comboBox.getSelectedItem());
        //System.out.println( comboBox.getSelectedItem() );
    }
}