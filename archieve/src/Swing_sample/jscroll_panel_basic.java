package Swing_sample;
import java.awt.Panel;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

class jscroll_panel_basic{
       public static void main(String args[]){
             JFrame f = new JFrame("ScrollPaneTest");
             f.setSize(300, 200);
             
             JScrollPane sp = new JScrollPane();
             JPanel p = new JPanel();
 
             p.add(new JButton("ù��°"));
             p.add(new JButton("�ι�°"));      
             p.add(new JButton("����°"));
             p.add(new JButton("�׹�°"));
             
             p.setBounds(f.getContentPane().getBounds());
             sp.setViewportView(p);	//����!!!!!!!!!!!!!!! JScrollPanel�� add�� �ִ°� �ƴ�.
             //sp.add(p);			//�̷��� ��� ����
             
             sp.setBounds(f.getContentPane().getBounds());
             f.add(sp);
             
             f.setVisible(true);
             
       }
}

