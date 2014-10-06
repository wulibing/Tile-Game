package support;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HelpfulImageMethods {
	
	
	/**
	 * @param filePath
	 * @return The BufferedImage object created from the data in the file
	 *         located at filePath; if no image data can be loaded, null
	 *         is returned.
	 */
	public static BufferedImage loadImage(String filePath) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(filePath));
		}
		catch (IOException e) {
			System.err.println("I could not load the file \'"+filePath+"'.  Sorry.");
		}
		return img;
	}

	/**
	 * @param img
	 * @param sx
	 * @param sy
	 * @param imageWidth
	 * @param imageHeight
	 * @return a BufferedImage object which is cut out from the BufferedImage
	 *         object 'img'.  The returned image is the sub-image of 'img' whose
	 *         upper-left corner is at (sx,sy) and whose width is imageWidth,
	 *         and whose height is imageHeight.
	 */
	public static BufferedImage createSubImage(BufferedImage img, int sx, int sy, int imageWidth,
			int imageHeight) {
		BufferedImage subImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
		Graphics g = subImage.getGraphics();
		int dx = 0;
		int dy = 0;
		g.drawImage(img,dx, dy, dx+imageWidth, dy+imageHeight,
                        sx, sy, sx+imageWidth, sy+imageHeight,
                        null);
		g.dispose();
		return subImage;
	}
}
