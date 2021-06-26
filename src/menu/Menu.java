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

public class Menu{
    private final Game game;
    private final JPanel panel;


    private String[] petChoices = { "Dog","Cat"};
    private JComboBox<String> petMenu = new JComboBox<String>(petChoices);
    private int defaultPetIndex = 0;
    private String selectedPet = petChoices[defaultPetIndex];

    private JButton playButton = new JButton("Play");
    
    private String[] levelChoices = { "Easy","Medium", "Hard" };
    private JComboBox<String> levelMenu = new JComboBox<String>(levelChoices);
    private int defaultLevelIndex = 0;
    private String selectedLevel = levelChoices[defaultLevelIndex];
    
    // private JPanel loadingWindow = new JPanel();
    
    public Menu(Game game, JPanel panel){
        this.game = game;
        this.panel = panel;

        petMenu.setSelectedIndex(defaultPetIndex);
        petMenu.setVisible(true);
        petMenu.addActionListener(new PetMenuListener(this));
        petMenu.setFocusable(false);

        playButton.setFocusable(false);
        //playButton.setUI(new StyledButtonUI());
        playButton.addActionListener(new PlayButtonListener(this));

        levelMenu.setSelectedIndex(defaultLevelIndex);
        levelMenu.setVisible(true);
        levelMenu.addActionListener(new LevelMenuListener(this));
        levelMenu.setFocusable(false);

        // loadingWindow.add(new JLabel("Loading..."));
        // loadingWindow.setVisible(true);
        // loadingWindow.setFocusable(false);
    }
    public void loadToPanel(){
        panel.add(petMenu);
        panel.add(playButton);
        panel.add(levelMenu);
        //System.out.printf("loadToPanel\n");
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
        //panel.add(loadingWindow);
        this.game.newStart();
    }

    public String getSelectedLevel(){return selectedLevel;}
    public String getSelectedPet(){return selectedPet;}
    
    // public static void loadingPopup(){
    //     JLabel messageLabel = new JLabel("<html><body><p style='width: 300px;'>"+"Loading"+"</p></body></html>");
    //     Timer timer = new Timer(5000, 
    //         new ActionListener()
    //         {   
    //             @Override
    //             public void actionPerformed(ActionEvent event)
    //             {
    //                 SwingUtilities.getWindowAncestor(messageLabel).dispose();
    //             }
    //         });
    //     timer.setRepeats(false);
    //     timer.start();
    //     JOptionPane.showMessageDialog(null, messageLabel);
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