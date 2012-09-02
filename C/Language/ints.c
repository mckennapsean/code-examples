// by Sean McKenna
// test array functions and output for integers

#include <stdio.h>

void main(){
  int data[] = {0, 1, 2, 3, 4, 5, 6};

  printf("%u\n", (unsigned) data);
  printf("%u\n", (unsigned) (data+1));

  double numbers[] = {0.57721566, 1.61803398, 2.71828183, 3.1415965};

  printf("%u\n", (unsigned) numbers);
  printf("%u\n", (unsigned) (numbers+1));
}
