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
	private int n_id;


	public add_friend_frame(Socket _connected_socket, BufferedReader _listen, PrintWriter _send, int _n_id ) {

		connected_socket = _connected_socket;
		listen = _listen;
		send = _send;
		n_id = _n_id;
		///////////////////////////////////////////////////////////////

		getContentPane().setLayout(null);
		setVisible(true);
		setSize(260, 226);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		text_field1 = new JTextField("친구 id");
		//f_id.setColumns(5);	??
		text_field1.setBounds(47, 44, 148, 37);
		getContentPane().add(text_field1);
		text_field1.addActionListener(action);

		b_add = new JButton("추가");
		b_add.setBounds(112, 128, 116, 24);
		getContentPane().add(b_add);
		b_add.addActionListener(action);

		/*txtAaaa = new JTextField();
		txtAaaa.setText("aaaa");
		txtAaaa.setBounds(25, 63, 116, 24);
		getContentPane().add(txtAaaa);
		txtAaaa.setColumns(10);
		 */
		b_add.addActionListener(action);

	}

	ActionListener action = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();

			if (obj==b_add){
				String str = text_field1.getText();
				send.println("add_friend");
				send.flush();
				send.print(str);
				send.flush();
			}
			//else if (obj==b_add_friend){
			//}
		}
	};
}

