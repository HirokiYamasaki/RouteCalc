/*
 * 五地点間の最短経路とその時の距離を計算するクラス
 *
 * Frame.javaで入力された値を受け取り、全ての経路とその時の距離を求め
 * 最短ルートとその時の距離をFrame.javaへ返す
 *
 */


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


public class CalcDistance {
	static String points;
	private double selected_X;
	private double selected_Y;
	private double transDouble1;
	private double transDouble2;
	static double resultDis;
	static String resultRoute;

	List<Double> list_X = new ArrayList<>();
	List<Double> list_Y = new ArrayList<>();
	List<String> list_perm = new ArrayList<>();

	List<BigDecimal> list_bdNum_X = new ArrayList<>();
	List<BigDecimal> list_bdNum_Y = new ArrayList<>();


	//画面から入力された値をdouble型へ変換する
	CalcDistance(String selected_X, String selected_Y, String[][] unselected) {
		resultDis = 1000000000;
		this.selected_X = Double.parseDouble(selected_X);
		this.selected_Y = Double.parseDouble(selected_Y);

		for (int i=0; i < unselected.length ;i++) {
			transDouble1 = Double.parseDouble(unselected[i][0]);
			transDouble2 = Double.parseDouble(unselected[i][1]);
			list_X.add(transDouble1);
			list_Y.add(transDouble2);
		}

		permutation("0123", "");
	}

	public ReturnValues start() {
		ReturnValues value = new ReturnValues();
		for (int i=0; i < list_perm.size() ;i++) {
			List<Double> xPoints = new ArrayList<>();
			List<Double> yPoints = new ArrayList<>();

			points = list_perm.get(i);
			int point1 = Integer.parseInt(points.substring(0,1));
			int point2 = Integer.parseInt(points.substring(1,2));
			int point3 = Integer.parseInt(points.substring(2,3));
			int point4 = Integer.parseInt(points.substring(3,4));

			System.out.println(point1 + " "+ point2 + " "+ point3 + " "+point4 + " 順列");

			xPoints.add(selected_X);
			xPoints.add(list_X.get(point1));
			xPoints.add(list_X.get(point2));
			xPoints.add(list_X.get(point3));
			xPoints.add(list_X.get(point4));
			xPoints.add(selected_X);

			yPoints.add(selected_Y);
			yPoints.add(list_Y.get(point1));
			yPoints.add(list_Y.get(point2));
			yPoints.add(list_Y.get(point3));
			yPoints.add(list_Y.get(point4));
			yPoints.add(selected_Y);

			//小数点第二位までを有効な数字とする（第三位以下を切り捨て）
			for (int j=0; j < xPoints.size() ;j++) {
				BigDecimal bdNumX = roundDown(xPoints.get(j));
				double doubleNumX = bdNumX.doubleValue();
				xPoints.set(j, doubleNumX);
			}

			for (int j=0; j < yPoints.size() ;j++) {
				BigDecimal bdNumY = roundDown(yPoints.get(j));
				double doubleNumY = bdNumY.doubleValue();
				yPoints.set(j, doubleNumY);
			}


			info(yPoints.size(), xPoints, yPoints);
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


	//小数点第3位で切り捨てるメソッド
	public BigDecimal roundDown(double val) {
		double S = val;
		BigDecimal bd = new BigDecimal(S);
		BigDecimal bdScale = bd.setScale(2, RoundingMode.FLOOR);
		return bdScale;
	}


	//2地点間の距離を計算
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
		double distX = distanceRoute(n, listX, listY);
		//System.out.println(distX + " sum");
		if (resultDis > distX) {
			resultDis = distX;
			resultRoute = points;
		}

	}
}

