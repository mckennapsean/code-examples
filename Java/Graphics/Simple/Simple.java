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

// create some simple Java 2D graphics
// uses several object classes, by commenting & un-commenting the "panel" object

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Simple extends JFrame{
  private static final int SIMPLE_WIDTH = 512;
  private static final int SIMPLE_HEIGHT = 512;
  private static final String SIMPLE_TITLE = "Simple";
  
  public Simple(){
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(SIMPLE_WIDTH, SIMPLE_HEIGHT);
    this.setTitle(SIMPLE_TITLE);
    Container pane = this.getContentPane();
    
    //JPanel panel = new JPanel();
    //panel.setBackground(Color.CYAN);
    //pane.add(panel);
    
    //SimplePicture panel = new SimplePicture();
    //pane.add(panel);
    
    //SimpleBitmap panel = new SimpleBitmap();
    //pane.add(panel);
    
    //SimpleImage panel = new SimpleImage();
    //pane.add(panel);
    
    //SimpleAnimation panel = new SimpleAnimation();
    //pane.add(panel);
    //Timer timer = new Timer(20, panel);
    //timer.start();
    
    ShadedTriangle panel = new ShadedTriangle();
    pane.add(panel);
    
    this.setVisible(true);
  }

  public static void main(String[] args){
    Simple simple = new Simple();
  }
}
