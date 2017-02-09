import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

public class test {

	private int n1=0;

	public static void main(String[] args) throws UnknownHostException, IOException {

		Vector<Integer> v = new Vector<>(0);
		
		v.addElement(55);
		v.addElement(66);
		
		//int a = 5020000000;
		System.out.println(v.elementAt(v.size()-1));

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
