package controller;

import model.World;
import menu.Menu;
import media.AudioPlayer;

/**
 * @author - Yung-Yi Chen
 */

public abstract class GameLoop {
    //Test game: running = menuHasRendered = true
    //Test menu: running = menuHasRendered = false
    //private boolean running = true;
    private View view;
    
    public Thread gameThread;
    private boolean menuHasRendered = false;
    private boolean pauseMenuHasRendered = false;
    private int tutorialTimeCount = 0;
    private int resultTimeCount = 0;
    private enum STATE {MENU, GAME, PAUSE, TUTORIAL, RESULT};
    private STATE state = STATE.MENU;
    public static final String AUDIO_GAME = "game";
    public static final String AUDIO_MENU = "menu";

    public void setView(View view) {
        this.view = view;
    }

    public void start() {
        gameThread = new Thread(this::gameLoop);
        gameThread.start();
        //new Thread(this::gameLoop).start();
    }

    private void gameLoop() {
        while(true){ 
            // menu
            switch(state){
                case MENU:
                    resultTimeCount = 0;
                    if(menuHasRendered == false){
                        view.renderMenu();
                        menuHasRendered = true;
                        System.out.printf("MenuRender\n");
                        // AudioPlayer.playSounds(AUDIO_MENU);
                    }
                    break;
                case GAME:
                    //System.out.printf("GameLoop: STATE.GAME\n");
                    tutorialTimeCount = 0;
                    menuHasRendered = false; //busy wait
                    pauseMenuHasRendered = false;
                    World world = getWorld();
                    world.update();
                    if(world.isGameOver()){
                        over();
                    }
                    view.render(world);
                    break;
                case PAUSE:
                    if(pauseMenuHasRendered == false){
                        view.renderPauseMenu();
                        pauseMenuHasRendered = true;
                    }
                    break;
                case TUTORIAL:
                    //System.out.printf("GameLoop: STATE.TUTORIAL TimeCount:%d\n", tutorialTimeCount);
                    if(tutorialTimeCount % 67 == 0){
                        view.renderTutorialPage( 5 - tutorialTimeCount/67 , getWorld());
                    }
                    tutorialTimeCount++;
                    if(tutorialTimeCount >= 335){
                        resume();
                    }
                    break;
                case RESULT:
                    if(resultTimeCount % 67 == 0){
                        view.renderResultPage( 5 - resultTimeCount/67, getWorld());
                    }
                    resultTimeCount++;
                    if(resultTimeCount >= 335){
                        exit();
                    }
                    break;
            }

            delay(15);
        }
        
    }

    protected abstract World getWorld();

    public void pause() {
        // synchronized(gameThread){
            // try{    
            //     gameThread.wait();
            //     System.out.printf("gameThread.wait\n");
            // }
            // catch(InterruptedException e) {
            //     e.printStackTrace();
            // }finally{
            //     state = STATE.MENU;
            //     System.out.printf("running = false\n");
            // }
        // }
        //state = STATE.MENU;
        AudioPlayer.pauseSounds(AUDIO_GAME);
        state = STATE.PAUSE;
    }

    public void exit(){
        AudioPlayer.stopSounds(AUDIO_GAME);
        
        state = STATE.MENU;
    }

    public void resume() {
        // synchronized(gameThread){
        //     state = STATE.GAME;
        //     System.out.printf("running = true\n");
        //     gameThread.notify();
        //     System.out.printf("gameThread.notify\n");
        // }
        AudioPlayer.playSounds(AUDIO_GAME);
        state = STATE.GAME;
    }

    public void over(){
        state = STATE.RESULT;
    }

    public void resumeWithTutorial(){
        // AudioPlayer.stopSounds(AUDIO_MENU);
        state = STATE.TUTORIAL;
    }

    protected void delay(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Menu getMenu(){
        return this.view.getMenu();
    }


    public interface View {  
        Menu getMenu();
        void render(World world);
        void renderMenu();
        void renderPauseMenu();
        void renderTutorialPage(int time, World world);
        void renderResultPage(int time, World world);
    }

    public boolean stateIsGAME(){return state==STATE.GAME;}
    public boolean stateIsMENU(){return state==STATE.MENU;}
    public boolean stateIsPAUSE(){return state==STATE.PAUSE;}
    public boolean stateIsTUTORIAL(){return state==STATE.TUTORIAL;}
    public boolean stateIsRESULT(){return state==STATE.RESULT;}
}
