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
