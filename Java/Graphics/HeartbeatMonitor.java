// by Sean McKenna
// inspired by Leon Tabak
// works as an object in Heartbeat.java, for the animated ball

// import graphical elements
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

// create a moving, animated ball
public class HeartbeatMonitor extends JPanel implements ActionListener, ChangeListener{

  // background and foreground colors
  private static final Color BG_COLOR = Color.GREEN;
  private static final Color FG_COLOR = Color.WHITE;
  
  // normalized incrementing angle to draw animated frames
  // somewhere between 0 & 1, to determine the speed / frequency
  private final double ANGLE_INCREMENT = 0.04 ;
  private final double RADIUS = 0.02;
  private double angle = 0.0;
  private int frequency;
  
  // create a moving ball with some frequency
  public HeartbeatMonitor(int frequency){
    this.setBackground(BG_COLOR);
    this.setForeground(FG_COLOR);
    this.frequency = frequency;
  }
  
  // determine how to draw the ball
  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    
    // keep the coordinates normalized about the screen (between 0 & 1)
    double x = this.angle / (2.0 * Math.PI);
    double y = (1 + Math.cos(this.frequency * this.angle)) / 2;
    
    // get width and height of panel being drawn on top of
    int width = this.getWidth();
    int height = this.getHeight();
    
    // scale ball position (not too close to the upper and lower edges, though)
    x = x * width ;
    y = 3 * height / 4 - (y * height) / 2 ;
    double r = RADIUS * Math.min(width, height);
    
    // find the upper left-hand corner for the moving ball
    double ulx = x - r;
    double uly = y - r;
    
    // compute size of the ball
    double size = 2 * r;
    
    Ellipse2D ball = new Ellipse2D.Double( ulx, uly, size, size);
    g2d.fill(ball);
  }
  
  // with the timer, change the ball position
  public void actionPerformed(ActionEvent e){
    this.angle = this.angle + ANGLE_INCREMENT;
    if(this.angle > (2 * Math.PI))
      this.angle = this.angle - (2.0 * Math.PI);
    this.repaint();
  }
  
  // with the slider, change the ball's frequency
  public void stateChanged(ChangeEvent e){
    JSlider controller = (JSlider) e.getSource();
    if(!controller.getValueIsAdjusting())
      this.frequency = controller.getValue();
  }
}
