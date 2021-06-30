package menu;

import controller.Game;

import java.awt.*;
import java.awt.event.*;
import views.GameView;
import javax.swing.*;
import utils.ImageStateUtils;
import java.awt.image.BufferedImage;
//import javax.swing.plaf.multi.MultiComboBoxUI;
//import javax.swing.JOptionPane;

/**
 * @author - Yung-Yi Chen
 */

public class Menu{
    private final Game game;
    private final JPanel panel;

    private BufferedImage background;

    //Leyna lower first letter
    private String[] petChoices = { "puppy","kitten"};
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
        //petMenu.setSize(new Dimension(150,100));
        //System.out.printf("%d, %d\n", petMenu.getPreferredSize().width, petMenu.getPreferredSize().height);
        //petMenu.setPreferredSize(new Dimension(150, 100));
        
        petMenu.setBounds(margin,580,petMenu.getPreferredSize().width, petMenu.getPreferredSize().height); 
        //petMenu.setFont(new Font("Utopia", Font.BOLD, 40));

        playButton.setFocusable(false);
        playButton.setUI(new StyledButtonUI(255,182,25));
        playButton.addActionListener(new PlayButtonListener(this));
        //playButton.setPreferredSize(new Dimension(200, 100));
        playButton.setBounds(400, 550, 200, 100);
        playButton.setFont(new Font("Utopia", Font.BOLD, 40));

        levelMenu.setSelectedIndex(defaultLevelIndex);
        levelMenu.setVisible(true);
        levelMenu.addActionListener(new LevelMenuListener(this));
        levelMenu.setFocusable(false);
        // levelMenu.setBounds(GameView.WIDTH - margin - levelMenu.getPreferredSize().width, 580, levelMenu.getPreferredSize().width, levelMenu.getPreferredSize().height);
        int tmp = GameView.WIDTH - margin - levelMenu.getPreferredSize().width;
        levelMenu.setBounds(tmp, 580, levelMenu.getPreferredSize().width, levelMenu.getPreferredSize().height);
        //levelMenu.setPreferredSize(new Dimension(200, 100));
        //levelMenu.setFont(new Font("Utopia", Font.BOLD, 40));

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

    public BufferedImage getBackground(){return background;}
    
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
        //System.out.printf( "startButton is clicked\n" );
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