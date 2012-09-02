// by Sean McKenna on April 14th, 2011
//

import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class Door extends CityScapeObject{

  public Door(int x, int y,int w, int h, boolean size){

    Rectangle r = new Rectangle(x,y,w,h);
    Color rc = new Color(107,73,0);
    shapes.add(r);
    colors.add(rc);

    Ellipse2D.Double knob = new Ellipse2D.Double(x+4*w/5,y+5*h/9,3,3);
    Color knobc = new Color(200,180,0);
    shapes.add(knob);
    colors.add(knobc);

    if(size){
      Rectangle r2 = new Rectangle(x+w+1,y,w,h);
      Color rc2 = new Color(107,73,0);
      shapes.add(r2);
      colors.add(rc2);

      Ellipse2D.Double knob2 = new Ellipse2D.Double(x+w+1*w/5-3,y+5*h/9,3,3);
      Color knobc2 = new Color(200,180,0);
      shapes.add(knob2);
      colors.add(knobc2);

    }
  }
}
