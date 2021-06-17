import controller.Game;
import pet.Pet;
import pet.PetCollisionHandler;
import model.HealthPointSprite;
import model.World;
import model.Stage;
import views.GameView;

import java.awt.*;
import java.io.File;

import static media.AudioPlayer.addAudioByFilePath;

import stage.StageEasy1;

//陳咏誼
 
public class Main {
    public static void main(String[] args) {
        //TODO: 加入音效
        //addAudioByFilePath(Walking.AUDIO_STEP1, new File("assets/audio/step1.wav"));
        //addAudioByFilePath(Walking.AUDIO_STEP2, new File("assets/audio/step2.wav"));
        //addAudioByFilePath(Attacking.AUDIO_SWORD_CLASH_1, new File("assets/audio/sword-clash1.wav"));
        //addAudioByFilePath(Attacking.AUDIO_SWORD_CLASH_2, new File("assets/audio/sword-clash2.wav"));
        addAudioByFilePath(HealthPointSprite.AUDIO_DIE, new File("assets/audio/die.wav"));

        // initialization procedure
        Pet p1 = new Pet(500,20);
        //TODO: 之後要加入改變關卡的menu？
        Stage stage = new StageEasy1();
        World world = new World(new PetCollisionHandler(), stage, p1);  // model
        Game game = new Game(world, p1);  // controller
        GameView view = new GameView(game);  // view
        game.start();  // run the game and the game loop
        view.launch(); // launch the GUI
    }
}
