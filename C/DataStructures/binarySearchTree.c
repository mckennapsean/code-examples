// by Sean McKenna
// inspired by Leon Tabak
// implementation of binary search tree

#include <stdio.h>
#include <stdlib.h>

// create a squared function
#define SQR(x) x * x

// define each node of the tree
struct node{
  int val;
  struct node* left;
  struct node* right;
};

typedef struct node Node;
typedef struct node* NodePointer;

// initialize base variables for the tree
NodePointer root = NULL;
int size = 0;

// adding a node onto the tree
NodePointer add(int val, NodePointer current){
  
  // creating a new node
  if(current == NULL){
    current = (NodePointer) malloc(sizeof(Node));
    current->val = val;
    current->left = NULL;
    current->right = NULL;
    size = size + 1;
  
  // all other nodes on a tree
  // create & set a new current node (linking to previous one)
  }else{
    if(val < current->val){
      current->left = add(val, current->left);
    }else if(val > current->val){
      current->right = add(val, current->right);
    }
  }
  
  // send back the new current node
  return current;
}

// removing a node from the tree
NodePointer delete(int val, NodePointer current){
  if(current == NULL){
    // no node to remove!
  }
  
  // determine which sub-tree to remove the node from
  if(val < current->val){
    current->left = delete(val, current->left);
  }else if(val > current->val){
    current->right = delete(val, current->right);
  }else{
    
    // move the sub-tree appropriately, depending on existence & size
    if((current->left != NULL) && (current->right != NULL)){
      int new = min(current->right);
      current->right = delete(new, current->right);
      current->val = new;
    }else if(current->left == NULL){
      NodePointer new = current->right;
      free(current);
      current = new;
      size--;
    }else if(current->right == NULL){
      NodePointer new = current->left;
      free(current);
      current = new;
      size--;
    }else{
      free(current);
      current = NULL;
      size--;
    }
  }
  
  // send back the new current node
  return current;
}

// determine the minimum value of the tree
int min(NodePointer current){
  
  // undefined for an empty tree
  if(current == NULL){
    return -1;
  
  // otherwise, get the minimum value
  }else if(current->left == NULL){
    return current->val;
  }else{
    return min(current->left);
  }
}

// determine the maximum value of the tree
int max(NodePointer current){
  
  // undefined for an empty tree
  if(current == NULL){
    return -1;
  
  // otherwise, get the maximum value
  }else if(current->right == NULL){
    return current->val;
  }else{
    return max(current->right);
  }
}

// determine if a value is a member of the tree or not
int isMember(int val, NodePointer current){
  if(current == NULL){
    return 0;
  }else if(current->val == val){
    return 1;
  }else if(val < current->val){
    return isMember(val, current->left);
  }else{
    return isMember(val, current->right);
  }
}

// print out the tree (from some position down)
void printInOrder(NodePointer current){
  
  // do not print when at the end of a sub-tree
  if(current == NULL){
    return;
  }
  
  // otherwise print the sub-tree
  printInOrder(current->left);
  printf("%d, ", current->val);
  printInOrder(current->right);
}

// print out the tree / sub-tree by the selected value, then left, then right
void printPreOrder(NodePointer current){
  
  // do not print when at the end of a sub-tree
  if(current == NULL){
    return;
  }
  
  // otherwise print the sub-tree in the appropriate order
  printf("%d, ", current->val);
  printPreOrder(current->left);
  printPreOrder(current->right);
}

// print out the tree / sub-tree by the left, right, and then selected value
void printPostOrder(NodePointer current){
  
  // do not print when at the end of a sub-tree
  if(current == NULL){
    return;
  }
  
  // otherwise print the sub-tree in the appropriate order
  printPostOrder(current->left);
  printPostOrder(current->right);
  printf("%d, ", current->val);
}

// conduct the main test of the binary search tree
void main(){
  
  // fill up binary tree
  root = add(5, root);
  add(2, root);
  add(11, root);
  add(4, root);
  add(1, root);
  add(8, root);
  add(3, root);
  
  // delete nodes from the binary tree
  // uncomment to adjust tree for the methods below!
  //root = delete(5, root);
  //root = delete(2, root);
  //root = delete(3, root);
  //root = delete(8, root);
  //root = delete(11, root);
  //root = delete(1, root);
  //root = delete(4, root);
  
  // check different methods of the binary tree
  printf("11?: %d | ", isMember(11, root));
  printf("2?: %d | ", isMember(2, root));
  printf("14?: %d | ", isMember(14, root));
  printf("max?: %d | ", max(root));
  printf("min?: %d | ", min(root));
  printf("size: %d\n", size);
  
  printPreOrder(root);
  printf("\n");
  
  printf("Also, for fun, this method says 2 x 2 = %d \n", SQR(2));
}
