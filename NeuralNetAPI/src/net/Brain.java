package net;

import java.util.Arrays;

public class Brain {

	double bias1 = 1;	
	double[][] bias2 = {{1,1,1}};

	double[][] synapse1 = {{Math.random()*2 -1,Math.random()*2 -1, Math.random()*2 -1},
			{Math.random()*2 -1,Math.random()*2 -1,Math.random()*2 -1}};

	double[][] synapse2 = {{Math.random()*2 -1},{Math.random()*2-1},{Math.random()*2-1}};


	public Brain() {}

	public double guess(Point p) {

		double[][] x = {{p.x/400.0,p.y/400.0}};	

		double[][] z2 = addBias(mult(x,synapse1));

		double[][] a2 = sigmoid(z2);

		double[][] z3 = mult(a2,synapse2);
		z3 = addConst(z3, bias1);

		double[][] yhat = sigmoid(z3);	

		if(yhat[0][0] > 0.5) {
			return 1;
		}else {
			return 0;
		}
	}

	public void train(Point p, double rate) {
		//Initial values from forward propogation
		double[][] target = {{p.value}};

		double[][] input = {{((double)(p.x))/400.0,((double)(p.y))/400.0}};

		double[][] inputtimesweights = addBias(mult(input,synapse1));

		
		double[][] inputsTimesWeightsActive = sigmoid(inputtimesweights);

		double[][] secondLayerInputsTimesWeights = mult(inputsTimesWeightsActive,synapse2);
		secondLayerInputsTimesWeights = addConst(secondLayerInputsTimesWeights, bias1);

		double[][] output = sigmoid(secondLayerInputsTimesWeights);		

		double[][] error = minus(output,target);

		double[][] d3 = multByElement(error, sigmoidD(secondLayerInputsTimesWeights));

		double[][] djdw2 = mult(transpose(inputsTimesWeightsActive), d3);		
		
	
		
		double [][] error1 = mult(d3,transpose(synapse2));

		double[][] d2 = multByElement(error1,sigmoidD(inputtimesweights));

		double[][] djdw1 = mult(transpose(input), d2);

		//Altering weights
		synapse2 = minus(synapse2,scale(djdw2,rate));
		synapse1 = minus(synapse1,scale(djdw1,rate));	
		bias1 = bias1 - d3[0][0] * rate;
		bias2 = minus(bias2,scale(d2,rate)) ;
	}
	public void printSize(double[][] x) {
		System.out.println(x.length + " r " + x[0].length + " c ");

	}
	public void train(double[][] x, double[][] target) {

		//Initial values from forward propogation
		double[][] z2 = addBias(mult(x,synapse1));

		double[][] a2 = sigmoid(z2);

		double[][] z3 = mult(a2,synapse2);

		z3 = addConst(z3, bias1);

		double[][] yhat = sigmoid(z3);		


		double[][] error = minus(yhat,target);

		double[][] d3 = multByElement(error, sigmoidD(z3));

		double[][] djdw2 = mult(transpose(a2), d3);

		double [][] error1 = mult(d3,transpose(synapse2));

		double[][] d2 = multByElement(error1,sigmoidD(z2));

		double[][] djdw1 = mult(transpose(x), d2);
		
		//Altering weights
		synapse2 = minus(synapse2,djdw2);
		synapse1 = minus(synapse1,djdw1);	
		for(int i = 0; i < d3.length; i++) {
			bias1 = bias1 - d3[i][0];
		}
		
		for(int i = 0; i < bias2.length; i++) {
			for(int j = 0; j < bias2[0].length; j++) {
				for(int k = 0; k < d2.length; k++) {
					bias2[i][j] = bias2[i][j] - d2[k][j];
				}
			}
		}
	}


	public void printMatrix(double[][] a) {

		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[0].length; j++) {
				System.out.print(a[i][j] + ",");
			}
			System.out.println("");
		}
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

	public double[][] scale(double[][] a, double b){


		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[0].length; j++) {
				a[i][j] = a[i][j]*b;
			}
		}

		return a;

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

	public double[][] transpose(double[][] a){
		double[][] out = new double[a[0].length][a.length];

		for(int i = 0; i < a[0].length; i++) {
			for(int j = 0; j < a.length; j++) {
				out[i][j] = a[j][i];
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

	public double[][] add(double[][] a, double[][] b){
		double[][] out = new double[a.length][a[0].length];

		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[0].length; j++) {
				out[i][j] = a[i][j] + b[i][j];
			}
		}
		return out;

	}

	public double[][] addConst(double[][] a, double c){
		double[][] out = new double[a.length][a[0].length];

		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[0].length; j++) {
				out[i][j] = a[i][j] + c;
			}
		}
		return out;

	}

	public double[][] addBias(double[][] a){

		double[][] out = new double[a.length][a[0].length];
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[0].length; j++) {
				out[i][j] = a[i][j] + bias2[0][j];
			}
		}


		return out;

	}


}
