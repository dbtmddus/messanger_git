/**
 *  12/02
 *  oracle 연동 직후
 * 
 */

package server_obj;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import javax.sound.sampled.Port;
import java.sql.*;

public class server_obj extends Thread {

	static DB_obj db = new DB_obj();

	ServerSocket server_socket = null;	//static 선언시 thread끼리 공유
	Socket soc = null;
	int open_port_num;
	BufferedReader listen;
	PrintWriter send;


	final String login = "login";	//!! switch용도, system 버전에 따라 오류날 수 있음
	final String signin = "signin";


	public server_obj(ServerSocket ss) throws IOException{
		server_socket = ss;
		System.out.println(server_socket + "is made");
	}


	public void run(){
		try {
			soc = server_socket.accept(); //새 고객 접속 대기
		} catch (IOException e) {
			System.out.println("e_num3 - 고객 접속 오류");
			e.printStackTrace();
		}

		System.out.println("client is admitted");
		System.out.println("server 정보 : "+soc.getLocalSocketAddress());
		System.out.println("client 정보 : "+soc.getRemoteSocketAddress());

		while (soc !=null){
			System.out.println("server socket 정보 : " + server_socket.toString());

			while(true){
				try {
					listen = new BufferedReader(new InputStreamReader(soc.getInputStream()));
					send = new PrintWriter(new BufferedWriter(new OutputStreamWriter(soc.getOutputStream())));

					while(true){
						String str_from_client;
						str_from_client = listen.readLine();
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
				} catch (IOException e) {
					System.out.println("e_num4 - error at run(), exit port" + open_port_num);
					e.printStackTrace();
					break;
				}
			}
		}
	}
	public void login() throws IOException{
		System.out.println("log-in handling");
		String id = listen.readLine();
		int password = Integer.parseInt(listen.readLine());

		System.out.println("id : "+id+ ", password : "+password+"\n");
		System.out.println("log-in is " +db.confirm_login(id, password));
		send.println("log-in is " +db.confirm_login(id, password));
		if (db.confirm_login(id, password) == true){
			//open
		}
	}
	public void signin() throws IOException{
		System.out.println("sign-in handling");
		String id = listen.readLine();
		int pw = Integer.parseInt(listen.readLine());

		System.out.println("id : "+id+ ", password : "+pw+"\n");

		db.insert_new_client(id, pw);
	}//주석

	public void connect_db(){
		db.connect();
	}
}
