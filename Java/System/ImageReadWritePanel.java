// by Sean McKenna
// sets up the GUI to embed an image
// works with ImageReadWrite.java

// import graphics
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import javax.swing.JPanel;

public class ImageReadWritePanel extends JPanel{
  
  private BufferedImage image;
  public ImageReadWritePanel(){
    this.image = null;
  }
  
  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    
    if(this.image != null){
      
      // scale the image to the size of the window
      double imageWidth = this.image.getWidth();
      double imageHeight = this.image.getHeight();
      double panelWidth = this.getWidth();
      double panelHeight = this.getHeight();
      double scaleX = 1.0;
      double scaleY = 1.0;
      if(imageWidth > imageHeight){
        scaleX = panelWidth / imageWidth;
        scaleY = scaleX;
      }
      else{
        scaleY = panelHeight / imageHeight;
        scaleX = scaleY;
      }
      
      // perform the scaling operation
      AffineTransform transform = new AffineTransform();
      transform.setToScale(scaleY, scaleY);
      
      // convolution to affect the image
      float[] kernelData = {
        -0.125f, -0.125f, -0.125f,
        -0.125f, 2.000f, -0.125f,
        -0.125f, -0.125f, -0.125f
      };
      
      // create the kernel of the convolution
      Kernel kernel = new Kernel(3, 3, kernelData);
      
      // create the convolution operator
      ConvolveOp operator = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
      
      // create the filter
      BufferedImageFilter filter = new BufferedImageFilter(operator);
      
      // store filtered image
      BufferedImage filteredImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
      
      // apply the filter
      operator.filter(image, filteredImage);
      
      // set up and draw screen
      g2d.setColor( Color.BLACK );
      g2d.fillRect(0,0, (int) panelWidth, (int) panelHeight) ;
      
      // prepare to draw image
      AffineTransform scale = new AffineTransform() ;
      scale.setToScale( panelWidth, panelHeight );
      int xMin = image.getMinX();
      int xMax = xMin + image.getWidth();
      int yMin = image.getMinY();
      int yMax = yMin + image.getHeight();
      
      // draw image
      g2d.drawImage(filteredImage, transform, this);
    }
  }
  
  public void addImage(BufferedImage image){
    this.image = image;
    this.repaint();
  }
}
