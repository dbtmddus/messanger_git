import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

class scrollbar{
       public static void main(String args[]){
             JFrame f = new JFrame("ScrollPaneTest");
 
             f.setVisible(true);             
             f.setSize(300, 200);
             
             JScrollPane sp = new JScrollPane();
             sp.setBounds(f.getContentPane().getBounds());
       
             JPanel p = new JPanel();
             
             sp.setViewportView(p);
             
             p.setLayout(new GridLayout(5, 2));
             p.add(new JButton("ù��°"));
             p.add(new JButton("�ι�°"));      
             p.add(new JButton("����°"));
             p.add(new JButton("�׹�°"));
             
             p.setBounds(f.getContentPane().getBounds());
             
             f.add(sp);    
             
//             f.setVisible(true);             
             
       }
}

