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
 * @param 
 * @returns (pointer) to the hashmap
*/
hashmap* map_create(int size) {
	if (size < 1) {
		return NULL;
	}

	// create the MAP
	hashmap* newMap = malloc(sizeof(hashmap));
	newMap->size = size;

	// create an array
    newMap->contents = (h_node **)malloc(size * sizeof(h_node *));

	// initialize everything to NULL
	for (int i = 0; i < size; i++) {
		newMap->contents[i] = NULL;
	}

	return newMap;
}

/**
 * Gets a value from the hashmap. 
 * If a value is not found, return -1.0F.
 * 
 * @returns (float) the value of the key
 * @returns (float) -1.0F if not found
*/
float map_get(hashmap* map, char *key) {

	if (map == NULL || key == NULL) {
		return -1.0F;
	}
	
	int index = get_hash(key) % map->size;
	
	// case 1. location is empty
	if (map->contents[index] == NULL) {
		return -1.0F;
	}
	// case 2. location has something but not the same key
	else if (map->contents[index]->key != key) {

		for (h_node* current_bucket = map->contents[index]; 
		current_bucket != NULL; 
		current_bucket = current_bucket->next) 
		{
			if(strcmp(current_bucket->key, key) == 0) {
				return current_bucket->value;
			}
		}

		// as a while loop
		/* while (current_bucket->next != NULL) {
			if(strcmp(current_bucket->key, key) == 0) {
				return current_bucket->value;
			}
			current_bucket = current_bucket->next;
		}
		// after current reaches the end
		// check if that is the 
		if(strcmp(current_bucket->key, key) == 0) {
			return current_bucket->value;
		} */

	}
	// case 3. location has the same key
	else if (strcmp(map->contents[index]->key, key) == 0) {
		return map->contents[index]->value;
	}
	return -1.0F;
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
	int index = get_hash(key) % map->size;
	float value;

	// Check if the bucket is empty
    if (map->contents[index] == NULL) {
        return -1.0F;
    }

    // Check if the first node matches the key
    if (strcmp(map->contents[index]->key, key) == 0) {
        h_node* temp = map->contents[index];
        value = temp->value;
        map->contents[index] = map->contents[index]->next;
        free(temp->key);
        free(temp);
        return value;
    }

    // Search for the key in the linked list
    h_node* current_bucket = map->contents[index];
    while (current_bucket->next != NULL) {
        if (strcmp(current_bucket->next->key, key) == 0) {
            h_node* temp = current_bucket->next;
            value = temp->value;
            current_bucket->next = current_bucket->next->next;
            free(temp->key);
            free(temp);
            return value;
        }
        current_bucket = current_bucket->next;
    }

    // Key not found
    return -1.0F;
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
	int index = get_hash(key) % map->size;

	// create the node
	h_node* new_node = malloc(sizeof(h_node));
	new_node->next = NULL;
	new_node->key = strdup(key);
	new_node->value = value;

	// case 1. index is empty, key is not in the map
	if (map->contents[index] == NULL) {
		// put the pointer to there
		map->contents[index] = new_node;
	}

	// case 2. COLLISION
	// 2.1 if key already exists at index location
	else if (strcmp(map->contents[index]->key, key) == 0) {
		// update the value
		map->contents[index]->value = value;
		return;
	}

	// 2.2 if key at index is not the same as we are inserting
	// traverse until either key is found or we reach the end
	else {
		for (h_node* current_bucket = map->contents[index]; 
		current_bucket != NULL; 
		current_bucket = current_bucket->next) {
			// if a key matches the one we are adding
			if (strcmp(current_bucket->key, key) == 0) {
                current_bucket->value = value;
				free(new_node->key); free(new_node); return;
            }

			// if no key matched after the last node,
			if (current_bucket->next == NULL) {
				current_bucket->next = new_node;
				return;
			}
			/*
			// whhile loop option
 			// set the current node
			h_node* current = map->contents[index];

			// move to next
			while (current->next != NULL) {
                if (strcmp(current->key, key) == 0) {
                    current->value = value;
					free(new_node->key); free(new_node); return;
            	}
                else {
				    current = current->next;
                }
			}
			
			// after traversing to the last node
			// if the last node has the same key, update
			if (strcmp(current->key, key) == 0) {
                current->value = value;
				free(new_node->key); free(new_node); return;
            } */

			/* 	
			// NOTE: This code adds to the front instead of back
	// get index
	int index = get_hash(key) % map->size;

	// create the node
	h_node* new_node = malloc(sizeof(h_node));
	new_node->key = key;
	new_node->value = value;

	// check if key already exists
	h_node* current = map->contents[index];
	if (current == NULL || strcmp(current->key, key) != 0) {
		// key does not exist, add new node
		new_node->next = map->contents[index];
		map->contents[index] = new_node;
		return;
	}

	// key exists, update value
	current->value = value;
	return; */
		}
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
	printf("{");
    for (int i = 0; i < map->size; i++) {
        h_node* current = map->contents[i];
        while (current != NULL) {
            printf("%s : %.2f", current->key, current->value);
            current = current->next;
            if (current != NULL) {
                printf(", ");
            }
        }
    }
	printf("}\n");
}

/**
 * Frees the map in memory. Make sure
 * to free all the individual nodes. 
*/
void map_free(hashmap* map) {
    for (int i = 0; i < map->size; i++) {
        h_node* current_bucket = map->contents[i];
        while (current_bucket != NULL) {
            h_node* temp_item = current_bucket->next;
            free(current_bucket->key);
            free(current_bucket);
            current_bucket = temp_item;
        }
    }
    free(map->contents);
    free(map);
}

#endif