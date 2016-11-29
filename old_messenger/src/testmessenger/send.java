package testmessenger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class send {				// �߽��� ���� Ŭ����


	private InetAddress ia;
	private Socket soc;
	private PrintWriter out ;		//�߰�
	private String chat;
	
		public send(String ip_n,int soc_n){
			
			ia =null;
			soc = null;
			out = null;		//�߰�
			chat="���Է�";
			
		try{
			ia = InetAddress.getByName(ip_n);
			soc = new Socket(ia, soc_n);
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(soc.getOutputStream()))); //�߰�
		}catch(IOException ee){
			System.err.println("���ӿ��� : "+ee.toString());
			System.exit(-1);
		}
		}
		
		public void sendmessage(String st2){
	
		out.println(st2+"\n");	//�߰�
		out.flush();		//���� ��, ���� ���
		}

	}
	