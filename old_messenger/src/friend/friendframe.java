package friend;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import testmessenger.listen3;
import testmessenger.send3;

public class friendframe {
	
	private String id="none";
	private JFrame friendfr;
	private JMenuItem menu1;					//file 메뉴
	private JMenuBar menuBar;
	
	private JTextField tf1 = new JTextField(10);
	
	private JFrame infopr = new JFrame("친구 정보");
	private JTextField tf2_1 = new JTextField(5);
	private JTextField tf2_2 = new JTextField(5);
	private JTextField tf2_3 = new JTextField(5);
	private JTextField tf2_4 = new JTextField(5);
	private JLabel l1=new JLabel("ID");
	private JLabel l2=new JLabel("IP");	
	private JButton b1=new JButton("추가");


	
	private JMenu fr_menu1;
	private JMenuItem mitem1_1;
	private JMenuItem mitem1_2;
	private JButton fr_b1;
	private int bnum1;
	private String fr_id1;
	private String fr_ip1;
	private JPopupMenu jp1;
	
	
	private JMenu fr_menu2;
	private JMenuItem mitem2_1;
	private JMenuItem mitem2_2;
	private JButton fr_b2;
	private int bnum2;
	private String fr_id2;
	private String fr_ip2;
	private JPopupMenu jp2;

	
	private JMenu fr_menu3;
	private JMenuItem mitem3_1;
	private JMenuItem mitem3_2;
	private JButton fr_b3;
	private int bnum3;
	private String fr_id3;
	private String fr_ip3;
	private JPopupMenu jp3;
	
	private JMenu fr_menu4;
	private JMenuItem mitem4_1;
	private JMenuItem mitem4_2;
	private JButton fr_b4;
	private int bnum4;
	private String fr_id4;
	private String fr_ip4;
	private JPopupMenu jp4;

	private JMenu fr_menu5;
	private JMenuItem mitem5_1;
	private JMenuItem mitem5_2;
	private JButton fr_b5;
	private int bnum5;
	private String fr_id5;
	private String fr_ip5;
	private JPopupMenu jp5;
	
	private JMenu fr_menu6;
	private JMenuItem mitem6_1;
	private JMenuItem mitem6_2;
	private JButton fr_b6;
	private int bnum6;
	private String fr_id6;
	private String fr_ip6;
	private JPopupMenu jp6;
	
	private JMenu fr_menu7;
	private JMenuItem mitem7_1;
	private JMenuItem mitem7_2;
	private JButton fr_b7;
	private int bnum7;
	private String fr_id7;
	private String fr_ip7;
	private JPopupMenu jp7;
	
	private int fr_y;
	private int buttonnum;
	private JLabel bgimg;
	
	
	public friendframe(String id2) throws IOException {
		
		id=id2;
		friendfr  = new JFrame("친구목록");
		friendfr.setVisible(true);
		friendfr.setSize(160, 500);
		
		Container c = friendfr.getContentPane();
		c.setBackground(new Color (116,116,116));
		//c.setBackground(Color.white);
		
		bgimg = new JLabel(new ImageIcon(ImageIO.read(new File("aa2.jpg"))));
		friendfr.setContentPane(bgimg);
		
		menuBar = new JMenuBar();
		menu1 = new JMenuItem("친구 추가");
		menuBar.add(menu1);
		
		buttonnum=0;
		bnum1=0;
		bnum2=0;
		bnum3=0;
		
		fr_y=50;
		String test="a";
		
		String data = "d:\\"+id+".txt";
		
		FileReader fr = new FileReader(data);
		BufferedReader br = new BufferedReader(fr);

		br.readLine();
		br.readLine();
		
		friendfr.setLayout(null);
		
		//친구 삭제시 test3참고 ( ==@ 일때 위치 재지정, 크기 0 지정)
		
		
		////////////////////////////////////////  fr1        ////////////////////////////
				
		test = br.readLine();
	
		if ( test != null &&  !test.equals("") && !test.equals("eea")){	
		//	if (test != null){	
			System.out.println("친구1");
		fr_id1 =test;
		fr_ip1 = br.readLine()+"."+br.readLine()+"."+br.readLine()+"."+br.readLine();

		jp1 = new JPopupMenu();
		
		mitem1_1 = new JMenuItem("대화");
		mitem1_2 = new JMenuItem("친구 삭제");
		jp1.add(mitem1_1);
		jp1.add(mitem1_2);
		fr_b1 = new JButton(fr_id1);
		//fr_b1.setBackground(new Color(240,240,240));
		//fr_b1.setBorderPainted(true);
		

		friendfr.add(fr_b1);
		fr_b1.setBounds(31, fr_y, 90, 20); // 가로,세로,탑,레프트위치
		System.out.println("\n"+fr_ip1);
		fr_y+=35;
		buttonnum++;
		bnum1= buttonnum;
		
		mitem1_1.addActionListener(action);
		mitem1_2.addActionListener(action);
		fr_b1.addActionListener(action);
		}else {
			 br.readLine();
			 br.readLine();
			 br.readLine();
			 br.readLine();
		}
	
		/////////////////////////////////////	fr2 ///////////////////////////////////////
		
		test = br.readLine();
		
		if ( test != null &&  !test.equals("") && !test.equals("eea")){
			System.out.println("친구1");
			fr_id2 =test;
			fr_ip2 = br.readLine()+"."+br.readLine()+"."+br.readLine()+"."+br.readLine();

			jp2 = new JPopupMenu();

			mitem2_1 = new JMenuItem("대화");
			mitem2_2 = new JMenuItem("친구 삭제");
			jp2.add(mitem2_1);
			jp2.add(mitem2_2);

			//fr_b2 = new JButton(fr_id2,b_icon);
			fr_b2 = new JButton(fr_id2);
			friendfr.add(fr_b2);
			fr_b2.setBounds(31, fr_y,90, 20); // 가로,세로,탑,레프트위치
			System.out.println("\n"+fr_ip2);
			fr_y+=30;
			buttonnum++;
			bnum2= buttonnum;

			mitem2_1.addActionListener(action);
			mitem2_2.addActionListener(action);
			fr_b2.addActionListener(action);
		}else {
			 br.readLine();
			 br.readLine();
			 br.readLine();
			 br.readLine();
		}
		/////////////////////////	fr3 	///////
		
		test = br.readLine();

		if ( test != null &&  !test.equals("") && !test.equals("eea") ){
			System.out.println("친구3");
			fr_id3 =test;
			fr_ip3 = br.readLine()+"."+br.readLine()+"."+br.readLine()+"."+br.readLine();

			jp3 = new JPopupMenu();

			mitem3_1 = new JMenuItem("대화");
			mitem3_2 = new JMenuItem("친구 삭제");
			jp3.add(mitem3_1);
			jp3.add(mitem3_2);

			fr_b3 = new JButton(fr_id3);
			friendfr.add(fr_b3);
			fr_b3.setBounds(31, fr_y, 90, 20); // 가로,세로,탑,레프트위치
			System.out.println("\n"+fr_ip3);
			fr_y+=30;
			buttonnum++;
			bnum3= buttonnum;

			mitem3_1.addActionListener(action);
			mitem3_2.addActionListener(action);
			fr_b3.addActionListener(action);
		}else {
			 br.readLine();
			 br.readLine();
			 br.readLine();
			 br.readLine();
		}
/////////////////////////////////fr4 ///////////////////////////////////////////	
		
		test = br.readLine();

		if ( test != null &&  !test.equals("") && !test.equals("eea") ){
			System.out.println("친구4");
			fr_id4 =test;
			fr_ip4 = br.readLine()+"."+br.readLine()+"."+br.readLine()+"."+br.readLine();

			jp4 = new JPopupMenu();

			mitem4_1 = new JMenuItem("대화");
			mitem4_2 = new JMenuItem("친구 삭제");
			jp4.add(mitem4_1);
			jp4.add(mitem4_2);

			fr_b4 = new JButton(fr_id4);
			friendfr.add(fr_b4);
			fr_b4.setBounds(31, fr_y, 90, 20); // 가로,세로,탑,레프트위치
			System.out.println("\n"+fr_ip4);
			fr_y+=30;
			buttonnum++;
			bnum4= buttonnum;

			mitem4_1.addActionListener(action);
			mitem4_2.addActionListener(action);
			fr_b4.addActionListener(action);
		}else {
			 br.readLine();
			 br.readLine();
			 br.readLine();
			 br.readLine();
		}
////////////////////////////////////// fr5 //////////////////////////////////
		
		test = br.readLine();

		if ( test != null &&  !test.equals("") && !test.equals("eea") ){
			System.out.println("친구5");
			fr_id5 =test;
			fr_ip5 = br.readLine()+"."+br.readLine()+"."+br.readLine()+"."+br.readLine();

			jp5 = new JPopupMenu();

			mitem5_1 = new JMenuItem("대화");
			mitem5_2 = new JMenuItem("친구 삭제");
			jp5.add(mitem5_1);
			jp5.add(mitem5_2);

			fr_b5 = new JButton(fr_id5);
			friendfr.add(fr_b5);
			fr_b5.setBounds(31, fr_y, 90, 20); // 가로,세로,탑,레프트위치
			System.out.println("\n"+fr_ip5);
			fr_y+=30;
			buttonnum++;
			bnum5= buttonnum;

			mitem5_1.addActionListener(action);
			mitem5_2.addActionListener(action);
			fr_b5.addActionListener(action);
		}else {
			 br.readLine();
			 br.readLine();
			 br.readLine();
			 br.readLine();
		}
///////////////////////////////  fr 6 	/////////////////////////////
		
		test = br.readLine();

		if ( test != null &&  !test.equals("") && !test.equals("eea") ){
			System.out.println("친구6");
			fr_id6 =test;
			fr_ip6 = br.readLine()+"."+br.readLine()+"."+br.readLine()+"."+br.readLine();

			jp6 = new JPopupMenu();

			mitem6_1 = new JMenuItem("대화");
			mitem6_2 = new JMenuItem("친구 삭제");
			jp6.add(mitem6_1);
			jp6.add(mitem6_2);

			fr_b6 = new JButton(fr_id6);
			friendfr.add(fr_b6);
			fr_b6.setBounds(31, fr_y, 90, 20); // 가로,세로,탑,레프트위치
			System.out.println("\n"+fr_ip6);
			fr_y+=30;
			buttonnum++;
			bnum6= buttonnum;

			mitem6_1.addActionListener(action);
			mitem6_2.addActionListener(action);
			fr_b6.addActionListener(action);
		}else {
			 br.readLine();
			 br.readLine();
			 br.readLine();
			 br.readLine();
		}
//////////////////////// fr7  /////////////////////////////////
		
		test = br.readLine();

		if ( test != null &&  !test.equals("") && !test.equals("eea") ){
			System.out.println("친구7");
			fr_id7 =test;
			fr_ip7 = br.readLine()+"."+br.readLine()+"."+br.readLine()+"."+br.readLine();

			jp7 = new JPopupMenu();

			mitem7_1 = new JMenuItem("대화");
			mitem7_2 = new JMenuItem("친구 삭제");
			jp7.add(mitem7_1);
			jp7.add(mitem7_2);

			fr_b7 = new JButton(fr_id7);
			friendfr.add(fr_b7);
			fr_b7.setBounds(31, fr_y, 90, 20); // 가로,세로,탑,레프트위치
			System.out.println("\n"+fr_ip7);
			fr_y+=30;
			buttonnum++;
			bnum7= buttonnum;

			mitem7_1.addActionListener(action);
			mitem7_2.addActionListener(action);
			fr_b7.addActionListener(action);
		}else {
			 br.readLine();
			 br.readLine();
			 br.readLine();
			 br.readLine();
		}
//////////////////////////////////////////////////
		friendfr.setJMenuBar(menuBar);			
		
		menu1.addActionListener(action);
		b1.addActionListener(action);	
		}

	
	 ActionListener action = new ActionListener() {

         public void actionPerformed(ActionEvent e) {

                Object obj = e.getSource();

				if		(obj == menu1 )
               		{System.out.println("친구등록창");
					inputfrinfo();}		
				
				if		(obj == b1 ){ 	//친구추가버튼
					addfriend(id,tf1.getText(),tf2_1.getText(),tf2_2.getText(),tf2_3.getText(),tf2_4.getText() );
					infopr.dispose();
					friendfr.dispose();

					try {
						friendframe friendframe = new friendframe(id);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}	
		      
				if (obj == fr_b1)
					{jp1.show((Component)e.getSource(), 70, 5 );
					System.out.println(bnum1+ fr_ip1);
					}	
				if (obj == fr_b2)
					{jp2.show((Component)e.getSource(), 70, 5 );
					System.out.println(bnum2+ fr_ip2);
					}
				if (obj == fr_b3)
					{jp3.show((Component)e.getSource(), 70, 5 );
					System.out.println(bnum3+ fr_ip3);
					}
				if (obj == fr_b4)
				{jp4.show((Component)e.getSource(), 70, 5 );
				System.out.println(bnum4+ fr_ip4);
				}
				if (obj == fr_b5)
				{jp5.show((Component)e.getSource(), 70, 5 );
				System.out.println(bnum5+ fr_ip5);
				}
				if (obj == fr_b6)
				{jp6.show((Component)e.getSource(), 70, 5 );
				System.out.println(bnum6+ fr_ip6);
				}
				if (obj == fr_b7)
				{jp7.show((Component)e.getSource(), 70, 5 );
				System.out.println(bnum7+ fr_ip7);
				}
				
				if (obj == mitem1_1){
					send3 send_1 = new send3(fr_ip1);
					send_1.setid(fr_id1);
					Thread t1 = new Thread(send_1);
					t1.start();
				}
				if (obj == mitem1_2){
					fr_id1 = "eea";
					friendfr.dispose();
					try {
						friendframe friendframe = new friendframe(id);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (obj == mitem2_1){
					send3 send_2 = new send3(fr_ip2);
					send_2.setid(fr_id2);
					Thread t2 = new Thread(send_2);
					t2.start();
				}
				if (obj == mitem2_2){
					fr_id2 = "eea";
					friendfr.dispose();
					try {
						friendframe friendframe = new friendframe(id);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (obj == mitem3_1){
					send3 send_3 = new send3(fr_ip3);
					send_3.setid(fr_id3);
					Thread t3 = new Thread(send_3);
					t3.start();
				}
				if (obj == mitem3_2){
					fr_id3 = "eea";
					friendfr.dispose();
					try {
						friendframe friendframe = new friendframe(id);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (obj == mitem4_1){
					send3 send_4 = new send3(fr_ip4);
					send_4.setid(fr_id4);
					Thread t4 = new Thread(send_4);
					t4.start();
				}
				if (obj == mitem4_2){
					fr_id4 = "eea";
					friendfr.dispose();
					try {
						friendframe friendframe = new friendframe(id);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (obj == mitem5_1){
					send3 send_5 = new send3(fr_ip5);
					send_5.setid(fr_id5);
					Thread t5 = new Thread(send_5);
					t5.start();
				}
				if (obj == mitem5_2){
					fr_id5 = "eea";
					friendfr.dispose();
					try {
						friendframe friendframe = new friendframe(id);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (obj == mitem6_1){
					send3 send_6 = new send3(fr_ip6);
					send_6.setid(fr_id6);
					Thread t6 = new Thread(send_6);
					t6.start();
				}
				if (obj == mitem6_2){
					fr_id6 = "eea";
					friendfr.dispose();
					try {
						friendframe friendframe = new friendframe(id);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (obj == mitem7_1){
					send3 send_7 = new send3(fr_ip7);
					send_7.setid(fr_id7);
					Thread t7 = new Thread(send_7);
					t7.start();
				}
				if (obj == mitem7_2){
					fr_id7 = "eea";
					friendfr.dispose();
					try {
						friendframe friendframe = new friendframe(id);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
         }
    };
    
	public void inputfrinfo(){
		System.out.println("메뉴 이벤트");
		
		infopr.setVisible(true);
		infopr.setSize(430, 250);
		
		infopr.add(tf1);
		//infopr.add(tf2);
		
		infopr.add(tf2_1);
		infopr.add(tf2_2);
		infopr.add(tf2_3);
		infopr.add(tf2_4);
		
		infopr.add(l1);
		infopr.add(l2);
		
		infopr.add(b1);
		
		infopr.setLayout(null);

		l1.setBounds(70, 50, 20, 20);     
        tf1.setBounds(100, 50, 100, 20); 
       
        l2.setBounds(70, 100, 20, 20); 
        tf2_1.setBounds(100, 100, 45, 20);     
        tf2_2.setBounds(160, 100, 45, 20);      
        tf2_3.setBounds(220, 100, 45, 20);     
        tf2_4.setBounds(280, 100, 45, 20);     
        
        b1.setBounds(300, 140, 60, 30);      
        b1.addActionListener(action);
	}
	
	public void addfriend(String id, String frid, String ip1, String ip2, String ip3, String ip4)
	{
			
	     try{
	      FileWriter fw = new FileWriter("d:\\"+id+".txt",true);  
	      BufferedWriter save_bw = new BufferedWriter(fw);
	      
	      save_bw.write(frid); 	 
	      save_bw.write("\r\n");
	      
	      save_bw.write(ip1); 	 
	      save_bw.write("\r\n");
	      save_bw.write(ip2); 	 
	      save_bw.write("\r\n");
	      save_bw.write(ip3); 	 
	      save_bw.write("\r\n");
	      save_bw.write(ip4); 	 
	      save_bw.write("\r\n");
	      
	      save_bw.close();
	      }catch(Exception e){
	    	 System.out.println("아이디 생성 오류");
	     }	  
	    System.out.println("생성");
	    tf1 = new JTextField("eea");
		tf2_1 = new JTextField(5);
		tf2_2 = new JTextField(5);
		tf2_3 = new JTextField(5);
		tf2_4 = new JTextField(5);
	}			
}

/*
InetAddress ip = null;
try {
	ip = InetAddress.getLocalHost();
} catch (UnknownHostException e) {
	e.printStackTrace();
}        
ip.getHostAddress();
*/