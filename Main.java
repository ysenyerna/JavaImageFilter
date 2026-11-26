import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {
	public static void main(String[] args) {
		System.out.println("Java Image Filter Viewer");
		System.out.println("------------------------\n");

		try {

			// Load the image file
			System.out.println("Loading image: original.jpg");
			File file = new File("original.jpg");

			BufferedImage img = ImageIO.read(file);
			if (img == null) {
				System.err.println("Failed to read original.jpg.");
				return;
			}

			System.out.println("Image loaded successfully.");

			// Apply grayscale filter
			applyFilter(img, "grayscale.png", Main::toGrayscale);


		} catch (Exception e) {
			System.err.println("Image processing failed: " + e.getMessage());
		}

	}

	// Apply a filter to an image and save the image as a new file
	private static void applyFilter(BufferedImage img, String outputPath, CustomFilter filter) {

		BufferedImage output = new BufferedImage(
			img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

		// Iterate through each pixel of the image file
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
				// Apply the filter to each pixel
                Color pixel = new Color(img.getRGB(x, y));
                Color filteredPixel = filter.apply(pixel);
                output.setRGB(x, y, filteredPixel.getRGB());
            }
        }
		// Write the filtered image to a new file
		try {
			ImageIO.write(output, "png", new File(outputPath));
		} catch (IOException e) {
			System.err.println("Error writing to file: " + e.getMessage());
		}

	}

	// Interface for applying a filter to an individual pixel 
	@FunctionalInterface 
	private interface CustomFilter {
		Color apply(Color pixel);
	}

	// Custom filters
	private static Color toGrayscale(Color pixel) {
		float avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
		return new Color(avg /255, avg/255, avg/255);

	}
}
