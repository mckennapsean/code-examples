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

// a building object in CityScape.java

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
