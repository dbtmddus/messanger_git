package swing_test;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneLayout;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;

class chat_layout{
	/**
	 * @wbp.parser.entryPoint
	 */
	public chat_layout(){
		JFrame f = new JFrame("ScrollPaneTestttt");
		JScrollPane sp = new JScrollPane();
		JPanel p = new JPanel();   

		f.setSize(350, 207);

		sp.setBounds(f.getContentPane().getBounds());
		
		sp.setViewportView(p);

		//p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		//tp1.setAlignmentX(Component.LEFT_ALIGNMENT);	//레이아웃을 줘야 적용됨. flow는 레이아웃자체에 align이 있기때문에 이 문항 적용X

		int len_str;
		int len_w;
		int len_h;
		int font_size_w=10;
		int font_size_h=30;
		
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JTextPane tp1 = new JTextPane();
		tp1.setText("11111111111111111");
		len_str = tp1.getText().length();
		len_w =len_str;
		if (len_w>=20){
			len_w = 20;
		}
		len_h = (len_str/20)+1;
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		p1.setMaximumSize(new Dimension(f.getMaximumSize().width, len_h*font_size_h));	//이것도 부모가 layou 매니저 사용할때 크기 제한.
		tp1.setPreferredSize(new Dimension(len_w*font_size_w, len_h*font_size_h));	//부모가 laout매니저 사용할때 크기 조절
		p1.add(tp1);
		
		JTextPane date1 = new JTextPane();
		date1.setText("5:20");
		p1.add(date1);
		p.add(p1);
		
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JTextPane tp2 = new JTextPane();
		tp2.setText("222222222222222222222222");
		len_str = tp2.getText().length();
		len_w =len_str;
		if (len_w>=20){
			len_w = 20;
		}
		len_h = (len_str/20)+1;
		p2.setMaximumSize(new Dimension(f.getMaximumSize().width, len_h*font_size_h));	//이것도 부모가 layou 매니저 사용할때 크기 제한.
		tp2.setPreferredSize(new Dimension(len_w*font_size_w, len_h*font_size_h));
		
		p2.add(tp2);
		p.add(p2);
		
		/*
		JPanel p3 = new JPanel();
		p3.setPreferredSize(new Dimension(p.WIDTH, 100));
		p.add(p3);
		*/
		//
		f.getContentPane().add(sp);    
		f.setVisible(true);             
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main(String args[]){
		chat_layout g1 = new chat_layout();
	}
}

