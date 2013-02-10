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

// creates a simple bitmap image
// works with Simple.java

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import javax.swing.JPanel;

public class SimpleBitmap extends JPanel{
  private static final int BITMAP_WIDTH = 512;
  private static final int BITMAP_HEIGHT = 512;
  private BufferedImage image;

  public SimpleBitmap(){
    int w = BITMAP_WIDTH;
    int h = BITMAP_HEIGHT;
    int imageType = BufferedImage.TYPE_INT_RGB;
    this.image = new BufferedImage(w,h,imageType);
  }

  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    int w = this.getWidth();
    int h = this.getHeight();
    AffineTransform scale = new AffineTransform();
    scale.setToScale(((double) w) / BITMAP_WIDTH,((double) h) / BITMAP_HEIGHT);
    WritableRaster raster = this.image.getRaster();
    int[] black = {0,0,0};
    int[] yellow = {255,255,0};

    for(int j=0;j<h;j++){
      for(int i=0;i<w;i++){
        if(i<j){
          raster.setPixel(i,j,yellow);
        }else{
          raster.setPixel(i,j,black);
        }
      }
    }
    g2d.drawImage(image,scale,this);
  }
}
