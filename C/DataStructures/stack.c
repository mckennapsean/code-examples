// by Leon Tabak
// implementation of a stack

#include <stdio.h>
#include <stdlib.h>

struct node {
  int value;
  struct node* next;
} ;

typedef struct node Node;
typedef struct node* NodePointer;

NodePointer top = NULL;

int isEmpty() {
  return top == NULL;
} // isEmpty()

void push( int n ) {
  NodePointer np = (NodePointer) malloc( sizeof(Node) );
  np->value = n;
  np->next = top;

  top = np;
} // push( int )

void main() {

  printf( "Is stack empty? %d\n", isEmpty() );

  push( 1 );
  push( 2 );
  push( 3 );

  printf( "Is stack empty? %d\n", isEmpty() );

  NodePointer np = top;
  while( np != NULL ) {
    printf( "%d\n", np->value );
    np = np->next;
  } // while

} // main()
