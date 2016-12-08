package client_swing;

import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class add_friend_frame extends JFrame {

	private static friend_panel p_friend;

	private JPanel contentPane;

	private JTextField text_field1;
	private JButton b_add;


	private Socket connected_socket;
	private BufferedReader listen;
	private PrintWriter send;
	private int id_n;


	public add_friend_frame(Socket _connected_socket, BufferedReader _listen, PrintWriter _send, int _id_n ) {

		connected_socket = _connected_socket;
		listen = _listen;
		send = _send;
		id_n = _id_n;
		///////////////////////////////////////////////////////////////

		getContentPane().setLayout(null);
		setVisible(true);
		setSize(368, 219);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		text_field1 = new JTextField("친구 id");
		//f_id.setColumns(5);	??
		text_field1.setBounds(90, 49, 148, 37);
		getContentPane().add(text_field1);
		text_field1.addActionListener(action);

		b_add = new JButton("추가");
		b_add.setBounds(178, 113, 128, 37);
		getContentPane().add(b_add);
		b_add.addActionListener(action);
	}

	ActionListener action = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();

			if (obj==b_add){
				b_add_event();
			}
			//else if (obj==b_add_friend){
			//}
		}
	};

	public void b_add_event(){
		//String str = text_field1.getText();	??????????? 에러 유발?????????????? 확인하고 갈것
		send.println("add_friend");
		send.flush();
		String str_FID = text_field1.getText();
		send.println(str_FID);		//println과 print 혼용시 문자열이 합쳐지는 오류
		send.flush();

		String response="";
		try {
			response = listen.readLine();
			if(response.equals("confirm_add_friend")){
				JOptionPane.showMessageDialog(null, "추가되었습니다", "친구추가", JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null, "이미 추가된 친구입니다", "친구추가", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}

