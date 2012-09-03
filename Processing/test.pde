// by Sean McKenna
// some simple line effects and drawing

int lineX = 0;
int lineY = 0;

void setup(){
  size(300, 300);
  colorMode(RGB, 100);
  smooth();
  frameRate(45);
  background(97);

  fill(100, 60, 75, 75);
  stroke(50, 30, 100, 75);
  ellipse(75, 75, 45, 30);
  
  fill(50, 30, 100, 75);
  stroke(100, 60, 75, 75);
  rect(100, 100, 100, 75);
}

void draw(){
  stroke(random(1, 100), random(1, 100), random(1, 100), 3);
  //stroke(frameCount);
  line(lineX, lineY, mouseX, mouseY);
}

void mousePressed(){
  stroke(100, 65, 5);
  fill(100, 65, 6);
  line(lineX, lineY, mouseX, mouseY);
  lineX = mouseX;
  lineY = mouseY;
}
