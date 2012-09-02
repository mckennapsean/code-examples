// by Sean McKenna on April 12th, 2011
//

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CityScape extends JFrame implements MouseListener{
  private static final long serialVersionUID = 1L;
  private static final int WIDTH = 800;
  private static final int HEIGHT = 600;
  private static final String TITLE = "City-Scape";
  private CityScapePanel panel;
  private Container pane;

  public static void main(String[] args){
    CityScape cityScape = new CityScape();
  }

  public CityScape(){
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(WIDTH, HEIGHT);
    this.setTitle(TITLE);
    pane = this.getContentPane();

    panel = new CityScapePanel(WIDTH,HEIGHT);
    pane.add(panel);
    pane.addMouseListener(this);

    this.setVisible(true);
  }

  public void mouseClicked(MouseEvent e){
    panel = new CityScapePanel(WIDTH,HEIGHT);
    pane.add(panel);
    this.setVisible(true);
  }

  public void mouseEntered(MouseEvent e){}
  public void mouseExited(MouseEvent e) {}
  public void mousePressed(MouseEvent e){}
  public void mouseReleased(MouseEvent e){}
}
