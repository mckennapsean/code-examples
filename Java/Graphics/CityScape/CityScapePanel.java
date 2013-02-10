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

// the cityscape panel in CityScape.java

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Polygon;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Random;
import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class CityScapePanel extends JPanel{
  //set the width, height, and array lists
  private int WIDTH;
  private int HEIGHT;
  private int SIZE;
  private ArrayList<Shape> shapes = new ArrayList<Shape>();
  private ArrayList<Color> colors = new ArrayList<Color>();

  //call to generate the shapes, get shapes and colors
  public CityScapePanel(int w, int h){
    WIDTH = w;
    HEIGHT = h;
    CityScapeGenerator gen = new CityScapeGenerator(w,h);
    shapes = gen.getShapes();
    colors = gen.getColors();
  }

  //override to draw and color shapes
  @Override
  public void paintComponent(Graphics g){	
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    //automatic coloring based on shapes and colors in the array list
    int iter = 0;
    for(Shape s:shapes){
      g.setColor(colors.get(iter));
      g2d.fill(s);
      iter++;
    }
  }
}
