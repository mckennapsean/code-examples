// by Sean McKenna
// inspired by Leon Tabak
// macros and console output types

#include <stdio.h>

// create some macros / functions
#define YEAR 2012
#define METRIC(N) 2 * N + 30

// run main macro test
void main(){
  
  // print out the macro for the year
  printf("This collection of samples came together in %d\n", YEAR);
  
  // print out the macro method / function
  // this is a joke from Bob & Doug McKenzie
  printf( "Double it and add 30: 31 goes to %d\n", METRIC(31));
}
