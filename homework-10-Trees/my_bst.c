/** Implementation of the BST data structure 
 * 
 * @author Pranchal Shah
 * Semester Summer 2023 Online
*/

#include "my_bst.h"

/**
 * Checks to see if the tree is empty
 * by looking at the tree size.
 * returns true if the tree is empty.
 * False otherwise.
*/
bool bst_is_empty(BST *tree) {
    if (tree->size < 1) {
        return false;
    } 
    else {
        return true;
    }
}

/**
 * Checks to see if the value exists in the tree.
 * returns true if the value exists in the tree.
 * False otherwise.
*/
bool bst_exists(BST *tree, int value)
{
    
    if (tree->root == NULL) {
        return false;
    }
    
    else {
        // 1. check root
        if (tree->root->data == value) {
            return true;
        }
        // 2. if not check if bigger or smaller than root
        // 3. iterate on left / right depending on 2. bst_exists_helper(node, value)
        else if (value < tree->root->data) {
            bst_exists_helper(tree->root->left, value);
        }
        else if (value > tree->root->data) {
            bst_exists_helper(tree->root->right, value);
        }
    }
    
    // 4. if you reach the end, return false
    // 5. true if you found the value somewhere
    return false;
}

bool bst_exists_helper(Node *current, int value) {
    if (current == NULL) {
        return false;
    }
    else if (current->data == value) {
        return true;
    }
    else if (value < current->data) {
        return bst_exists_helper(current->left, value);
    }
    else if (value > current->data) {
        return bst_exists_helper(current->right, value);
    }
    return false;
}

/**
 * Returns the size of the tree.
*/
unsigned int bst_size(BST *tree) {
    return tree->size;
}

/**
 * Adds a value to the tree.
 * returns 1 if the value was added successfully.
 * returns 0 if the value already exists in the tree.
 * returns -1 if the value could not be added due to errors. (malloc failed)
*/
int bst_add(BST *tree, int value) {
    Node *new_node = (Node *)malloc(sizeof(Node));

    // 0. check if tree is empty
    if (tree->root == NULL) {
        tree->root = new_node;
    }
    else {
        // 1. start traversing the tree
        Node *current = tree->root;
        if (current->data == value) {
            return 0;
        }
        // 2. find a location
        else if (value < current->data) {
            return bst_add_helper(current->left, value);
        }
        // 2. find a location
        else if (value > current->data) {
            return bst_add_helper(current->right, value);
        }
    }
}

int bst_add_helper(Node *current, int value) {
    // base case - next node is null
    // 3. place it there
    if (current == NULL) {
        Node *new_node = (Node *)malloc(sizeof(Node));
        new_node->data = value;
        current = new_node;
        // return 1 if successful
        return 1;
    }
    // if a duplicate data is found return 0
    else if (current->data == value) {
        return 0;
    }
    else if (value < current->data) {
        return bst_add_helper(current->left, value);
    }
    else if (value > current->data) {
        return bst_add_helper(current->right, value);
    }
}

/**
 * Frees the memory allocated for the tree.
 * hint: Think about which order works for traversal (pre, in, post) and how that
 * can help you free the memory
*/
void bst_free(BST *tree) {
    bst_free_helper(tree->root);
}

void bst_free_helper(Node *current) {
    if (current == NULL) {
        return;
    }
    // post order traversal
    // to ensure that the children are freed before the parent
    bst_free_helper(current->left);
    bst_free_helper(current->right);
    free(current);
}

/**
 * Creates a new BST.
 * returns a pointer to the new BST.
 * The root node will still be NULL until the first bst_add is called
*/
BST *create_bst() {
    BST *new_bst = (BST *)malloc(sizeof(BST));
    new_bst->root = NULL;
    new_bst->size = 0;
    return new_bst;
}
