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
             f.setSize(300, 200);
             
             JScrollPane sp = new JScrollPane();
             JPanel p = new JPanel();
             p.setLayout(new GridLayout(5, 2));
             p.add(new JButton("첫번째"));
             p.add(new JButton("두번째"));      
             p.add(new JButton("세번째"));
             p.add(new JButton("네번째"));
             
             p.setBounds(f.getContentPane().getBounds());
             sp.setViewportView(p);

             //sp.add(p);
             sp.setBounds(f.getContentPane().getBounds());
             f.add(sp);
             
             f.setVisible(true);
             
       }
}

