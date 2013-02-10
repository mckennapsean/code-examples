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
