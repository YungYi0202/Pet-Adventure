package views;

import controller.Game;
import controller.GameLoop;
import model.Sprite;
import model.World;
import menu.Menu;
import menu.PauseMenu;
import menu.TutorialPage;
import menu.ResultPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collection;
import java.lang.*;
import java.awt.image.BufferedImage;

/**
 * @author - Yung-Yi Chen
 */
//TODO: background是不是應該放在這裡，在Canvas.paintComponent裡更新(g.setColor())

public class GameView extends JFrame {
    public static final int HEIGHT = 800;
    public static final int WIDTH = 1000;
    //保留以後可以多個玩家遊玩的延伸性
    public static final int P1 = 1;
    public static final int P2 = 2;
    
    private final Canvas canvas;
    private final Game game;

    public GameView(Game game) throws HeadlessException {
        this.game = game;
        this.canvas = new Canvas(game);
        game.setView(canvas);
    }

    public void launch() {
        // GUI Stuff
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(canvas);
        setSize(WIDTH, HEIGHT);
        //setContentPane(canvas);
        setVisible(true);

        // Keyboard listener
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if(game.stateIsGAME()){
                    //System.out.printf("keyPressed: stateIsGAME\n");
                    switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        game.jumpPet(P1);
                        break;
                    case KeyEvent.VK_DOWN:
                        game.slidePet(P1);
                        break;
                    case KeyEvent.VK_S:
                        System.out.println("pause");
                        game.pause();
                        break;
                    case KeyEvent.VK_LEFT:
                        System.out.println("useProp");
                        game.usePropPet(P1);
                        break;
                    }
                

                }
                else if(game.stateIsMENU()){
                    //System.out.printf("keyPressed: stateIsMENU\n");
                    switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_S:
                        game.newStart();
                        break;
                    }
                }
                else if(game.stateIsPAUSE()){
                    //System.out.printf("keyPressed: stateIsPAUSE\n");
                    switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_E:
                        game.exit();
                        break;
                    case KeyEvent.VK_S:
                        game.resume();
                        break;
                    }
                }
                else if(game.stateIsTUTORIAL()){
                    //System.out.printf("keyPressed: stateIsPAUSE\n");
                    switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_S:
                        game.resume();
                        break;
                    }
                }
                else if(game.stateIsRESULT()){
                    //System.out.printf("keyPressed: stateIsPAUSE\n");
                    switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_S:
                        game.exit();
                        break;
                    }
                }
                
            }

            /*
            @Override
            public void keyPressed(KeyEvent keyEvent)
            */
        });
    }

    public static class Canvas extends JPanel implements GameLoop.View {
        private World world;
        private Menu menu;
        private PauseMenu pauseMenu;
        private TutorialPage tutorialPage;
        private ResultPage resultPage;
        private int tutorialPageCountDownTime;
        private int resultPageCountDownTime;

        private enum STATE {MENU, GAME, PAUSE, TUTORIAL, RESULT};
        private STATE state;
        private boolean menuHasRendered = false;
        private boolean pauseMenuHasRendered = false;
        private boolean tutorialPageHasRendered = false;
        private boolean resultPageHasRendered = false;
        
        public Canvas(Game game){
            //this.pauseMenu = new PauseMenu(game, this);
            this.setLayout(null);
            this.menu = new Menu(game, this);
            this.pauseMenu = new PauseMenu(game, this);
            this.tutorialPage = new TutorialPage(game, this);
            this.resultPage = new ResultPage(game, this);
            menu.loadToPanel(); 
            pauseMenu.loadToPanel();
            tutorialPage.loadToPanel(5);
            resultPage.loadToPanel("WIN", 5);
        }

        @Override
        public void render(World world) {
            this.world = world;
            state = STATE.GAME;
            repaint(); // ask the JPanel to repaint, it will invoke paintComponent(g) after a while.
        }

        @Override
        public void renderMenu() {
            //System.out.printf("renderMenu\n");
            state = STATE.MENU;
            menuHasRendered = false; 
            repaint(); // ask the JPanel to repaint, it will invoke paintComponent(g) after a while.            
        }   
        @Override
        public void renderPauseMenu() {
            //System.out.printf("renderMenu\n");
            state = STATE.PAUSE;
            pauseMenuHasRendered = false;
            repaint(); // ask the JPanel to repaint, it will invoke paintComponent(g) after a while.            
        }   
        @Override
        public void renderTutorialPage(int time,World world) {
            //System.out.printf("renderMenu\n");
            state = STATE.TUTORIAL;
            tutorialPageHasRendered = false;
            tutorialPageCountDownTime = time;
            this.world = world;
            repaint(); // ask the JPanel to repaint, it will invoke paintComponent(g) after a while.            
        }   
        @Override
        public void renderResultPage(int time, World world){
            state = STATE.RESULT;
            resultPageHasRendered = false;
            resultPageCountDownTime = time;
            this.world = world;
            repaint(); // ask the JPanel to repaint, it will invoke paintComponent(g) after a while. 
        }

        @Override
        protected void paintComponent(Graphics g /*paintbrush*/) {
            super.paintComponent(g);
            // Now, let's paint
            g.setColor(Color.WHITE); // paint background with all white
            g.fillRect(0, 0, GameView.WIDTH, GameView.HEIGHT);
            
            if(state == STATE.GAME){  
                this.removeAll();

                //g.setColor(Color.WHITE); // paint background with all white
                //g.fillRect(0, 0, GameView.WIDTH, GameView.HEIGHT);
            
                world.render(g); // ask the world to paint itself and paint the sprites on the canvas
            }
            else if(state == STATE.MENU && menuHasRendered == false){
                this.removeAll();
                BufferedImage img = menu.getBackground();
                g.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null);
                //g.setColor(Color.WHITE); // paint background with all white
                //g.fillRect(0, 0, GameView.WIDTH, GameView.HEIGHT);
                
                menu.loadToPanel();
                menuHasRendered = true; 
            }else if(state == STATE.PAUSE && pauseMenuHasRendered == false){
                this.removeAll();
                world.render(g);
                g.setColor(PauseMenu.backgroundColor); // paint background with all white
                g.fillRoundRect(GameView.WIDTH/10, GameView.HEIGHT/10 + 20, GameView.WIDTH*8/10, GameView.HEIGHT*6/10, 40, 40);
                
                pauseMenu.loadToPanel();
                pauseMenuHasRendered = true;
            }else if(state == STATE.TUTORIAL && tutorialPageHasRendered == false){
                //System.out.printf("world.render(g);\n");
                world.render(g);
                g.setColor(PauseMenu.backgroundColor); // paint background with all white
                g.fillRoundRect(GameView.WIDTH/10, GameView.HEIGHT/10 + 20, GameView.WIDTH*8/10, GameView.HEIGHT*6/10, 40, 40);

                this.removeAll();
                tutorialPage.loadToPanel(tutorialPageCountDownTime);
                tutorialPageHasRendered = true;
            
            }else if(state == STATE.RESULT && resultPageHasRendered == false){
                //System.out.printf("world.render(g);\n");
                world.render(g);
                g.setColor(PauseMenu.backgroundColor); // paint background with all white
                g.fillRoundRect(GameView.WIDTH/10, GameView.HEIGHT/10 + 20, GameView.WIDTH*8/10, GameView.HEIGHT*6/10, 40, 40);

                this.removeAll();
                resultPage.loadToPanel(world.getResult(), resultPageCountDownTime);
                resultPageHasRendered = true;
            }
        }

        public Menu getMenu(){return this.menu;}

    }
}
