// by Sean McKenna on April 11th, 2011
//
//Clipping.java interacts using PolygonClipping.java to create a random, shaded
//polygon clipped inside of a random window on a fixed window. Additionally,
//mouse clicks will re-generate a new polygon set.
//

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Clipping extends JFrame implements MouseListener{
  private static final long serialVersionUID = 1L;
  private static final int WIDTH = 512;
  private static final int HEIGHT = 512;
  private static final String TITLE = "Polygon Clipping";
  private PolygonClipping panel;
  private Container pane;

  public static void main(String[] args){
    Clipping polygon = new Clipping();
  }

  public Clipping(){
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(WIDTH, HEIGHT);
    this.setTitle(TITLE);
    pane = this.getContentPane();

    panel = new PolygonClipping();
    pane.add(panel);
    pane.addMouseListener(this);

    this.setVisible(true);
  }

  public void mouseClicked(MouseEvent e){
    panel = new PolygonClipping();
    pane.add(panel);
    this.setVisible(true);
  }

  public void mouseEntered(MouseEvent e){}
  public void mouseExited(MouseEvent e) {}
  public void mousePressed(MouseEvent e){}
  public void mouseReleased(MouseEvent e){}
}
