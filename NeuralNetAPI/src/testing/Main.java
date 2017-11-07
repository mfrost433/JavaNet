package testing;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import data.DataRequester;
import data.Request;
import net.Network;
import net.Point;

public class Main extends JFrame {
	
	/*
	 * please fix this matt 
	 */
	private static final long serialVersionUID = 1L;
	Graphics _g;
	int iterations = 0;
	Brain b = new Brain();	

	int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 
			59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 
			139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199};

	public static final double ETA = 0.3;	
	int[] networkNodes = {5,20,20,1};
	Network n = new Network(networkNodes);
	List<Point> plist = new ArrayList<Point>();


	public static void main(String[] args) {


		DataRequester requester = DataRequester.getInstance();
		Request request = new Request("GOOG");

		try {
			requester.sendGetRequest(request);
		} catch (Exception e) {
			e.printStackTrace();
		}



		//Main m = new Main();
		/*

	/*
	public static void main(String[] args) {
		Main m = new Main();

		URL u;

		try {
			u = new URL("http://freegeoip.net/csv/8.8.8.8");
			URLConnection connection = u.openConnection();
			InputStream i = connection.getInputStream();
			InputStreamReader ir = new InputStreamReader(i);
			BufferedReader br = new BufferedReader(ir);
			System.out.println(br.readLine());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}





		double[][] inps = new double[6][400];
		double[][] inp2=new double[2][400];
		ImageProcessor img = new ImageProcessor();
		int[] networkNodes = {2,3,1};



		 */

		/*

		for(int i = 1; i <= 6; i ++) {
			inps[i-1] = img.imageToArray(new File("D:\\Neural Net\\Neural-net-deep-learning-API\\Neural Net Images\\2-"+ i +".png"))[0];
		}
		for(int i = 1; i <= 2; i++) {
			inp2[i-1] = img.imageToArray(new File("D:\\Neural Net\\Neural-net-deep-learning-API\\Neural Net Images\\not2-" + i +".png"))[0];
		}

		Network n = new Network(networkNodes);
		int c = 0;
		double sumOfError = 0;
		int count = 0;
		while(count < 1000) {	
			double[][] temp = new double[1][400];

			double[][] targ = {{1}};
			double[][] targ2 = {{0}};
			for(int i = 0; i < 6; i++) {
				temp[0] = inps[i];
				sumOfError =  sumOfError +n.train(temp,targ);
			}
			for(int i = 0; i < 2; i++) {
				temp[0] = inp2[i];
				sumOfError =  sumOfError +n.train(temp,targ2);
			}
			if(c == 10) {
				c = 0;
				sumOfError = 0;
			}
			c++;
			count++;

		}

		for(int i = 0 ; i < 100; i++) {

			for(int j = 1; j <= 6; j++) {
				System.out.println(n.guess(img.imageToArray(new File("D:\\Neural Net\\Neural-net-deep-learning-API\\Neural Net Images\\2-"+j+".png"))));
			}
			System.out.println("|||||||||||||||||||");
			System.out.println(n.guess(img.imageToArray(new File("D:\\Neural Net\\Neural-net-deep-learning-API\\Neural Net Images\\not2-1.png"))));
			System.out.println(n.guess(img.imageToArray(new File("D:\\Neural Net\\Neural-net-deep-learning-API\\Neural Net Images\\not2-2.png"))));
			System.out.println(n.guess(img.imageToArray(new File("D:\\Neural Net\\Neural-net-deep-learning-API\\Neural Net Images\\blank.png"))));
			System.out.println("|||||||||||||||||||");
			System.out.println(n.guess(img.imageToArray(new File("D:\\Neural Net\\Neural-net-deep-learning-API\\Neural Net Images\\test.png"))));
			for(int j = 2; j <= 5; j++) {
				System.out.println(n.guess(img.imageToArray(new File("D:\\Neural Net\\Neural-net-deep-learning-API\\Neural Net Images\\test"+j+".png"))));
			}
			System.out.println("|||||||||||||||||||");
		}

	}

	public Main() {
		this.setSize(400, 400);
		setVisible(true);
	}


	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.clearRect(0, 0, 400, 400);
		g2.drawLine(0, 0, 400, 400);

		for(Point p : newList2()) {

			double[][] inp = {{}};
			double guess = n.guess(inp);

			if(guess == p.getValue()) {
				if(guess == 1) {
					g2.drawOval(p.getX(), p.getY(), 10, 10);
				}else {
					g2.drawRect(p.getX(), p.getY(), 10, 10);
				}
			}else {
				if(guess == 1) {
					g2.fillOval(p.getX(), p.getY(), 10, 10);
				}else {
					g2.fillRect(p.getX(), p.getY(), 10, 10);
				}
			}

		}

		test();
		//double[][] inps = new double[arrSize][2];

		//int i = 0;
		while(true) {
			for(int x = 5; x < primes.length; x++) {
				double[][] inps = {{((double)(primes[x-1]))/1000,((double)(primes[x-2]))/1000,((double)(primes[x-3]))/1000,((double)(primes[x-4]))/1000,((double)(primes[x-5]))/1000}};
				double[][] targs = {{((double)(primes[x]))/1000}};

				//System.out.println(targs[0][0]);

				n.train(inps, targs);
				//System.out.println(Math.abs((int)(n.guess(inps)*1000)) + " vs " + primes[x]);
				//System.out.println(Math.abs((int)(n.guess(inps)*1000) - primes[x] ) < 2);
				test();


				for(Point p : newList2()) {

					double[][] inps2 = {{((double)(p.getX()))/400,((double)(p.getY()))/400}};
					double[][] targ2 = {{p.getValue()}};
					//n.train(inps2, targ2);
					if(i == arrSize) {
						n.train(inps, targs);
						i = 0;
						inps =  new double[arrSize][2];
						targs = new double[arrSize][1];
					}
					inps[i][0] = ((double)(p.getX()))/400;
					inps[i][1] = ((double)(p.getY()))/400;
					targs[i][0] = p.getValue();
					i++;


				}
		}


			for(int j = 0; j < 100; j ++) {
				g2.clearRect(0, 0, 400, 400);
				for(Point p : newList2()) {

					double[][] inp = {{((double)(p.getX()))/400,((double)(p.getY()))/400}};
					double guess = n.guess(inp);

					if(guess == p.getValue()) {
						if(guess == 1) {
							g2.drawOval(p.getX(), p.getY(), 10, 10);
						}else {
							g2.drawRect(p.getX(), p.getY(), 10, 10);
						}
					}else {
						if(guess == 1) {
							g2.fillOval(p.getX(), p.getY(), 10, 10);
						}else {
							g2.fillRect(p.getX(), p.getY(), 10, 10);
						}
					}

				}
			}

		}
	}
	double correct = 1;
	double incorrect = 2;
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
	private void test() {
		double b;
		int inpS;
		int correct = 0;
		for(int i = 1; i < 100; i++) {
			inpS =(int) (Math.random()*(primes.length-1)) + 1;
			double[][] inp = {{181.0/1000, 191.0/1000, 193.0/1000, 197.0/1000, 199.0/1000}};
			b = n.guess(inp);
			System.out.println((int)(b*1000));
		}
		//System.out.println(correct + " out of 100");
	}
		 */
	}
}
