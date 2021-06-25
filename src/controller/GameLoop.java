package controller;

import model.World;
import menu.Menu;

// 陳咏誼
//TODO: 如果要做暫停功能，還需要新增resume()

public abstract class GameLoop {
    //Test game: running = menuHasRendered = true
    //Test menu: running = menuHasRendered = false
    //private boolean running = true;
    private View view;
    
    public Thread gameThread;
    private boolean menuHasRendered = false;
    private enum STATE {MENU, GAME};
    private STATE state = STATE.MENU;

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
            if(state == STATE.MENU && menuHasRendered == false){  //busy wait
                view.render(this.getMenu());
                menuHasRendered = true;
            }
            while (state == STATE.GAME) {
                menuHasRendered = false; //busy wait
                World world = getWorld();
                world.update();
                view.render(world);
                delay(15);
            }
            // System.out.printf("out of running loop\n");
            delay(15);
        }
        
    }

    protected abstract World getWorld();
    protected abstract Menu getMenu();

    public void stop() {
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
        state = STATE.MENU;
    }

    public void resume() {
        // synchronized(gameThread){
        //     state = STATE.GAME;
        //     System.out.printf("running = true\n");
        //     gameThread.notify();
        //     System.out.printf("gameThread.notify\n");
        // }
        state = STATE.GAME;
    }

    private void delay(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public interface View {  
        void render(World world);
        void render(Menu menu);
    }

    public boolean stateIsGAME(){return state==STATE.GAME;}
    public boolean stateIsMENU(){return state==STATE.MENU;}
}
