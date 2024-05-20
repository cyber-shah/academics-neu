/** Implementation of BST utility functions
 *
 *  @author Pranchal Shah
 * Semester Summer 2023 Online
 *
*/

#include <stdio.h>
#include "my_bst_util.h"

/**
 * Returns the sum of all the values in the tree.
*/
int sum(BST *tree) {
    // 1. if tree is NULL or if root is NULL
    if (tree == NULL || tree->root == NULL) {
        return 0;
    }

    int sum = 0;
    // 2. start recursing
    return sum_helper(tree->root, sum);
}

/**
 * Helper function for sum
 *
 * @param current current node.
 * @param sum sum of all the values.
 * @return sum of all the values.
 */
int sum_helper (Node* current, int sum) {
    // 1. check if current is null, not found
    // base case
    if (current == NULL) {
        return sum;
    }

    // 2. add current value to sum
    sum += current->data;

    // 3. recurse on left and right
    sum = sum_helper(current->left, sum);
    sum = sum_helper(current->right, sum);

    // 4. return sum
    return sum;
}

/**
 * Returns the minimum value in the tree. 
*/
int min(BST *tree) {
    // 1. if tree is NULL or if root is NULL
    if (tree == NULL || tree->root == NULL) {
        return -1;
    }

    // 2. iterate on left until you reach the end
    Node *current = tree->root;
    while (current->left != NULL) {
        current = current->left;
    }

    // 3. return the value of the last node
    return current->data;
}


/**
 * Returns the maximum value in the tree.
*/
int max(BST *tree) {
    // 1. if tree is NULL or if root is NULL
    if (tree == NULL || tree->root == NULL) {
        return -1;
    }

    // 2. iterate on right until you reach the end
    Node *current = tree->root;
    while (current->right != NULL) {
        current = current->right;
    }

    // 3. return the value of the last node
    return current->data;
}
