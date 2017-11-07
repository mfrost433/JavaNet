package net;

public class InputLayer extends Layer {

	public InputLayer(int inpSize, int numOut) {

		super(inpSize, numOut);
	}

	@Override
	public void feedForward(double[][] inp) {

		input = inp;		

		a = inp;
		out = addBias(mult(input,synapse));

	}
}
