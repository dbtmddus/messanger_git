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

public class listen3 extends Thread {

		private String str="���Է�";
		private ServerSocket ss =null;
		private Socket soc = null;
		
		private JFrame frame;
		private JTextArea log;			//ä�ó���
		private JScrollPane scroll_log;
		private JTextField field1;		//�߽�â
		private String strsum;
		private String senderid;
		
		private send s2;
		
		public listen3(){
			
			frame = new JFrame("����");
			log = new JTextArea("ä��â");
			scroll_log = new JScrollPane(log);
			field1 = new JTextField("");
			strsum="";
			senderid="";
			
			frame.add(scroll_log);
			frame.add (field1,BorderLayout.SOUTH);
			//frame.setVisible(true);
			frame.setSize(270,450);
			
			field1.addActionListener(action);
			
			try{
				ss = new ServerSocket(12345);
				}catch(IOException ee){
				System.out.println("�ش� ��Ʈ ��������");
				System.exit(-1);
			}
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
					changelog("��(HOST)"+" : "+st1);	//
					}		
		     }
		};
	
		public void setid(String str){
			senderid = str;
		}
	
		public void run(){
		try{
			soc = ss.accept();
			frame.setVisible(true);
			//System.out.println("������ ��������� : "+soc.toString() );
			//j.changelog(soc.getInetAddress()+"����"
					//+soc.getPort()+"��Ʈ�� �����ϼ̽��ϴ�");
					
			String sip =  soc.getInetAddress().toString().substring(1,soc.getInetAddress().toString().length());
			 System.out.println(sip);
			 s2 = new send(sip,12346);//�߽��� ��Ʈ		

			BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));

			while(str !="1\n"){
			str = in.readLine();		//�Էµɶ����� ���⼭  ���
			//changelog(soc.getInetAddress()+" : "+str);
			changelog(senderid+" : "+str);
			in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			}
			soc.close();
		}catch(IOException ee){
			System.out.println("soc �κ� ���� �߻�");
			}
		}

	}
