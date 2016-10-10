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
