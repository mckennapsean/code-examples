// a window object in CityScape.java

import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class Window extends CityScapeObject{
  private Random rng = new Random();

  public Window(int x, int y,int w, int h){

    Rectangle b = new Rectangle(x,y,w,h);
    Color bc = new Color(20,20,20);
    shapes.add(b);
    colors.add(bc);

    Rectangle r = new Rectangle(x+1,y+1,w-1,h-1);
    int offset = -20 + rng.nextInt(40);
    Color rc = new Color(200+offset,200+offset,200+offset);
    shapes.add(r);
    colors.add(rc);

    Rectangle s = new Rectangle(x+w/2-1,y,1,h);
    Rectangle t = new Rectangle(x,y+h/2-1,w,1);
    Color stc = new Color(20,20,20);
    shapes.add(s);
    shapes.add(t);
    colors.add(stc);
    colors.add(stc);

  }
}
