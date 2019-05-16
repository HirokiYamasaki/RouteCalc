/*
 * エラー処理クラス
 *
 * Frame.javaからの入力を受け取り "0" or "1" or "2" をFrame.javaへ返す
 * "0":正常な入力
 * "1":数値ではない入力
 * "2":-500～500 に範囲に収まっていない入力
 *
 */


import java.util.ArrayList;
import java.util.List;

public class CheckError {

	//正しい入力であれば"0" 数値じゃない入力は"1" -500以下500以上なら"2" を返す
	public static String filter(String selected_X, String selected_Y, String[][] unselected) {

		//入力された値を保持
		List<String> listNum = new ArrayList<>();

		//正常な値であれば"0" 異常な値はエラー文字列を保持
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
				String overNum = overNumber(intNum);
				if (overNum == "overNum") {
					listBool.set(i, "overNum");
				}
			}
		}

		//eが含まれているか
		for (int i=0; i<listNum.size(); i++) {
			if (listBool.get(i) != "notNum" && listBool.get(i) != "overNum") {
				String eNum = eNumber(listNum.get(i));
				if (eNum == "containsE") {
					listBool.set(i, "contE");
				}
			}
		}

		//Frame.javaへ返す
		if (listBool.contains("notNum") || listBool.contains("contE")) {
			return "1";
		} else if (listBool.contains("overNum")) {
			return "2";
		} else {
			return "0";
		}
	}


	//数値に変換できないものがあればfalseを返す
	//返り値  正常な値:"0" 異常な値:notNum
	private static String isNumber(String val) {
		try {
			Double.parseDouble(val);
			return "0";
		} catch (NumberFormatException nfex) {
			return "notNum";
		}
	}

	//e(指数表現が含まれているか)
	//返り値 正常な値:"0" 異常な値:"containsE"
	private static String eNumber(String val) {
		if (val.contains("e")) {
			return "containsE";
		} else {
			return "0";
		}
	}


	//-500 <= X <= 500に収まっているか
	//返り値 正常な値:"0" 異常な値:"overNum"
	private static String overNumber(double val) {
		if (-500 <= val && val <= 500) {
			return "0";
		} else {
			return "overNum";
		}
	}
}
