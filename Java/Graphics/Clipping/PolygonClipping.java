// by Sean McKenna on April 11th, 2011
// works with Clipping.java to test the Sutherland-Hodgeman Clipping Algorithm
// clipping algorithm was inspired by the open-source project JHAVE
// the rest of the JHAVE project is GPL-licensed and NOT licensed by the sample code base

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Polygon;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Random;

public class PolygonClipping extends JPanel{
  //set the width, height, and random number generator
  private static final int WIDTH = 512;
  private static final int HEIGHT = 512;
  private Random rng = new Random();


  //initiate all polygons
  private Polygon p = new Polygon();
  private Polygon q = new Polygon();
  private Polygon r = new Polygon();


  //initiate all polygon array lists
  private ArrayList<Point> input = new ArrayList();
  private ArrayList<Edge> window = new ArrayList();
  private ArrayList<Point> output = new ArrayList();


  //main class, does the 'work'
  public PolygonClipping(){
    //create input and window polygons and fill array lists
    input = generateInput();
    window = generateWindow();

    //generate output from S.H.-clipping algorithm
    output = clippingAlg(input,window);
    System.out.print("Clipped polygon from "+input.size());
    System.out.println(" to "+output.size()+" points.");
  }


  //creates the input polygon, random 3-300 vertices
  private ArrayList<Point> generateInput(){
    ArrayList<Point> in = new ArrayList();
    int numbPts = rng.nextInt(298) + 3;
//    numbPts = 4000;
    for(int iter=0;iter<numbPts;iter++){
      int x = rng.nextInt(WIDTH);
      int y = rng.nextInt(HEIGHT);
      in.add(new Point(x,y,0));
      p.addPoint(x,y);
    }
    return in;
  }

  //creates the window to clip the input polygon, random quadrilateral
  public ArrayList<Edge> generateWindow(){
    ArrayList<Edge> box = new ArrayList();
    int p0X = rng.nextInt(WIDTH/4);
    int p0Y = rng.nextInt(HEIGHT/4);
    int p1X = p0X + rng.nextInt(WIDTH-p0X) + 1;
    int p3Y = p0Y + rng.nextInt(HEIGHT-p0Y) + 1;
    int p1Y = p3Y - rng.nextInt(p3Y) - 1;
    int p3X = p0X - rng.nextInt(p0X) - 1;
    int p2X = p1X + 1;
    int p2Y = p3Y + 1;
    box.add(new Edge(new Point(p0X,p0Y,0), new Point(p1X,p1Y,0)));
    box.add(new Edge(new Point(p1X,p1Y,0), new Point(p2X,p2Y,0)));
    box.add(new Edge(new Point(p2X,p2Y,0), new Point(p3X,p3Y,0)));
    box.add(new Edge(new Point(p3X,p3Y,0), new Point(p0X,p0Y,0)));
    r.addPoint(p0X,p0Y);
    r.addPoint(p1X,p1Y);
    r.addPoint(p2X,p2Y);
    r.addPoint(p3X,p3Y);
    return box;
  }

  //conducts the Sutherland-Hodgman clipping algorithm
  //returns array list of points, points of the output polygon
  public ArrayList<Point> clippingAlg(ArrayList subjectP,ArrayList clippingP){
    ArrayList<Edge> edges = clippingP;
    ArrayList<Point> in = subjectP;
    ArrayList<Point> out = new ArrayList<Point>();

    //begin looping through edges and points
    int casenum = 0;
    for(int i=0;i<edges.size();i++){
      Edge e = edges.get(i);
      Point r = edges.get((i+2)%edges.size()).getP1();
      Point s = in.get(in.size()-1);
      for(int j=0;j<in.size();j++){
        Point p = in.get(j);

        //first see if the point is inside the edge
        if(Edge.isPointInsideEdge(e,r,p)){
          //then if the specific pair of points is inside 
          if(Edge.isPointInsideEdge(e,r,s)){
            casenum = 1;
          //pair goes outside, so one point still inside
          }else{
            casenum = 4;
          }

        //no point inside
        }else{
          //does the specific pair go inside
          if(Edge.isPointInsideEdge(e,r,s)){
            casenum = 2;
          //no points in pair are inside
          }else{
            casenum = 3;
          }
        }

        switch(casenum){

          //pair is inside, add point
          case 1:
            out.add(p);
            break;

          //pair goes inside, add intersection only
          case 2:
            Point inter0 = e.computeIntersection(s,p);
            out.add(inter0);
            break;

          //pair outside, add nothing
          case 3:
            break;

          //pair goes outside, add point and intersection
          case 4:
            Point inter1 = e.computeIntersection(s,p);
            out.add(inter1);
            out.add(p);
            break;
        }
        s = p;
      }
      in = (ArrayList<Point>)out.clone(); 
      out.clear();
    }
    for(Point pt:in){
      q.addPoint((int)pt.getX(),(int)pt.getY());
    }  
    return in;
  }


  //override to color the polygons for the drawing
  @Override
  public void paintComponent(Graphics g){	
    super.paintComponent(g);

    //color background gray (rectangle)
    g.setColor(new Color(25,25,25));
    g.fillRect(0,0,512,512);

    //color polygon to be clipped (red-orange)
    g.setColor(new Color(255,25,15));
    g.fillPolygon(p);

    //color the clipping window (blue-white)
    g.setColor(new Color(225,225,255));
    g.fillPolygon(r);

    //color the clipped polygon (blue)
    g.setColor(new Color(75,0,255));
    g.fillPolygon(q);
  }
}
