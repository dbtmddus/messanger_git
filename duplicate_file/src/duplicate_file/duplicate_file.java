package duplicate_file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class duplicate_file {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		FileInputStream in= new FileInputStream("a.jpg");
		FileOutputStream out = new FileOutputStream("a_duplicated5.jpg");
		
		int data;
		byte byte_data;
		
		while ((data=in.read()) != -1) {
			byte_data = (byte)data;
			//out.write(byte_data);
			out.write(data);
		}
		
		in.close();
		out.close();
	}//end main
}// end
