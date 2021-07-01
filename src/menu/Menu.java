package menu;

import controller.Game;
import views.GameView;
import utils.ImageStateUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * @author - Yung-Yi Chen
 */

public class Menu{
    private final Game game;
    private final JPanel panel;

    private BufferedImage background;
    public static final String PUPPY = "puppy";
    public static final String KITTEN = "kitten";
    //Leyna lower first letter
    private String[] petChoices = { PUPPY,KITTEN};
    private JComboBox<String> petMenu = new JComboBox<String>(petChoices);
    private int defaultPetIndex = 0;
    private String selectedPet = petChoices[defaultPetIndex];
    private int margin = 180;

    private JButton playButton = new JButton("Start (S)");
    
    private String[] levelChoices = { "Easy", "Hard" };
    private JComboBox<String> levelMenu = new JComboBox<String>(levelChoices);
    private int defaultLevelIndex = 0;
    private String selectedLevel = levelChoices[defaultLevelIndex];
    
    // private JPanel loadingWindow = new JPanel();
    
    public Menu(Game game, JPanel panel){
        this.game = game;
        this.panel = panel;
        this.background = ImageStateUtils.getImage("assets/menu/menu_2.png");

        petMenu.setSelectedIndex(defaultPetIndex);
        petMenu.setVisible(true);
        petMenu.addActionListener(new PetMenuListener(this));
        petMenu.setFocusable(false);
        
        petMenu.setBounds(margin,550,petMenu.getPreferredSize().width, petMenu.getPreferredSize().height); 

        playButton.setFocusable(false);
        playButton.setUI(new StyledButtonUI(255,182,25));
        playButton.addActionListener(new PlayButtonListener(this));
        playButton.setBounds(400, 510, 200, 100);
        playButton.setFont(new Font("Utopia", Font.BOLD, 40));

        levelMenu.setSelectedIndex(defaultLevelIndex);
        levelMenu.setVisible(true);
        levelMenu.addActionListener(new LevelMenuListener(this));
        levelMenu.setFocusable(false);
        // levelMenu.setBounds(GameView.WIDTH - margin - levelMenu.getPreferredSize().width, 580, levelMenu.getPreferredSize().width, levelMenu.getPreferredSize().height);
        int tmp = GameView.WIDTH - margin - levelMenu.getPreferredSize().width;
        levelMenu.setBounds(tmp, 550, levelMenu.getPreferredSize().width, levelMenu.getPreferredSize().height);
    }
    public void loadToPanel(){
        panel.add(petMenu);
        panel.add(playButton);
        panel.add(levelMenu);
    }
    public void removeFromPanel(){
        panel.remove(petMenu);
        panel.remove(playButton);
        panel.remove(levelMenu);
    }
    public void setSelectedLevel(String s){
        System.out.printf("setSelectedLevel: %s\n", s);
        this.selectedLevel = s;
    }

    public void setSelectedPet(String s){
        System.out.printf("setSelectedPet: %s\n", s);
        this.selectedPet = s;
    }

    public void gameNewStart(){
        this.game.newStart();
    }

    public String getSelectedLevel(){return selectedLevel;}
    public String getSelectedPet(){return selectedPet;}

    public BufferedImage getBackground(){return background;}
    
}

class PlayButtonListener implements  ActionListener{
    private Menu menu;
    public PlayButtonListener(Menu m){
        this.menu = m;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        menu.gameNewStart();
    }
}

class LevelMenuListener implements  ActionListener{
    private Menu menu;
    public LevelMenuListener(Menu m){
        this.menu = m;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        JComboBox comboBox = (JComboBox)e.getSource();
        menu.setSelectedLevel((String)comboBox.getSelectedItem());
    }
}

class PetMenuListener implements  ActionListener{
    private Menu menu;
    public PetMenuListener(Menu m){
        this.menu = m;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        JComboBox comboBox = (JComboBox)e.getSource();
        menu.setSelectedPet((String)comboBox.getSelectedItem());
    }
}