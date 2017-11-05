package net;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Network {	
	private List<Layer> layers = new ArrayList<Layer>();

	public Network(int[] neuronsPerLayer) {
		int numHiddenLayers = neuronsPerLayer.length - 2;
		layers.add(new InputLayer(neuronsPerLayer[0], neuronsPerLayer[1]));
		for(int i = 1; i < numHiddenLayers+1; i++) {

			layers.add(new Layer(neuronsPerLayer[i],neuronsPerLayer[i+1]));

		}
		layers.add(new OutputLayer(neuronsPerLayer[neuronsPerLayer.length-1]));

	}

	//use in main
	private static int[][] convertTo2DUsingGetRGB(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		int[][] result = new int[height][width];

		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				result[row][col] = image.getRGB(col, row);
			}
		}

		return result;
	}

	public int guess(double[][] input) {
		layers.get(0).feedForward(input);
		Layer temp = layers.get(0);
		boolean first = true;
		for(Layer l : layers) {
			if(!first) {
				l.feedForward(temp.getOutput());			
				temp = l;	
			}else {
				first = false;
			}
		}

		OutputLayer out = (OutputLayer) temp;
		return out.getFinalVal();

	}
	


	public void train(double[][] input, double[][] target) {
		layers.get(0).feedForward(input);
		Layer temp = layers.get(0);
		boolean first = true;
		for(Layer l : layers) {
			if(!first) {
				l.feedForward(temp.getOutput());			
				temp = l;	
			}else {
				first = false;
			}
		}


		OutputLayer out = (OutputLayer) temp;
		out.backPropagate(target, layers.get( layers.size()-2));
		for(int i = layers.size()-2; i>= 1; i--) {
			layers.get(i).backPropagate(layers.get(i-1), layers.get(i+1));		
	}
		first = true;
		for(Layer l : layers) {
			if(!first) {
				l.setSynapse();
			}else {
				first = false;
			}
		}
	}
}
