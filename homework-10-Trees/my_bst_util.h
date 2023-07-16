/**
 * Some standard utility functions for binary search trees.
 * @author Pranchal Shah
 * Semester Summer 2023 Online
 */
#ifndef MYBSTUTIL_H
#define MYBSTUTIL_H

#include <stdlib.h>
#include "my_bst.h"

int sum(BST* tree);
int min(BST* tree);
int max(BST* tree);
int sum_helper (Node* current, int sum);


#endif