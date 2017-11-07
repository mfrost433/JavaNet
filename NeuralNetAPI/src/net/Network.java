package net;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains encapsulates the layers of the neural network, and allows you to train it
 * and test it, using train and guess. Constructor recieves an array containing the number of nodes
 * in each layer, and builds the network from that.
 * 
 * @author Matthew
 *
 */
public class Network {

	private List<Layer> layers = new ArrayList<Layer>();

	public Network(int[] neuronsPerLayer) {
		//uses the size of the input array to calculate the number of hidden, input and output layers
		int numHiddenLayers = neuronsPerLayer.length - 2;
		//adds inital input layer
		layers.add(new InputLayer(neuronsPerLayer[0], neuronsPerLayer[1]));
		
		//adds hidden layers
		for(int i = 1; i < numHiddenLayers+1; i++) {

			layers.add(new Layer(neuronsPerLayer[i],neuronsPerLayer[i+1]));

		}
		
		//adds output layer
		layers.add(new OutputLayer(neuronsPerLayer[neuronsPerLayer.length-1]));

	}

	// does a feed forward test on an input, and returns the output value.
	public double guess(double[][] input) {
		
		//feeds into input first.
		layers.get(0).feedForward(input);
		Layer temp = layers.get(0);
		
		boolean first = true;
		//feeds through all other layers
		for(Layer l : layers) {
			if(!first) {
				l.feedForward(temp.getOutput());			
				temp = l;	
			}else {
				first = false;
			}
		}
		//gets last layer as an output layer object to retrieve final value.
		OutputLayer out = (OutputLayer) temp;
		return out.getFinalVal();

	}

	//feed forward, followed by back propagation.
	public double train(double[][] input, double[][] target) {
		
		//feeds forward same as guess()
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
		
		//back propagates through output layer first, as it has different inputs
		out.backPropagate(target, layers.get( layers.size()-2));
		
		//back propagates all hidden layers - calculates change in error / change in weights
		for(int i = layers.size()-2; i>= 1; i--) {
			layers.get(i).backPropagate(layers.get(i-1), layers.get(i+1));		
		}
		
		//finally, adjusts all weights based on the dE/dW value calculated beforehand.
		first = true;
		for(Layer l : layers) {
			if(!first) {
				l.setSynapse();
			}else {
				first = false;
			}
		}
		
		//returns value of error for debugging
		return out.getError();
	}
}
