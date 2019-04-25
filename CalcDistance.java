import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

//import java.awt.Point;

/*
class route{

	String name;
	int x;
	int y;

	route(String _name,int _x,int _y){
		this.name = _name;
		this.x = _x;
		this.y = _y;
	}
}
*/
class Info{
	String key;
	int x;
	int y;
}

public class CalcDistance {
	private String Z0;
	private int Z1;
	private int Z2;
	private int Z3;
	private int Z4;
	private int selected_X;
	private int selected_Y;
	private int transInt1;
	private int transInt2;
	//private List ssss;

	//HashMap<String, List> point = new HashMap<String, List>();
	List<Integer> list_X = new ArrayList<>();
	List<Integer> list_Y = new ArrayList<>();
	List<String> list_perm = new ArrayList<>();

	//画面から入力された値をint型へ変換する
	CalcDistance(String selected_X, String selected_Y, String[][] unselected) {
	    this.selected_X = Integer.parseInt(selected_X);
		this.selected_Y = Integer.parseInt(selected_Y);

		for (int i=0; i < unselected.length ;i++) {
			transInt1 = Integer.parseInt(unselected[i][0]);
			transInt2 = Integer.parseInt(unselected[i][1]);
			list_X.add(transInt1);
			list_Y.add(transInt2);
		}

		System.out.println(selected_X + " " + selected_Y);
		/*
		for (int i=0; i < list_X.size(); i++) {
			System.out.println(list_X.get(i) + " " + list_Y.get(i));
		}
		*/

		permutation("0123", "");

		for (int i=0; i < list_perm.size() ;i++) {
			//System.out.println(list_perm.get(i));
			Z0 = list_perm.get(i);
			Z1 = Integer.parseInt(Z0.substring(0,1));
			Z2 = Integer.parseInt(Z0.substring(1,2));
			Z3 = Integer.parseInt(Z0.substring(2,3));
			Z4 = Integer.parseInt(Z0.substring(3,4));

			System.out.println(Z1 + " "+ Z2 + " "+ Z3 + " "+Z4);
		}
		//System.out.println(Z0 + " " + Z1 + " "+ Z2 + " "+ Z3);
 	}

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



	public int add() {
		return this.selected_X + this.selected_Y;
	}



	//2地点間の距離を出す
	public double distancePoint(Point p, Point q) {
		return p.distance(q);
	}



	public void printData() {
		System.out.println(this.add());
	}
}
