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
// implementation of a stack

#include <stdio.h>
#include <stdlib.h>

struct node{
  int val;
  struct node* next;
};

typedef struct node Node;
typedef struct node* NodePointer;

NodePointer top = NULL;

// test if the stack is empty
int isEmpty() {
  return top == NULL;
}

// add a number to the stack
void push(int num){
  NodePointer np = (NodePointer) malloc(sizeof(Node));
  np->val = num;
  np->next = top;
  top = np;
}

// test the stack implementation
void main(){
  
  // is the stack empty?
  printf("Is stack empty? %d\n", isEmpty());
  
  // add some numbers to the stack
  push(1);
  push(2);
  push(3);
  
  // now see if the stack is empty?
  printf("Is stack empty? %d\n", isEmpty());
  
  // and then print off the stack
  NodePointer np = top;
  while(np != NULL){
    printf("%d\n", np->val);
    np = np->next;
  }
}
