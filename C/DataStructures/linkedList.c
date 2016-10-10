// linked List implementation in C
// takes over screen with optional functions and a view of the linked list.

#include <stdio.h>
#include <stdlib.h>

// Create a linked list structure (Node)
struct node{
  int value;
  struct node* next;
  struct node* prev;
};
typedef struct node Node;
typedef struct node* NodePtr;

// Initialize blank lists
NodePtr head = NULL;
NodePtr tail = NULL;
int length = 0;

// NULL state
void removeAll(){
  head = NULL;
  tail = NULL;
  length = 0;
}

// Return 1 if we have blank list
int isEmpty(){
  if(length < 1)
    return 1;
  else
    return 0;
}

// Add to top of stack (push)
void addFirst(int val){
  NodePtr np = (NodePtr) malloc(sizeof(Node));
  np->prev = NULL;
  np->next = head;
  np->value = val;

  if(head != NULL)
    head->prev = np;
  else
    tail = np;
  head = np;
  length++;
}

// Add to bottom of stack (push)
void addLast(int val){
  NodePtr np = (NodePtr) malloc(sizeof(Node));
  np->next = NULL;
  np->prev = tail;
  np->value = val;

  if(tail != NULL)
    tail->next = np;
  else
    head = np;
  tail = np;
  length++;
}

// Remove from top of stack (pop)
void removeFirst(){
  if(length > 1){
    NodePtr tmp = head->next;
    free(head);
    head = tmp;
    head->prev = NULL;
    length -= 1;
  }else{
    removeAll();
  }
}

// Remove from bottom of stack (pop)
void removeLast(){
  if(length > 1){
    NodePtr tmp = tail->prev;
    free(tail);
    tail = tmp;
    tail->next = NULL;
    length -= 1;
  }else{
    removeAll();
  }
}

// Print out linked list (head in front)
void print(){
  printf("(");
  NodePtr p = head;
  while(p != NULL){
    if(p->next != NULL)
      printf("%d, ", p->value);
    else
      printf("%d", p->value);
    p = p->next;
  }
  printf(")\n\n");
}

// Clear the entire list
void clearList(){
  while(length > 1){
    removeFirst();
  }
  removeAll();
}

// Linked list implementation interface
void main(){
  // Initialize variables
  int mode = 1;
  char input[256];
  int add = 0;

  // Present options (clearing screen) each time
  while(mode != 8){
    system("clear");
    printf("Please choose an option for the linked list: \n");
    printf("  1. Add value\n  2. Add value to end\n  3. Remove value\n  4. Remove value from end\n");
    printf("  5. Clear all values\n  6. Test if empty\n  7. Length of list\n  8. Exit Program\n\n");

    // Display the most recent list
    printf("Current list: ");
    print();

    // Carry-over action for easy display
    switch(mode){
      // Inform user of successful clearing
      case 5:
        printf("The list has been cleared.\n\n");
        break;

      // Test list if empty
      case 6:
        if(isEmpty() == 1)
          printf("The list is currently empty.\n\n");
        else
          printf("The list is not empty.\n\n");
        break;

      // Print out length of list
      case 7:
        printf("Length: %d\n\n", length);
        break;

      // Do nothing
      default:
        break;
    }

    // Display option input
    printf("Option: ");
    scanf("%d", &mode);

    // Conduct user's action
    switch(mode){
      // Add a value to beginning of list
      case 1:
        printf("\nPlease enter an integer to add to the list: ");
        scanf("%s", input);
        add = atoi(input);
        addFirst(add);
        printf("\n");
        break;

      // Add a value to the end of the list
      case 2:
        printf("\nPlease enter an integer to add to the end of the list: ");
        scanf("%s", input);
        add = atoi(input);
        addLast(add);
        printf("\n");
        break;

      // Remove value from beginning of list
      case 3:
        removeFirst();
        break;

      // Remove value from end of list
      case 4:
        removeLast();
        break;

      // Clear all values in the list
      case 5:
        clearList();
        break;

      // Do nothing, tell user on next iteration
      case 6:
        break;

      // Do nothing, print length on next iteration
      case 7:
        break;

      // Do nothing, will exit program
      case 8:
        break;

      // Print out input warning
      default:
        printf("That is not a valid menu option. To quit, type '8'.\n");
        break;
    }
  }

  // Reset terminal window
  system("clear");
}
