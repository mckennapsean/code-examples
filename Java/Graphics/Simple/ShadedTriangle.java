// by Sean McKenna on April 6th, 2011
// creates a shaded triangle
// works with Simple.java

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import javax.swing.JPanel;

public class ShadedTriangle extends JPanel{
  private static final int BITMAP_WIDTH = 512;
  private static final int BITMAP_HEIGHT = 512;
  private BufferedImage image;

  //3 coordinates of triangle (relative)
  private static final int[] p0 = {(int) (0.49*BITMAP_WIDTH), (int) (0.05*BITMAP_HEIGHT)};
  private static final int[] p1 = {(int) (0.02*BITMAP_WIDTH), (int) (0.86*BITMAP_HEIGHT)};
  private static final int[] p2 = {(int) (0.86*BITMAP_WIDTH), (int) (0.68*BITMAP_HEIGHT)};

  //3 colors of triangle (relative)
  private static final int[] c0 = {(int) (1.00*255), (int) (0.40*255), (int) (0.00*255)};
  private static final int[] c1 = {(int) (0.00*255), (int) (1.00*255), (int) (0.47*255)};
  private static final int[] c2 = {(int) (0.12*255), (int) (0.00*255), (int) (1.00*255)};

  //points to define the line we are drawing
  private static final int[] p3 = {(int) (0.25*BITMAP_WIDTH), (int) (0.85*BITMAP_HEIGHT)};
  private static final int[] p4 = {(int) (0.99*BITMAP_WIDTH), (int) (0.98*BITMAP_HEIGHT)};

  //colors of the line points
  private static final int[] c3 = {255,0,0};
  private static final int[] c4 = {255,255,255};

  public ShadedTriangle(){
    int w = BITMAP_WIDTH;
    int h = BITMAP_HEIGHT;
    int imageType = BufferedImage.TYPE_INT_RGB;
    this.image = new BufferedImage(w,h,imageType);
  }

  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    int w = this.getWidth();
    int h = this.getHeight();
    AffineTransform scale = new AffineTransform();
    scale.setToScale(((double) w) / BITMAP_WIDTH,((double) h) / BITMAP_HEIGHT);
    WritableRaster raster = this.image.getRaster();
    int[] black = {0,0,0};

    //store components of the implicit line formula
    int[] aLine = {(p0[1]-p1[1]), (p1[1]-p2[1]), (p2[1]-p0[1])};
    int[] bLine = {(p1[0]-p0[0]), (p2[0]-p1[0]), (p0[0]-p2[0])};
    int[] cLine = {((p0[0]*p1[1])-(p1[0]*p0[1])), ((p1[0]*p2[1])-(p2[0]*p1[1])), ((p2[0]*p0[1])-(p0[0]*p2[1]))};

    //implicit line applied to each of the three coordinates separately
    double lineAlpha = (double) (aLine[1]*p0[0] + bLine[1]*p0[1] + cLine[1]);
    double lineBeta = (double) (aLine[2]*p1[0] + bLine[2]*p1[1] + cLine[2]);
    double lineGamma = (double) (aLine[0]*p2[0] + bLine[0]*p2[1] + cLine[0]);

    //initialize the barycentric coordinates to the starting triangle vertices
    double alpha = 0.0;
    double beta = 0.0;
    double gamma = 0.0;

    //loop through the image
    for(int j=0;j<h;j++){
      for(int i=0;i<w;i++){

        //calculate barycentric coordinates of the point about the triangle sides
        int[] first = {aLine[0]*i, aLine[1]*i, aLine[2]*i};
        int[] second = {bLine[0]*j, bLine[1]*j, bLine[2]*j};
        double pointAlpha = (double) (first[1] + second[1] + cLine[1]);
        double pointBeta = (double) (first[2] + second[2] + cLine[2]);
        double pointGamma = (double) (first[0] + second[0] + cLine[0]);

        //relative barycentric coordinates, used for shading inside triangle
        alpha = pointAlpha / lineAlpha;
        beta = pointBeta / lineBeta;
        gamma = pointGamma / lineGamma;

        //if point inside the triangle 
        if(alpha >= 0.0 && beta >= 0.0 && gamma >= 0.0 && alpha <= 1.0 && beta <= 1.0 && gamma <= 1.0){

          //change the color of the pixel based on barycentric coordinates
          int R = (int) (alpha*c0[0] + beta*c1[0] + gamma*c2[0]);
          int G = (int) (alpha*c0[1] + beta*c1[1] + gamma*c2[1]);
          int B = (int) (alpha*c0[2] + beta*c1[2] + gamma*c2[2]);
          int[] c3 = {R,G,B};

          //draw pixel with altered color
          raster.setPixel(i,j,c3);

        //else if outside triangle
        }else{
          //draw a black pixel
          raster.setPixel(i,j,black);
        }
      }
    }

    //create line from parameters above
    int y = p3[1];
    int distSqrdTot = (p4[0]-p3[0])*(p4[0]-p3[0]) + (p4[1]-p3[1])*(p4[1]-p3[1]);
    int d = 2*(p3[1]-p4[1])*(p3[0]+1) + (p4[0]-p3[0])*(2*p3[1]+1) + 2*p3[0]*p4[1] - 2*p4[0]*p3[1];
    int distSqrd = 0;
    for(int x=p3[0];x<=p4[0];x++){
      distSqrd = (x-p3[0])*(x-p3[0]) + (y-p3[1])*(y-p3[1]);
      double ratio = ((double) distSqrd) / ((double) distSqrdTot);
      int[] c5 = {(int) (c3[0]*(1.0-ratio)),(int) (c3[1]*(1.0-ratio)),(int) (c3[2]*(1.0-ratio))};
      int[] c6 = {(int) (c4[0]*ratio),(int) (c4[1]*ratio),(int) (c4[2]*ratio)};
      int[] c7 = {c5[0]+c6[0], c5[1]+c6[1], c5[2]+c6[2]};
//      System.out.println("The coordinate on the line is ("+x+","+y+")");
//      System.out.println("Now coloring that pixel ("+c7[0]+","+c7[1]+","+c7[2]+")");
      raster.setPixel(x,y,c7);
      if(d<0){
        y++;
        d += 2*(p4[0]-p3[0]) + 2*(p3[1]-p4[1]);
      }else{
        d += 2*(p3[1]-p4[1]);
      }
    }

    //draw the image
    g2d.drawImage(image,scale,this);
  }
}
