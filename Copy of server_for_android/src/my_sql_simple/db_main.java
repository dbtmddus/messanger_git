package my_sql_simple;

import java.sql.SQLException;

public class db_main {
	public static void main(String[] args) throws SQLException {
		DB_obj db = new DB_obj();
		db.connect();
	
		db.show_all_db6();
		
		
		System.out.println("CO ���� �ð� : "+db.get_expected_time("CO"));
		System.out.println("SMOKE ���� �ð� : "+db.get_expected_time("SMOKE"));
		System.out.println("gas ���� �ð� : "+db.get_expected_time("GAS"));
			
	}
}
