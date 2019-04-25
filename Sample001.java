import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 */

/**
 * @author h_yamasaki
 *
 */
public class Sample001 extends JFrame implements ActionListener{

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
	JTextField[][] textArr;

	public String unselected[][];
	public String selected_X;
	public String selected_Y;


	public static void main(String[] args) {
		Sample001 frame = new Sample001();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(10, 10, 600, 400);
		frame.setTitle("タイトル");
		frame.setVisible(true);
	}

	Sample001() {

		//ラジオボタン5つ作成
		ButtonGroup group = new ButtonGroup();

		radio = new JRadioButton[5];
		radio[0] = new JRadioButton();
		radio[1] = new JRadioButton();
		radio[2] = new JRadioButton();
		radio[3] = new JRadioButton();
		radio[4] = new JRadioButton();

		group.add(radio[0]);
		group.add(radio[1]);
		group.add(radio[2]);
		group.add(radio[3]);
		group.add(radio[4]);

		//
		textArr = new JTextField[5][2];

		//





		//入力を受け取るパネル
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

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
		JPanel resultPanel = new JPanel();
		JLabel sentense = new JLabel("出力結果");
		JLabel resultRoute = new JLabel("efece");
		JLabel resultDistance = new JLabel("最短移動距離");

		resultPanel.add(sentense);
		resultPanel.add(resultRoute);
		resultPanel.add(resultDistance);


		outputPanel.add(calcPanel);
		outputPanel.add(resultPanel);


		mainPanel.add(outputPanel);


		getContentPane().add(mainPanel, BorderLayout.CENTER);
	}


	//計算ボタンがクリックされたとき
	public void actionPerformed(ActionEvent e){
		unselected = new String[4][2];
		int cnt = 0;
		for (int i=0; i < radio.length ;i++) {
			if (radio[i].isSelected()) {
				selected_X = textArr[i][0].getText();
				selected_Y = textArr[i][1].getText();
			} else {
				unselected[cnt][0] = textArr[i][0].getText();
				unselected[cnt][1] = textArr[i][1].getText();
				cnt++;
			}
		}
		CalcDistance distance = new CalcDistance(selected_X, selected_Y, unselected);

		//distance.permutation("012", "");
		distance.printData();

	}
	/*
		CalcDistance distance = new CalcDistance(A_textX.getText(), A_textY.getText(),
												  B_textX.getText(), B_textY.getText());
		distance.printData();
	*/
	}

//}
