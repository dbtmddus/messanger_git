package server_obj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;

public class server_obj_save {

	ServerSocket server_socket = null;	//static 선언시 thread끼리 공유
	Socket soc = null;
	int open_socket_num = 1245;
	BufferedReader br;

	final String login = "login";	//!! switch용도, system 버전에 따라 오류날 수 있음
	final String signin = "signin";


	public server_obj_save(int port_n) throws IOException{
		open_socket_num = port_n;
		System.out.println("server is made");
		server_socket = new ServerSocket(open_socket_num);
	}

	public void admit_client() throws IOException{
		while (true){
			soc = server_socket.accept();//기다리다 연결되면 accept통해 만들어지는 것이 클라이언트 소켓 

			System.out.println("client is admitted");
			System.out.println("server 정보 : "+soc.getLocalSocketAddress());
			System.out.println("client 정보 : "+soc.getRemoteSocketAddress());

			if (soc !=null){
				System.out.println("server socket 정보 : " + server_socket.toString());

				while(true){
					br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
					while(true){
						String str_from_client= br.readLine();
						System.out.println("client command : "+str_from_client);
			
						switch(str_from_client){
						case login:
							login();
							break;
						case signin:
							signin();
							break;
						default:
							break;
						}
					}
				}
			}
		}
	}
	public void login() throws IOException{
		System.out.println("log-in handling");
		String id = br.readLine();
		String password = br.readLine();

		System.out.println("id : "+id+ ", password : "+password+"\n");
	}
	public void signin() throws IOException{
		System.out.println("sign-in handling");
		String id = br.readLine();
		String password = br.readLine();

		System.out.println("id : "+id+ ", password : "+password+"\n");
	}
}
