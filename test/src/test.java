import java.util.Vector;

public class test {

	private int n1=0;
	private static int n2=0;
	
	public static void main(String[] args) {
		obj o1= new obj();
		obj o2= new obj();
		
		o1.q1++;	//public int q1;
		o1.q2++;	//public static int q2;
		
		System.out.println(o2.q1);
		System.out.println(o2.q2);

		obj o3 = new obj();
		
		System.out.println(o2.q1);
		System.out.println(o2.q2);

	}
	
	
}
