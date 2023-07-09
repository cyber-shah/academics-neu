/** 
 * CS 5008 - Homework 09
 * Student: Pranchal Shah
 * Semester: Summer 2023 Online
*/
#ifndef HASHMAP
#define HASHMAP

#include <stdlib.h>
#include <string.h>
#include <stdio.h>

typedef struct node {
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
 * 
 * @return (unsigned long) representing the hash
*/
ul get_hash(char *str) {
  ul hash = 5381;

  for (int i = 0; i < strlen(str); i++) {
	hash = ((hash << 5) + hash) + str[i];
  }

/*   int c;

	while ((c = *str++)) {
		hash = ((hash << 5) + hash) + c; // hash * 33 + c
	} 
*/
  return hash;
}

/**
 * Creates with the specified size.
 *
 * @returns (pointer) to the hashmap
*/
hashmap* map_create(int size) {
   hashmap* hashMap = malloc(sizeof(h_node) * size);
   return hashMap;
} 

/**
 * Gets a value from the hashmap. 
 * If a value is not found, return -1.0F.
 * 
 * @returns (float) the value of the key
 * @returns (float) -1.0F if not found
*/
float map_get(hashmap* map, char *key) {

	ul index = get_hash(key);
	
	// case 1. location is empty
	if (map->contents[index] == NULL) {
		return -1.0F;
	}
	// case 2. location has something but not the same key
	else if (map->contents[index]->key != key) {
		h_node* current = map->contents[index];
		while (current->next != NULL) {
			if(strcmp(current->key, key) == 0) {
				return current->value;
			}
			current = current->next;
		}

	}
	// case 3. location has the same key
	else if (strcmp(map->contents[index]->key, key) == 0) {
		return map->contents[index]->value;
	}
}

/**
 * Removes an item from the hashmap, returning
 * the value of the item. If an item
 * is not found to remove, return -1.0F
 * 
 * Remember to free the key value, before free the node.
 * 
 * @returns (float) the value of the key if found
 * @returns (float) -1.0F if not found
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
	// get index
	ul index = get_hash(key);

	// create the node
	h_node* new_node = malloc(sizeof(h_node));
	new_node->next = NULL;
	new_node->key = key;
	new_node->value = value;
	
	// case 1. index is empty
	if (map->contents[index] == NULL) {
		// if yes malloc a new h_node and 
		// put the pointer to it there
	}
	// case 2. index is not empty so there is a collision
	else if (map->contents[index] != NULL) {
		h_node* current = map->contents[index];
		while (current->next != NULL) {
			current = current->next;
		}
		current->next = new_node;
	}
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