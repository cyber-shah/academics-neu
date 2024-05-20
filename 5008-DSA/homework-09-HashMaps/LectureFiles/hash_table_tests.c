/**
 * CC-BY - Albert Lionelle
 **/

#include "hash_table.h"


int main() {

    hashTable tbl = create();
    add(tbl, "Princess Bride", "Buttercup");
    add(tbl, "Dread Pirate Roberts", "Wesley");
    print_table(tbl);

    printf("the princess bride is: %s\n", get(tbl, "Princess Bride"));
    printf("%s\n", get(tbl, "Man in black"));
    
    free_table(tbl);
    
    return 0;
}