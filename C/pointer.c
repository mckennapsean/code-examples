// illustrate pointers in C

#include <stdio.h>

int main(){
  // normal int
  int x;

  // pointer to int
  int *p;

  // x becomes p-address
  p = &x;

  // change var
  scanf("%d", &x);
  printf("%d\n", *p);
}
