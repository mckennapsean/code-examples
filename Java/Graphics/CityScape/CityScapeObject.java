// a general definition of a cityscape object in CityScape.java

import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class CityScapeObject{
  protected ArrayList<Shape> shapes = new ArrayList<Shape>();
  protected ArrayList<Color> colors = new ArrayList<Color>();

  public ArrayList<Shape> getShapes(){
    return shapes;
  }

  public ArrayList<Color> getColors(){
    return colors;
  }
}
