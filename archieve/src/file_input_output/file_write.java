package file_input_output;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class file_write {

	public static void main(String[] args) throws UnknownHostException, IOException {
	
		record_txt("dd");
		
	}
	
	public static void record_txt(String str) throws IOException{
		//File f = new File(".\\test\\dd.txt");
		FileOutputStream f_out = new FileOutputStream("C:\\Users\\dbtmddus\\Desktop\\dd.txt");
		f_out.write(str.getBytes(Charset.forName("UTF-8")));
		f_out.close();
	}	
	
	
}
