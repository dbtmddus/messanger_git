package new_messanger_simple_test;
/**
 *  12/02
 *  oracle 연동 직후
 * 
 */


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

public class server_obj extends Thread {

	//static (thread)
	static final int max_client_num = 100000;
	static String[][] ip_and_port = new String[max_client_num][2];
	static int next_available_id_n=1;	//다음 부여될 고유번호 관리
	static Socket[][] socket_by_id_n = new Socket[max_client_num][2];
	///////////////////////
	int logged_in_id_n=0;
	String logged_in_id ="";

	private ServerSocket server_socket = null;	//static 선언시 thread끼리 공유 (향후 분산처리 가능하므로 static x)
	private Socket soc = null;
	int open_port_num;
	private BufferedReader listen;
	PrintWriter send;
	//
	private Socket soc_chat;
	private PrintWriter send_for_chat ;		//추가
	private BufferedReader listen_for_chat;

	static ReentrantLock locker = new ReentrantLock();				// 동기화

	public server_obj(ServerSocket ss) throws IOException{
		server_socket = ss;
		System.out.println(server_socket + "is made");
	}

	public void receive_new_client_connection(){
		try {
			locker.lock();
			try{
				soc = server_socket.accept(); //새 고객 접속 대기
				System.out.println("\n커맨드 소켓 연결 성공(서버)");

				soc_chat = server_socket.accept(); //이렇게 열면 완전 새 클라이언트용이랑 이것중 아무거나 선택해서 들어감.
				System.out.println("채팅 소켓 연결 성공(서버)");

				listen = new BufferedReader(new InputStreamReader(soc.getInputStream()));
				send = new PrintWriter(new BufferedWriter(new OutputStreamWriter(soc.getOutputStream())));
				System.out.println("\n 커맨드 소켓 정보");

			}catch(Exception ee){
				System.out.println("초기 접속 오류---------------------------------------------");
				soc.close();
				soc_chat.close();
			}finally{
				locker.unlock();
			}

			while (soc !=null){		// 이동 등으로 ip주소가 바뀌는 경우 (일시적으로 인터넷이 끊기는 경우 //위 listen, send 포함 함수화 하고 catch 부분에서 해당 함수 계속 다시 시도하도록 변경
				call_func();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public void set_chat_socket() throws IOException{
		soc_chat = server_socket.accept(); //새 고객 접속 대기
		System.out.println("통신 소켓 연결 성공(서버측)");
	}
	public void run(){	//메인 기능 함수, 클라리언트 접속 대기하고, 이후 접송 종료까지 고객요청 접수 
		receive_new_client_connection();
	}

	public void call_func() throws IOException{
		String _client_request;
		_client_request = listen.readLine();
		System.out.println("\n--------------------------------------------");
		System.out.println("client request : " +_client_request+"\n----");

		switch(_client_request){
		case command.update_txt:
			update_txt();
			break;

		default:
			break;
		}
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

	public void update_txt() throws IOException{
		String server_StrDir = ".\\qwe";
		File server_file = new File(server_StrDir+"\\server.txt");		
		Vector<String> server_ChattingRecord = new Vector<String>(0);
		BufferedReader server_br = new BufferedReader(new FileReader(server_file));
		String line_for_server;
		while ((line_for_server = server_br.readLine()) != null) {
			server_ChattingRecord.add(line_for_server);		//서버측 txt 내용 전체 순서대로 다 넣음
		}
		System.out.println("server_ChattingRecord : "+server_ChattingRecord.toString());

		String str_stop = "continue";
		for (int i=server_ChattingRecord.size()-1; i>=0 && !str_stop.equals("stop"); i--){		//역순으로 하나씩보냄, 끝이면 클라이언트에서 중단 요청 
			send_and_record(server_ChattingRecord.elementAt(i), CC.chat_record);
			str_stop = listen_and_record(CC.str_stop);
		}
		server_br.close(); 
	}


}//end class

