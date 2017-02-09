/**
 *  12/02
 *  oracle 연동 직후
 * 
 */

package simple_server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Vector;

import my_sql_simple.DB_obj;

public class server_obj extends Thread {

	static final int max_client_num = 100000;
	ServerSocket server_socket = null;	//static 선언시 thread끼리 공유 (향후 분산처리 가능하므로 static x)
	int open_port_num;
	Socket soc = null;
	BufferedReader listen;
	PrintWriter send;

	static DB_obj db = new DB_obj();

	public server_obj(ServerSocket ss) throws IOException{
		server_socket = ss;
		System.out.println(server_socket + "is made");	
	}

	public void receive_new_client_connection(){
		//while(soc!=null){
		try {
			System.out.println("waiting new client..");
			soc = server_socket.accept(); //새 고객 접속 대기
			reconnect(); //다음 고객 처리 위해 오픈
			System.out.println("client is admitted");
			listen = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			send = new PrintWriter(new BufferedWriter(new OutputStreamWriter(soc.getOutputStream())));
			show_connection_info(soc);

			System.out.println("first str from client : "+listen.readLine());
		}
		catch (IOException e) {
			System.out.println("error in connection - 01");
			close_and_reconnect();
			e.printStackTrace();
		}	

		//	while (soc.isConnected()){
		while (!soc.isClosed()){	//soc.isconnected로는 오작동함.
			try{
				String client_request;
				System.out.println("ready for command..");
				client_request = listen.readLine();
				System.out.println("get command..");
				if (client_request!=null){
					System.out.println("client command : "+client_request);
					call_func(client_request);
				}else{
					System.out.println("error - null command - 02");
					close_and_reconnect();
				}
			}
			catch (IOException e) {
				System.out.println("error in getting command - 03");
				close_and_reconnect();
				e.printStackTrace();
			}		
		}

		//}
	}

	public void reconnect(){
		server_obj s = null;
		try {
			s = new server_obj(server_socket);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		s.start();
	}

	public void close_and_reconnect() {
		try {
			System.out.println("closeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
			soc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		reconnect();
	}

	public void run(){	//메인 기능 함수, 클라리언트 접속 대기하고, 이후 접송 종료까지 고객요청 접수 
		receive_new_client_connection();
	}

	public void call_func(String _client_request) throws NumberFormatException, IOException{
		if (_client_request.equals("send_one_info_from_id_n")){
			send_info_from_id_n();
		}else if (_client_request.equals("send_all_info")){
			send_all_info_with_size();
		}else if (_client_request.equals("expection")){
			expection();
		}else if (_client_request.equals("open")){
			b_open();
		}else if (_client_request.equals("close")){
			b_close();
		}

		/*switch(_client_request){
		case command.send_one_info_from_id_n:
			send_info_from_id_n();
			break;
		case command.send_all_info:
			send_all_info_with_size();
			break;		
		default:
			break;
		}*/
	}

	public void show_connection_info(Socket _soc){
		System.out.println("server 정보 : "+_soc.getLocalSocketAddress());
		System.out.println("client 정보 : "+_soc.getRemoteSocketAddress());
		System.out.println("server socket 정보 : " + server_socket.toString());
	}
	public void connect_db(){
		db.connect();
	}

	public void send_info_from_id_n() throws NumberFormatException, IOException{
		System.out.println("start send_info_from_id_n()");
		int _id_n=Integer.parseInt(listen.readLine());
		int[] arr = db.search_info_from_id_n(_id_n);
		send.println(arr[0]);
		send.println(arr[1]);
		send.println(arr[2]);
		send.println(arr[3]);
		send.flush();
		System.out.println("end send info from id_n, " + Arrays.toString(arr));
	}

	public void send_info_from_id_n(int _id_n){
		int[] arr = db.search_info_from_id_n(_id_n);
		send.println(arr[0]);
		send.println(arr[1]);
		send.println(arr[2]);
		send.println(arr[3]);
		send.flush();
	}

	public void send_all_info_with_size(){
		Vector<double[]> _vec = db.get_all_info();
		int _size = _vec.size();
		System.out.println("total info size : "+_size);

		send.println(_size);
		send.flush();
		for (int i=0; i<_size ;i++){
			send.println(_vec.elementAt(i)[0]);
			send.flush();
			send.println(_vec.elementAt(i)[1]);
			send.println(_vec.elementAt(i)[2]);
			
			//send.println(_vec.elementAt(i)[3]);
			send.flush();			
		}
	}
	public void set_b_table() throws NumberFormatException, IOException{
		int b = Integer.parseInt(listen.readLine());
		db.set_b_table(b);
	}

	public void expection(){
		String str1 =Double.toString(db.get_expected_time_2("CO")); 
		String str2 =Double.toString(db.get_expected_time_2("SMOKE")); 

		send.println(str1);
		send.flush();
		send.println(str2);
		send.flush();
		
		System.out.println(str1);
		System.out.println(str2);
	}
	public void b_open(){
		db.set_b_table(1);
	}
	public void b_close(){
		db.set_b_table(0);		
	}
}//end class

