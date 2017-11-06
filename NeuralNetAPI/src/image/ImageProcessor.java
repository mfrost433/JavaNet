package image;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageProcessor {
	public ImageProcessor() {

	}
	public double[][] imageToArray(File f){
		double[][] out = null;
		try {
			BufferedImage image = ImageIO.read(f);
			out = convertTo2DUsingGetRGB(image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return out;

	}

	private double[][] convertTo2DUsingGetRGB(BufferedImage image) {
		Raster r = image.getRaster();
		int width = image.getWidth();
		int height = image.getHeight();
		double[][] result = new double[1][width*height];
		int i = 0;
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				result[0][i] = ((double)r.getSample(col, row,0))/255;
				//System.out.println(image.getRGB(col, row));
				i++;
			}
		}

		return result;
	}




}



