import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class swing_test {

	public swing_test() throws IOException{

		JFrame frame =new JFrame("dd");
		frame.setBounds(0,0,500,500);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 500, 300);
		frame.add(panel);
		panel.setLayout(null);


		File f = new File("C:\\messanger_image\\qkf.png");
		Image image = ImageIO.read(f);
		ImageIcon icon = new ImageIcon(image);
		JButton b1 = new JButton();
		b1.setBounds(0, 0, 100, 100);
		b1.setIcon(icon);
		
		panel.add(b1);

		JButton b2= new JButton("b1");
		panel.add(b2);

		frame.setVisible(true);

		//frame.removeAll();
	}

	public static void main(String[] args) throws IOException {
		swing_test s = new swing_test();

	}//end creator
}//end

