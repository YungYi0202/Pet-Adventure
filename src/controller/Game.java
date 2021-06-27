package controller;

import pet.Pet;
import pet.PetCollisionHandler;
import model.World;
import menu.Menu;
import stage.StageManager;
import model.Stage;
/**
 * @author - Yung-Yi Chen
 */

public class Game extends GameLoop {
    private Pet p1;
    //TODO: 保留加入p2的延伸性

    private World world;


    // public Game(World world, Pet p1) {
    //     this.p1 = p1;
    //     this.world = world;
    // }

    public void newStart(){
        //Menu.loadingPopup();
        Menu menu = this.getMenu();
        Stage stage = StageManager.getStage( menu.getSelectedLevel() );
        //TODO:設定寵物是狗還是貓
        Pet pet1 = new Pet(500,40,"puppy");
        this.p1 = pet1;
        this.world = new World(new PetCollisionHandler(), stage, pet1);
        System.out.printf("Game: world exists\n");
        resumeWithTutorial();
    }

    //在 GameView.java 的 addKeyListener{}裡需要用到，這邊只會寫鍵盤可以操控的Pet行為

    public void jumpPet(int playerNumber){
        getPlayer(playerNumber).jump();
    }

    public void slidePet(int playerNumber){
        getPlayer(playerNumber).slide();
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
