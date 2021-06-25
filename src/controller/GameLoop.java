package controller;

import model.World;

// 陳咏誼
//TODO: 如果要做暫停功能，還需要新增resume()

public abstract class GameLoop {
    //TODO: 之後要把這個true拿掉
    private boolean running = true;
    private View view;
    //public Thread gameThread;

    public void setView(View view) {
        this.view = view;
    }

    public void start() {
        new Thread(this::gameLoop).start();
    }

    private void gameLoop() {
        while(true){
            // 
            while (running) {
                //System.out.printf("gameLoop: running\n");
                World world = getWorld();
                world.update();
                view.render(world);
                delay(15);
            }
            //System.out.printf("gameLoop: not running\n");
        }
    }

    protected abstract World getWorld();

    public void stop() {
        running = false;
    }

    public void resume() {
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
