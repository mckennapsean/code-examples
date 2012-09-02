// by Sean McKenna on April 12th, 2011
//

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Polygon;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Random;
import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class CityScapePanel extends JPanel{
  //set the width, height, and array lists
  private int WIDTH;
  private int HEIGHT;
  private int SIZE;
  private ArrayList<Shape> shapes = new ArrayList<Shape>();
  private ArrayList<Color> colors = new ArrayList<Color>();

  //call to generate the shapes, get shapes and colors
  public CityScapePanel(int w, int h){
    WIDTH = w;
    HEIGHT = h;
    CityScapeGenerator gen = new CityScapeGenerator(w,h);
    shapes = gen.getShapes();
    colors = gen.getColors();
  }

  //override to draw and color shapes
  @Override
  public void paintComponent(Graphics g){	
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    //automatic coloring based on shapes and colors in the array list
    int iter = 0;
    for(Shape s:shapes){
      g.setColor(colors.get(iter));
      g2d.fill(s);
      iter++;
    }
  }
}
