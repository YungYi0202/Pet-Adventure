package pet;

import state.ImageRenderer;

import java.awt.*;

/**
 * @author - andyyoung
 */
public class PetImageRenderer implements ImageRenderer {
    protected Pet pet;

    public Pet_Image_renderer(Pet pet) {
        this.Pet = pet;
    }

    @Override
    public void render(Image image, Graphics g) {
    // Todo 
    }
}