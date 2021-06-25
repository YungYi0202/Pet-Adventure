package controller;

import model.World;

// 陳咏誼
//TODO: 如果要做暫停功能，還需要新增resume()

public abstract class GameLoop {
    //TODO: 之後要把這個true拿掉
    private boolean running = true;
    private View view;
    public Thread gameThread;

    public void setView(View view) {
        this.view = view;
    }

    public void start() {
        gameThread = new Thread(this::gameLoop);
        gameThread.start();
    }

    private void gameLoop() {
        //while(true){
            //System.out.println("hi");
            //delay(15);
        while (running) {
            World world = getWorld();
            world.update();
            view.render(world);
            delay(15);
        }
        //}
    }

    protected abstract World getWorld();

    public void stop() {
        //

        synchronized(gameThread){
            try {
                running = false;
                System.out.println(running);
                gameThread.wait();
                running = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        //System.out.println(running);
        
    }

    public void resume() {
        System.out.println("fuck");
        synchronized(this){
            //running = true;
            notify();
        }
        
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
