// a window generator object in CityScape.java

import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class WindowGenerator extends CityScapeObject{
  private int floorHeight;
  private int windowHeight;
  private int windowWidth;

  public WindowGenerator(int x, int y,int w, int h, int door){

    floorHeight = door + 10;
    windowHeight = floorHeight / 3;
    windowWidth = windowHeight;
    for(int i=h-floorHeight*2;i>-windowHeight-1;i--){
      for(int j=windowWidth;j<w-windowWidth-1;j++){
        Window window = new Window(x+j,y+i+windowHeight,windowWidth,windowHeight);
        shapes.addAll(window.getShapes());
        colors.addAll(window.getColors());
        j += windowHeight * 2;
        j--;
      }
      i -= floorHeight;
      i++;
    }
  }
}
