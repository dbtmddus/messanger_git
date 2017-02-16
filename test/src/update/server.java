package update;

import java.io.IOException;
import java.net.ServerSocket;

public class server {

	public static void main(String[] args) throws IOException {
		ServerSocket server_socket = new ServerSocket(1245);
		server_obj server1 = new server_obj(server_socket);
		server1.start();
	}
}
