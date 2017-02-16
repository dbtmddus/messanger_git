package update;

import java.io.IOException;


public class client {

	public static void main(String[] args) throws IOException {	
		client_obj cl = new client_obj("127.0.0.1");//, 1246);
		cl.enter();
	}//end main
}
