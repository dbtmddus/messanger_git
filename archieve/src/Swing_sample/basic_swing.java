package Swing_sample;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class basic_swing extends JFrame{

	private JTextField text_field1;
	private JButton button1;
	public basic_swing() {		
		super();

		getContentPane().setLayout(null);
		setSize(260, 226);

		text_field1 =new JTextField("aa");
		text_field1.setBounds(59, 43, 116, 24);	//안하면 안나옴 (아마 크기 0인듯)
		getContentPane().add(text_field1);							//안하면 안 나옴
		text_field1.addActionListener(action);		//안 넣으면 이벤트 감지 못함

		button1 =new JButton("confirm");
		button1.setBounds(112, 121, 116, 24);	//안하면 안나옴 (아마 크기 0인듯)
		getContentPane().add(button1);							//안하면 안 나옴
		button1.addActionListener(action);		//안 넣으면 이벤트 감지 못함

		setVisible(true);	//마지막에 선언해줘야, 하위 컴포넌트 설정값들이 정상적으로 처음에 출력됨
	}//end creator

	ActionListener action = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if (obj==button1){
				System.out.println("button1 is clicked");
			}
			//else if (obj==signin_button){}
		}
	};
	
	
}//end

