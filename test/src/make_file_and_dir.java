import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchEvent.Modifier;
import java.util.Iterator;

public class make_file_and_dir {

	public static void main(String[] args) throws UnknownHostException, IOException {
		record_txt("dd");
	}
	
	public static void record_txt(String str) throws IOException{
		String str_dir = "C:\\Users\\dbtmddus\\Desktop\\qwe";
		File f = new File(str_dir+"\\dd.txt");		
		new File(str_dir).mkdirs();
		FileOutputStream f_out = new FileOutputStream(f);
		f_out.write(str.getBytes(Charset.forName("UTF-8")));
		f_out.close();
		
		/*Path p = Paths.get(str_p);
		Files.createDirectories(p);	�̷��Ե� ��. it also works well
		*/
		
	}	
	
	
}
