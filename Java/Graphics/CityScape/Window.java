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

// a window object in CityScape.java

import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class Window extends CityScapeObject{
  private Random rng = new Random();

  public Window(int x, int y,int w, int h){

    Rectangle b = new Rectangle(x,y,w,h);
    Color bc = new Color(20,20,20);
    shapes.add(b);
    colors.add(bc);

    Rectangle r = new Rectangle(x+1,y+1,w-1,h-1);
    int offset = -20 + rng.nextInt(40);
    Color rc = new Color(200+offset,200+offset,200+offset);
    shapes.add(r);
    colors.add(rc);

    Rectangle s = new Rectangle(x+w/2-1,y,1,h);
    Rectangle t = new Rectangle(x,y+h/2-1,w,1);
    Color stc = new Color(20,20,20);
    shapes.add(s);
    shapes.add(t);
    colors.add(stc);
    colors.add(stc);

  }
}
