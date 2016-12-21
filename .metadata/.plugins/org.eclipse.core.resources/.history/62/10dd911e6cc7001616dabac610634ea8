package messanger_test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Vector;

public class duplicate_file2 {

	public static void main(String[] args) throws IOException {
		
		FileInputStream in= new FileInputStream("a.jpg");
		InputStreamReader in_r = new InputStreamReader(in);
		
		FileOutputStream out = new FileOutputStream("a_duplicated100.jpg");
		//OutputStreamWriter out_w = new OutputStreamWriter(out);
				
		int data;		
		while ((data=in_r.read()) != -1) {
			out.write((byte)data);
		}
		
		in_r.close();
		in.close();
		//out_w.close();
		out.close();
		
	}//end main
}// end
