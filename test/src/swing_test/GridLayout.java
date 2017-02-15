package swing_test;
 import javax.swing.*;

  public class GridLayout {
     public static void main( String[] args ) {
        SwingUtilities.invokeLater( new Runnable() {
           public void run() {

              JFrame frame = new JFrame();

              frame.getContentPane().setLayout( new java.awt.GridLayout( 0, 3 ) );
              for( int i = 0; i < 21; i++ ) {
                 JPanel panel = new JPanel();  // Make a new panel
                 JButton button = new JButton( "Button "+i );
                 panel.add( button );  // add the button to the panel...
                 frame.getContentPane().add( panel );   // ...then add the panel to the layout
              }
              frame.pack();
              frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
              frame.setLocationRelativeTo( null );
              frame.setVisible( true );
           }
        } );
     }
  }