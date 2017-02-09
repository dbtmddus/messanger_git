package simple_server;

import java.io.IOException;
import java.net.ServerSocket;

public class server_main {
	public static void main(String[] args) throws IOException {
		ServerSocket server_socket = new ServerSocket(1245);
		server_obj s = new server_obj(server_socket);
		s.receive_new_client_connection();
		System.out.println(s.listen.readLine());
		s.send.println("str_from_pc");
		s.send.flush();
	}
}
