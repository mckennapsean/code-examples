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
// cool paint-brush effect on the canvas

// storing the brush fade effect as the mouse moves (blur out brush effect)
int num = 60;
float mx[] = new float[num];
float my[] = new float[num];

// set up screen
void setup(){
  size(200, 200);
  smooth();
  noStroke();
  fill(255, 153);
}

// continually draw the brush effect where the mouse is
void draw(){
  
  // clear the background each time
  // this leaves no residual effect
  background(51);
  
  // adjust the array by one, one time-step of num (60)
  // ignore the last array value
  for(int i = 1; i < num; i++){
    mx[i - 1] = mx[i];
    my[i - 1] = my[i];
  }
  
  // add the current mouse values to the end of the array
  mx[num - 1] = mouseX;
  my[num - 1] = mouseY;
  
  // draw the brush with changing size over the previous mouse positions
  for(int i = 0; i < num; i++)
    ellipse(mx[i], my[i], i / 2, i / 2);
}
