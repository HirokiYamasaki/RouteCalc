/*
 * 画面表示クラス
 *
 * 入力された値をCheckError.javaやCalcDistancd.javaへ渡す
 *
 */


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


class ReturnValues {
	double resultdistance;
	String resultRoute;
}

public class Frame extends JFrame implements ActionListener{

	//各inputを格納する変数
	JTextField A_textX;
	JTextField A_textY;
	JTextField B_textX;
	JTextField B_textY;
	JTextField C_textX;
	JTextField C_textY;
	JTextField D_textX;
	JTextField D_textY;
	JTextField E_textX;
	JTextField E_textY;

	JRadioButton[] radio;

	//入力された値を一時的に保持する配列
	JTextField[][] textArr;

	//結果出力ラベル
	JLabel resultRoute;
	JLabel resultDistance;

	public String unselected[][];
	public int selected_num;
	public String selected_X;
	public String selected_Y;

	public static void main(String[] args) {
		Frame frame = new Frame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(10, 10, 600, 400);
		frame.setTitle("5地点間最短ルート計算プログラム");
		frame.setVisible(true);
	}

	Frame() {

		//ラジオボタン5つ作成
		ButtonGroup group = new ButtonGroup();

		radio = new JRadioButton[5];
		radio[0] = new JRadioButton();
		radio[1] = new JRadioButton();
		radio[2] = new JRadioButton();
		radio[3] = new JRadioButton();
		radio[4] = new JRadioButton();
		radio[0].setSelected(true);

		group.add(radio[0]);
		group.add(radio[1]);
		group.add(radio[2]);
		group.add(radio[3]);
		group.add(radio[4]);

		textArr = new JTextField[5][2];

		//入力を受け取るパネル
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

		///////////////

		JPanel descriptionPanel = new JPanel();

		JLabel description1 = new JLabel();
		JLabel description2 = new JLabel();
		JLabel description3 = new JLabel();


		description1.setText("xy平面上にある座標を5つを回る最短ルートを出力します");
		description2.setText("注: 出発点にする座標のラジオボタンをチェックしてください");
		description3.setText("入力は-500～500までの数値です");
		descriptionPanel.add(description1);
		descriptionPanel.add(description2);
		descriptionPanel.add(description3);

		////////////////////

		JLabel emptylbl = new JLabel("");
		JLabel lblX = new JLabel("X");
		JLabel lblY = new JLabel("Y");

		JPanel panelXY = new JPanel();
		panelXY.setLayout(new BoxLayout(panelXY, BoxLayout.LINE_AXIS));
		panelXY.add(emptylbl);
		panelXY.add(Box.createRigidArea(new Dimension(70,0)));
		panelXY.add(lblX);
		panelXY.add(Box.createRigidArea(new Dimension(100,0)));
		panelXY.add(lblY);

		//各座標の値を入力するパネル
		//AAAAAAAAAAAAA
		JLabel lblA = new JLabel("座標A");
		textArr[0][0] = new JTextField(10);
		textArr[0][1] = new JTextField(10);

		JPanel panel_A = new JPanel();
		panel_A.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
		//panel_A.setBackground(Color.orange);
		panel_A.add(radio[0]);
		panel_A.add(lblA);
		panel_A.add(textArr[0][0]);
		panel_A.add(textArr[0][1]);


		//BBBBBBBBBB
		JLabel lblB = new JLabel("座標B");
		textArr[1][0] = new JTextField(10);
		textArr[1][1] = new JTextField(10);

		JPanel panel_B = new JPanel();
		panel_B.setMaximumSize(new Dimension(Short.MAX_VALUE, 30));
		//panel_B.setBackground(Color.YELLOW);
		panel_B.add(radio[1]);
		panel_B.add(lblB);
		panel_B.add(textArr[1][0]);
		panel_B.add(textArr[1][1]);

		//CCCCCCCCCCCCCCCCC
		JLabel lblC = new JLabel("座標C");
		textArr[2][0] = new JTextField(10);
		textArr[2][1] = new JTextField(10);


		JPanel panel_C = new JPanel();
		panel_C.setMaximumSize(new Dimension(Short.MAX_VALUE, 30));
		//panel_C.setBackground(Color.blue);
		panel_C.add(radio[2]);
		panel_C.add(lblC);
		panel_C.add(textArr[2][0]);
		panel_C.add(textArr[2][1]);

		//DDDDDDDDDDDDDDDDDDDD
		JLabel lblD = new JLabel("座標D");
		textArr[3][0] = new JTextField(10);
		textArr[3][1] = new JTextField(10);

		JPanel panel_D = new JPanel();
		panel_D.setMaximumSize(new Dimension(Short.MAX_VALUE, 30));
		//panel_D.setBackground(Color.gray);
		panel_D.add(radio[3]);
		panel_D.add(lblD);
		panel_D.add(textArr[3][0]);
		panel_D.add(textArr[3][1]);

		//EEEEEEEEEEEEEEEEEEEEEEEE
		JLabel lblE = new JLabel("座標E");
		textArr[4][0] = new JTextField(10);
		textArr[4][1] = new JTextField(10);

		JPanel panel_E = new JPanel();
		panel_D.setMaximumSize(new Dimension(Short.MAX_VALUE, 20));
		//panel_E.setBackground(Color.red);
		panel_E.add(radio[4]);
		panel_E.add(lblE);
		panel_E.add(textArr[4][0]);
		panel_E.add(textArr[4][1]);



		mainPanel.add(descriptionPanel);
		mainPanel.add(panelXY);
		mainPanel.add(panel_A);
		mainPanel.add(panel_B);
		mainPanel.add(panel_C);
		mainPanel.add(panel_D);
		mainPanel.add(panel_E);



		JPanel outputPanel = new JPanel();
		outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
		//計算開始パネル
		JPanel calcPanel = new JPanel();
		JButton calcStart = new JButton("計算");
		calcStart.addActionListener(this);

		calcStart.setPreferredSize(new Dimension(100, 30));
		calcPanel.add(calcStart);

		//結果出力パネル
		//最短経路出力パネル
		JPanel routePanel = new JPanel();
		JLabel sentenseRoute = new JLabel("最短経路 : ");
		resultRoute = new JLabel("座標を入力してください");

		routePanel.add(sentenseRoute);
		routePanel.add(resultRoute);

		//最短経路の距離出力パネル
		JPanel distancePanel = new JPanel();
		JLabel sentenseDistance = new JLabel("最短距離 :");
		resultDistance = new JLabel("○○");

		distancePanel.add(sentenseDistance);
		distancePanel.add(resultDistance);


		outputPanel.add(calcPanel);
		outputPanel.add(routePanel);
		outputPanel.add(distancePanel);


		mainPanel.add(outputPanel);

		getContentPane().add(mainPanel, BorderLayout.CENTER);
	}


	//計算ボタンがクリックされたとき
	//
	public void actionPerformed(ActionEvent e){
		unselected = new String[4][2];
		int cnt = 0;
		for (int i=0; i < radio.length ;i++) {
			if (radio[i].isSelected()) {
				selected_num = i;
				selected_X = textArr[i][0].getText();
				selected_Y = textArr[i][1].getText();
			} else {
				unselected[cnt][0] = textArr[i][0].getText();
				unselected[cnt][1] = textArr[i][1].getText();
				cnt++;
			}
		}

		//入力された値をcheckError.javaへ渡す
		CheckError error = new CheckError();
		String flag = error.filter(selected_X, selected_Y, unselected);

		//flag = "0":正常な値  "1":数値ではない値  "2":規定値超過
		if (flag == "0") {

			//正常な値が入力されていたらCalcDistanceで最短ルートを算出
			CalcDistance distance = new CalcDistance(selected_X, selected_Y, unselected);
			ReturnValues value = distance.start();
			//System.out.println(value.resultdistance);
			//System.out.println(value.resultRoute);


			//resultRoute("0123"など)String型の文字列で戻ってきた経路のポイントをABCDEなどのアルファベットに直す。
			//以下の処理かなり汚い。解決策求む
			List<String> list_root = new ArrayList<>();
			if (selected_num == 0) {
				list_root.add("A");
				for (int i=0; i < value.resultRoute.length() ;i++) {
					System.out.println(value.resultRoute.substring(i,i+1));
					if (value.resultRoute.substring(i,i+1).equals("0")) {
						list_root.add("B");
					} else if (value.resultRoute.substring(i,i+1).equals("1")) {
						list_root.add("C");
					} else if (value.resultRoute.substring(i,i+1).equals("2")) {
						list_root.add("D");
					} else if (value.resultRoute.substring(i,i+1).equals("3")) {
						list_root.add("E");
					}
				}
				list_root.add("A");
			} else if (selected_num == 1) {
				list_root.add("B");
				for (int i=0; i < value.resultRoute.length() ;i++) {
					System.out.println(value.resultRoute.substring(i,i+1));
					if (value.resultRoute.substring(i,i+1).equals("0")) {
						list_root.add("A");
					} else if (value.resultRoute.substring(i,i+1).equals("1")) {
						list_root.add("C");
					} else if (value.resultRoute.substring(i,i+1).equals("2")) {
						list_root.add("D");
					} else if (value.resultRoute.substring(i,i+1).equals("3")) {
						list_root.add("E");
					}
				}
				list_root.add("B");
			} else if (selected_num == 2) {
				list_root.add("C");
				for (int i=0; i < value.resultRoute.length() ;i++) {
					System.out.println(value.resultRoute.substring(i,i+1));
					if (value.resultRoute.substring(i,i+1).equals("0")) {
						list_root.add("A");
					} else if (value.resultRoute.substring(i,i+1).equals("1")) {
						list_root.add("B");
					} else if (value.resultRoute.substring(i,i+1).equals("2")) {
						list_root.add("D");
					} else if (value.resultRoute.substring(i,i+1).equals("3")) {
						list_root.add("E");
					}
				}
				list_root.add("C");
			} else if (selected_num == 3) {
				list_root.add("D");
				for (int i=0; i < value.resultRoute.length() ;i++) {
					System.out.println(value.resultRoute.substring(i,i+1));
					if (value.resultRoute.substring(i,i+1).equals("0")) {
						list_root.add("A");
					} else if (value.resultRoute.substring(i,i+1).equals("1")) {
						list_root.add("B");
					} else if (value.resultRoute.substring(i,i+1).equals("2")) {
						list_root.add("C");
					} else if (value.resultRoute.substring(i,i+1).equals("3")) {
						list_root.add("E");
					}
				}
				list_root.add("D");
			} else if (selected_num == 4) {
				list_root.add("E");
				for (int i=0; i < value.resultRoute.length() ;i++) {
					System.out.println(value.resultRoute.substring(i,i+1));
					if (value.resultRoute.substring(i,i+1).equals("0")) {
						list_root.add("A");
					} else if (value.resultRoute.substring(i,i+1).equals("1")) {
						list_root.add("B");
					} else if (value.resultRoute.substring(i,i+1).equals("2")) {
						list_root.add("C");
					} else if (value.resultRoute.substring(i,i+1).equals("3")) {
						list_root.add("D");
					}
				}
				list_root.add("E");
			}

			//小数点第二位まで切り捨てて画面出力
			System.out.println(list_root);
			String point0 = list_root.get(0);
			String point1 = list_root.get(1);
			String point2 = list_root.get(2);
			String point3 = list_root.get(3);
			String point4 = list_root.get(4);

			resultRoute.setText(point0 + "→" + point1 + "→" + point2 + "→" + point3 + "→" + point4 + "→" + point0);
			resultDistance.setText(String.format("%.2f", value.resultdistance));

		} else if (flag == "1") {
			resultRoute.setText("数値を入力してください");
			resultDistance.setText("○○");
		} else if (flag == "2") {
			resultRoute.setText("-500～500までの値を入力してください");
			resultDistance.setText("○○");
		}
	}


}

