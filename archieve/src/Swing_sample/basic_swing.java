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
		text_field1.setBounds(59, 43, 116, 24);	//���ϸ� �ȳ��� (�Ƹ� ũ�� 0�ε�)
		getContentPane().add(text_field1);							//���ϸ� �� ����
		text_field1.addActionListener(action);		//�� ������ �̺�Ʈ ���� ����

		button1 =new JButton("confirm");
		button1.setBounds(112, 121, 116, 24);	//���ϸ� �ȳ��� (�Ƹ� ũ�� 0�ε�)
		getContentPane().add(button1);							//���ϸ� �� ����
		button1.addActionListener(action);		//�� ������ �̺�Ʈ ���� ����

		setVisible(true);	//�������� ���������, ���� ������Ʈ ���������� ���������� ó���� ��µ�
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

