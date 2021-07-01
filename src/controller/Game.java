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

    public void newStart(){
        Menu menu = this.getMenu();
        Stage stage = StageManager.getStage( menu.getSelectedLevel() );
        Pet pet1 = new Pet(500,40,menu.getSelectedPet());
        this.p1 = pet1;
        this.world = new World(new PetCollisionHandler(), stage, pet1);
        resumeWithTutorial();
    }

    //在 GameView.java 的 addKeyListener{}裡需要用到，這邊只會寫鍵盤可以操控的Pet行為

    public void jumpPet(int playerNumber){
        getPlayer(playerNumber).jump();
    }

    public void slidePet(int playerNumber){
        getPlayer(playerNumber).slide();
    }

    public void usePropPet(int playerNumber){
        getPlayer(playerNumber).useProp();
    }

    public Pet getPlayer(int playerNumber) {
        return p1;
    }

    @Override
    protected World getWorld() {
        return world;
    }
}
