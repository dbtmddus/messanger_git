package my_sql_simple;
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

	//static String url = "jdbc:mysql://localhost:3306/";	
	static String url = "jdbc:mysql://localhost/test?useSSL=false";
	static String id = "root";
	static String pw = "134652";

	public DB_obj(){

	}

	public void connect(){	//http://onoctober.tistory.com/54
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("driver load success");
			try{
				conn = DriverManager.getConnection(url, id, pw);
				//conn = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=134652");
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
			//pstmt.setString(1, x);
			rs = pstmt.executeQuery();
			//System.out.println(rs.next());
			while (rs.next()){
				System.out.print("name : " + rs.getString("NAME"));
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

	public void insert_new_client(int _id_n, int _den_1, int _den_2,int _den_3){
		try{
			String sql="insert into t_test values(?,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, _id_n);
			pstmt.setInt(2, _den_1);
			pstmt.setInt(3, _den_2);
			pstmt.setInt(4, _den_3);
			pstmt.executeUpdate();
			//rs = pstmt.executeQuery();	//!!!!!!!!!!!!!!!!! 결과물이 없는 쿼리의 경우 쓰면 에러 유말 (쿼리 동작은 함)

			System.out.println("insert complete");
			pstmt.close();					
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}

	public void delete_all(){
		try{
			String sql="delete from login_test";

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
			String sql = "select * from t_test";

			pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1, x);
			rs = pstmt.executeQuery();
			//System.out.println(rs.next());
			while (rs.next()){
				System.out.print("id_n : " + rs.getInt("id_n"));
				System.out.println(" / den_1 : " + rs.getInt("den_1"));
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

	public int[] search_info_from_id_n(int _id_n){
		int[] return_v = new int[4];
		try{
			String sql="select * from t_test where id_n=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, _id_n);
			rs = pstmt.executeQuery();

			if (rs.next()){
				return_v[0] = rs.getInt("id_n");
				return_v[1] = rs.getInt("den_1");
				return_v[2] = rs.getInt("den_2");
				return_v[3] = rs.getInt("den_3");
				rs.close();
				pstmt.close();					
				return return_v;
			}else{
				System.out.println("해당하는 값이 없습니다.");
				rs.close();
				pstmt.close();
			}			
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		int[] error_return = {-1,-1,-1,-1};
		return error_return;
	}

	public int info_size(){
		int size_info = 0;
		try{
			String sql="select * from t_test";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()){
				size_info++;
			}
			rs.close();
			pstmt.close();					
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return size_info;
	}

	public Vector<int[]> get_all_info_old_v(){
		Vector<int[]> info= new Vector<int[]>(0);
		//int info_size=0;
		try{
			String sql="select * from t_test";
			//info.set(0, new int[]{0,0,0,0});d에러
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()){
				//info_size++;
				int[] t_arr = new int[4];
				t_arr[0] = rs.getInt("id_n");
				t_arr[1] = rs.getInt("den_1");
				t_arr[2] = rs.getInt("den_2");
				t_arr[3] = rs.getInt("den_3");
				info.add(t_arr);
			}
			rs.close();
			pstmt.close();					
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		//info.elementAt(0)[0] = info_size;
		System.out.println("size at db : "+ info.size());
		return info;
	}


	public Vector<int[]> get_all_info(){
		Vector<int[]> info= new Vector<int[]>(0);
		//int info_size=0;
		try{
			String sql="select * from db_05";
			//info.set(0, new int[]{0,0,0,0});d에러
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()){
				//info_size++;
				int[] t_arr = new int[4];
				t_arr[0] = rs.getInt("PPI_CO");
				t_arr[1] = rs.getInt("PPI_SMOKE");
				//t_arr[2] = rs.getInt("den_2");
				//t_arr[3] = rs.getInt("den_3");
				info.add(t_arr);
			}
			rs.close();
			pstmt.close();					
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		//info.elementAt(0)[0] = info_size;
		System.out.println("size at db : "+ info.size());
		return info;
	}

	public void set_b_table(int b){
		try{
			String sql = "update b_table set bool=? where n = 1";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b);
			pstmt.executeUpdate();

			pstmt.close();					
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}

	public void show_all_db6(){
		try{
			String sql = "select * from db_06";

			pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1, x);
			rs = pstmt.executeQuery();
			//System.out.println(rs.next());
			while (rs.next()){
				System.out.println("co : " + rs.getInt("PPI_CO"));
				System.out.println("smoke : " + rs.getInt("PPI_SMOKE"));
			}
			rs.close();
			pstmt.close();					
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	public double get_expected_time(String str){

		try{
			String sql="SELECT * FROM DB_06 ORDER BY Time DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			double[] time = new double[5];
			double[] ppi = new double[5];

			//rs>5=일때
			for (int i=0; i<5; i++){
				rs.next();
				ppi[i] = rs.getDouble("PPI_"+str);					
				time[i] = rs.getDouble("TIME_CO");	
			}

			rs.close();
			pstmt.close();		

			double x = time[0]+time[1]+time[2]+time[3]+time[4];
			double x2 = (time[0]*time[0]) + (time[1]*time[1]) + (time[2]*time[2]) + (time[3]*time[3]) + (time[4]*time[4]);

			double y = ppi[0] + ppi[1] + ppi[2] + ppi[3] + ppi[4];
			double xy = (time[0]*ppi[0]) + (time[1]*ppi[1]) + (time[2]*ppi[2]) + (time[3]*ppi[3]) + (time[4]*ppi[4]);
			double a = ((x2*y)-(x*xy)) / ( ((5*x2)-(x*x)) + 0.0001 );
			double b = ((5*xy)-(x*y)) / ( ((5*x2)-(x*x)) + 0.0001 );
			
			double golden_time;
			if (str.equals("CO")){
				System.out.println("co");
				golden_time = (100-a)/(b+0.0001);
			}else if (str.equals("SMOKE")){
				System.out.println("smoke");
				golden_time = (250-a)/(b+0.0001);	
			}else{
				System.out.println("gas");
				golden_time = (200-a)/(b+0.0001);			
			}
			double remaining_time = golden_time-time[0];
			return remaining_time;
		}catch (SQLException e){
			e.printStackTrace();
			return -1;
		}
	}
}//end class
