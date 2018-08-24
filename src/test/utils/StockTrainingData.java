package test.utils;

import java.util.Date;

public class StockTrainingData {
	private double[][] inp_; 
	private double[][] target_;
	private Date d_;
	
	public StockTrainingData(double[][] inp, double[][] target, Date d) {
		inp_ = inp;
		target_ = target;
		d_ = d;
	}
	
	public double[][] getInput(){
		return inp_;
	}
	
	public double[][] getTarget(){
		return target_;
	}
	
	public Date getDate(){
		return d_;
	}
	
	public void normalize(int maximum) {
		for(int i = 0; i < inp_.length ; i++) {
			for(int j = 0; j < inp_[0].length ; j++) {
				inp_[i][j] = inp_[i][j]/maximum;
			}
		}
		for(int i = 0; i < target_.length ; i++) {
			for(int j = 0; j < target_[0].length ; j++) {
				target_[i][j] = target_[i][j]/maximum;
			}
		}
	}

}
