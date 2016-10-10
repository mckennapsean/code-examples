// outputs points around unit circle
// takes input of how many equi-distant points

#include <stdio.h>
#include <math.h>

void main(){

  // get the number of points
  int pts;
  printf("How many points in the circle? ");
  scanf("%d", &pts);

  // set constants & initial angle
  double pi = M_PI;
  double ang = 2.0 * pi / ((double) pts);

  // generate points around the circle
  int i;
  for(i=0; i<pts; i++){

    // change angle around circle
    double ang2 = ang + ang*i;

    // get the coordinates
    double x = cos(ang2);
    double y = sin(ang2);

    // output the coordintes
    printf("(%f, %f)\n", x, y);
  }
}
