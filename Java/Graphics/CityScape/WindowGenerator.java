// Copyright 2013 Sean McKenna
//
//    Licensed under the Apache License, Version 2.0 (the "License");
//    you may not use this file except in compliance with the License.
//    You may obtain a copy of the License at
//
//        http://www.apache.org/licenses/LICENSE-2.0
//
//    Unless required by applicable law or agreed to in writing, software
//    distributed under the License is distributed on an "AS IS" BASIS,
//    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//    See the License for the specific language governing permissions and
//    limitations under the License.
//

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
