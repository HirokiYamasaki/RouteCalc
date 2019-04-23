//import java.awt.Point;

public class CalcDistance {
	private String X_A;
	private String Y_A;
	//private int X_B;
	//private int Y_B;
	//private int X_C;
	//private int Y_C;
	//private int X_D;
	//private int Y_D;
	//private int X_E;
	//private int Y_E;

	CalcDistance(String X_A, String Y_A) {
		this.X_A = X_A;
		this.Y_A = Y_A;
 	}

	public String add() {
		return this.X_A + this.Y_A;
	}

	/*
	//2地点間の距離を出す
	public double distancePoint(Point p, Point q) {
		return p.distance(q);
	}
	*/


	public void printData() {
		System.out.println(this.add());
	}
}
