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
    if (current->data == value) {
        return true;
    }
    else if (value < current->data) {
        bsl_exists_helper(current->left, value);
    }
    else if (value > current->data) {
        bst_exists_helper(current->right, value);
    }
}

/**
 * Returns the size of the tree.
*/
unsigned int bst_size(BST *tree) {
    return 0;
}

/**
 * Adds a value to the tree.
 * returns 1 if the value was added successfully.
 * returns 0 if the value already exists in the tree.
 * returns -1 if the value could not be added due to errors. (malloc failed)
*/
int bst_add(BST *tree, int value) {
    return 0;
}

/**
 * Frees the memory allocated for the tree.
 * hint: Think about which order works for traversal (pre, in, post) and how that
 * can help you free the memory
*/
void bst_free(BST *tree) {

}

/**
 * Creates a new BST.
 * returns a pointer to the new BST.
 * The root node will still be NULL until the first bst_add is called
*/
BST *create_bst() {
    return NULL;
}

