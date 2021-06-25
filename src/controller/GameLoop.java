package controller;

import model.World;

// 陳咏誼
//TODO: 如果要做暫停功能，還需要新增resume()

public abstract class GameLoop {
    //Test game: running = menuHasRendered = true
    //Test menu: running = menuHasRendered = false
    private boolean running = false;
    private View view;
    public Thread gameThread;
    private boolean menuHasRendered = false;

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
            //menu
            if(menuHasRendered == false){  //busy wait

            }
            while (running) {
                menuHasRendered = false; //busy wait
                World world = getWorld();
                world.update();
                view.render(world);
                delay(15);
            }
            //System.out.printf("out of running loop\n");
            delay(15);
        }
    }

    protected abstract World getWorld();

    public void stop() {
        /*synchronized(gameThread){
            try{    
                gameThread.wait();
                running = false;
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        running = false;
    }

    public void resume() {
        /*synchronized(gameThread){
            running = true;
            gameThread.notify();
        }*/
        running = true;
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
    }

    public boolean getRunning(){return running;}
}
