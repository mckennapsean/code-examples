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

// use OpenGL to display many points on the screen

// import openGL libraries
import javax.media.opengl.GL;
import javax.media.opengl.GL2;

// how many points
int numPts = 40000;

// other variables (display, color, etc.)
int w, h;
color outline = color(31, 120, 180, 100);
color fill = color(31, 120, 100, 50);
color background = color(255, 255, 255, 0);

// keep track of moving points
Interpolator[] xInterpolator, yInterpolator;

// set up window
void setup(){
  w = h = 600;
  size(w, h, P2D);
  smooth();
  
  // initialize interpolators
  xInterpolator = new Interpolator[numPts];
  yInterpolator = new Interpolator[numPts];
  for(int i = 0; i < numPts; i++ ){
    xInterpolator[i] = new Interpolator(int(random(1, w)));
    yInterpolator[i] = new Interpolator(int(random(1, h)));
  }

}

// use OpenGL to generate many points
// note: screen gets mapped to (-1, -1) to (1, 1)
void makeRectangles(){
  
  // Processing OpenGL JOGL
  PGraphicsOpenGL pg = ((PGraphicsOpenGL)g);
  PGL pgl = pg.beginPGL();
  GL gl = pgl.gl;
  GL2 gl2 = pgl.gl.getGL2();

  // grab colors
  float r = fill >> 16 & 0xFF;
  float g = fill >> 8 & 0xFF;
  float b = fill & 0xFF;

  // draw points
  gl2.glColor4f(r / 255.0, g / 255.0, b / 255.0, alpha(fill) / 255.0);  
  gl2.glPointSize(9);
  gl2.glBegin(gl2.GL_POINTS);
  
  for(int n = 0; n < numPts; n++){
	  gl2.glVertex2f(2 * (xInterpolator[n].value()) / w - 1, 2 * (yInterpolator[n].value()) / h - 1);
    
    // update interpolator to new position
    xInterpolator[n].update();
    yInterpolator[n].update();
  }
  
  // finish points
  gl2.glEnd();
  
  // disable OpenGL mode
  pg.endPGL();
}

// update the screen to animate points
void draw(){
  background(background);
  
  // interpolated OpenGL points
  makeRectangles();
  
  // output the frame rate for testing
  println(int(frameRate));
}

// update and randomize the target position of the interpolating points
void keyPressed(){
  for( int i = 0; i < numPts; i++ ){
    xInterpolator[i].enable(int(random(1, w)));
    yInterpolator[i].enable(int(random(1, h)));
  }
}
