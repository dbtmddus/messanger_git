package my_sql_simple;

import java.sql.SQLException;
import java.util.Random;

public class db_main {
	public static void main(String[] args) throws SQLException {
		DB_obj db = new DB_obj();
		db.connect();
	//	db.show_all();
		
	}


	public static double get_expected_time(){
		double[] time = {110,120,130,140,150};
		//double[] time = {10,20,30,40,50};
		double[] ppi = {10,30,50,70,90};
		
		double x = time[0]+time[1]+time[2]+time[3]+time[4];
		double x2 = (time[0]*time[0]) + (time[1]*time[1]) + (time[2]*time[2]) + (time[3]*time[3]) + (time[4]*time[4]);

		double y = ppi[0] + ppi[1] + ppi[2] + ppi[3] + ppi[4];
		double xy = (time[0]*ppi[0]) + (time[1]*ppi[1]) + (time[2]*ppi[2]) + (time[3]*ppi[3]) + (time[4]*ppi[4]);
		double a = ((x2*y)-(x*xy)) / ( ((5*x2)-(x*x)) + 0.0001 );
		double b = ((5*xy)-(x*y)) / ( ((5*x2)-(x*x)) + 0.0001 );

		double golden_time;
		System.out.println("co");
		golden_time = (100-a)/(b+0.0001);

		double remaining_time = golden_time-time[4];
		
		System.out.println("x : " +x);
		System.out.println("x2 : " +x2);
		System.out.println("y : " +y);
		System.out.println("xy : " +xy);
		System.out.println("a : " +a);
		System.out.println("b : " +b);
		
		System.out.println("golden : " +golden_time);
		
		return remaining_time;
	}

}

