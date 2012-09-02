// by Leon Tabak
// implementation of binary search tree

#include <stdio.h>
#include <stdlib.h>

#define SQR(x) x * x

struct node{
    int value;
    struct node* left;
    struct node* right;
};

typedef struct node Node;
typedef struct node* NodePointer;


NodePointer root = NULL;
int size = 0;

NodePointer add(int val, NodePointer cur){
	if(cur == NULL){
		cur = (NodePointer) malloc(sizeof(Node));
		cur->value = val;
		cur->left = NULL;
		cur->right = NULL;
		size = size + 1;
	}else{
		if(val < cur->value){
			cur->left = add(val, cur->left);
		}else if(val > cur->value){
			cur->right = add(val, cur->right);
		}
		//Ignore duplicate values.
	}
	return cur;
}

NodePointer delete(int val, NodePointer cur){
	if(cur == NULL){
        //val wasn't in tree to begin with.
    }
	
	if( val < cur->value){
		cur->left = delete(val, cur->left);
	}else if(val > cur->value){
		cur->right = delete(val, cur->right);
	}else if(val == cur->value){
		if( (cur->left != NULL) && (cur->right != NULL) ){
			int successor = min(cur->right);
			cur->right = delete(successor,cur->right);
			cur->value = successor;
		}else if(cur->left == NULL){
			NodePointer successor = cur->right;
			free(cur);
			cur = successor;
			size--;
		}else if(cur->right == NULL){
			NodePointer successor = cur->left;
			free(cur);
			cur = successor;
			size--;
		}else{
			free(cur);
			cur = NULL;
			size--;
		}
	}else{
		printf("shouldn't see this.");
    }
	return cur;
}


int min(NodePointer cur){
    if(cur == NULL){
        //Error: No minimum of an empty tree.
        return -1;
    }else if(cur->left == NULL){
        return cur->value;
    }else{
        return min(cur->left);
    }
}

int max(NodePointer cur){
    if(cur == NULL){
        //Error: No minimum of an empty tree.
        return -1;
    }else if(cur->right == NULL){
        return cur->value;
    }else{
        return max(cur->right);
    }
}

int isMember(int val, NodePointer cur){
    if(cur == NULL){
        return 0;
    }else if(cur->value == val){
        return 1;
    }else if(val < cur->value){
        return isMember(val,cur->left);
    }else if(val > cur->value){
        return isMember(val,cur->right);
    }else{
		printf("shouldn't see this.");
        return 0;
    }
}

void printInOrder(NodePointer cur){
    if(cur == NULL){
        return;
    }
    printInOrder(cur->left);
    printf("%d, ",cur->value);
    printInOrder(cur->right);
}

void printPreOrder(NodePointer cur){
    if(cur == NULL){
        return;
    }
    printf("%d, ",cur->value);
    printPreOrder(cur->left);
    printPreOrder(cur->right);
}

void printPostOrder(NodePointer cur){
    if(cur == NULL){
        return;
    }
    printPostOrder(cur->left);
    printPostOrder(cur->right);
    printf("%d, ",cur->value);
}

void main(){
    root = add(5,root);
    add(2,root);
    add(11,root);
    add(4,root);
    add(1,root);
	add(8,root);
    add(3,root);

	root = delete(5,root);
	root = delete(2,root);
	root = delete(3,root);
	root = delete(8,root);
	root = delete(11,root);
	root = delete(1,root);
	root = delete(4,root);

	printf("11?:%d | ",isMember(11,root));
	printf("2?:%d | ",isMember(2,root));
	printf("14?:%d | ",isMember(14,root));
	printf("max?:%d | ",max(root));
	printf("min?:%d\n",min(root));

	printf("size: %d\n",size);

    printPreOrder(root);
    printf("\n");

    printf( "Leon says 2 x 2 = %d\n", SQR(2) );
}
