package simple_server;

import java.io.IOException;
import java.net.ServerSocket;

import my_sql_simple.DB_obj;

public class server_main {
	static DB_obj db;
	public static void main(String[] args) throws IOException {
		//ServerSocket server_socket = new ServerSocket(1245);
		ServerSocket server_socket = new ServerSocket(13267);

		server_obj s0 = new server_obj(server_socket);
		s0.connect_db();

		/*server_obj[] ser = new server_obj[100];
		for(int i=0; i<20; i++){
			ser[i] = new server_obj(server_socket);
			ser[i].start();
		}*/
		
		//������ ������, �׽�Ʈ�� �ҽ�. ���񽺿��� �� 20�� Ŭ���̾�Ʈ ����
		server_obj s1 = new server_obj(server_socket);
		s1.start();	

		server_obj s2 = new server_obj(server_socket);
		s2.start();

	}
}
