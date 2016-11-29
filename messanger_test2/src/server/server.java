package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class server {

	public static void main(String[] args) throws IOException {
		System.out.println("server");		
		ServerSocket ss = new ServerSocket(1245);
		while (true){
			System.out.println("1");
			Socket s = ss.accept();
			System.out.println("2");
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			System.out.println(s.toString());
			System.out.println("3");

			String str = br.readLine();
			System.out.println(str);
		}
	}

}


//InputStream is = s.getInputStream();
//BufferedReader br = new BufferedReader(new InputStreamReader(is));
