import java.util.ArrayList;
import java.util.List;


public class CalcDistance {
	static String Z0;
	private int Z1;
	private int Z2;
	private int Z3;
	private int Z4;
	private double selected_X;
	private double selected_Y;
	private double transInt1;
	private double transInt2;
	static double resultDis;
	static String resultRoute;

	List<Double> list_X = new ArrayList<>();
	List<Double> list_Y = new ArrayList<>();
	List<String> list_perm = new ArrayList<>();


	//画面から入力された値をint型へ変換する
	CalcDistance(String selected_X, String selected_Y, String[][] unselected) {
		resultDis = 1000000000;
		this.selected_X = Double.parseDouble(selected_X);
		this.selected_Y = Double.parseDouble(selected_Y);

		for (int i=0; i < unselected.length ;i++) {
			transInt1 = Double.parseDouble(unselected[i][0]);
			transInt2 = Double.parseDouble(unselected[i][1]);
			list_X.add(transInt1);
			list_Y.add(transInt2);
		}

		//System.out.println(selected_X + " " + selected_Y + " selected");


		permutation("0123", "");
	}

	public ReturnValues start() {
		ReturnValues value = new ReturnValues();
		for (int i=0; i < list_perm.size() ;i++) {
			List<Double> listX = new ArrayList<>();
			List<Double> listY = new ArrayList<>();
			//System.out.println(list_perm.get(i));
			Z0 = list_perm.get(i);
			Z1 = Integer.parseInt(Z0.substring(0,1));
			Z2 = Integer.parseInt(Z0.substring(1,2));
			Z3 = Integer.parseInt(Z0.substring(2,3));
			Z4 = Integer.parseInt(Z0.substring(3,4));

			System.out.println(Z1 + " "+ Z2 + " "+ Z3 + " "+Z4 + " 順列");

			listX.add(selected_X);
			listX.add(list_X.get(Z1));
			listX.add(list_X.get(Z2));
			listX.add(list_X.get(Z3));
			listX.add(list_X.get(Z4));
			listX.add(selected_X);

			listY.add(selected_Y);
			listY.add(list_Y.get(Z1));
			listY.add(list_Y.get(Z2));
			listY.add(list_Y.get(Z3));
			listY.add(list_Y.get(Z4));
			listY.add(selected_Y);

			for (int k=0;k<listX.size();k++) {
				System.out.println(listX.get(k) + " " + listY.get(k) + " listXY");
			}
			info(listX.size(), listX, listY);
		}

		//最短経路の距離とルートを戻す
		value.resultdistance = resultDis;
		value.resultRoute = resultRoute;
		return value;
 	}



	//順列を生成
	public void permutation(String q, String ans){
        // Base Case
        if(q.length() <= 1) {
            list_perm.add(ans + q);
        }
        // General Case
        else {
            for (int i = 0; i < q.length(); i++) {
                permutation(q.substring(0, i) + q.substring(i + 1),
                        ans + q.charAt(i));
            }
        }
    }


	//2地点間の距離を出す
	/*
	static double distancePoint(Point p, Point q) {
		return p.distance(q);
	}
	*/
	static double distancePoint(double x1, double y1, double x2, double y2) {
		double distance = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));

		return distance;
	}

	//経路の距離
	static double distanceRoute(int n, List<Double> listX, List<Double> listY) {
		double sum = 0;
		for (int i=0; i<n-1 ;i++) {
			sum += distancePoint(listX.get(i), listY.get(i), listX.get(i+1), listY.get(i+1));
		}

		return sum;
	}

	//経路の表示
	static void info(int n, List<Double> listX, List<Double> listY) {
		double XXX = distanceRoute(n, listX, listY);
		System.out.println(XXX + " sum");
		if (resultDis > XXX) {
			resultDis = XXX;
			resultRoute = Z0;
		}

	}
}

