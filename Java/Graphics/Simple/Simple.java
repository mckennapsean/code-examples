import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Simple extends JFrame{
  private static final int SIMPLE_WIDTH = 512;
  private static final int SIMPLE_HEIGHT = 512;
  private static final String SIMPLE_TITLE = "Simple";

  public Simple(){
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(SIMPLE_WIDTH, SIMPLE_HEIGHT);
    this.setTitle(SIMPLE_TITLE);
    Container pane = this.getContentPane();

//    JPanel panel = new JPanel();
//    panel.setBackground(Color.CYAN);
//    pane.add(panel);

//    SimplePicture panel = new SimplePicture();
//    pane.add(panel);

//    SimpleBitmap panel = new SimpleBitmap();
//    pane.add(panel);

//    SimpleImage panel = new SimpleImage();
//    pane.add(panel);

//    SimpleAnimation panel = new SimpleAnimation();
//    pane.add(panel);
//    Timer timer = new Timer(20,panel);
//    timer.start();

    ShadedTriangle panel = new ShadedTriangle();
    pane.add(panel);

    this.setVisible(true);
  }

  public static void main(String[] args){
    Simple simple = new Simple();
  }
}
