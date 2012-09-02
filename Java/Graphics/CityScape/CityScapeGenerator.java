// by Sean McKenna on April 14th, 2011
//

import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class CityScapeGenerator extends CityScapeObject{
  private int WIDTH;
  private int HEIGHT;

  public CityScapeGenerator(int w, int h){
    WIDTH = w;
    HEIGHT = h;

    Street st = new Street(0,HEIGHT-140,WIDTH,140);
    shapes.addAll(st.getShapes());
    colors.addAll(st.getColors());

    Sidewalk sw = new Sidewalk(0,HEIGHT-180,WIDTH,40);
    shapes.addAll(sw.getShapes());
    colors.addAll(sw.getColors());

    Sky sky = new Sky(0,0,WIDTH,420);
    shapes.addAll(sky.getShapes());
    colors.addAll(sky.getColors());

    BuildingGenerator bg = new BuildingGenerator(0,0,WIDTH,420);
    shapes.addAll(bg.getShapes());
    colors.addAll(bg.getColors());

  }
}
