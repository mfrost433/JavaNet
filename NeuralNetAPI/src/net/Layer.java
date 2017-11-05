package net;

import testing.Main;

public class Layer {
	double[][] synapseToSet;
	double[][] synapse;
	double[][] bias;
	double[][] input;
	double[][] out;
	double[][] z;
	double[][] a;
	double[][] delta;
	Layer layerPrev;
	public Layer(int numNeurons, int numOut) {

		bias = new double[1][numOut];
		synapse = new double[numNeurons][numOut];


		for(int i = 0; i < synapse.length; i++) {
			for(int j = 0; j < synapse[0].length; j++) {
				synapse[i][j] = Math.random()*2 -1;
			}
		}

		for(int j = 0; j < bias[0].length; j++) {
			bias[0][j] = 1;
		}
	}

	public void feedForward(double[][] inp) {
		input = inp;

		a = sigmoid(inp);	
		out = addBias(mult(a,synapse));

	}

	public double[][] getDelta(){
		return delta;
	}

	public double[][] getSynapse(){
		return synapse;
	}

	public double[][] getActiveOutput(){
		return a;
	}

	public double[][] getInactiveOutput(){
		return out;
	}
	public double[][] getInput(){
		return input;
	}

	public double[][] getOutput(){
		return out;
	}

	public void setSynapse( double[][] syn){
		synapse = syn;
	}
	public void setBias( double[][] b){
		bias = b;
	}
	public double[][] getBias(){
		return bias;
	}
	public void printSize(double[][] x) {
		System.out.println(x.length + " r " + x[0].length + " c ");

	}
	public void backPropagate(Layer lp, Layer lf) {
		layerPrev = lp;
		double [][] error1 = mult(lf.getDelta(),transpose(synapse));

		delta = multByElement(error1,sigmoidD(input));

		double[][] djdw = mult(transpose(lp.getInput()), delta);
		synapseToSet = minus(lp.getSynapse(),scale(djdw,Main.ETA));
		lp.setBias(minus(lp.getBias(), scale(delta,Main.ETA)));
	}
	
	public void setSynapse() {
		layerPrev.setSynapse(synapseToSet);
	}

	public double[][] sigmoidD(double[][] a){

		double[][] out = new double[a.length][a[0].length];

		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[0].length; j++) {
				out[i][j] = 0.0;

			}
		}		

		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[0].length; j++) {
				out[i][j] =  Math.pow(Math.E , -a[i][j]) / (Math.pow(1.0 + Math.pow(Math.E , -a[i][j]),2));
			}
		}

		return out;
	}

	public double[][] minus(double[][] a, double[][] b){
		double[][] out = new double[a.length][a[0].length];

		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[0].length; j++) {
				out[i][j] = a[i][j] - b[i][j];
			}
		}
		return out;

	}

	public void printMatrix(double[][] a) {

		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[0].length; j++) {
				System.out.print(a[i][j] + ",");
			}
			System.out.println("");
		}
	}
	public double[][] multByElement(double[][] a, double[][] b){

		double[][] out = new double[a.length][b[0].length];

		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < b[0].length; j++) {
				out[i][j] = 0.0;

			}
		}

		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[0].length; j++) {
				out[i][j] = a[i][j]*b[i][j];
			}
		}

		return out;

	}

	public double[][] mult(double[][] a, double[][] b){

		double[][] out = new double[a.length][b[0].length];

		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < b[0].length; j++) {
				out[i][j] = 0.0;

			}
		}

		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < b[0].length; j++) {
				for(int k = 0; k < a[0].length; k++) {
					out[i][j] += a[i][k]*b[k][j];
				}
			}
		}

		return out;

	}

	public double[][] sigmoid(double[][] a){

		double[][] out = new double[a.length][a[0].length];

		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[0].length; j++) {
				out[i][j] = 0.0;

			}
		}

		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[0].length; j++) {
				out[i][j] = 1.0 / (1.0 + Math.pow(Math.E , -a[i][j]));
			}
		}

		return out;
	}

	public double[][] addBias(double[][] a){

		double[][] out = new double[a.length][a[0].length];
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[0].length; j++) {
				out[i][j] = a[i][j] + bias[0][j];
			}
		}


		return out;

	}

	public double[][] transpose(double[][] a){
		double[][] out = new double[a[0].length][a.length];

		for(int i = 0; i < a[0].length; i++) {
			for(int j = 0; j < a.length; j++) {
				out[i][j] = a[j][i];
			}
		}
		return out;

	}
	
	public double[][] scale(double[][] a, double b){


		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[0].length; j++) {
				a[i][j] = a[i][j]*b;
			}
		}

		return a;

	}



}
