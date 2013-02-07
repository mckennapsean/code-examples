// by Sean McKenna on April 14th, 2011
// a street object in CityScape.java

import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class Street extends CityScapeObject{

  private int stripeWidth = 8;
  private int stripeLength = 25;

  public Street(int x, int y,int w, int h){

    Rectangle r = new Rectangle(x,y,w,h);
    Color rc = new Color(100,100,100);
    shapes.add(r);
    colors.add(rc);

    stripeWidth = h/32;
    stripeLength = w/32;
    for(int i=0;i<(w+stripeWidth+1);i++){
      Rectangle s = new Rectangle(x+25+i,y+3*h/4,stripeLength,stripeWidth);
      Color sc = new Color(245,255,235);
      shapes.add(s);
      colors.add(sc);
      i += 2*stripeLength - 1;
    }

    Rectangle t = new Rectangle(x,y+stripeWidth*3,w,stripeWidth);
    Color tc = new Color(230,140,15);
    shapes.add(t);
    colors.add(tc);

  }
}
