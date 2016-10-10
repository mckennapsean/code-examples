// creates a simple animated object / shape
// works with Simple.java

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import javax.swing.JPanel;

public class SimpleAnimation extends JPanel implements ActionListener{
  private static final double SPEED = 0.02;
  private double angle;

  public SimpleAnimation(){
    this.angle = 0.0;
  }

  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    int w = this.getWidth();
    int h = this.getHeight();
    AffineTransform scale = new AffineTransform();
    scale.setToScale(w/2.0,h/2.0);
    AffineTransform translate = new AffineTransform();
    translate.setToTranslation(1.0,1.0);
    AffineTransform transform = new AffineTransform();
    transform.concatenate(scale);
    transform.concatenate(translate);

    double x0 = Math.cos(this.angle);
    double y0 = Math.sin(this.angle);
    double x1 = Math.cos(this.angle + Math.PI);
    double y1 = Math.sin(this.angle + Math.PI);
    Line2D line = new Line2D.Double(x0,y0,x1,y1);
    Shape shape = transform.createTransformedShape(line);
    g2d.draw(shape);
  }

  public void actionPerformed(ActionEvent ae){
    this.angle += SPEED;
    this.repaint();
  }
}
