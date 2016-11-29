package testmessenger;

/*
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
*/
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class send3 extends Thread {

		private String str="미입력";
		private ServerSocket ss =null;
		private Socket soc = null;
		
		private JFrame frame;
		private JTextArea log;			//채팅내역
		private JScrollPane scroll_log;
		private JTextField field1;		//발신창
		private String strsum;
		private String listenerid;
		
		private send s2;
		
		public send3(String guestip){
			
			frame = new JFrame("발신");
			log = new JTextArea("채팅창");
			scroll_log = new JScrollPane(log);
			field1 = new JTextField("");
			strsum="";
			listenerid="";
			
			frame.add(scroll_log);
			frame.add (field1,BorderLayout.SOUTH);
			frame.setVisible(true);
			frame.setSize(270,450);
			
			field1.addActionListener(action);
			
			try{
				ss = new ServerSocket(12346);
				}catch(IOException ee){
				System.out.println("해당 포트 열려있음");
				System.exit(-1);
			}
			s2 = new send(guestip,12345);
		}
		
		public void changelog(String st1)  {
			strsum = strsum+"\n"+st1;
			log.setText(strsum);
			}

		 ActionListener action = new ActionListener() {

		     public void actionPerformed(ActionEvent e) {
		    	 Object obj = e.getSource();
		    	 
		    	 String st1 = "";
		    	 if (obj == field1){
					st1 = String.format("%s", e.getActionCommand());//textfield1:
					s2.sendmessage(st1);
					field1.setText("");
					changelog("나(HOST):"+" : "+st1);	//
					}		
		     }
		};
		

		public void setid(String str){
			listenerid = str;
		}
		
		public void run(){
		try{
			soc = ss.accept();
			System.out.println("대기중");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));

			while(str !="1\n"){
			str = in.readLine();		//입력될때까지 여기서  대기
			//changelog(soc.getInetAddress()+" : "+str);
			changelog(listenerid+" : "+str);
			in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			}
			soc.close();
		}catch(IOException ee){
			System.out.println("soc 부분 오류 발생");
			}
		}
/*
		public static void main(String[] args){
			send3 l3 = new send3("211.202.8.153");
			Thread t = new Thread(l3);
			t.start();	
		}*/
	}