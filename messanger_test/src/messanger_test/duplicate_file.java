package messanger_test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class duplicate_file {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		FileInputStream inputStream= new FileInputStream("a.jpg");
		FileInputStream inputStream2= new FileInputStream("a.jpg");
		FileOutputStream outputStream = new FileOutputStream("a_duplicated.jpg");
		
		int input_size;
		byte input_data;
		
		while ( (input_size = inputStream2.read() ) != -1) {
			input_data = (byte)inputStream.read();
			outputStream.write(input_data);
		}
		
		inputStream.close();
		outputStream.close();
	}//end main
}// end
