// creates & draws a Mandelbrot set with color

import java.awt.Color;
import java.awt.Container;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Fractals extends JFrame{
  private static final int WINDOW_WIDTH = 800;
  private static final int WINDOW_HEIGHT = 600;
  private static final String TITLE = "Mandelbrot Set";
  private static final int maxIterations = 500;
  private int iterationsNeeded = 0;
  private ArrayList<Integer> xFractal = new ArrayList();
  private ArrayList<Integer> yFractal = new ArrayList();
  private Container pane;
  private FractalPanel fp;
  private BufferedImage fractalImage;
  private WritableRaster fractalRaster;

  public static void main(String[] args){
    Fractals f = new Fractals();
  }

  public Fractals(){
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
    setTitle(TITLE);
    setResizable(false);
    setVisible(true);
    pane = getContentPane();
    fractalImage = new BufferedImage(WINDOW_WIDTH,WINDOW_HEIGHT,BufferedImage.TYPE_INT_RGB);
    fractalRaster = fractalImage.getRaster();
    //whiteBackground();
    fp = new FractalPanel(fractalImage);
    pane.add(fp);
    createMandelbrot();
    validate();
  }

  public void whiteBackground(){
    for(int i=0;i<WINDOW_WIDTH;i++){
      for(int j=0;j<WINDOW_HEIGHT;j++){
        setPixelColor(i,j,Color.WHITE);
      }
    }
  }

  public void setPixelColor(int x,int y,Color col){
    int[] color = new int[3];
    color[0] = col.getRed();
    color[1] = col.getGreen();
    color[2] = col.getBlue();
    fractalRaster.setPixel(x,y,color);
    repaint();
  }

  //method should be cleaned up
  public void createMandelbrot(){
    double minRe = -2.0;
    double maxRe = 1.0;
    double minIm = -1.18;
    //double minRe = -1.0;
    //double maxRe = 0.0;
    //double minIm = 0.15;
    //double minRe = -0.75;
    //double maxRe = -0.25;
    //double minIm = 0.39;
    //double minRe = -0.6;
    //double maxRe = -0.4;
    //double minIm = 0.54;
    //double minRe = -0.52;
    //double maxRe = -0.48;
    //double minIm = 0.62;
    //double minRe = -0.5005;
    //double maxRe = -0.499999;
    //double minIm = 0.6103;
    double maxIm = minIm + (maxRe - minRe) * WINDOW_HEIGHT/WINDOW_WIDTH;
    double reFactor = (maxRe - minRe) / (WINDOW_WIDTH - 1);
    double imFactor = (maxIm - minIm) / (WINDOW_HEIGHT - 1);
    for(int y=0;y<WINDOW_HEIGHT;y++){
      double im = maxIm - y * imFactor;
      for(int x=0;x<WINDOW_WIDTH;x++){
        double re = minRe + x * reFactor;
        Complex c = new Complex(re,im);
        Complex z = c;
        boolean isInside = isInsideMandelbrotSet(z,c);
        if(isInside){
          xFractal.add(x);
          yFractal.add(y);
        //code to color image
        }else{
          setPixelColor(x,y,new Color((iterationsNeeded*7)%255,(iterationsNeeded*3)%255,255));
        }
      }
    }
    drawMandelbrot();
  }

  //boolean (within some iterations) to see if any given complex number c &
  //magnitude diverges or else converges, and continues for z = z^2 + c
  public boolean isInsideMandelbrotSet(Complex z, Complex c){
    for(int n=0;n<maxIterations;n++){
      double real = z.getReal();
      double imaginary = z.getImaginary();
      if(real*real + imaginary*imaginary > 4){
        iterationsNeeded = n;
        return false;
      }
      z = z.squared().add(c);
    }
    iterationsNeeded = 0;
    return true;
  }

  public void drawMandelbrot(){
    for(int i=0;i<xFractal.size();i++){
      setPixelColor(xFractal.get(i),yFractal.get(i),Color.BLACK);
    }
  }
}
