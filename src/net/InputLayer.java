package net;

/**
 * This class provides an alternative feed forward method that accepts inputs. 
 * @author matt frost
 *
 */
public class InputLayer extends Layer {
	
	/**
	 * 
	 * @param inpSize
	 * @param numOut
	 * @param eta
	 */
	public InputLayer(int inpSize, int numOut, int eta) {
		super(inpSize, numOut, eta);
	}
	
	/**
	 * 
	 */
	@Override
	public void feedForward(double[][] inp) {
		input = inp;
		a = inp;
		out = addBias(mult(input,synapse));
	}
}
