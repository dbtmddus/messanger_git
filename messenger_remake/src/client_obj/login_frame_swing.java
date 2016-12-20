package client_obj;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import client_swing.main_frame;

public class login_frame_swing extends JFrame{

	private Socket connected_socket;
	private PrintWriter send ;		//추가
	private BufferedReader listen;

	private JLabel Password_label; 
	private JLabel Id_label;
	private JTextField ID_textfield;
	private JTextField password_textfield;
	private JButton login_button;
	private JButton signin_button;

	static String id;
	static String password;
	
	public login_frame_swing(Socket _connected_socket, BufferedReader _listen, PrintWriter _send ) throws IOException
	{
		super("로그인");
		connected_socket = _connected_socket;		
		listen = _listen;
		send = _send;

		//swing
		getContentPane().setLayout(null);
		//setVisible(true);		//다른 컴포넌트 설정 전에 호출 시 frame resize시에 뒤늦게 제대로 표시될 수 있음
		setSize(460, 226);

		Id_label = new JLabel("ID :");
		getContentPane().add(Id_label);
		Id_label.setBounds(66, 37, 43, 20); // 가로,세로,탑,레프트위치

		ID_textfield = new JTextField();
		ID_textfield.setBounds(123, 36, 202, 22);
		getContentPane().add(ID_textfield);

		Password_label = new JLabel("password :");
		Password_label.setBounds(45, 88, 79, 18);
		getContentPane().add(Password_label);

		password_textfield = new JTextField();
		password_textfield.setBounds(123, 85, 202, 24);
		getContentPane().add(password_textfield);
		password_textfield.setColumns(10);

		login_button = new JButton("login");
		login_button.setBounds(231, 135, 79, 32);
		getContentPane().add(login_button);
		login_button.addActionListener(action);

		signin_button = new JButton("sign in");
		signin_button.setBounds(324, 135, 87, 32);
		getContentPane().add(signin_button);
		signin_button.addActionListener(action);
		
		ID_textfield.setText("dbtmddus112");
		password_textfield.setText("1234");
		
		setVisible(true);
//		getContentPane().repaint();
	}//end creator

	ActionListener action = new ActionListener() {

		public void actionPerformed(ActionEvent e) {

			Object obj = e.getSource();

			if (obj==login_button){
				send.println("login");
				send.flush();
				id = ID_textfield.getText();
				password = password_textfield.getText();
				send.println(id);
				send.println(password);
				send.flush();

				boolean b_approved = false;
				try {
					b_approved = Boolean.parseBoolean(listen.readLine());
					System.out.println(b_approved);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				if (b_approved){	//when login is approved
					int id_n;
					try {
						id_n = Integer.parseInt(listen.readLine());
						main_frame mf = new main_frame(connected_socket, listen, send, id_n );
						setVisible(false);
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(null, "일치하는 정보가 없습니다.", "로그인", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else if (obj==signin_button){
				send.println("signin");
				send.flush();
				id = ID_textfield.getText();
				password = password_textfield.getText();
				send.println(id);
				send.println(password);
				send.flush();
			}
		}
	};

}//end
