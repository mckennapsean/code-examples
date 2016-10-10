// a sidewalk object in CityScape.java

import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class Sidewalk extends CityScapeObject{
  private int stripeWidth = 8;
  private int stripeLength = 25;

  public Sidewalk(int x, int y,int w, int h){

    Rectangle r = new Rectangle(x,y,w,h);
    Color rc = new Color(180,190,210);
    shapes.add(r);
    colors.add(rc);

    stripeWidth = h;
    stripeLength = h;
    for(int i=0;i<(w+stripeWidth+1);i++){
      Rectangle s = new Rectangle(x+25+i,y,1,stripeWidth-3);
      Color sc = new Color(150,150,150);
      shapes.add(s);
      colors.add(sc);
      Rectangle t = new Rectangle(x+26+i,y+3,1,stripeWidth-3);
      Color tc = new Color(105,125,95);
      shapes.add(t);
      colors.add(tc);
      i += 2*stripeLength - 1;
    }
  }
}
