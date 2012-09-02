import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;

public class SimplePicture extends JPanel{

  public SimplePicture(){
    this.setBackground(Color.DARK_GRAY);
  }

  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    int w = this.getWidth();
    int h = this.getHeight();
    AffineTransform scale = new AffineTransform();
    scale.setToScale(w,h);
    Ellipse2D circle = new Ellipse2D.Double(0,0,1,1);
    Shape shape = scale.createTransformedShape(circle);
    g2d.setColor(Color.RED);
    g2d.fill(shape);
  }
}
