import controller.Game;
import controller.GameLoop;
import pet.Pet;
import pet.PetCollisionHandler;
import model.HealthPointSprite;
import model.World;
import model.Stage;
import views.GameView;
import media.AudioPlayer;
import stage.StageEasy1;

import java.awt.*;
import java.io.File;


/**
 * @author - Yung-Yi Chen
 */
 
public class Main {
    public static void main(String[] args) {
        AudioPlayer.addAudioByFilePath(Game.AUDIO_GAME, new File("assets/audio/game.wav"));
        AudioPlayer.addAudioByFilePath(Game.AUDIO_MENU, new File("assets/audio/menu.wav"));
        AudioPlayer.addAudioByFilePath(Pet.AUDIO_ADDSCORE, new File("assets/audio/addScore.wav"));
        AudioPlayer.addAudioByFilePath(Pet.AUDIO_ADDPROP, new File("assets/audio/addProp.wav"));
        AudioPlayer.addAudioByFilePath(Pet.AUDIO_USEPROP, new File("assets/audio/useProp.wav"));
        AudioPlayer.addAudioByFilePath(Pet.AUDIO_COSTHP, new File("assets/audio/costHp.wav"));
        AudioPlayer.addAudioByFilePath(Pet.AUDIO_PUPPYDIE, new File("assets/audio/puppyDie.wav"));
        AudioPlayer.addAudioByFilePath(Pet.AUDIO_KITTENDIE, new File("assets/audio/kittenDie.wav"));
        
        Game game = new Game();  // controller
        GameView view = new GameView(game);  // view        
        game.start();  // run the game and the game loop
        view.launch(); // launch the GUI
        AudioPlayer.playSounds(Game.AUDIO_MENU);
    }
}
