/**
 *  12/02
 *  oracle ���� ����
 * 
 */

package server_obj;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantLock;

import common_use.CC;
import common_use.command;

public class server_obj extends Thread {

	//static (thread)
	static DB_obj db = new DB_obj();
	static final int max_client_num = 100000;
	static String[][] ip_and_port = new String[max_client_num][2];
	static int next_available_id_n=1;	//���� �ο��� ������ȣ ����
	static Socket[][] socket_by_id_n = new Socket[max_client_num][2];
	///////////////////////
	int logged_in_id_n=0;
	String logged_in_id ="";

	private ServerSocket server_socket = null;	//static ����� thread���� ���� (���� �л�ó�� �����ϹǷ� static x)
	private Socket soc = null;
	int open_port_num;
	private BufferedReader listen;
	PrintWriter send;
	//
	private Socket soc_chat;
	private PrintWriter send_for_chat ;		//�߰�
	private BufferedReader listen_for_chat;

	static ReentrantLock locker = new ReentrantLock();				// ����ȭ

	public server_obj(ServerSocket ss) throws IOException{
		server_socket = ss;
		System.out.println(server_socket + "is made");
	}

	public void init_server(){
		connect_db();
		for (int i=0; i<max_client_num ; i++){
			ip_and_port[i][0] = null;
			socket_by_id_n[i][0] = null;
			socket_by_id_n[i][1] = null;
		}
	}

	public void receive_new_client_connection(){
		try {
			locker.lock();
			try{
				soc = server_socket.accept(); //�� �� ���� ���
				System.out.println("\nĿ�ǵ� ���� ���� ����(����)");

				soc_chat = server_socket.accept(); //�̷��� ���� ���� �� Ŭ���̾�Ʈ���̶� �̰��� �ƹ��ų� �����ؼ� ��.
				System.out.println("ä�� ���� ���� ����(����)");

				listen = new BufferedReader(new InputStreamReader(soc.getInputStream()));
				send = new PrintWriter(new BufferedWriter(new OutputStreamWriter(soc.getOutputStream())));
				System.out.println("\n Ŀ�ǵ� ���� ����");
				show_connection_info(soc);

			}catch(Exception ee){
				System.out.println("�ʱ� ���� ����---------------------------------------------");
				soc.close();
				soc_chat.close();
			}finally{
				locker.unlock();
			}

			while (soc !=null){		// �̵� ������ ip�ּҰ� �ٲ�� ��� (�Ͻ������� ���ͳ��� ����� ��� //�� listen, send ���� �Լ�ȭ �ϰ� catch �κп��� �ش� �Լ� ��� �ٽ� �õ��ϵ��� ����
				call_func();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public void set_chat_socket() throws IOException{
		soc_chat = server_socket.accept(); //�� �� ���� ���
		System.out.println("��� ���� ���� ����(������)");
	}
	public void run(){	//���� ��� �Լ�, Ŭ�󸮾�Ʈ ���� ����ϰ�, ���� ���� ������� ����û ���� 
		receive_new_client_connection();
	}

	public void call_func() throws IOException{
		String _client_request;
		_client_request = listen.readLine();
		System.out.println("\n--------------------------------------------");
		System.out.println("client request : " +_client_request+"\n----");

		try{
			switch(_client_request){
			case command.login:
				login();
				break;
			case command.sign_up:
				sign_up();
				break;
			case command.friends_detail_info:
				send_friend_detail_info();
				break;
			case command.add_friend:
				add_friend();
				break;
			case command.friend_ip_and_port:
				send_friend_ip_and_port();
				break;
			case command.normal_message:
				listen_normal_message();
				break;
			case command.id_n_from_id:
				send_id_n_from_id();
				break;
			case command.update_txt:
				update_txt();
				break;
				
			default:
				break;
			}
		} catch (IOException e) {
			System.out.println("client exited");
			if (logged_in_id_n!=0){
				ip_and_port[logged_in_id_n][0]=null;
				ip_and_port[logged_in_id_n][1]="0";
				socket_by_id_n[logged_in_id_n][0] = null;
				socket_by_id_n[logged_in_id_n][1] = null;
			}
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void login() throws IOException{
		//System.out.println("enter log-in func");
		String id = listen_and_record(CC.id);
		int password = Integer.parseInt(listen_and_record(CC.pw));

		boolean b_approved = db.confirm_login(id, password);
		send_and_record(Boolean.toString(b_approved), CC.b_approved);

		if (b_approved){
			logged_in_id = id;
			logged_in_id_n = db.get_id_n_from_id(id);			
			ip_and_port[logged_in_id_n][0] = soc.getInetAddress().toString().substring(1);
			ip_and_port[logged_in_id_n][1] = Integer.toString( soc.getPort() );
			socket_by_id_n[logged_in_id_n][0] = soc;
			socket_by_id_n[logged_in_id_n][1] = soc_chat;

			send_and_record(Integer.toString(logged_in_id_n), CC.logged_in_id_n);
			show_connected_client_10();
		}
		//System.out.println("finish log-in func");
	}

	public void sign_up() throws IOException{
		//System.out.println("enter sign up func");
		String id = listen_and_record(CC.SignUp_Id);
		int pw = Integer.parseInt(listen_and_record(CC.SignUp_Pw));

		while(db.inspect_id_n_exist_already(next_available_id_n)){		//������ȣ ���� (�̹� ä���� ������ȣ�� �Ѿ)
			next_available_id_n++;						//���� ��� �� ���� �� �� ���, ���ѷ�Ʈ�� �� �� ����	(���� ���ɼ�)
		}
		db.insert_new_client(next_available_id_n, id, pw);
		System.out.println("new client signed up : ( "+id+ " / "+pw+" / "+next_available_id_n );
		//System.out.println("finish log-in func");
	}

	public void add_friend() throws IOException {
		//System.out.println("enter add friend func");
		String f_id = listen_and_record(CC.fid);

		boolean b_approved = db.add_friend(logged_in_id_n, logged_in_id, f_id); 
		send_and_record(Boolean.toString(b_approved), CC.b_approved);
		//System.out.println("finish add friend func");
	}

	public void send_friend_detail_info() throws IOException, ClassNotFoundException{
		//System.out.println("enter friend list func");
		Vector[] temp_f_info = db.get_friend_info2(logged_in_id_n);

		//ģ�� ���, detail_info �� ����
		int num_of_friend = temp_f_info[0].size();
		send_and_record(Integer.toString(num_of_friend), CC.num_of_friend);
		for(int i=0; i<num_of_friend; i++){
			send.println(temp_f_info[0].elementAt(i));
			send.println(temp_f_info[1].elementAt(i));
			send.flush();
			File file_temp = (File)temp_f_info[2].elementAt(i);
			//send_file_with_obj_stream(file_temp);	// send.println(temp_f_info[2].elementAt(i)) ����
			send_file_fast(file_temp);
			send.println(temp_f_info[3].elementAt(i));
			send.flush();
		}
		for (int i=0; i<4; i++){
			System.out.println(logged_in_id + "�� ģ�� detail info : " + temp_f_info[i].toString());
		}
		//System.out.println("finish friend list func");
	}

	public void send_file_with_obj_stream(File _file) throws IOException, ClassNotFoundException{//objectstream ����, �����ϰ� �� �����ϳ� ����� ���� ������.
		soc.getInputStream().read();	//�� �κ� ������, ������ ������� �ʰ� Ŭ���̾�Ʈ�� ������ �ϳ��� �޾ƿ��� ���� ���� ���� ���������µ�, �� �� Ŭ���̾�Ʈ�� ���� �����͵� ����ְ� �Ǿ� �� �Է��� ����ϴ� ���� ����.
		ObjectOutputStream toClient = new ObjectOutputStream(soc.getOutputStream());
		toClient.reset();
		if (_file!=null){
			toClient.writeObject(_file);
		}
		else{
			File null_file = null;
			toClient.writeObject(null_file);
		}
		toClient.flush();
	}

	public void send_file_fast(File _file) throws IOException{		
		soc.getInputStream().read();	//�� �κ� ������, ������ ������� �ʰ� Ŭ���̾�Ʈ�� ������ �ϳ��� �޾ƿ��� ���� ���� ���� ���������µ�, �� �� Ŭ���̾�Ʈ�� ���� �����͵� ����ְ� �Ǿ� �� �Է��� ����ϴ� ���� ����.

		//send len
		int _file_len = (int)_file.length();
		send.println(_file_len);
		send.flush();

		//file data load
		if(_file_len!=0){
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(_file));
			byte[] mybytearray  = new byte[(int)_file.length()];
			bis.read(mybytearray,0,mybytearray.length);

			//data transmit
			OutputStream os = soc.getOutputStream();
			os.write(mybytearray,0,mybytearray.length);
			os.flush();

			//System.out.println("server send " + _file.getName() + "(" + _file.length() + " bytes)");

			//close
			bis.close();
		}
	}


	public void send_friend_ip_and_port() throws IOException{
		String f_id = listen_and_record(CC.f_id);
		//String f_id = listen.readLine();
		int f_id_n = db.get_id_n_from_id(f_id);
		send_and_record(ip_and_port[f_id_n][0], CC.friend_ip);	//friend ip
		send_and_record(ip_and_port[f_id_n][1], CC.friend_port); 	//firned port
	}

	public void send_id_n_from_id() throws IOException{
		String _id = listen_and_record(CC._id);
		int _id_n = db.get_id_n_from_id(_id); 
		send_and_record(Integer.toString(_id_n), CC.f_id_n);
	}

	public void connect_db(){
		db.connect();
	}

	public void show_client_info(){
		System.out.println( soc.getRemoteSocketAddress());
	}

	public void show_connected_client_10(){
		for(int i=0; i<10; i++){
			System.out.print(ip_and_port[i][0] +"/"+ip_and_port[i][1]+", ");
		}
		System.out.println();
		System.out.println();
	}

	public void show_connection_info(Socket _soc){
		System.out.println("server ���� : "+_soc.getLocalSocketAddress());
		System.out.println("client ���� : "+_soc.getRemoteSocketAddress());
		System.out.println("server socket ���� : " + server_socket.toString());
		System.out.println();
	}
	public String listen_and_record(String str_var) throws IOException{
		String str_listen = listen.readLine();

		StackTraceElement[] stack = this.getStackTrace();	
		System.out.println("listen msg : "+str_listen +"	/	"+str_var+"	/	at "+stack[2]);
		System.out.println("---------------------------------------------------------");
		return str_listen;
	}
	public void send_and_record(String str_send,String str_var) throws IOException{
		send.println(str_send);
		send.flush();

		StackTraceElement[] stack = this.getStackTrace();	
		System.out.println("send msg : "+str_send +"	/	"+str_var+"	/	at "+stack[2]);
		System.out.println("---------------------------------------------------------");
	}


	public void listen_normal_message() throws NumberFormatException, IOException{

		int _f_id_n= Integer.parseInt(listen_and_record(CC.f_id_n));
		String m = listen_and_record(CC.chatting_msg);

		System.out.println(m+" to "+ _f_id_n+" from "+ logged_in_id_n);

		record_chat_txt(_f_id_n, m);	//������ �ϵ忡 ��ȭ ���
		
		if(socket_by_id_n[_f_id_n][1]!=null){
			send_normal_message(_f_id_n, m);
		}else{
			System.out.println("������ ���Դϴ�");
			//vector�� �߰�
		}
	}

	public void send_normal_message(int _f_id_n, String msg) throws NumberFormatException, IOException{
		Socket f_soc_chat = socket_by_id_n[_f_id_n][1];
		//String _f_id = db.get_id_from_id_n(_f_id_n);

		if (f_soc_chat != null){
			System.out.println("friend being connected");			
			PrintWriter send_to_fr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(f_soc_chat.getOutputStream())));
			send_to_fr.println(logged_in_id);
			send_to_fr.println(msg);
			send_to_fr.flush();
		}else{
			System.out.println("friend being not connected");			
		}
	}

	public void record_chat_txt(int _f_id_n, String str) throws IOException{
		String friend_id = db.get_id_from_id_n(_f_id_n);
		String str_dir = ".\\server\\chat_record\\"+logged_in_id+"\\"+friend_id;
		File f = new File(str_dir+"\\"+friend_id+".txt");		
		new File(str_dir).mkdirs();
		FileOutputStream f_out = new FileOutputStream(f, true);	//�̾� ����
		f_out.write(str.getBytes(Charset.forName("UTF-8")));
		f_out.close();
	}
	
	public void update_txt() throws IOException{
		String server_StrDir = ".\\qwe";
		File server_file = new File(server_StrDir+"\\server.txt");		
		Vector<String> server_ChattingRecord = new Vector<String>(0);
		BufferedReader server_br = new BufferedReader(new FileReader(server_file));
		String line_for_server;
		while ((line_for_server = server_br.readLine()) != null) {
			server_ChattingRecord.add(line_for_server);		//������ txt ���� ��ü ������� �� ����
		}
		System.out.println("server_ChattingRecord : "+server_ChattingRecord.toString());

		String str_stop = "continue";
		for (int i=server_ChattingRecord.size()-1; i>=0 && !str_stop.equals("stop"); i--){		//�������� �ϳ�������, ���̸� Ŭ���̾�Ʈ���� �ߴ� ��û 
			send_and_record(server_ChattingRecord.elementAt(i), CC.chat_record);
			str_stop = listen_and_record(CC.str_stop);
		}
		server_br.close(); 
	}

}//end class

