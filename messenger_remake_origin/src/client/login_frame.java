package client;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class login_frame extends JFrame{

	public login_frame() throws IOException
	{
		super("로그인");
		setLayout(null);
		setVisible(true);
		setSize(390, 210);
		
		JLabel label_1 = new JLabel("label 1 ");
		add(label_1);
		label_1.setBounds(30, 50, 100, 20); // 가로,세로,탑,레프트위치
		
		JTextField text_field_1 = new JTextField("label 1 ");
		add(text_field_1);
		label_1.setBounds(30, 50, 100, 20); // 가로,세로,탑,레프트위치
		
	}//end creator


}
