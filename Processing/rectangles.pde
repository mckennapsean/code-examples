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

// inspiration by Casey Reas and Ben Fry
// adjustable rectangles, based on mouse position

// set up screen
void setup(){
  
  // screen set up
  size(200, 200);
  noStroke();
  
  // how to define colors and draw rectangles from their center
  colorMode(RGB, 255, 255, 255, 100);
  rectMode(CENTER);
}

// what to draw on each loop
void draw(){   
  
  // reset background each time
  background(51); 
  
  // draw left rectangle
  fill(255, 80);
  rect(mouseX, height / 2, mouseY / 2 + 10, mouseY / 2 + 10);
  
  // draw right rectangle
  fill(255, 80);
  rect(width - mouseX, height / 2, ((height - mouseY) / 2) + 10, ((height - mouseY) / 2) + 10);
}
