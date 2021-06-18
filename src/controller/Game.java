package controller;

import pet.Pet;

import model.World;

//陳咏誼

public class Game extends GameLoop {
    private final Pet p1;
    //TODO: 保留加入p2的延伸性

    private final World world;

    public Game(World world, Pet p1) {
        this.p1 = p1;
        this.world = world;
    }

    //在 GameView.java 的 addKeyListener{}裡需要用到，這邊只會寫鍵盤可以操控的Pet行為

    public void jumpPet(int playerNumber){
        getPlayer(playerNumber).jump();
    }

    public void slidePet(int playerNumber){
        // getPlayer(playerNumber).slide();
    }

    //TODO: 使用道具 
    //public void useProp(int playerNumber){}

    public Pet getPlayer(int playerNumber) {
        return p1;
        //return playerNumber == 1 ? p1 : p2;
    }

    @Override
    protected World getWorld() {
        return world;
    }
}
