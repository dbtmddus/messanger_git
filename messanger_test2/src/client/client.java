package client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class client {

	public static void main(String[] args) {
		System.out.println("client");
		Socket s = null;
		try {
			s = new Socket("127.0.0.1", 1245);
		} catch (UnknownHostException e) {
			System.out.print("1");
		} catch (IOException e) {
			System.out.print("2");
		}
		System.out.println(s.toString());
		
		try {
			PrintWriter out;
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())));
			out.println("success");
			out.flush();
			//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream())); //�߰�
			//bw.write("success\n");
		} catch (IOException e) {
			System.out.print("3");
		} //�߰�
		
		
		while(true){

		}
	}

}
