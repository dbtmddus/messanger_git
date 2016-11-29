package client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class client {

	public static void main(String[] args) throws IOException {

		boolean server_on = false;
		Socket connected_socket = null;
		int port_num = 1245;
		String server_ip = "127.0.0.1";
		/************************************************************************************/ //서버 port에 접속
		while (true){
			try {
				//connected_socket = new Socket("127.0.0.1", port_num);
				connected_socket = new Socket(server_ip, port_num);
				server_on = true;
				break;
			} catch (UnknownHostException e) {
				e.printStackTrace();
				System.out.println("unknown host error : "+ e);
				System.exit(1);
				break;
			} catch (IOException e) {
				if (port_num > 2000){
					System.exit(1);
					break;		
				}
				//port_num++;
				System.out.println("서버가 닫혀있거나 가능한 모든 포트가 사용중입니다");	// 추후 다른 포트로 접속 시도하도록 변경, 관련 함수도 있을것으로 생각
			}
		}
		/************************************************************************************/

		//if (server_on == true){
		if (true){
			System.out.println("on");
			System.out.println("address : "+connected_socket.getLocalAddress());
			System.out.println("port : " +connected_socket.getPort());
			
			login_frame_swing lf = new login_frame_swing(server_ip, port_num);
		}





	}//end main
}
