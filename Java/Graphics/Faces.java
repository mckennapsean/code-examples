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
// creates a happy face and a ... dead face

// imports for graphics & GUI
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

// create a GUI for drawing a face
public class Faces extends JFrame implements ActionListener{
  
  // variables for creating the faces
  private static final int WINDOW_WIDTH = 512;
  private static final int WINDOW_HEIGHT = 512;
  private static final String TITLE = "Faces";
  private static final String DISPOSITION = "Disposition";
  private static final String SMILE = "Smile";
  // new disposition: "death" in German
  private static final String STERBEN = "Sterben";
  private static final String EXIT = "Exit";
  private static final Color MENUBAR_COLOR = new Color(240, 224, 192);
  private Container pane;
  private HashMap<String, JPanel> pictures;
  
  // create the graphical faces
  public Faces(){
    this.pictures = new HashMap<String, JPanel>();
    
    // draw smiley face
    this.pictures.put(SMILE,
        new JPanel(){
          
          @Override
          public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int w = this.getWidth();
            int h = this.getHeight();
            g2d.setColor(Color.PINK);
            g2d.fillRect(0, 0, w, h);
            BasicStroke stroke = new BasicStroke(8);
            
            // draw head & smile
            Ellipse2D head = new Ellipse2D.Double(0, 0, w, h);
            g2d.setColor(Color.WHITE);
            g2d.setStroke(stroke);
            g2d.draw(head);
            double x0 = 0.2 * w;
            double y0 = 0.8 * h;
            double x1 = 0.4 * w;
            double y1 = 0.9 * h;
            double x2 = 0.6 * w;
            double y2 = 0.9 * h;
            double x3 = 0.8 * w;
            double y3 = 0.8 * h;
            CubicCurve2D smile = new CubicCurve2D.Double(x0, y0, x1, y1, x2, y2, x3, y3);
            g2d.draw(smile);
            
            //addition of eyes
            double x4 = 0.175 * w;
            double y4 = 0.2 * h;
            double d = 0.4 * w;
            double w0 = 0.25 * w;
            double h0 = 0.2 * h;
            Ellipse2D leftEye = new Ellipse2D.Double(x4, y4, w0, h0);
            Ellipse2D rightEye = new Ellipse2D.Double(x4 + d, y4, w0, h0);
            g2d.draw(leftEye);
            g2d.draw(rightEye);
            
            //creation of nose
            double x5 = 0.45 * w;
            double x6 = 0.37 * w;
            double y5 = 0.45 * h;
            double y6 = 0.65 * h;
            Line2D nose1 = new Line2D.Double(x5, y5, x6 ,y6);
            Line2D nose2 = new Line2D.Double(x6, y6, x5, y6);
            g2d.draw(nose1);
            g2d.draw(nose2);
          }
        });
    
    // put in death face
    this.pictures.put(STERBEN,
        new JPanel(){
          
          @Override
          public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            Graphics2D g2d2 = (Graphics2D) g;
            int w = this.getWidth();
            int h = this.getHeight();
            g2d.setColor(Color.BLUE);
            g2d.fillRect(0, 0, w, h);
            BasicStroke stroke = new BasicStroke(8);
            
            // draw head
            Ellipse2D head = new Ellipse2D.Double(0, 0, w, h);
            g2d.setColor(Color.WHITE);
            g2d.setStroke(stroke);
            g2d.draw(head);
            
            // draw mouth (not smiling!)
            double x0 = 0.2 * w;
            double y0 = 0.8 * h;
            double x1 = 0.4 * w;
            double y1 = 0.8 * h;
            double x2 = 0.6 * w;
            double y2 = 0.8 * h;
            double x3 = 0.8 * w;
            double y3 = 0.8 * h;
            CubicCurve2D smile = new CubicCurve2D.Double(x0, y0, x1, y1, x2, y2, x3, y3);
            g2d.draw(smile);
            
            // draw nose
            double x8 = 0.45*w;
            double x9 = 0.37*w;
            double y8 = 0.45*h;
            double y9 = 0.65*h;
            Line2D nose1 = new Line2D.Double(x8, y8, x9, y9);
            Line2D nose2 = new Line2D.Double(x9, y9, x8, y9);
            g2d.draw(nose1);
            g2d.draw(nose2);
            
            // draw eyes (crossed out!)
            double x4 = 0.2*w;
            double x5 = 0.4*w;
            double x6 = 0.6*w;
            double x7 = 0.8*w;
            double y4 = 0.2*h;
            double y5 = 0.4*h;
            Line2D eyes1 = new Line2D.Double(x4, y4, x5, y5);
            Line2D eyes2 = new Line2D.Double(x4, y5, x5, y4);
            Line2D eyes3 = new Line2D.Double(x6, y4, x7, y5);
            Line2D eyes4 = new Line2D.Double(x6, y5, x7, y4);
            g2d2.setColor(Color.RED);
            g2d2.setStroke(stroke);
            g2d2.draw(eyes1);
            g2d2.draw(eyes2);
            g2d2.draw(eyes3);
            g2d2.draw(eyes4);
          }
        });
    
    // wrap up window setup
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    this.setTitle(TITLE);
    
    // set up layout for displaying faces
    this.pane = this.getContentPane();
    this.pane.setLayout(new BorderLayout());
    this.pane.add(this.pictures.get(SMILE), BorderLayout.CENTER);
    
    // create menu bar for selecting which face
    JMenuBar menuBar = new JMenuBar();
    menuBar.setBackground(MENUBAR_COLOR);
    this.setJMenuBar(menuBar);
    JMenu dispositionMenu = new JMenu(DISPOSITION);
    menuBar.add(dispositionMenu);
    
    // smiley face option
    JMenuItem smile = new JMenuItem(SMILE);
    smile.setActionCommand(SMILE);
    smile.addActionListener(this);
    dispositionMenu.add(smile);
    
    // death face option
    JMenuItem morgenmuffel = new JMenuItem(STERBEN);
    morgenmuffel.setActionCommand(STERBEN);
    morgenmuffel.addActionListener(this);
    dispositionMenu.add(morgenmuffel);
    
    // finish window setup
    JMenuItem exit = new JMenuItem(EXIT);
    exit.setActionCommand(EXIT);
    exit.addActionListener(this);
    dispositionMenu.add(exit);
    this.setVisible(true);
  }
  
  // main method to draw faces
  public static void main(String[] args){
    Faces faces = new Faces();
  }
  
  // detect menu option changes for drawing new faces
  public void actionPerformed(ActionEvent e){
    String command = e.getActionCommand();
    
    // determine which action & change the face appropriately
    if(command.equals(SMILE)){
      this.pane.removeAll();
      Component component = this.pictures.get(SMILE);
      this.pane.add(component, BorderLayout.CENTER);
      component.repaint();
      this.validate();
    }else if(command.equals(STERBEN)){
      this.pane.removeAll();
      Component component = this.pictures.get(STERBEN);
      this.pane.add(component, BorderLayout.CENTER);
      component.repaint();
      this.validate();
    }else if(command.equals(EXIT)){
      this.dispose();
      System.exit(0);
    }else{
      String message = "Unrecognized menu option";
      throw new IllegalArgumentException(message);
    }
  }
}
