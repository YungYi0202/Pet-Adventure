package stage;

import model.Stage;
/**
 * @author - Yung-Yi Chen
 */
public class StageManager{
    public static Stage getStage(String level){
        //TODO:
        switch(level){
            case "Easy":
                return new StageEasy1();
            case "Medium":
                return new StageEasy1();
            case "Hard":
                return new StageEasy();
        }
        return null;
    }
}
