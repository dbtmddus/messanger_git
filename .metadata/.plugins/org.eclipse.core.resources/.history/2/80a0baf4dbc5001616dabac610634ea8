package send_file_basic;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class client {

	public final static int SOCKET_PORT = 13267;      // you may change this
	public final static String SERVER = "127.0.0.1";  // localhost
	public final static String FILE_TO_RECEIVED = "qkf_downloaded.png";  // you may change this, I give a

	public final static int FILE_SIZE = 6022386; // file size temporary hard coded
	// should bigger than the file to be downloaded

	public static void main (String [] args ) throws IOException, InterruptedException {
		int bytesRead;
		int current = 0;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		Socket sock = null;
		try {
			sock = new Socket(SERVER, SOCKET_PORT);
			System.out.println("Connecting...");

			// receive file
			byte [] mybytearray  = new byte [FILE_SIZE];
			InputStream is = sock.getInputStream();
			fos = new FileOutputStream(FILE_TO_RECEIVED);
			bos = new BufferedOutputStream(fos);

			current = 0;				//2번안. 가독성 위해 위 2줄 대신 직접 넣은 부분. 사실 필요는 없으나 초기화 해주자.
			while( (bytesRead=is.read(mybytearray, current, 40000))  > 0) {
				current += bytesRead;	// read하면서 실제 파일 길이 측정, 값 저장
				System.out.println("current : " + current);
			}
			bos.write(mybytearray, 0 , current);		//실제 길이만큼 write
			
			//Thread.sleep(8000);
			//bos.write(mybytearray, 0 , 165982);
			bos.flush();
			System.out.println("File " + FILE_TO_RECEIVED
					+ " downloaded (" + current + " bytes read)");
		}
		finally {
			fos.close();
			bos.close();
			sock.close();
		}
	}

}