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
