package media;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import controller.Game;

/**
 * @author - Yung-Yi Chen
 */

// gameMusic:
// Monkeys Spinning Monkeys Kevin MacLeod (incompetech.com)
// Licensed under Creative Commons: By Attribution 3.0 License
// http://creativecommons.org/licenses/by/3.0/
// Music promoted by https://www.chosic.com/

// menuMusic:
// Fluffing a Duck Kevin MacLeod (incompetech.com)
// Licensed under Creative Commons: By Attribution 3.0 License
// http://creativecommons.org/licenses/by/3.0/
// Music promoted by https://www.chosic.com/

// Not OCP method
public class AudioPlayer {
    private static final Map<Object, File> sounds = new HashMap<>();
    private static Clip gameClip;
    private static long gameClipTimePosition = 0;
    private static Clip menuClip;
    private static long menuClipTimePosition = 0;

    public static void addAudioByFilePath(Object audioName, File file) {
        sounds.put(audioName, file);
    }

    public static void addAudioByFilePath(Object audioName, String path) {
        sounds.put(audioName, Paths.get(path).toFile());
    }

    public static void playSounds(Object audioName) {
        try {
            switch((String)audioName){
                case Game.AUDIO_GAME:
                    gameClip = AudioSystem.getClip();
                    gameClip.open(AudioSystem.getAudioInputStream(sounds.get(audioName)));
                    gameClip.setMicrosecondPosition(gameClipTimePosition);
                    gameClip.start();
                    gameClip.loop(Clip.LOOP_CONTINUOUSLY);
                    break;
                case Game.AUDIO_MENU:
                    menuClip = AudioSystem.getClip();
                    menuClip.open(AudioSystem.getAudioInputStream(sounds.get(audioName)));
                    menuClip.setMicrosecondPosition(menuClipTimePosition);
                    menuClip.start();
                    menuClip.loop(Clip.LOOP_CONTINUOUSLY);
                    break;
                default:
                    Clip clip = AudioSystem.getClip();
                    clip.open(AudioSystem.getAudioInputStream(sounds.get(audioName)));
                    clip.start();
                    break;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void pauseSounds(Object audioName){
        try {
            switch((String)audioName){
                case Game.AUDIO_GAME:
                    gameClipTimePosition = gameClip.getMicrosecondPosition();
                    gameClip.stop();
                    break;
                case Game.AUDIO_MENU:
                    menuClipTimePosition = menuClip.getMicrosecondPosition();
                    menuClip.stop();
                    break;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public static void stopSounds(Object audioName){
        try {
            switch((String)audioName){
                case Game.AUDIO_GAME:
                    gameClipTimePosition = 0;
                    gameClip.stop();
                    break;
                case Game.AUDIO_MENU:
                    menuClipTimePosition = 0;
                    menuClip.stop();
                    break;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
