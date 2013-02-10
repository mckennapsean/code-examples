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

// a door object in CityScape.java

import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class Door extends CityScapeObject{

  public Door(int x, int y,int w, int h, boolean size){

    Rectangle r = new Rectangle(x,y,w,h);
    Color rc = new Color(107,73,0);
    shapes.add(r);
    colors.add(rc);

    Ellipse2D.Double knob = new Ellipse2D.Double(x+4*w/5,y+5*h/9,3,3);
    Color knobc = new Color(200,180,0);
    shapes.add(knob);
    colors.add(knobc);

    if(size){
      Rectangle r2 = new Rectangle(x+w+1,y,w,h);
      Color rc2 = new Color(107,73,0);
      shapes.add(r2);
      colors.add(rc2);

      Ellipse2D.Double knob2 = new Ellipse2D.Double(x+w+1*w/5-3,y+5*h/9,3,3);
      Color knobc2 = new Color(200,180,0);
      shapes.add(knob2);
      colors.add(knobc2);

    }
  }
}
