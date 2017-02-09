package my_sql_simple;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;

public class DB_obj {

	static Connection conn = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;

	//static String url = "jdbc:mysql://localhost:3306/";	
	static String url = "jdbc:mysql://localhost/test?useSSL=false";		//포트없음, local말고 남의 db접근은 
	static String id = "root";											//고정ip또는 라우팅한 공유기 아이피 넣으면됨
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
	
	public void insert_new_client(int co, int smoke){
		try{
			String sql="insert into db_06(PPI_CO,PPI_SMOKE,PPI_GAS,CEL_TEMP,PER_HUM,TIME_CO) values(?,?,0,0,0,0)";			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, co);
			pstmt.setInt(2, smoke);
			pstmt.executeUpdate();
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
			pstmt.close();					
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void show_all(){
		try{
			String sql = "select * from db_06";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()){
				double co = rs.getDouble("PPI_CO");

				double smoke = rs.getDouble("PPI_SMOKE");
				String date = rs.getString("TIME");
				
				String time = date.substring(date.length()-10, date.length()-2);
				String[] time_arr = time.split(":");
				int seconds = (60*60*Integer.parseInt(time_arr[0])) + (60*Integer.parseInt(time_arr[1])) + (Integer.parseInt(time_arr[2])); 
						
				System.out.print("time  : " + date);
				System.out.print(" / CO  : " + co);
				System.out.print(" / SMOKE  : " + smoke);
				System.out.println();
				
				System.out.println("time "+time);
				System.out.println(Arrays.toString(time_arr));
				System.out.println("second : " + seconds);
				System.out.println("day : " + date.substring(date.length()-13, date.length()-11));				
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
	
	public Vector<double[]> get_all_info(){
		Vector<double[]> info= new Vector<double[]>(0);
		try{
			String sql="select * from db_06 ORDER BY Time DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()){
				double[] t_arr = new double[3];	//
				t_arr[0] = rs.getDouble("PPI_CO");
				t_arr[1] = rs.getDouble("PPI_SMOKE");
				
				String date = rs.getString("TIME");
				String time = date.substring(date.length()-10, date.length()-2);
				String[] time_arr = time.split(":");
				String day = date.substring(date.length()-13, date.length()-11);
				
				double seconds = (Integer.parseInt(day)*24*60*60) + (60*60*Integer.parseInt(time_arr[0])) + (60*Integer.parseInt(time_arr[1])) + (Integer.parseInt(time_arr[2])); 
				t_arr[2] = seconds;
				info.add(t_arr);		
			}
			rs.close();
			pstmt.close();					
		}
		catch (SQLException e){
			e.printStackTrace();
		}
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
	
	public double get_expected_time(String str){	//time_co기준 계산. 해당 칩이 있었어야 가능
		try{
			String sql="SELECT * FROM DB_06 ORDER BY Time DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			double[] ppi = new double[5];
			double[] time = new double[5];
			
			//rs>5=일때. 없으면 에러 유발.
			for (int i=0; i<5; i++){
				rs.next();
				ppi[i] = rs.getDouble("PPI_"+str);					
				time[i] = rs.getDouble("TIME_CO");	
			}
			System.out.println("time : "+Arrays.toString(time));

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
	
	public double get_expected_time_2(String str){	//동일 값 반환 확인. 
		try{
			String sql="SELECT * FROM DB_06 ORDER BY Time DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			double[] ppi = new double[5];
			double[] time = new double[5];
			
			//rs>5=일때
			for (int i=0; i<5; i++){
				rs.next();
				ppi[i] = rs.getDouble("PPI_"+str);							
				String date = rs.getString("TIME");
				String day_time = date.substring(date.length()-10, date.length()-2);
				String[] time_arr = day_time.split(":");
				String day = date.substring(date.length()-13, date.length()-11);
				double seconds = (Integer.parseInt(day)*24*60*60) + (60*60*Integer.parseInt(time_arr[0])) + (60*Integer.parseInt(time_arr[1])) + (Integer.parseInt(time_arr[2])); 
				time[i] = seconds;
			}
			
			System.out.println("ppi : "+Arrays.toString(ppi));
			double old = time[4];
			for (int i=0; i<5; i++){
				time[i] = time[i]-old;
			}
			System.out.println("time : "+Arrays.toString(time));
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
				golden_time = (100-a)/(b+0.0001);	//각 수치는 임의 값이며, 라이터 사용시 co값은 거의 변하지 않으며
			}else if (str.equals("SMOKE")){
				System.out.println("smoke");
				golden_time = (250-a)/(b+0.0001);	//스모크는 100정도까지 오름, 80추천
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
