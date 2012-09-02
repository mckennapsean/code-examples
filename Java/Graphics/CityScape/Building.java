// by Sean McKenna on April 14th, 2011
//

import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class Building extends CityScapeObject{
  private boolean size = false;
  private Random rng = new Random();

  public Building(int x, int y,int w, int h){

    int red = -20 + rng.nextInt(40);
    int green = -20 + rng.nextInt(40);
    int blue = -20 + rng.nextInt(40);
    Rectangle r = new Rectangle(x,y,w,h);
    Color rc = new Color(205+red,190+green,170+blue);
    shapes.add(r);
    colors.add(rc);

    if(w>200){
      size = true;
    }
    Door d = new Door(x+w/2-11,y+h-40,20,40,size);
    shapes.addAll(d.getShapes());
    colors.addAll(d.getColors());

    WindowGenerator wg = new WindowGenerator(x,y,w,h,40);
    shapes.addAll(wg.getShapes());
    colors.addAll(wg.getColors());

  }
}
