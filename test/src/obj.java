public class obj {

	private int n1=0;
	private static int n2=0;

	public int q1=0;
	public static int q2=0;
	
	public obj(){		
		q1=0;
		q2=0;
	}

	public void increase_and_show(){
		n1++;
		n2++;
		System.out.println(n1+" / "+n2);
	}
	public void show(){
		System.out.println(n1+" / "+n2);
	}
	public void increase_n1(){
		n1++;
	}
	public static void increase_n2(){
		n2++;
	}
}
