import java.util.ArrayList;
import java.util.List;

public class checkError {

	//正しい入力であれば"0" 数値じゃない入力は"1" -500以下500以上なら"2" を返す
	public String filter(String selected_X, String selected_Y, String[][] unselected) {
		List<String> listNum = new ArrayList<>();
		List<String> listBool = new ArrayList<>();

		listNum.add(selected_X);
		listNum.add(selected_Y);
		for (int i=0; i<unselected.length; i++) {
			for (int j=0; j<unselected[0].length ;j++) {
				listNum.add(unselected[i][j]);
			}
		}

		//数値確認
		for (int i=0 ;i<listNum.size(); i++) {
			String isNum = isNumber(listNum.get(i));
			listBool.add(isNum);
		}

		//規定値範囲確認
		for (int i=0; i<listNum.size(); i++) {
			if (listBool.get(i) != "notNum") {
				double intNum = Double.parseDouble(listNum.get(i));
				boolean overNum = overNumber(intNum);
				if (overNum == false) {
					listBool.set(i, "overNum");
				}
			}
		}


		if (listBool.contains("notNum")) {
			return "1";
		} else if (listBool.contains("overNum")) {
			return "2";
		} else {
			return "0";
		}
	}


	//数値に変換できないものがあればfalseを返す
	public String isNumber(String val) {
		try {
			Double.parseDouble(val);
			return "0";
		} catch (NumberFormatException nfex) {
			return "notNum";
		}
	}

	//-500 <= X <= 500に収まっているか
	public boolean overNumber(double val) {
		if (-500 <= val && val <= 500) {
			return true;
		} else {
			return false;
		}
	}
}
