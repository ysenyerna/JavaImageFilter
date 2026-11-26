import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Main {
	public static void main(String[] args) {
		System.out.println("Java Image Filter Viewer");
		System.out.println("------------------------\n");

		try {

			// Load the image file
			System.out.println("Loading image: original.jpg");
			File file = new File("original.jpg");

			BufferedImage original = ImageIO.read(file);
			if (original == null) {
				System.err.println("Failed to read original.jpg.");
				return;
			}

			System.out.println("Image loaded successfully.");




		} catch (Exception e) {
			System.err.println("Image processing failed: " + e.getMessage());
		}

		

	}

}
