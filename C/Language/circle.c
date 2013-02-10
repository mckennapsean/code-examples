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
