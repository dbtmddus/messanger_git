package thread_test;

public class thread_extends extends Thread{

	public static int[] arr = new int[10];
	public int iden_n=0;
	public int a=0;
	
	public thread_extends(int _iden_n){
		a=0;
		iden_n= _iden_n;
	}	
	
	public void run(){
		print_a();
	}
	
	public void print_a(){
		while(true){
			if (a<100000){
				try {
					Thread.sleep(1000);
					a++;
					System.out.println(iden_n +" - "+a);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
