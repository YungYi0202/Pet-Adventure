package pet;

import state.ImageRenderer;

import java.awt.*;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Pet_Imager_enderer implements ImageRenderer {
    protected Pet pet;

    public Pet_Image_renderer(Pet pet) {
        this.Pet = pet;
    }

    @Override
    public void render(Image image, Graphics g) {
    // Todo 
    }
}