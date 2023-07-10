#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#include "random_key_gen.c"

// redefine the types for convenience
typedef unsigned long ul;

static int collisions = 0;

ul djb2Hash(char* str) {
    ul hash = 5381;
    int c;
    // TODO : understand how this is diff from multiplying
    while ((c = *str++)) {
        hash = ((hash << 5) + hash) + c; /* hash * 33 + c */
    }
    return hash;
}



int main() {
    srand(time(NULL));

    char* my_test = random_string(5);
    printf("Random String: %s \n", my_test);

    shuffle_string(my_test);
    printf("Shuffled String: %s \n", my_test);

    ul hash = djb2Hash(my_test);
    printf("Hashed: %lu \n", hash);

    int tmp[500];
    for(int i = 0; i < 100; i++) {
        // generate a random string of length 5
        char* my_string = random_string(5);

        // create a hash of that string
        // mod it by 500 to get a number between 0 and 499
        // this is the index of the array we will put the key in
        ul hash = djb2Hash(my_string) % 500;
        
        // put 1 to tmp at index hash, indicating that 
        // a key has been put there

        // if that location has 1 already, that means there is a collision
        if(tmp[hash] == 1) collisions++;
        else tmp[hash] = 1;

        shuffle_string(my_string);
        hash = djb2Hash(my_string) % 500;
        if (tmp[hash] == 1)
            collisions++;
        else
            tmp[hash] = 1;
    }
    printf("Collisions is %d\n", collisions);

    return 0;
}
