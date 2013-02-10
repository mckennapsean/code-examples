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

// a cityscape generator object in CityScape.java

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
