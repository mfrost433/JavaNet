package net;

import testing.Main;

public class OutputLayer extends Layer {
	double error;
	double finalVal = -1;
	public OutputLayer(int inpSize) {
		super(inpSize, 1);
	}

	@Override
	public void feedForward(double[][] inp) {
		input = inp;

		double[][] yhat = sigmoid(inp);	
		out = yhat;
		finalVal = out[0][0];
		//
	}

	public double getFinalVal() {
		return finalVal;
	}


	public void backPropagate(double[][] target, Layer lp) {
		layerPrev = lp;

		double[][] error = minus(out,target);
		input = scale(error,1.0/input.length);
		this.error = error[0][0];
		delta = multByElement(error, sigmoidD(input));

		double[][] djdw = mult(transpose(lp.getActiveOutput()), delta);	
		lp.setBias(minus(lp.getBias(), scale(delta,Main.ETA)));
		//printMatrix(error);
		
		// do all of these at end!!!!!!
		synapseToSet = minus(lp.getSynapse(),scale(djdw,Main.ETA));
	}
	

	public double getError() {
		return error;
	}
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
