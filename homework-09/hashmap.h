/** 
 * CS 5008 - Homework 09
 * Student: UPDATE NAME
 * Semester: UPDATE SEMESTER
*/
#ifndef HASHMAP
#define HASHMAP

#include <stdlib.h>
#include <string.h>
#include <stdio.h>

typedef struct node
{
    char *key;
    float value;
    struct node *next;
} h_node;

typedef struct ht {
    int size;
    h_node **contents;

} hashmap;

typedef unsigned long ul;
typedef h_node **hashTable;

/**
 * A hashing algorithm. Students may pick their own
 * but it is recommended they use one of the ones 
 * from the lab. 
*/
ul get_hash(char *str) {
  return 0;
}

/**
 * Creates with the specified size.
*/
hashmap* map_create(int size) {
   return NULL;
}

/**
 * Gets a value from the hashmap. 
 * If a value is not found, return -1.0F
*/
float map_get(hashmap* map, char *key) {
    return 0;
}

/**
 * Removes an item from the hashmap, returning
 * the value of the item. If an item
 * is not found to remove, return -1.0F
 * 
 * Remember to free the key value, before free the node.
*/
float map_del(hashmap* map, char *key) {
    return 0;
}

/**
 * Stores a value into the hashmap. 
 * 1. if a key is already in the map, overwrites it with the new value
 * 2. if the key is not in the map, adds the key/value (node) pair.
 * 
 * For Keys, you want ot use strcpy to copy the key into the node, so that
 * the original string passed into the function can be released.
*/
void map_put(hashmap* map, char *key, float value) {
    
}

/**
 * Prints the map in the specified format of
 * {key : value, key : value}
 * Notice there is not a comma after the last
 * value. Refer to lab if needed. It should
 * only produce strings of .2f (two decimals). 
*/
void map_print(hashmap* map) {
   
}

/**
 * Frees the map in memory. Make sure
 * to free all the individual nodes. 
*/
void map_free(hashmap* map) {

}


#endif