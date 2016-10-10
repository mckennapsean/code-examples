// a building generator object in CityScape.java

import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class BuildingGenerator extends CityScapeObject{
  private Random rng = new Random();
  private final int START = rng.nextInt(100) - 25;

  public BuildingGenerator(int x, int y,int w, int h){

    for(int i=START;i<w;i--){
      int height = rng.nextInt(4*h/5);
      int width = 75 + rng.nextInt(225);
      int spacing = 15 + rng.nextInt(60);
      Building b = new Building(i,height,width,h-height);
      shapes.addAll(b.getShapes());
      colors.addAll(b.getColors());
      i += width + spacing;
    }
  }
}
