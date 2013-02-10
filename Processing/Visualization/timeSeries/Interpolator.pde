// by Sean McKenna
// inspired by Miriah Meyer & Ben Fry
// interpolates between values for animation-effects
// not intended to be used alone

class Interpolator{
  
  // set variables for interpolation
  float val;
  float beg;
  float end;
  float norm;
  int i;
  
  // set steps for interpolation
  int numSteps;
  float sizeSteps;
  
  // boolean for interpolation
  boolean ip;
  
  // creating interpolator
  // need value of point and number of steps
  // calculates out the step size and normalization values
  Interpolator(float p, int steps){
    val = p;
    numSteps = steps;
    sizeSteps = 1.0 / (float) numSteps;
    float k = 0.0;
    for(int j = 0; j <= numSteps; j++)
      k += normF((float) j * sizeSteps);
    norm = 1.0 / k;
    i = 0;
  }
  
  // for a default number of steps
  Interpolator(float p){
    this(p, 20);
  }
  
  // function to calculate normalization
  float normF(float x){
    return 4.0 * x * (1.0 - x);
  }
  
  // update interpolator
  void update(){
    if(ip){
      val += normF((float) i * sizeSteps) * norm * (end - beg);
      i++;
      
      // turn off interpolation if end point is reached
      if(i > numSteps)
        disable();
    }
  }
  
  // return the current point value
  float value(){
    return val;
  }
  
  // return if actively interpolating or not
  boolean active(){
    return ip;
  }
  
  // when enabling interpolation to a point
  void enable(float p){
    beg = val;
    i = 0;
    ip = true;
    end = p;
  }
  
  // when disabling interpolation
  void disable(){
    ip = false;
  }
}
