package messanger_test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

public class duplicate_file2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		File f= new File("a.jpg"); 
		FileInputStream in= new FileInputStream(f);
		
		File f2= new File("a_dup2.jpg"); 

		Vector v = new Vector(0);
		v.addElement(f2);		
		File f3 = (File)v.elementAt(0);

		FileOutputStream out = new FileOutputStream(f3);
		
		int data;
		byte byte_data;
		
		while ((data=in.read()) != -1) {
			byte_data = (byte)data;
			out.write(byte_data);
		}
		
		in.close();
		out.close();
	}//end main
}// end
