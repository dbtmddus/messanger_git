import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

public class test {

	private int n1=0;

	public static void main(String[] args) throws UnknownHostException, IOException {

		Thread t1 = new Thread(new Runnable() {
			public void run(){f1();}
		});


		Thread t2 = new Thread(new Runnable() {
			public void run(){f2();}
		});

		t1.start();
		t2.start();


	}//end main

	public static void f1(){
		while(true){
			System.out.println("f1");
		}
	}

	public static void f2(){
		while(true){
			System.out.println("f2");
		}
	}


}
