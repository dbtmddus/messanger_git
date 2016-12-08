package client_swing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;

public class main {

	static Socket connected_socket = null;
	static PrintWriter send;
	static BufferedReader listen;
	static JFrame frame;

	public static void main(String[] args) throws IOException {

		main_frame mf = new main_frame(connected_socket, listen, send, frame);

	}//end main
}
