package testing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import net.Brain;
import net.Network;
import net.Point;

public class Main extends JFrame {
	Graphics _g;
	int iterations = 0;
	Brain b = new Brain();	
	public static final double ETA = 0.3;
	int[] networkNodes = {2,3,1};
	Network n = new Network(networkNodes);
	
	List<Point> plist = new ArrayList<Point>();
	public static void main(String[] args) {	

		Main m = new Main();
		m.setVisible(true);
		m.setSize(400, 400);


	}

	public Main() {}

	double correct = 1;
	double incorrect = 2;
	public void paint(Graphics g) {
		_g = g;
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		Line2D lin = new Line2D.Float(0, 200, 400, 200);
		Line2D lin2 = new Line2D.Float(200, 0, 200, 400);
		g2.draw(lin);
		g2.draw(lin2);
		double[][] inps2 = {{1.0/400, 2.0/400}};
		//double[][] targ = {{0}};
		
		
		//System.out.println("|||||||||||||||||||||||||||");
		//b.train(new Point(1,2),0);
		//System.out.println(new Point(1,2).value);
		for(Point p : newList2()) {
			double[][] inps = {{((double)(p.getX()))/400, ((double)(p.getY()))/400}};
	
			double i = 1;
			if(i == p.getValue()) {
				if(p.getValue() == 1) {
					g2.drawOval(p.getX(), p.getY(), 10, 10);
				}else {
					g2.drawRect(p.getX(), p.getY(), 10, 10);
				}
			}else {
				if(p.getValue() == 1) {
					g2.fillOval(p.getX(), p.getY(), 10, 10);
				}else {
					g2.fillRect(p.getX(), p.getY(), 10, 10);
				}
			}
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		boolean done= false;
		while(true) {
			double[][] inp = new double[newList().size()][2];
			double[][] target = new double[newList().size()][1];			

			iterations++;

			if(!done) {

				for(Point p : newList2()) {
					double[][] inps = {{((double)(p.getX()))/400, ((double)(p.getY()))/400}};
					double[][] targ = {{p.getValue()}};
					n.train(inps,targ);
					b.train(p, ETA);

				}
			}

			g2.clearRect(0,0, 400, 400);
			g2.draw(lin);
			g2.draw(lin2);
			correct = 0;
			incorrect = 0;

			for(Point p : newList2()) {
				double[][] inps = {{((double)(p.getX()))/400, ((double)(p.getY()))/400}};
				double j = n.guess(inps);
				if(j == p.getValue()) {
					correct++;
					if(p.getValue() == 1) {

						g2.drawOval(p.getX(), p.getY(), 10, 10);
					}else {
						g2.drawRect(p.getX(), p.getY(), 10, 10);
					}
				}else {
					incorrect++;
					if(p.getValue() == 1) {
						g2.fillOval(p.getX(), p.getY(), 10, 10);
					}else {
						g2.fillRect(p.getX(), p.getY(), 10, 10);
					}
				}

			}
		}
	}
	public List<Point> newList() {
		plist = new ArrayList<Point>();
		for(int i = 0; i < 1; i++) {
			plist.add(new Point());
		}
		return plist;
	}

	public List<Point> newList2() {
		plist = new ArrayList<Point>();
		for(int i = 0; i < 1000; i++) {
			plist.add(new Point());
		}
		return plist;
	}


}
