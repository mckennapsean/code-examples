// by Leon Tabak
// macros and console output types

#include <stdio.h>

#define YEAR 1859
#define DOUBLE(N) 2 * N

void main() {
  printf( "Riemann published his famous hypothesis in %d\n", YEAR );
  printf( "Twice %d is %d\n", 4, DOUBLE(4));
}
