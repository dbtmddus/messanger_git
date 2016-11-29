package login;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import testmessenger.listen3;
import friend.friendframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class loginframe extends JFrame {

	
	//JFrame frame;
	private JTextField tf1;
	private JTextField tf2;
	private JLabel l1;
	private JLabel l2;
	private JButton b1;
	private JButton b2;
	private JButton b3;
	
	private JLabel bgimg;
	
	
	public loginframe() throws IOException
	{
		
		super("로그인");
		tf1 = new JTextField(10);
		tf2 = new JTextField(10);
		l1 = new JLabel("        I D        : ");
		l2 = new JLabel("Password : ");
		b1 = new JButton("로그인");
		b2= new JButton("종료");	
		b3 = new JButton("계정생성");
		
		l1.setForeground(Color.white);
		l2.setForeground(Color.white);
		//Container c = getContentPane();
		//c.setBackground(new Color (165,248,52));
		//c.setBackground(Color.white);
		
		//Icon ic1 = new ImageIcon("bb.jpg");
		//bgimg= new JLabel(ic1);
		//add(bgimg);
		
		bgimg = new JLabel(new ImageIcon(ImageIO.read(new File("cc.jpg"))));
		setContentPane(bgimg);
		
		add(l1);
		add(l2);
		
		add(tf1);
		add(tf2);
		
		add(b1);
		add(b2);
		add(b3);
		
		
		setLayout(null);

		//bgimg.setBounds(400, 400, 100, 200); 
		l1.setBounds(30, 50, 100, 20); // 가로,세로,탑,레프트위치
        l2.setBounds(30, 90, 100, 20); // 가로,세로,탑,레프트위치     

        tf1.setBounds(110, 50, 120, 20); // 가로,세로,탑,레프트위치
        tf2.setBounds(110, 90, 120, 20); // 가로,세로,탑,레프트위치     

        b1.setBounds(250, 48, 80, 23); // 가로,세로,탑,레프트위치
        b2.setBounds(250, 89, 80, 23); // 가로,세로,탑,레프트위치     
        b3.setBounds(280, 140,90, 23); // 가로,세로,탑,레프트위치     
 
        setVisible(true);
        setSize(390, 210);
        
        b1.addActionListener(action);
        b2.addActionListener(action);
        b3.addActionListener(action);
        
	}
	
	
	ActionListener action = new ActionListener() {

         public void actionPerformed(ActionEvent e) {

                Object obj = e.getSource();
                
                if (obj==b1){
                	System.out.println("로그인 : " + tf1.getText() );
                	System.out.println("비밀번호 : " + tf2.getText() ); 
                	try {
						flogin(tf1.getText(),tf2.getText());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "존재하지 않는 아이디입니다", "로그인 오류", 0);
						System.out.println("존재하지 않는 계정입니다");//e1.printStackTrace();
					}
                	}
                
                else if (obj==b2){
                	System.exit(0);
                	}
                else if (obj==b3){                
                	System.out.println("회원가입");
                	fmakeaccount(tf1.getText(),tf2.getText());
                }
          }
};


	public void fmakeaccount(String id, String password) {		//저장을 password 말고 encipherment()로
		
    String data = "d:\\"+id+".txt";  	//디렉토리+파일명
    
    try{
     String str="";
     FileWriter fw = new FileWriter(data);  
     BufferedWriter save_bw = new BufferedWriter(fw);
     
     save_bw.write(id); 	 
     save_bw.write("\r\n");
     save_bw.write(encipherment(password));
     save_bw.write("\r\n");
     save_bw.close();
     setTitle("bb"); 					 
     }catch(Exception e){
   	 System.out.println("아이디 생성 오류");
    }	  
	}
	
	public String encipherment(String password){
		
		int intps = Integer.parseInt(password)+13245;
				
   		return Integer.toString(intps);
	}
	
	public String unencipherment(String encips){
		
		int intps = Integer.parseInt(encips)-13245;
		
   		return Integer.toString(intps);
	}
	
	public void flogin(String id, String password) throws IOException{
		int logmark=0;
		String data = "d:\\"+id+".txt";
		FileReader fr = new FileReader(data);
		BufferedReader br = new BufferedReader(fr);

		String savedid =br.readLine();
		String savedps = br.readLine();
		if (id.equals(savedid) && password.equals(unencipherment(savedps)) ){
			logmark=1;		
			
			System.out.println("로그인 되었습니다");
			dispose();
			
			listen3 li = new listen3();
			li.setid(id);
			Thread t = new Thread(li);
			t.start();
			
			friendframe friendframe = new friendframe(id);					//넘김
			}
		else {
			JOptionPane.showMessageDialog(null, "비밀번호 오류", "로그인 오류", 0);
			System.out.println("정보가 다릅니다");
		}

		 System.out.println(savedid.length());
		 System.out.println(savedps.length());
		 
		 System.out.println(id.length());
		 System.out.println(password.length());
	}
}
