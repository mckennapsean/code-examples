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
