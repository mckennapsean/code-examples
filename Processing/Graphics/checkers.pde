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

// generates a chess/checkers board with alternating colors based on mouse position

// for storing Java shapes in lists
import java.awt.geom.Point2D;
import java.awt.Rectangle;

// grid to display on-screen
int rows = 8;
int columns = 8;

// size of screen
int h = 300;
int w = 300;

// colors for the grid squares
int whiteColorR = 255;
int whiteColorG = 250;
int whiteColorB = 237;
int blackColorR = 108;
int blackColorG = 78;
int blackColorB = 0;

// background color
color backgroundColor = color(35);

// misc. (frame rate, anti-aliasing)
int frame_rate = 45;
boolean anti_aliasing = true;

// preset variables
float cellH = h / rows;
float cellW = w / columns;
float cellAve = (cellH + cellW) / 2;
int cells = rows * columns / 2;
boolean light = true;
color whiteColor = color(whiteColorR, whiteColorG, whiteColorB);
color blackColor = color(blackColorR, blackColorG, blackColorB);
int whiteSquaresPointer = 0;
Point2D whiteSquares[] = new Point2D[cells];
Rectangle whiteSquares2[] = new Rectangle[cells];
int whiteSquareColors[][] = new int[cells][3];
int blackSquaresPointer = 0;
Point2D blackSquares[] = new Point2D[cells];
Rectangle blackSquares2[] = new Rectangle[cells];
int blackSquareColors[][] = new int[cells][3];

void setup(){
  background(backgroundColor);
  size(w, h);
  if(anti_aliasing)
    smooth();
  frameRate(frame_rate);
  
  // create the grid of squares
  for(int i = 0; i < rows; i++){
    for(int j = 0; j < columns; j++){
      // current x and y position
      float x = (float) j * cellW;
      float y = (float) i * cellH;
      
      // operations to create each type of square
      // store data for later (position, color)
      if(light){
        int pp = whiteSquaresPointer;
        whiteSquares[pp] = new Point2D.Float(x, y);
        whiteSquares2[pp] = new Rectangle((int) x, (int) y, (int) cellW, (int) cellH);
        whiteSquareColors[pp] = new int[] {whiteColorR, whiteColorG, whiteColorB};
        fill(whiteColor);
        whiteSquaresPointer++;
      }
      else{
        int pp = blackSquaresPointer;
        blackSquares[pp] = new Point2D.Float(x, y);
        blackSquares2[pp] = new Rectangle((int) x, (int) y, (int) cellW, (int) cellH);
        blackSquareColors[pp] = new int[] {blackColorR, blackColorG, blackColorB};
        fill(blackColor);
        blackSquaresPointer++;
      }
      
      // draw square
      rect(x, y, cellW, cellH);
      
      // alternate colors between columns
      light = !light;
    }
    // alternate colors between rows
    light = !light;
  }
}

void draw(){
  // color every white square
  for(int i = 0; i < whiteSquaresPointer; i++){
    whiteColorR = whiteSquareColors[i][0];
    whiteColorG = whiteSquareColors[i][1];
    whiteColorB = whiteSquareColors[i][2];
    
    // if hovering over, make square darker
    if(whiteSquares2[i].contains(mouseX, mouseY)){
      if(whiteColorR > blackColorR)
        whiteColorR -= 10;
      if(whiteColorG > blackColorG)
        whiteColorG -= 10;
      if(whiteColorB > blackColorB)
        whiteColorB -= 10;
        
    // otherwise, return square to original color
    }else{
      if(whiteColorR < red(whiteColor))
        whiteColorR += 2;
      if(whiteColorG < green(whiteColor))
        whiteColorG += 2;
      if(whiteColorB < blue(whiteColor))
        whiteColorB += 2;
    }
    
    // save the data and paint the color on the square
    whiteSquareColors[i] = new int[] {whiteColorR, whiteColorG, whiteColorB};
    fill(whiteColorR, whiteColorG, whiteColorB);
    rect((float) whiteSquares[i].getX(), (float) whiteSquares[i].getY(), cellW, cellH);
  }
  
  // color every black square
  for(int i = 0; i < blackSquaresPointer; i++){
    blackColorR = blackSquareColors[i][0];
    blackColorG = blackSquareColors[i][1];
    blackColorB = blackSquareColors[i][2];
    
    // if hovering over, make square lighter
    if(blackSquares2[i].contains(mouseX, mouseY)){
      if(blackColorR < whiteColorR)
        blackColorR += 10;
      if(blackColorG < whiteColorG)
        blackColorG += 10;
      if(blackColorB < whiteColorB)
        blackColorB += 10;
    
    // otherwise, return square to original color
    }else{
      if(blackColorR > red(blackColor))
        blackColorR -= 2;
      if(blackColorG > green(blackColor))
        blackColorG -= 2;
      if(blackColorB > blue(blackColor))
        blackColorB -= 2;
    }
    
    // save the data and paint the color on the square
    blackSquareColors[i] = new int[] {blackColorR, blackColorG, blackColorB};
    fill(blackColorR, blackColorG, blackColorB);
    rect((float) blackSquares[i].getX(), (float) blackSquares[i].getY(), cellW, cellH);
  }
}
