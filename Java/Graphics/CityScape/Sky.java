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

// a sky object in CityScape.java

import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class Sky extends CityScapeObject{

  public Sky(int x, int y,int w, int h){
    Rectangle r = new Rectangle(x,y,w,h);
    Color rc = new Color(10,175,230);
    shapes.add(r);
    colors.add(rc);

    Ellipse2D.Double s = new Ellipse2D.Double(-w/16,-w/16,w/8,w/8);
    Color sc = new Color(255,255,0);
    shapes.add(s);
    colors.add(sc);
  }
}
