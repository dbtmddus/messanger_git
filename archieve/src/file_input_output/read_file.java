package file_input_output;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.UnknownHostException;


public class read_file {

	public static void main(String[] args) throws UnknownHostException, IOException {
		read_one_line();
	}
	
	public static void read_one_line() throws IOException{
		String str_dir = ".\\qwe";
		File f = new File(str_dir+"\\dd.txt");		
		
		try (BufferedReader br = new BufferedReader(new FileReader(f))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       System.out.println(line);
		    }
		}				
	}	
}
