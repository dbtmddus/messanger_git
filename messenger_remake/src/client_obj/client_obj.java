package client_obj;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class client_obj {

	static Socket connected_socket = null;
	static String server_ip;	//"127.0.0.1";
	//static String server_ip = "172.30.96.107";
	static int server_port;
	static PrintWriter out;

	public client_obj(String ip){//, int port_n) {
		server_ip = ip;
		server_port=1244;
		//server_port = port_n;
	}
	public void enter() throws IOException{
		while (true){
			try {
				connected_socket = new Socket(server_ip, server_port);
				
				System.out.println(connected_socket.isBound());
				break;
			} catch (UnknownHostException e) {
				e.printStackTrace();
				System.out.println("unknown host error : "+ e);
				System.exit(1);
				break;
			} catch (IOException e) {
				if (server_port > 2000){
					System.out.println("port is over 2000");
					System.exit(1);
					break;		
				}
				//port_num++;
				System.out.println(server_port+" is used, connect with other port");
				server_port++;
				//System.out.println("서버가 닫혀있거나 가능한 모든 포트가 사용중입니다");	// 추후 다른 포트로 접속 시도하도록 변경, 관련 함수도 있을것으로 생각
			}
		}
		/************************************************************************************/

		System.out.println("입장되셨습니다");
		show_info();
	}
	public void open_login_frame() throws IOException{
		login_frame_swing lf = new login_frame_swing(connected_socket);
	}
	public void show_info(){
		System.out.println("client 정보 : "+connected_socket.getLocalSocketAddress());
		System.out.println("server 정보 : "+connected_socket.getRemoteSocketAddress());
	}
		
}//end
