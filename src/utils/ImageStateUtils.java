package utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.AlphaComposite;
import javax.imageio.ImageIO;

/**
 * @author - Leyna
 */

public class ImageStateUtils {
	
	static public BufferedImage getImage(String path) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	static public BufferedImage resize(BufferedImage image, double rate){
		BufferedImage resizedImage = new BufferedImage((int)(image.getWidth() * rate), (int)(image.getHeight() * rate), image.getType());
		Graphics2D graphics2D = resizedImage.createGraphics();
    	graphics2D.drawImage(image, 0, 0, (int)(image.getWidth() * rate), (int)(image.getHeight() * rate), null);
    	graphics2D.dispose();
		return resizedImage;
	}
	
	static public BufferedImage opacity(BufferedImage image, float opacity){
		BufferedImage opacityImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		Graphics2D graphics2D = opacityImage.createGraphics();
		graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
    	graphics2D.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
    	graphics2D.dispose();
		return opacityImage;
	}
}
