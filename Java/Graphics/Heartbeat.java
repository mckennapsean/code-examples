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

// inspired by Leon Tabak
// bouncing ball, moving along a sine wave with adjustable frequency
// ball is modeled in HeartbeatMonitor.java

// import GUI elements
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.Timer;

// main Heartbeat class
public class Heartbeat extends JFrame{
  
  // window variables
  private static final int WINDOW_WIDTH = 512;
  private static final int WINDOW_HEIGHT = 512;
  private static final String TITLE = "Heartbeat";
  
  // create GUI window
  public Heartbeat(){
    
    // setup window attributes
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    this.setTitle(TITLE);
    
    // set up window container, with HeartbeatMonitor
    Container pane = this.getContentPane() ;
    HeartbeatMonitor monitor = new HeartbeatMonitor(4);
    pane.add(monitor);
    
    // add a slider to the window
    JSlider slider = new JSlider();
    slider.setMinimum(1);
    slider.setMaximum(8);
    slider.setOrientation(JSlider.VERTICAL);
    slider.addChangeListener(monitor) ;
    pane.add(slider, BorderLayout.EAST);
    
    // timer to activate events, every 50/1000 of a second (frequency = 20 Hz)
    Timer timer = new Timer(50, monitor);
    timer.start();
    
    // finish window setup
    this.setVisible(true);
  }
  
  // main method to start heartbeat monitor
  public static void main(String[] args){
    Heartbeat heartbeat = new Heartbeat();
  }
}
