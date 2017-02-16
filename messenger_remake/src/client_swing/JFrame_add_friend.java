package client_swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import common_use.command;


public class JFrame_add_friend extends JFrame {


	private Socket connected_socket;
	private BufferedReader listen;
	private PrintWriter send;
	private int id_n;

	private JPanel contentPane;
	private JTextField text_field1;
	private JButton b_add;

	public JFrame_add_friend(Socket _connected_socket, int _id_n ) throws IOException {

		connected_socket = _connected_socket;
		listen = new BufferedReader(new InputStreamReader(connected_socket.getInputStream()));
		send = new PrintWriter(new BufferedWriter(new OutputStreamWriter(connected_socket.getOutputStream())));
		id_n = _id_n;
		
		// swing configuration
		getContentPane().setLayout(null);
		setSize(368, 219);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		// - 해당 frame만 종료 / EXIT_ON_CLOSE - 프로그램 전체 종료

		text_field1 = new JTextField("친구 id");
		text_field1.setBounds(90, 49, 148, 37);
		getContentPane().add(text_field1);
		text_field1.addActionListener(action);

		b_add = new JButton("추가");
		b_add.setBounds(178, 113, 128, 37);
		getContentPane().add(b_add);
		b_add.addActionListener(action);
		
		setVisible(true);
	}

	ActionListener action = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();

			if (obj==b_add){
				b_add_event();
			}
		}
	};

	public void b_add_event(){
		send.println(command.add_friend);
		send.flush();
		String f_id_tf = text_field1.getText();
		send.println(f_id_tf);		//println과 print 혼용시 문자열이 합쳐지는 문제. 가능하면 통일해서 사용
		send.flush();

		try {
			boolean b_approved = Boolean.parseBoolean(listen.readLine());
			if(b_approved){
				JOptionPane.showMessageDialog(null, "추가되었습니다", "친구추가", JOptionPane.INFORMATION_MESSAGE);
				this.setVisible(false);
			}else{
				JOptionPane.showMessageDialog(null, "이미 추가된 친구입니다", "친구추가", JOptionPane.INFORMATION_MESSAGE);
				this.setVisible(false);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}

