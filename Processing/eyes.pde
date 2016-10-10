// inspiration by Casey Reas and Ben Fry
// moving eyes which follow the mouse

// declare "Eye" variables, as a class
Eye e1, e2, e3, e4, e5;

// initial set up
void setup(){
  
  // set up screen
  size(200, 200);
  smooth();
  noStroke();
  
  // initialize each eye with position & size
  e1 = new Eye(50, 16, 80);
  e2 = new Eye(64, 85, 40);  
  e3 = new Eye(90, 200, 120);
  e4 = new Eye(150, 44, 40); 
  e5 = new Eye(175, 120, 80);
}

// continually redraw the eyes to follow the mouse
void draw(){
  
  // redraw background each time
  background(102);
  
  // update each eye to stare at the mouse position
  e1.update(mouseX, mouseY);
  e2.update(mouseX, mouseY);
  e3.update(mouseX, mouseY);
  e4.update(mouseX, mouseY);
  e5.update(mouseX, mouseY);
  e1.display();
  e2.display();
  e3.display();
  e4.display();
  e5.display();
}

// generalize the eye into a class
class Eye{
  
  // eye position & size
  int ex, ey;
  int size;
  
  // angle to stare towards
  float angle = 0.0;
  
  // how to create an eye
  Eye(int x, int y, int s){
    
    // sets eye position and size
    ex = x;
    ey = y;
    size = s;
  }

  // update the position to stare at with the eye
  void update(int mx, int my){
    angle = atan2(my - ey, mx - ex);
  }

  // draw the eye with its properties to stare at some point
  void display(){
    
    // save current viewing matrix
    pushMatrix();
    
    // move to the new center of the eye
    translate(ex, ey);
    
    // draw the full eye
    fill(255);
    ellipse(0, 0, size, size);
    
    // move the inner part of the eye, to stare at mouse
    rotate(angle);
    fill(153);
    ellipse(size/4, 0, size/2, size/2);
    
    // reset the viewing matrix
    popMatrix();
  }
}
