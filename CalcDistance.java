import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class CalcDistance {
	static String Z0;
	private int Z1;
	private int Z2;
	private int Z3;
	private int Z4;
	private int selected_X;
	private int selected_Y;
	private int transInt1;
	private int transInt2;
	static double resultDis;
	static String resultRoute;

	List<Integer> list_X = new ArrayList<>();
	List<Integer> list_Y = new ArrayList<>();
	List<String> list_perm = new ArrayList<>();


	//画面から入力された値をint型へ変換する
	CalcDistance(String selected_X, String selected_Y, String[][] unselected) {
	    resultDis = 1000000000;
		this.selected_X = Integer.parseInt(selected_X);
		this.selected_Y = Integer.parseInt(selected_Y);

		for (int i=0; i < unselected.length ;i++) {
			transInt1 = Integer.parseInt(unselected[i][0]);
			transInt2 = Integer.parseInt(unselected[i][1]);
			list_X.add(transInt1);
			list_Y.add(transInt2);
		}

		//System.out.println(selected_X + " " + selected_Y);

		permutation("0123", "");
	}

	public ReturnValues start() {
		ReturnValues value = new ReturnValues();
		for (int i=0; i < list_perm.size() ;i++) {
			//System.out.println(list_perm.get(i));
			Z0 = list_perm.get(i);
			Z1 = Integer.parseInt(Z0.substring(0,1));
			Z2 = Integer.parseInt(Z0.substring(1,2));
			Z3 = Integer.parseInt(Z0.substring(2,3));
			Z4 = Integer.parseInt(Z0.substring(3,4));

			System.out.println(Z1 + " "+ Z2 + " "+ Z3 + " "+Z4);
			//System.out.println(list_X.get(Z1) + " " + list_Y.get(Z1));
			Point start = new Point(selected_X,selected_Y);
			Point a = new Point(list_X.get(Z1), list_Y.get(Z1));
			Point b = new Point(list_X.get(Z2), list_Y.get(Z2));
			Point c = new Point(list_X.get(Z3), list_Y.get(Z3));
			Point d = new Point(list_X.get(Z4), list_Y.get(Z4));
			List<Point> route = new ArrayList<Point>();
			route.add(start);
			route.add(a);
			route.add(b);
			route.add(c);
			route.add(d);
			route.add(start);
			info(route.size(), route);
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
            //System.out.println(ans + q);
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
	static double distancePoint(Point p, Point q) {
		return p.distance(q);
	}

	//経路の距離
	static double distanceRoute(int n, List<Point> route) {
		double sum = 0;
		for (int i=0; i<n-1 ;i++) {
			sum += distancePoint(route.get(i), route.get(i+1));
		}
		return sum;
	}

	//経路の表示
	static void info(int n, List<Point> route) {
		for (int i=0; i<n;i++) {
			System.out.println(route.get(i).x + " " + route.get(i).y + " " + "座標");
			//System.out.println();
		}
		System.out.println("distance " + distanceRoute(n,route));
		//System.out.println(Z0 + " Z0");
		if (resultDis > distanceRoute(n, route)) {
			resultDis = distanceRoute(n, route);
			resultRoute = Z0;
		}

	}
}

