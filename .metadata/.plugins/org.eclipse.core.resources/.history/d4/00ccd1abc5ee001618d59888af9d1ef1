import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class client {

	public final static int SOCKET_PORT = 13267;      
	public final static String SERVER = "127.0.0.1"; 
	public final static int FILE_SIZE = 165982*5; 
	
	public static void main (String [] args ) throws IOException, InterruptedException {
		Socket sock = new Socket(SERVER, SOCKET_PORT);
		System.out.println("Connecting...");

		long a=System.currentTimeMillis();
		
		for (int i=1; i<5; i++){
			String r_file_name = "qkf_downloaded"+i+".png";  
			File r_file = new File(r_file_name);
			receive_file(r_file, sock);
		}

		long b=System.currentTimeMillis();
		System.out.println(b-a);
		
		sock.close();
	}

	public static void receive_file(File _file, Socket _sock) throws IOException{
		_sock.getOutputStream().write(1);
		_sock.getOutputStream().flush();			
		
		BufferedReader listen = new BufferedReader(new InputStreamReader(_sock.getInputStream()));
		int file_len = Integer.parseInt(listen.readLine());
		
		byte [] mybytearray  = new byte [FILE_SIZE];
		InputStream is = _sock.getInputStream();
		int bytesRead =0;
		int current = 0;
		while( (bytesRead=is.read(mybytearray, current, file_len-current))  > 0){		
			current += bytesRead;	
			System.out.println("read: "+bytesRead +" / total: " + current);
			if (current == file_len){
				break;
			}
		}
		
		FileOutputStream fos = new FileOutputStream(_file);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		bos.write(mybytearray, 0 , current);
		bos.flush();
		System.out.println("File " + _file.getName() + " downloaded (" + current + " bytes read)");

		fos.close();
		bos.close();
	}

}