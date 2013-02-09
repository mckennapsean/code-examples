// by Sean McKenna
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
