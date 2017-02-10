package client_swing;

import java.awt.BorderLayout;
import java.awt.Panel;
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
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import common_use.command;

public class chat_frame extends JFrame {

	private Socket connected_socket;
	private BufferedReader listen;
	private PrintWriter send;
	private int id_n;
	private int f_id_n;
	
	private JPanel contentPane;

	private JMenuBar menuBar;
	private JButton b_send_file;
	private JButton b_configuration;

	private JScrollPane chat_recode_sp;
	private Panel chat_recode_p;

	private JPanel writting_p;	//
	private JTextArea ta;
	private JButton b_send;
	
	public chat_frame(Socket _connected_socket,int _id_n, int _f_id_n ) throws IOException, ClassNotFoundException {

		connected_socket = _connected_socket;
		listen = new BufferedReader(new InputStreamReader(connected_socket.getInputStream()));
		send = new PrintWriter(new BufferedWriter(new OutputStreamWriter(connected_socket.getOutputStream())));
		id_n = _id_n;
		f_id_n = _f_id_n;

		//swing configuration	
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		b_send_file = new JButton("파일 전송");
		b_send_file.addActionListener(action);
		menuBar.add(b_send_file);

		b_configuration = new JButton("설정");
		b_configuration.addActionListener(action);
		menuBar.add(b_configuration);
		/***********************************************************************/

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 410, 638);

		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		/****************************************************************/// 대화 기록 판넬
		chat_recode_p = new Panel();
		chat_recode_p.setLayout(null);
		
		JTextPane first_tp = new JTextPane();
		first_tp.setBounds(97, 12, 180, 25);
		first_tp.setText("대화가 시작되었습니다.");
		chat_recode_p.add(first_tp);
		
		chat_recode_sp = new JScrollPane();
		chat_recode_sp.setViewportView(chat_recode_p);
		chat_recode_sp.setBounds(getContentPane().getBounds());
		contentPane.add(chat_recode_sp, BorderLayout.CENTER);
		/****************************************************************///
		
		/****************************************************************/// 메세지 전송 판넬
		
		writting_p = new JPanel();
		contentPane.add(writting_p, BorderLayout.SOUTH);
		writting_p.setLayout(new BorderLayout());
		
		ta = new JTextArea();
		ta.setText("dddddddddddd");
		writting_p.add(ta, BorderLayout.CENTER);
		
		b_send = new JButton("전송");
		writting_p.add(b_send, BorderLayout.EAST);
		b_send.addActionListener(action);
		
		/****************************************************************/
		
		setVisible(true);
	}

	ActionListener action = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();

			if (obj==b_send_file){
				/*try {
					fp.set_friend_db();
				} catch (IOException e1) {
					e1.printStackTrace();
				}*/
			}
			else if (obj==b_configuration){
				try {
					add_friend_frame frame_add_friends = new add_friend_frame(connected_socket,  id_n);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if (obj==b_send){
				String text = ta.getText();
				send_message(text);
				ta.setText("");
			}
		}
	};
	public void send_message(String _text){
		send.println(command.normal_message);
		send.flush();
		
		send.println(f_id_n);
		send.println(ta.getText());
		send.flush();
	}
}

