/***
 * 12.14
 * 상태메세지 정상 구동 확인 후 채팅창 구현 예정
 * get_friend_list 전격 수전 직전 
 *  
 */

package server_obj;

import java.io.IOException;
import java.net.ServerSocket;

public class main {

	static ServerSocket server_socket;
	static server_obj server0;
	
	public static void main(String[] args) throws IOException {

		server_socket = new ServerSocket(1245);
		server0 = new server_obj(server_socket);
		server0.init_server();
		//server0.connect_db();

		server_obj server1 = new server_obj(server_socket);
		server1.start();

		server_obj server2 = new server_obj(server_socket);
		server2.start();
		
		server_obj server3 = new server_obj(server_socket);
		server3.start();
		
		show_connected_client();
		//server1.request_friend_list_test();		
	}//end main
	
	static public void show_connected_client(){
		while(true){
			try {
				server0.show_connected_client();
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}



//http://blog.naver.com/mktcrmer/220854105687 - oracle express 설치

//println과 print 혼용시 문자열이 합쳐지는 오류
//swing구현중 이벤트 헨들러 실수로 버튼 하나에 2번 이상 들어가면 해당 횟수만큼 반복 되는듯 (버튼 1번 눌렀는데 2번 이벤트 발생 등)
//String sql = "select f_id from login, friend_list where login.id_n = ?"; 로 수행 후,
// rs.getString(friend_list.f_id) 하면 오류남..... ㅂㄷㅂㄷ 그냥 f_id로 불러와야함
//DISPOSE_ON_CLOSE exit_ON_CLOSE	 -> JFrame 종료시 해당 프레임만 종료할지, 프로그램 자체 종료할지 설정
// setVisible(true); -> 너무 앞에서 호출 시 frame내의 컴포넌트 들이 frame을 resize했을때 뒤늦게 표시될 수 있음. 제일 마지막에 호출 권장
// revalidate(), repaint();		//2개 같이 쓰기 권장
