//Fractals.java was created by Sean McKenna on February 18th, 2010.
//Last Modified: February 22nd, 2010.
//Currently, its goal is to create & draw a Mandelbrot set with color.

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
//    whiteBackground();
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
//    double minRe = -1.0;
//    double maxRe = 0.0;
//    double minIm = 0.15;
//    double minRe = -0.75;
//    double maxRe = -0.25;
//    double minIm = 0.39;
//    double minRe = -0.6;
//    double maxRe = -0.4;
//    double minIm = 0.54;
//    double minRe = -0.52;
//    double maxRe = -0.48;
//    double minIm = 0.62;
//    double minRe = -0.5005;
//    double maxRe = -0.499999;
//    double minIm = 0.6103;
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


//This pseudocode explains the basic Mandelbrot set algorithm, also found online

/*
double MinRe = -2.0;
double MaxRe = 1.0;
double MinIm = -1.2;
double MaxIm = MinIm+(MaxRe-MinRe)*ImageHeight/ImageWidth;
double Re_factor = (MaxRe-MinRe)/(ImageWidth-1);
double Im_factor = (MaxIm-MinIm)/(ImageHeight-1);
unsigned MaxIterations = 30;

for(unsigned y=0; y<ImageHeight; ++y)
{
    double c_im = MaxIm - y*Im_factor;
    for(unsigned x=0; x<ImageWidth; ++x)
    {
        double c_re = MinRe + x*Re_factor;

        double Z_re = c_re, Z_im = c_im;
        bool isInside = true;
        for(unsigned n=0; n<MaxIterations; ++n)
        {
            double Z_re2 = Z_re*Z_re, Z_im2 = Z_im*Z_im;
            if(Z_re2 + Z_im2 > 4)
            {
                isInside = false;
                break;
            }
            Z_im = 2*Z_re*Z_im + c_im;
            Z_re = Z_re2 - Z_im2 + c_re;
        }
        if(isInside) { putpixel(x, y); }
    }
}


//The program below was copied from an online source.  It generates a Mandelbrot
//set and allows for zooming via mouse-dragging.


package fractals;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

public class Fractals extends JFrame implements MouseListener {

	BufferedImage fractal;
	WritableRaster raster;
	Calculator calc;
	int mouse_x,mouse_y;
	double x;
	double y;
	double width;
	double height;

	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {
		mouse_x = e.getX();
		mouse_y = e.getY();
	}

	public void mouseReleased(MouseEvent e) {
		double tmpx = translateX(mouse_x);
		double tmpy = translateY(mouse_y);
		double tmp  = translateX(e.getX());
		x = tmpx;
		y = tmpy;

		width = Math.abs(tmp - tmpx);
		height = 0.75 * width;
		while (!calc.isDone()) {
			try { Thread.sleep(1000); } catch (InterruptedException ex) {}
		}

		calc = new Calculator(632,453,raster, this);

		calc.setInsets(x,y,width,height);

		calc.start();
	}

	public double translateX(int ix) {
		return x + (((double) ix) / 632) * width;
	}

	public double translateY(int iy) {
		return y + (((double) iy) / 453) * height;
	}

	public Fractals (String args[]) {
		super("Mandelbrot");
		setSize(640,480);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);


		Component graphics = new Component() {
			public void paint(Graphics g) {
				g.drawImage(fractal, 0,0,null);
			}
		};


		graphics.addMouseListener(this);
		getContentPane().add(graphics);

		fractal = new BufferedImage(632,453, BufferedImage.TYPE_INT_RGB);
		raster = fractal.getRaster();


		System.out.println("Topleft corner of raster: " + raster.getMinX() + "," + raster.getMinY());
		System.out.println("Size of raster: " + raster.getWidth() + "," + raster.getHeight());

		show();

		calc = new Calculator(632,453,raster, this);

		x = -1.5;
		y = -1;
		width = 2;
		height = 0.75 * width;

		if (args.length == 0) {
			calc.setInsets(-1.5, -1, 2, 0.75 * 2);
		}

		if (args.length == 3) {

			x = Double.parseDouble(args[0]);
			y = Double.parseDouble(args[1]);
			width = Double.parseDouble(args[2]);
			height = 0.75 * width;
			calc.setInsets(x,y,width,height);
		}

		System.out.println("Calculating rectangle: ("+x+","+y+") to (" + (x + width) + "," + (y + height)+")");

		calc.start();

		
		Object[] colors = new Object[200];
		int c = 255;
		for (int i = 0 ; i < 50 ; i++) {
			int[] color = new int[3];
			color[0] = 0; color[1] = 255 - c; color[2] = c;
			colors[i] = color;
			c -= 5;
		}


		c = 255;
		for (int i = 50 ; i < 100 ; i++) {
			int[] color = new int[3];
			color[0] = 255 - c; color[1] = 255; color[2] = 0;
			colors[i] = color;
			c -= 5;
		}

		c = 255;
		for (int i = 100 ; i < 150 ; i++) {
			int[] color = new int[3];
			color[0] = c; color[1] = c; color[2] = 255 - c;
			colors[i] = color;
			c -= 5;
		}

		c = 255;
		for (int i = 150 ; i < 200 ; i++) {
			int[] color = new int[3];
			color[0] = 255 - c; color[1] = 0; color[2] = 255;
			colors[i] = color;
			c -= 5;
		}


		for (int i = 0 ; i < 200 ; i++) {
			for (int j = 0 ; j < 30 ; j++) {
				raster.setPixel(i + 20,j + 30, (int[]) colors[i]);
			}
		}
		repaint();
		
	}

	public static void main(String args[]) {
		new Fractals(args);
	}

}


class Calculator extends Thread {
	WritableRaster raster;
	int sizex,sizey;

	double startx, starty, width, height;
	double z, zi;
	int[] black;
	int[] red;
	Object[] colors;
	JFrame parent;
	boolean done;
	;
	public Calculator(int sx, int sy, WritableRaster r, JFrame p) {
		parent = p;
		raster = r;
		sizex = sx;
		sizey = sy;
		z = 0;
		zi = 0;
		black = new int[3];
		black[0] = 0;black[1] = 0;black[2] = 0;
		red = new int[3];
		red[0] = 255;red[1] = 0;red[2] = 0;

		done = true;

		colors = new Object[200];
		int c = 255;
		for (int i = 0 ; i < 50 ; i++) {
			int[] color = new int[3];
			color[0] = 0; color[1] = 255 - c; color[2] = c;
			colors[i] = color;
			c -= 5;
		}


		c = 255;
		for (int i = 50 ; i < 100 ; i++) {
			int[] color = new int[3];
			color[0] = 255 - c; color[1] = 255; color[2] = 0;
			colors[i] = color;
			c -= 5;
		}

		c = 255;
		for (int i = 100 ; i < 150 ; i++) {
			int[] color = new int[3];
			color[0] = c; color[1] = c; color[2] = 255 - c;
			colors[i] = color;
			c -= 5;
		}

		c = 255;
		for (int i = 150 ; i < 200 ; i++) {
			int[] color = new int[3];
			color[0] = 255 - c; color[1] = 0; color[2] = 255;
			colors[i] = color;
			c -= 5;
		}
	}

	public void setInsets(double sx, double sy, double w, double h) {
		startx = sx; starty = sy;
		width = w; height = h;
	}

	public static double comp_mult_real(double a, double b, double c, double d) {
		return (a * c) - (b * d);
	}

	public static double comp_mult_imag(double a, double b, double c, double d) {
		return (a * d) + (b * c);
	}

	public int mandelbrotTest(double a, double bi) {
		// System.out.println("Testing ("+ a + "," + bi + ")");

		double atmp, btmp;
		int number = 0;
		double z = 0,zi = 0;

		while ( (number != 200) && (comp_magnitude(z,zi) < 2)) {
			number++;
			atmp = comp_mult_real(z,zi,z,zi);
			btmp = comp_mult_imag(z,zi,z,zi);

			z = atmp;
			zi = btmp;

			z += a;
			zi += bi;

			// if (number < 10)
				//System.out.println(number + "("+ z + "," + zi + ")");
		}

		if (number == 200) {
			// System.out.println("Part of the Mandelbrot set!");
			return -1;
		} else {
			// System.out.print(" " + number);
			return number;
		}
	}

	public boolean isDone() {
		return done;
	}

	public static double comp_magnitude(double a, double b) {
		return Math.sqrt( a * a + b * b);
	}

	public void run() {
		double dx = width / sizex;
		double dy = height / sizey;

		double z = startx , zi = starty;

		done = false;

		System.out.println("Calculating...");
		for (int x = 0 ; x < sizex ; x++) {
			zi = starty;
			int it;
			for (int y = 0 ; y < sizey ; y++) {
				if ((it =mandelbrotTest(z, zi)) != -1) {
					// In the mandelbrot set.
					raster.setPixel(x,y,(int[]) colors[it]);
				} else {
					// Not in the mandelbrot set
					raster.setPixel(x,y,black);
				}
				zi += dy;
			}
			if ( (x%5) == 0) {
				parent.repaint();

			}
			z += dx;
		}

		done = true;

		System.out.println("Done!");
	}

}

*/
