// example from the Processing.js library
// interactive snake cursor exploring a canvas image

// store mouse position & image
float[] x = new float[20];
float[] y = new float[20];
float segLength = 10;
PImage img;

// set up the screen & background image
void setup(){
  size(320, 240);
  smooth();
  img = loadImage("http://processingjs.org/learning/custom/snake/data/dirt.jpg");
}

// update the screen with the cursor position
void draw(){
  
  // redraw background and image each time
  background(226);
  image(img, 0, 0);
  
  // draw a snake with respect to the mouse movement
  // & adjust the positions across the array
  dragSegment(0, mouseX - 8, mouseY - 8);
  for(int i = 0; i < (x.length - 1); i++)
    dragSegment(i + 1, x[i], y[i]);
}

// draw a piece of the snake
void dragSegment(int i, float xin, float yin){
  
  // calculate the difference between the next position and this one
  float dx = xin - x[i];
  float dy = yin - y[i];
  
  // calculate the angle for this change
  float angle = atan2(dy, dx);
  
  // store this value as the current position
  x[i] = xin - cos(angle) * segLength;
  y[i] = yin - sin(angle) * segLength;
  
  // save the viewing matrix  
  pushMatrix();
  
  // move to the proper segment position
  translate(x[i], y[i]);
  rotate(angle);
  
  // define the snake segment color (based on order)
  // either black, yellow, or red!
  color c;
  if(i % 3 == 1)
    c = color(0, 0, 0, 255);
  else if(i % 3 == 2)
    c = color(255, 255, 0, 255);
  else
    c = color(255, 0, 0, 255);
  
  // draw the segment of the snake
  stroke(c);
  strokeWeight(10);
  line(0, 0, segLength, 0);
  
  // draw the snake's tail
  if(i == x.length - 1){
    fill( c );
    noStroke();
    beginShape(TRIANGLES);
    vertex(0, 5);
    vertex(-2 * segLength, 0);
    vertex(0, -5);
    endShape();
  }
  
  // draw the snake's eyes
  if(i == 0){
    noStroke();
    fill(0, 255);
    ellipse(segLength, -2, 3, 3);
    ellipse(segLength, 2, 3, 3);
  }
  
  // reset the viewing matrix
  popMatrix();
}
