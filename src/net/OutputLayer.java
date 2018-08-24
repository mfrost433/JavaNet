package net;

/**
 * Provides alternative backprop / feed forward methods that are specific to the final 
 * layer of a neural network. 
 * @author Matt Frost
 *
 */
public class OutputLayer extends Layer {
	
	private double error;
	private double finalVal = -1;
	
	/**
	 * Output layer constructed 
	 * @param inpSize
	 * @param eta
	 */
	public OutputLayer(int inpSize, int eta) {
		super(inpSize, 1, eta);
	}

	/**
	 * gets the final output.
	 */
	@Override
	public void feedForward(double[][] inp) {
		input = inp;

		double[][] yhat = sigmoid(inp);	
		out = yhat;
		finalVal = out[0][0];
		//
	}
	
	/**
	 * retrieves the final value produced on a dataset.
	 * @return
	 */
	public double getFinalVal() {
		return finalVal;
	}

	/**
	 * compares the target values to the output, and performs backpropogation.
	 * @param target
	 * @param lp
	 */
	public void backPropagate(double[][] target, Layer lp) {
		layerPrev = lp;

		double[][] error = minus(out,target);
		input = scale(error,1.0/input.length);
		this.error = error[0][0];
		delta = multByElement(error, sigmoidD(input));

		double[][] djdw = mult(transpose(lp.getActiveOutput()), delta);	
		lp.setBias(minus(lp.getBias(), scale(delta,getEta())));
		//printMatrix(error);
		
		// do all of these at end!!!!!!
		synapseToSet = minus(lp.getSynapse(),scale(djdw,getEta()));
	}
	
	/**
	 * gets the calculated error
	 * @return
	 */
	public double getError() {
		return error;
	}
	
	/**
	 * helper function for adding two matrices.
	 * TODO make static
	 * @param a
	 * @param b
	 * @return
	 */
	public double[][] add(double[][] a, double[][] b){
		
		double[][] out = new double[a.length][a[0].length];

		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[0].length; j++) {
				out[i][j] = a[i][j] + b[i][j];
			}
		}
		return out;

	}

}
