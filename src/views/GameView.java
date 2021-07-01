package views;

import controller.Game;
import controller.GameLoop;
import model.Sprite;
import model.World;
import menu.Menu;
import menu.PauseMenu;
import menu.TutorialPage;
import menu.ResultPage;
import menu.ScoreBoard;
import utils.ImageStateUtils;

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
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(canvas);
        setSize(WIDTH, HEIGHT);
        setVisible(true);

        // Keyboard listener
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if(game.stateIsGAME()){
                    switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        game.jumpPet(P1);
                        break;
                    case KeyEvent.VK_DOWN:
                        game.slidePet(P1);
                        break;
                    case KeyEvent.VK_S:
                        game.pause();
                        break;
                    case KeyEvent.VK_LEFT:
                        game.usePropPet(P1);
                        break;
                    }
                

                }
                else if(game.stateIsMENU()){
                    switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_S:
                        game.newStart();
                        break;
                    }
                }
                else if(game.stateIsPAUSE()){
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

        });
    }

    public static class Canvas extends JPanel implements GameLoop.View {
        private World world;
        private Menu menu;
        private PauseMenu pauseMenu;
        private TutorialPage tutorialPage;
        private ResultPage resultPage;
        private ScoreBoard scoreBoard;
        private int tutorialPageCountDownTime;
        private int resultPageCountDownTime;

        private enum STATE {MENU, GAME, PAUSE, TUTORIAL, RESULT};
        private STATE state;
        private boolean menuHasRendered = false;
        private boolean pauseMenuHasRendered = false;
        private boolean tutorialPageHasRendered = false;
        private boolean resultPageHasRendered = false;
        private BufferedImage board = ImageStateUtils.getImage("assets/menu/menu_1.png");

        public Canvas(Game game){
            this.setLayout(null);
            this.menu = new Menu(game, this);
            this.pauseMenu = new PauseMenu(game, this);
            this.tutorialPage = new TutorialPage(game, this);
            this.resultPage = new ResultPage(game, this);
            this.scoreBoard = new ScoreBoard(game, this);
            menu.loadToPanel(); 
            pauseMenu.loadToPanel();
            tutorialPage.loadToPanel(5);
            resultPage.loadToPanel("WIN", 5, 0);
            scoreBoard.loadToPanel(0);
        }

        @Override
        public void render(World world) {
            this.world = world;
            state = STATE.GAME;
            repaint(); // ask the JPanel to repaint, it will invoke paintComponent(g) after a while.
        }

        @Override
        public void renderMenu() {
            state = STATE.MENU;
            menuHasRendered = false; 
            repaint(); // ask the JPanel to repaint, it will invoke paintComponent(g) after a while.            
        }   
        @Override
        public void renderPauseMenu() {
            state = STATE.PAUSE;
            pauseMenuHasRendered = false;
            repaint(); // ask the JPanel to repaint, it will invoke paintComponent(g) after a while.            
        }   
        @Override
        public void renderTutorialPage(int time,World world) {
            state = STATE.TUTORIAL;
            tutorialPageHasRendered = false;
            tutorialPageCountDownTime = time;
            this.world = world;
            repaint(); // ask the JPanel to repaint, it will invoke paintComponent(g) after a while.            
        }   
        @Override
        public void renderResultPage(int time){
            state = STATE.RESULT;
            resultPageHasRendered = false;
            resultPageCountDownTime = time;
            repaint(); // ask the JPanel to repaint, it will invoke paintComponent(g) after a while. 
        }

        @Override
        protected void paintComponent(Graphics g /*paintbrush*/) {
            super.paintComponent(g);
            
            if(state == STATE.GAME){  
                this.removeAll();
                scoreBoard.loadToPanel(world.getScore());
                world.render(g); // ask the world to paint itself and paint the sprites on the canvas
            }
            else if(state == STATE.MENU && menuHasRendered == false){
                this.removeAll();
                BufferedImage img = menu.getBackground();
                g.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null);
                
                menu.loadToPanel();
                menuHasRendered = true; 
            }else if(state == STATE.PAUSE && pauseMenuHasRendered == false){
                this.removeAll();
                scoreBoard.loadToPanel(world.getScore());
                world.render(g);
                this.renderBoard(g);
                
                pauseMenu.loadToPanel();
                pauseMenuHasRendered = true;
            }else if(state == STATE.TUTORIAL && tutorialPageHasRendered == false){
                world.render(g);
                this.renderBoard(g);

                this.removeAll();
                tutorialPage.loadToPanel(tutorialPageCountDownTime);
                tutorialPageHasRendered = true;
            
            }else if(state == STATE.RESULT && resultPageHasRendered == false){
                world.render(g);
                this.renderBoard(g);
                this.removeAll();
                resultPage.loadToPanel(world.getResult(), resultPageCountDownTime, world.getScore());
                resultPageHasRendered = true;
            }
        }

        public Menu getMenu(){return this.menu;}

        private void renderBoard(Graphics g){
            g.drawImage(this.board, GameView.WIDTH/10, GameView.HEIGHT/10 + 20, board.getWidth(), board.getHeight(), null);
        }

    }
}
