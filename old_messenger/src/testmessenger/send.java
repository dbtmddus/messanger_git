package testmessenger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class send {				// 발신자 전용 클레스


	private InetAddress ia;
	private Socket soc;
	private PrintWriter out ;		//추가
	private String chat;
	
		public send(String ip_n,int soc_n){
			
			ia =null;
			soc = null;
			out = null;		//추가
			chat="미입력";
			
		try{
			ia = InetAddress.getByName(ip_n);
			soc = new Socket(ia, soc_n);
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(soc.getOutputStream()))); //추가
		}catch(IOException ee){
			System.err.println("접속오류 : "+ee.toString());
			System.exit(-1);
		}
		}
		
		public void sendmessage(String st2){
	
		out.println(st2+"\n");	//추가
		out.flush();		//전송 후, 버퍼 비움
		}

	}
	