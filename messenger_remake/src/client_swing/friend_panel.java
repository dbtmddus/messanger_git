package client_swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class friend_panel {

	private Socket connected_socket;
	private PrintWriter send ;		//추가
	private BufferedReader listen;
	private int id_n;

	static int num_of_friend;
	static Vector<friend_info> f_info;
	//static friend_info[] f_info;
	/*
	static String[] fid_list;
	static String[] fimg_list;
	static String[] fm_list;
	 */

	private JTextField ID_textfield;
	private Panel[] panel = new Panel[100];

	private JScrollPane spanel;
	private JPanel big_panel;

	static Vector<JPanel> arr_jpanel = new Vector<JPanel>(0); 

	public friend_panel(Socket _connected_socket, BufferedReader _listen, PrintWriter _send, int _id_n, JFrame m_frame ) throws IOException{		

		/***************************************************************///
		connected_socket = _connected_socket;		
		listen = _listen;
		send = _send;
		id_n = _id_n;
		num_of_friend=0;

		System.out.println("여기???????????? 2");
		set_f_info();
		System.out.println("여기???????????? 3");
		/***************************************************************///

		/***************************************************************///swing
		spanel = new JScrollPane();
		//spanel.setViewportView(big_panel);
		spanel.setBounds(m_frame.getContentPane().getBounds());

		big_panel = new JPanel();
		big_panel.setLayout(new GridLayout(5, 2));
		big_panel.setBounds(m_frame.getContentPane().getBounds());


		for (int i=0; i<num_of_friend; i++){
			String f_id_temp= f_info.elementAt(i).id;
			JPanel each_panel = new JPanel();
			each_panel = one_friend_panel(i);
			each_panel.setName(f_id_temp);
			big_panel.add(each_panel);
			each_panel.addMouseListener(ml);
		}

		spanel.setViewportView(big_panel);	// 이것도 앞에 넣으면  반영전 panel값이 출력되는듯
		big_panel.setVisible(true);
		spanel.setVisible(true);
	}//end creator

	public void set_f_info() throws IOException{ 
		final String request_friend_list = "request_friend_list";
		send.println(request_friend_list);
		send.flush();

		try{
			num_of_friend = Integer.parseInt(listen.readLine());
		}catch(Exception e){
			num_of_friend = 0;
		}
		f_info = new Vector<friend_info>(0);

		for(int i=0; i<num_of_friend; i++){
			friend_info f_info_ele = new friend_info();

			f_info_ele.id = listen.readLine();
			f_info_ele.id_n = Integer.parseInt(listen.readLine());

			System.out.println("------------------" + f_info_ele.id+" / "+ f_info_ele.id_n);

			/*
			InputStream in = connected_socket.getInputStream();
			OutputStream out = new FileOutputStream("c_"+f_info_ele.id+".jpg");

			byte[] bytes = new byte[16*1024];
			int count;
			while ((count = in.read(bytes)) > 0) {
				out.write(bytes, 0, count);
			}
			out.close();
			 */


			//
			FileOutputStream out = new FileOutputStream("c_"+f_info_ele.id+".jpg");
			while (true){
				try{
					int data = listen.read();
					System.out.println(data+"       c");
					Byte byte_data = (byte)data;
					out.write(byte_data);
				}catch(Exception e){
					break;
				}
			}
			out.close();
			System.out.println("final read :"+listen.readLine()); 		// 없으면 어떻게 되는 지 추후 확인 (read와 readline 혼용문제)
			//


			f_info_ele.stmt_msg = listen.readLine();
			System.out.println("------------------" + f_info_ele.stmt_msg);
			f_info.add(f_info_ele);
		}

		System.out.println("size:"+num_of_friend);
		for (int i=0; i<num_of_friend ; i++){
			System.out.println(f_info.elementAt(i).id+"/"+f_info.elementAt(i).id_n+"/"+f_info.elementAt(i).stmt_msg);
		}
	}

	public JScrollPane get_spanel(){
		return spanel;
	}

	public JPanel one_friend_panel(int _i) throws IOException{

		String f_id_temp = f_info.elementAt(_i).id;
		int f_id_n_temp = f_info.elementAt(_i).id_n;
		File f_image_temp = f_info.elementAt(_i).image;
		String f_stmt_msg = f_info.elementAt(_i).stmt_msg;

		JPanel o_panel = new JPanel();
		o_panel.setLayout(new BorderLayout());
		o_panel.setBounds(0,0, 500,500);
		o_panel.addMouseListener(ml);

		JButton b_image = new JButton("default");
		if(f_image_temp!=null){
			//File f = new File("C:\\messanger_image\\qkf.png");
			Image image_temp = ImageIO.read(f_image_temp);
			ImageIcon icon_temp = new ImageIcon(image_temp);
			b_image.setIcon(icon_temp);
		}else{
			b_image.setText("profile image isn't set");
		}
		o_panel.add(b_image, BorderLayout.WEST);
		b_image.addMouseListener(ml);


		JPanel right_panel = new JPanel();
		right_panel.setName(f_id_temp);
		right_panel.setLayout(new GridLayout(2, 1));
		o_panel.add(right_panel, BorderLayout.CENTER);

		////////////////
		JButton b_id = new JButton(f_id_temp);
		right_panel.add(b_id);
		b_id.addMouseListener(ml);

		JButton b_stmt_msg = new JButton("default");
		if(f_stmt_msg!=null){
			b_stmt_msg.setText(f_stmt_msg);	
		}else{
			b_stmt_msg.setText("stmt_msg isn't set");	
		}
		right_panel.add(b_stmt_msg);
		b_stmt_msg.addMouseListener(ml);

		o_panel.setVisible(true);
		return o_panel;
	}

	ActionListener action = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			/*if (obj==p){
				//.getComponents() login_button){
			}

			else if (obj==signin_button){	
			}*/
		}
	};

	MouseListener ml = new MouseListener(){
		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			String clicked_friend_id = e.getComponent().getParent().getName();
			send.println("request_friend_ip");
			send.flush();
			send.println(clicked_friend_id);
			send.flush();

			try {
				System.out.println("friend ip : "+ listen.readLine());
				System.out.println("friend port : "+ listen.readLine());
				if (!main_frame.already_exist_chat_v.contains(clicked_friend_id)){
					chat_frame chat_f = new chat_frame(connected_socket, listen, send, id_n, 0);
					main_frame.already_exist_chat_v.addElement(clicked_friend_id);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			String clicked_friend_id = e.getComponent().getParent().getName();
			send.println("request_friend_ip");
			send.flush();
			send.println(clicked_friend_id);
			send.flush();

			try {
				System.out.println("friend ip : "+ listen.readLine());
				System.out.println("friend port : "+ listen.readLine());
				if (!main_frame.already_exist_chat_v.contains(clicked_friend_id)){
					chat_frame chat_f = new chat_frame(connected_socket, listen, send, id_n, 2);
					main_frame.already_exist_chat_v.addElement(clicked_friend_id);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	};

	class friend_info{

		public String id;
		public int id_n;
		public String stmt_msg;
		public File image;

		public friend_info(){
			id = null;
			id_n = -1;
			stmt_msg = null;
			image = null;

		}
		public friend_info(String _id, int _id_n, String _stmt_msg, File _image){
			id = _id;
			id_n = _id_n;
			stmt_msg = _stmt_msg;
			image = _image;
		}

	}
}//end

