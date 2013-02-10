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

// interacts with PolygonClipping.java to create random, shaded polygons
// these are then clipped to some arbitrary window (Sutherland-Hodgeman clipping algorithm)
// mouse clicks generate a new random polygons & clipping windo

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Clipping extends JFrame implements MouseListener{
  private static final long serialVersionUID = 1L;
  private static final int WIDTH = 512;
  private static final int HEIGHT = 512;
  private static final String TITLE = "Polygon Clipping";
  private PolygonClipping panel;
  private Container pane;

  public static void main(String[] args){
    Clipping polygon = new Clipping();
  }

  public Clipping(){
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(WIDTH, HEIGHT);
    this.setTitle(TITLE);
    pane = this.getContentPane();

    panel = new PolygonClipping();
    pane.add(panel);
    pane.addMouseListener(this);

    this.setVisible(true);
  }

  public void mouseClicked(MouseEvent e){
    panel = new PolygonClipping();
    pane.add(panel);
    this.setVisible(true);
  }

  public void mouseEntered(MouseEvent e){}
  public void mouseExited(MouseEvent e) {}
  public void mousePressed(MouseEvent e){}
  public void mouseReleased(MouseEvent e){}
}
