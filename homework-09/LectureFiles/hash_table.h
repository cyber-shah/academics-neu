/** 
 * Simple hash table in C (partial implementation)
 * CC-BY - Albert Lionelle
*/
#ifndef HASH_TABLE
#define HASH_TABLE

#include <stdlib.h>
#include <string.h>
#include <stdio.h>

#define SIZE 100

typedef struct node {
    char *key;
    char *value;
    struct node *next;
} h_node;

typedef unsigned long ul;
typedef h_node **hashTable;


ul get_hash(char *str) {
    ul hash = 5381;
    int c;
    while ((c = *str++)) hash = ((hash << 5) + hash) + c;
    return hash;
}

hashTable create() {
    return (hashTable) malloc(sizeof(h_node)*SIZE);
}

char* get(hashTable tbl, char *key) {
    int hash = get_hash(key) % SIZE;
    if(tbl[hash] == NULL) {
        return NULL;
    }else {
        h_node *cur = tbl[hash];
        while(cur != NULL) {
            if(strcmp(cur->key, key) == 0) return cur->value;
            cur = cur->next;
        }
        return NULL;
    }
}

void add(hashTable tbl, char *key, char *value) {
    int hash = get_hash(key) % SIZE;

    h_node *new_node = (h_node*) malloc(sizeof(h_node));
    new_node->key = key;
    new_node->value = value;
    new_node->next = NULL;

    if(tbl[hash] == NULL) {
        tbl[hash] = new_node;
    }else {
        h_node *cur = tbl[hash];
        while(cur->next != NULL) cur = cur->next;
        cur->next = new_node;
    }

}

void print_table(hashTable tbl) {
    printf("{");
    int print_once = 0;
    for(int i = 0; i < SIZE; i++) {
        if(tbl[i] != NULL) {
            h_node *cur = tbl[i];
            while(cur != NULL) {
                if(print_once) printf(", ");
                printf("%s : %s", cur->key, cur->value);
                cur = cur->next;
                if(print_once == 0) print_once = 1;
            }
        }
    }
    printf("}\n");
}

void free_table(hashTable tbl) {
    for(int i = 0; i < SIZE; i++) {
        h_node *cur = tbl[i];
        while(cur != NULL) {
            h_node *tmp = cur->next;
            free(cur);
            cur = tmp;
        }
    }
    free(tbl);
}


#endif