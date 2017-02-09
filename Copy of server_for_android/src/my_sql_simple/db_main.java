package my_sql_simple;

import java.sql.SQLException;

public class db_main {
	public static void main(String[] args) throws SQLException {
		DB_obj db = new DB_obj();
		db.connect();
	
		db.show_all_db6();
		
		
		System.out.println("CO 남은 시간 : "+db.get_expected_time("CO"));
		System.out.println("SMOKE 남은 시간 : "+db.get_expected_time("SMOKE"));
		System.out.println("gas 남은 시간 : "+db.get_expected_time("GAS"));
			
	}
}
