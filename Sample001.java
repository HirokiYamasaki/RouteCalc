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


	public static void main(String[] args) {
		Sample001 frame = new Sample001();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(10, 10, 600, 400);
		frame.setTitle("タイトル");
		frame.setVisible(true);
	}

	Sample001() {

		ButtonGroup group = new ButtonGroup();


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
		JRadioButton radio_A = new JRadioButton();
		A_textX = new JTextField(10);
		A_textY = new JTextField(10);

		JPanel panel_A = new JPanel();
		panel_A.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
		//panel_A.setBackground(Color.orange);
		group.add(radio_A);
		panel_A.add(radio_A);
		panel_A.add(radio_A);
		panel_A.add(lblA);
		panel_A.add(A_textX);
		panel_A.add(A_textY);


		//BBBBBBBBBB
		JLabel lblB = new JLabel("座標B");
		JRadioButton radio_B = new JRadioButton();
		B_textX = new JTextField(10);
		B_textY = new JTextField(10);

		JPanel panel_B = new JPanel();
		panel_B.setMaximumSize(new Dimension(Short.MAX_VALUE, 30));
		//panel_B.setBackground(Color.YELLOW);
		group.add(radio_B);
		panel_B.add(radio_B);
		panel_B.add(lblB);
		panel_B.add(B_textX);
		panel_B.add(B_textY);

		//CCCCCCCCCCCCCCCCC
		JLabel lblC = new JLabel("座標C");
		JRadioButton radio_C = new JRadioButton();
		C_textX = new JTextField(10);
		C_textY = new JTextField(10);

		JPanel panel_C = new JPanel();
		panel_C.setMaximumSize(new Dimension(Short.MAX_VALUE, 30));
		//panel_C.setBackground(Color.blue);
		group.add(radio_C);
		panel_C.add(radio_C);
		panel_C.add(lblC);
		panel_C.add(C_textX);
		panel_C.add(C_textY);

		//DDDDDDDDDDDDDDDDDDDD
		JLabel lblD = new JLabel("座標D");
		JRadioButton radio_D = new JRadioButton();
		D_textX = new JTextField(10);
		D_textY = new JTextField(10);

		JPanel panel_D = new JPanel();
		panel_D.setMaximumSize(new Dimension(Short.MAX_VALUE, 30));
		//panel_D.setBackground(Color.gray);
		group.add(radio_D);
		panel_D.add(radio_D);
		panel_D.add(lblD);
		panel_D.add(D_textX);
		panel_D.add(D_textY);

		//EEEEEEEEEEEEEEEEEEEEEEEE
		JLabel lblE = new JLabel("座標E");
		JRadioButton radio_E = new JRadioButton();
		E_textX = new JTextField(10);
		E_textY = new JTextField(10);

		JPanel panel_E = new JPanel();
		panel_D.setMaximumSize(new Dimension(Short.MAX_VALUE, 20));
		//panel_E.setBackground(Color.red);
		group.add(radio_E);
		panel_E.add(radio_E);
		panel_E.add(lblE);
		panel_E.add(E_textX);
		panel_E.add(E_textY);


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

	public void actionPerformed(ActionEvent e){
		//System.out.println(D_textY.getText());
		CalcDistance distance = new CalcDistance(A_textX.getText(), A_textY.getText());
		distance.printData();

	}

}
