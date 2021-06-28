package utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * @author - Leyna
 */

public class ImageStateUtils {
	
	public BufferedImage getImage(String path) {
		BufferedImage img = null;
		try {
			//img = ImageIO.read(getClass().getResource(path));
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}

	public BufferedImage resize(BufferedImage image, double rate){
		BufferedImage resizedImage = new BufferedImage((int)(image.getWidth() * rate), (int)(image.getHeight() * rate), image.getType());
		Graphics2D graphics2D = resizedImage.createGraphics();
    	graphics2D.drawImage(image, 0, 0, (int)(image.getWidth() * rate), (int)(image.getHeight() * rate), null);
    	graphics2D.dispose();
		return resizedImage;
	}
	
}

// import state.ImageRenderer;
// import state.ImageState;

// import javax.imageio.ImageIO;
// import java.awt.*;
// import java.io.File;
// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.util.List;
// import java.util.regex.Pattern;

// import static java.util.stream.Collectors.toList;

// public class ImageStateUtils {
//     public static final String SUPPORTED_FILE_NAME_PATTERN = "([1-9][0-9]*|0)\\.(bmp|jpg|png)";

//     public static List<ImageState> imageStatesFromFolder(String folderPath, ImageRenderer renderer) {
//         try {
//             List<File> files = Files.list(Path.of(folderPath))
//                     .map(ImageStateUtils::readFile)
//                     .filter(ImageStateUtils::validateFileName)
//                     .collect(toList());
//             sortFilesByAscendingIndex(files);
//             return files.stream().map(ImageStateUtils::readImage)
//                     .map(image -> new ImageState(image, renderer))
//                     .collect(toList());
//         } catch (IOException e) {
//             throw new IllegalArgumentException(e);
//         }
//     }

//     private static File readFile(Path filePath) {
//         return filePath.toFile();
//     }

//     private static Image readImage(File file) {
//         try {
//             return ImageIO.read(file);
//         } catch (IOException e) {
//             throw new RuntimeException(e);
//         }
//     }

//     private static boolean validateFileName(File file) {
//         String fileName = file.getName();
//         return Pattern.matches(SUPPORTED_FILE_NAME_PATTERN, fileName);
//     }

//     private static void sortFilesByAscendingIndex(List<File> picFiles) {
//         picFiles.sort((f1, f2) -> {
//             int f1Index = Integer.parseInt(f1.getName().split("\\.")[0]);
//             int f2Index = Integer.parseInt(f2.getName().split("\\.")[0]);
//             return f1Index - f2Index;
//         });
//     }
// }
