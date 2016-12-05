package server_obj;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class DB_obj {

	static Connection conn = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;

	static String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";	
	static String id = "dbtmddus";
	static String pw = "134652";

	public DB_obj(){

	}

	public void connect(){	//http://onoctober.tistory.com/54
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("driver load success");
			try{
				conn = DriverManager.getConnection(url, id, pw);
				System.out.println("db connect success");
			}
			catch (SQLException e){
				System.out.println("db connect fail");
				System.out.println(e);
			}
		}
		catch(ClassNotFoundException e){
			System.out.println("driver load fail");
			System.out.println(e);
		}
	}


	public void exec_quety(String str_query){
		try{
			String sql = str_query;

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()){
				System.out.print("id : " + rs.getString("id"));
				System.out.println(" / password : " + rs.getInt("password"));
				System.out.println();
			}
			rs.close();
			pstmt.close();					
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}

	public void insert_new_client(int new_id_n, String new_id, int new_pw){	//회원가입
		try{
			String sql="insert into login values(?,?,?)";

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, new_id_n);
			pstmt.setString(2, new_id);
			pstmt.setInt(3, new_pw);
			
			pstmt.executeUpdate();
			//rs = pstmt.executeQuery();	//!!!!!!!!!!!!!!!!! 결과물이 없는 쿼리의 경우 쓰면 에러 유말 (쿼리 동작은 함)

			System.out.println("insert complete");
			pstmt.close();					
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}

	public void delete_all(){				// login 테이블 정보 모두 삭제
		try{
			String sql="delete from login";

			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();

			System.out.println("delete complete");
			pstmt.close();					
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	public void show_all(){
		try{
			String sql = "SELECT * FROM login";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()){
				System.out.print("id_n : " + rs.getInt("id_n"));
				System.out.print("/ id : " + rs.getString("id"));
				System.out.println(" / password : " + rs.getInt("password"));
				System.out.println();
			}
			rs.close();
			pstmt.close();					
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	public void disconnect() throws SQLException{
		conn.close();
	}

	public boolean confirm_login(String input_id, int input_pw){
		String sql = "select password from login where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, input_id);
			rs = pstmt.executeQuery();
			if (rs.next()){
				int pw_from_db = rs.getInt("password");
				if (  pw_from_db== input_pw){	//해당 id의 비밀번호
					//System.out.println("log-in is confirmed!");
					pstmt.close();
					rs.close();
					return true;
				}
				else{
					//System.out.println("log-in is denied_1");
				}
			}else{
				//System.out.println("log-in is denied_2");				
			}
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("error - at confirm_login");
			e.printStackTrace();
		}
		return false;
	}

	public Vector<String> get_friend_list(String input_id){
		Vector<String> friend_list = new Vector<String>();
		try{
			String sql = "select login.f_id from login, friend_list where login.id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, input_id);
			rs = pstmt.executeQuery();
			
			friend_list.addElement(Integer.toString(rs.getFetchSize()));
			for (int i=0; rs.next(); i++){
				friend_list.addElement(rs.getString("id"));
			}
			rs.close();
			pstmt.close();					
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		//System.out.println(friend_list.toString());
		return friend_list;
	}

	public void add_friend(int _id_n, String _id, String _f_id){
		int f_id_n= get_id_n_from_id(_f_id);
		
		try{
			String sql = "insert into friend_list values(?,?,?,?)";

			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, _id_n);
			pstmt.setString(2, _id);
			pstmt.setInt(3, f_id_n);
			pstmt.setString(4, _f_id);
			pstmt.executeUpdate();
			
			pstmt.close();					
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public int get_id_n_from_id(String input_id){
		int return_v = 0;
		try{
			String sql = "select id_n from login where id=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, input_id);

			rs = pstmt.executeQuery();

			if (rs.next()){
				return_v = rs.getInt("id_n");
				rs.close();
				pstmt.close();					
				return return_v;
			}else{
				System.out.println("고유번호 id_n 오류");
				rs.close();
				pstmt.close();					
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return return_v;	//에러 경우 리턴값
	}
	public boolean inspect_id_n_exist_already(int _id_n){
		try{
			String sql = "select id_n from login where id_n=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, _id_n);
			rs = pstmt.executeQuery();

			if (rs.next()){
				rs.close();
				pstmt.close();					
				return true;
			}else{
				rs.close();
				pstmt.close();					
				return false;
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	public boolean inspect_id_exist_already(String id){
		try{
			String sql = "select id_n from login where id_n=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()){
				rs.close();
				pstmt.close();					
				return false;
			}else{
				rs.close();
				pstmt.close();					
				return true;
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}
}
