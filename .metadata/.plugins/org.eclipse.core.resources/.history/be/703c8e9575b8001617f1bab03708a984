package server_obj;

import java.io.IOException;
import java.net.ServerSocket;

public class main {
	server_obj[] servers =new server_obj[1000];
	public static void main(String[] args) throws IOException {

		ServerSocket server_socket = new ServerSocket(1245);

		//for(int i=0; i<1000; i++){
			server_obj server1 = new server_obj(server_socket);
			server1.start();
		//}

			server_obj server2 = new server_obj(server_socket);
			server2.start();
	
	}//end main
}

