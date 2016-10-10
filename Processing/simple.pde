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

// some simple drawing & line effects

// store current line values
int lineX = 0;
int lineY = 0;

// set up screen
void setup(){
  
  // set up screen, color mode, & background
  size(300, 300);
  colorMode(RGB, 100);
  smooth();
  frameRate(45);
  background(97);
  
  // display two shapes to permanently leave on the screen
  fill(100, 60, 75, 75);
  stroke(50, 30, 100, 75);
  ellipse(75, 75, 45, 30);
  fill(50, 30, 100, 75);
  stroke(100, 60, 75, 75);
  rect(100, 100, 100, 75);
}

// shows all the possible paths where the mouse moves to draw a new line
// selects random colors over time
void draw(){
  stroke(random(1, 100), random(1, 100), random(1, 100), 3);
  line(lineX, lineY, mouseX, mouseY);
}

// when clicking the mouse, draw a new line to connect to the previous one
void mousePressed(){
  stroke(100, 65, 5);
  fill(100, 65, 6);
  line(lineX, lineY, mouseX, mouseY);
  lineX = mouseX;
  lineY = mouseY;
}
