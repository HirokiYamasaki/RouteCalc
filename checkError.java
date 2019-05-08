import java.util.ArrayList;
import java.util.List;

public class checkError {

	//正しい入力であれば"true" そうでないなら"false"を返すメソッド
	public String filter(String selected_X, String selected_Y, String[][] unselected) {
		List<String> listNum = new ArrayList<>();
		List<Boolean> listBool = new ArrayList<>();

		listNum.add(selected_X);
		listNum.add(selected_Y);
		for (int i=0; i<unselected.length; i++) {
			for (int j=0; j<unselected[0].length ;j++) {
				listNum.add(unselected[i][j]);
			}
		}

		//
		for (int i=0 ;i<listNum.size(); i++) {
			//System.out.println(listNum.get(i) + "listNum");
			boolean isNum = isNumber(listNum.get(i));
			listBool.add(isNum);
		}

		for (int i=0; i<listNum.size(); i++) {
			if (listBool.get(i) == true) {
				int intNum = Integer.parseInt(listNum.get(i));
				boolean overNum = overNumber(intNum);
				if (overNum == false) {
					listBool.set(i, false);
				}
			}
		}

		/*
		for (int i=0 ;i<listNum.size(); i++) {
			System.out.println(listBool.get(i) + " " + listNum.get(i) + " listBool listNum");
		}
		*/

		if (listBool.contains(false)) {
			return "false";
		} else {
			return "true";
		}
	}


	//数値に変換できないものがあればfalseを返す
	public boolean isNumber(String val) {
		try {
			Integer.parseInt(val);
			return true;
		} catch (NumberFormatException nfex) {
			return false;
		}
	}

	//-500 <= X <= 500に収まっているか
	public boolean overNumber(int val) {
		if (-500 <= val && val <= 500) {
			return true;
		} else {
			return false;
		}
	}
}
