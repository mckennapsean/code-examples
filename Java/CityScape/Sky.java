// a sky object in CityScape.java

import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class Sky extends CityScapeObject{

  public Sky(int x, int y,int w, int h){
    Rectangle r = new Rectangle(x,y,w,h);
    Color rc = new Color(10,175,230);
    shapes.add(r);
    colors.add(rc);

    Ellipse2D.Double s = new Ellipse2D.Double(-w/16,-w/16,w/8,w/8);
    Color sc = new Color(255,255,0);
    shapes.add(s);
    colors.add(sc);
  }
}
