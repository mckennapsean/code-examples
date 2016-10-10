// inspiration by Casey Reas and Ben Fry
// creates a working clock on the screen

// set up the scren
void setup(){
  
  // set screen size & stroke settings
  size(200, 200);
  stroke(255);
  smooth();
}

// draw repetitively on the screen
void draw(){
  
  // redraw background each time
  background(0);
  
  // draw clock
  fill(80);
  noStroke();
  ellipse(100, 100, 160, 160);
  
  // remember that the clock is originall set to be at 3:00
  // adjust as needed (by one-quarter turn or half of pi)
  float sec = map(second(), 0, 60, 0, TWO_PI) - HALF_PI;
  float min = map(minute(), 0, 60, 0, TWO_PI) - HALF_PI;
  float hr = map(hour() % 12, 0, 12, 0, TWO_PI) - HALF_PI;
  
  // prepare to draw hands
  stroke(255);
  strokeWeight(1);
  
  // draw hands
  line(100, 100, cos(sec) * 72 + 100, sin(sec) * 72 + 100);
  strokeWeight(2);
  line(100, 100, cos(min) * 60 + 100, sin(min) * 60 + 100);
  strokeWeight(4);
  line(100, 100, cos(hr) * 50 + 100, sin(hr) * 50 + 100);
}
