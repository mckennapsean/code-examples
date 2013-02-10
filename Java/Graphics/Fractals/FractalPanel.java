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

// generates a GUI for displaying the fractal
// works with Fractals.java

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FractalPanel extends JPanel implements ActionListener{
  private BufferedImage fractalImage;

  public FractalPanel(BufferedImage im){
    super(new BorderLayout());
    fractalImage = im;
  }

  public void actionPerformed(ActionEvent e){
    System.out.println("Action has been performed.");
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.drawImage(fractalImage,0,0,this);
  }
}
