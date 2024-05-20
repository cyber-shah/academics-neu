#ifndef MYDLL_H
#define MYDLL_H

#include <stdlib.h>

/* Create a node data structure to store data within
 our DLL. In our case, we will stores 'integers'*/
typedef struct node
{
    int data;
    struct node* next;
    struct node* previous;
} node_t;

/* Create a DLL data structure
 Our DLL holds a pointer to the first node in our DLL called head,
 and a pointer to the last node in our DLL called tail.*/
typedef struct DLL
{
    int count;    // count keeps track of how many items are in the DLL.
    node_t* head; // head points to the first node in our DLL.
    node_t* tail; // tail points to the last node in our DLL.
} dll_t;

/* Creates a DLL
 Returns a pointer to a newly created DLL.
 The DLL should be initialized with data on the heap.
 (Think about what the means in terms of memory allocation)
 The DLLs fields should also be initialized to default values.
 Returns NULL if we could not allocate memory.*/
dll_t *create_dll()
{
    dll_t* myDLL = malloc(sizeof(dll_t));
    myDLL->count = 0;
    myDLL->head = NULL;
    myDLL->tail = NULL;
    return myDLL;
}

node_t* create_node(int item) {
    node_t* new_node = malloc(sizeof(node_t));
    new_node->data = item;
    new_node->previous = NULL;
    new_node->next = NULL;
    return new_node;
}

/* DLL Empty
 Check if the DLL is empty
 Returns -1 if the dll is NULL.
 Returns 1 if true (The DLL is completely empty)
 Returns 0 if false (the DLL has at least one element enqueued)*/
int dll_empty(dll_t* l)
{
    if (l == NULL) {
        return -1;
    }
    else if (l->count == 0) {
        return 1;
    }
    else {
        return 0;
    }
}

/* push a new item to the front of the DLL ( before the first node in the list).
 Returns -1 if DLL is NULL.
 Returns 1 on success
 Returns 0 on failure ( i.e. we couldn't allocate memory for the new node)
 (i.e. the memory allocation for a new node failed).*/
int dll_push_front(dll_t* l, int item)
{
    if (l == NULL) {
        return -1;
    }

    node_t* new_node = create_node(item);
    if (new_node == NULL) {
        return 0;
    }

    if (l->head == NULL) {
        l->head = new_node;
        l->tail = new_node;
    }
    else {
        node_t* temp_node = l->head;
        l->head = new_node;
        l->head->next = temp_node;
        l->head->previous = NULL;
        temp_node->previous = l->head;
    }
    l->count++;
    return 1;
}

/* push a new item to the end of the DLL (after the last node in the list).
 Returns -1 if DLL is NULL.
 Returns 1 on success
 Returns 0 on failure ( i.e. we couldn't allocate memory for the new node)
 (i.e. the memory allocation for a new node failed).*/
int dll_push_back(dll_t* l, int item)
{
    if (l == NULL) {
        return -1;
    }
    node_t* new_node = create_node(item);
    if (new_node == NULL) {
        return 0;
    }

    if (l->tail == NULL) {
        l->tail = new_node;
        l->head = new_node;
    }
    else
    {
        node_t* temp_node = l->tail;
        l->tail = new_node;
        l->tail->previous = temp_node;
        l->tail->next = NULL;
        temp_node->next = l->tail;
    }

    l->count++;
    return 1;
}

/* Returns the first item in the DLL and also removes it from the list.
 Returns -1 if the DLL is NULL.
 Returns 0 on failure, i.e. there is nothing to pop from the list.
 Assume no negative numbers in the list or the number zero. */
int dll_pop_front(dll_t *t)
{
    // If DLL is null
    if (t == NULL) {
        return -1;
    }

    // If no head
    if (t->head == NULL) {
        return 0;
    }

    int item = t->head->data;

    // If only one item in the list
    if (t->head->next == NULL) {
        free(t->head);
        t->head = NULL;
        t->tail = NULL;
        t->count--;
        return item;
    }
    else {
        node_t *old_head = t->head;
        t->head = t->head->next;
        t->head->previous = NULL;
        free(old_head);
        t->count--;
        return item;
    }
}

/* Returns the last item in the DLL, and also removes it from the list.
 Returns -1 if the DLL is NULL.
 Returns 0 on failure, i.e. there is nothing to pop from the list.
 Assume no negative numbers in the list or the number zero. */
int dll_pop_back(dll_t *t)
{
    // If DLL is null
    if (t == NULL) {
        return -1;
    }

    // If there is no tail
    if (t->tail == NULL) {
        return 0;
    }

    int item = t->tail->data;

    // Only one node in the list
    if (t->tail->previous == NULL) {
        free(t->tail);
        t->tail = NULL;
        t->head = NULL;
        t->count--;
        return item;
    }
    else {
        node_t *old_tail = t->tail;
        t->tail = t->tail->previous;
        t->tail->next = NULL;
        free(old_tail);
        t->count--;
        return item;
    }
}

/* Inserts a new node before the node at the specified position.
 Returns -1 if the list is NULL
 Returns 1 on success
 Retruns 0 on failure:
  * we couldn't allocate memory for the new node
  * we tried to insert at a negative location.
  * we tried to insert past the size of the list
   (inserting at the size should be equivalent as calling push_back).*/
int dll_insert(dll_t *l, int pos, int item)
{
    node_t* new_node = create_node(item);

    if (l == NULL) {
        return -1;
    }
    else if (pos > l->count || pos < 0 || new_node == NULL) {
        return 0;
    }

    if (pos == 0) {
        dll_push_front(l, item);
        return 1;
    }
    else if (pos == l->count) {
        dll_push_back(l, item);
        return 1;
    }
    else {
        node_t* current_node = l->head;
        for (int i = 1; i < pos; i++) {
            current_node = current_node->next;
        }
        node_t* temp_node = current_node->next; // inserting at this position
        // change new node's pointers
        new_node->previous = current_node;
        new_node->next = temp_node;
        // change the pointers of nodes inside the list
        temp_node->previous = new_node;
        current_node->next = new_node;
        l->count++;
        return 1;
    }
}

/* Returns the item at position pos starting at 0 ( 0 being the first item )
 Returns -1 if the list is NULL
  (does not remove the item)
 Returns 0 on failure:
  * we tried to get at a negative location.
  * we tried to get past the size of the list
 Assume no negative numbers in the list or the number zero.*/
int dll_get(dll_t *l, int pos)
{
    if (l == NULL) {
        return -1;
    }
    else if (pos >= l->count || pos < 0) {
        return 0;
    }

    node_t* temp_node = l->head;
    for (int i = 0; i < pos; i++) {
        temp_node = temp_node->next;
    }
    return temp_node->data;
}

/* Removes the item at position pos starting at 0 ( 0 being the first item )
 Returns -1 if the list is NULL
 Returns 0 on failure:
  * we tried to remove at a negative location.
  * we tried to remove get past the size of the list
 Assume no negative numbers in the list or the number zero.
 Otherwise returns the value of the node removed.*/
int dll_remove(dll_t *l, int pos)
{
    // if l is NULL
    if (l == NULL) {
        return -1;
    }
        // if position is negative or greater than size
    else if (pos > (l->count - 1) || pos < 0) {
        return 0;
    }
    // set current node to head
    node_t* current_node = l->head;

    // if trying to remove the first node/head
    if (pos == 0) {
        return dll_pop_front(l);
    }
    else if (pos == l->count-1) {
        return dll_pop_back(l);
    }

    // for anything except head
    for (int i = 1; i <= pos; i++) {
        current_node = current_node->next;
    }
    current_node->previous->next = current_node->next;
    current_node->next->previous = current_node->previous;
    int item = current_node -> data;
    free(current_node);
    l->count--;
    return item;
}

/* DLL Size
 Returns -1 if the DLL is NULL.
 Queries the current size of a DLL*/
int dll_size(dll_t* t)
{
    if (t == NULL) {
        return -1;
    }
    return (t->count);
}

/* Free DLL
 Removes a DLL and all of its elements from memory.
 This should be called before the program terminates.*/
void free_dll(dll_t *t)
{
    while (t->head != NULL) {
        node_t* temp_node = t->head;
        t->head = t->head->next;
        if (t->head != NULL) {
            t->head->previous = NULL;
        }
        free(temp_node);
    }
    t->head = NULL;
    t->tail = NULL;
    t->count = 0;
    free(t);
}


#endif
