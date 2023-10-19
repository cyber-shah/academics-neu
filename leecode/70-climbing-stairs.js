let dpTable = new Array(100).fill(-1);

/**
 * @param {number} n
 * @return {number}
 */
var climbStairs = function(n) {
  // two ways to climb stair - 1 step or 2 steps
  // in how many ways can you climb the stair?
  
  /*
  Part 1 - Base case:
    eventually we know that if there is one step, there is only one way to climb, 
    and if there are two steps, we have two ways to climb. 
    therefore, we have  n[1] = 1
                        n[2] = 2

    Part 2 - Choice Diagram
        So lets say we begin with the last step, we have two options, 
        either take one step    = n - 1
        or take two steps       = n - 2
    
    Part 3 - Deriving the recursion
        From the choice diagram we can say that for each step i,
        we wish to calculate both (i-1) and (i-2)
        then answer will be (i-1) + (i-2)
    */

    if (n <= 2) {
        return n;
    }

    if (dpTable[n] != -1) {
        return dpTable[n];
    }
    else { 
        // either take one step or two steps
        // and add them because thats the total number of options
        dpTable[n] = climbStairs(n - 1) + climbStairs(n - 2);
    }

    return dpTable[n];
}


/**
 * @param {number} n
 * @return {number}
 */
var climbStairsIterative = function(n) {
    let dpTable = new Array(n);
  
    dpTable[0] = 0;
    dpTable[1] = 1;
    dpTable[2] = 2;
    
    for (let i = 3; i <= n; i++) {
        dpTable[i] = dpTable[i - 1] + dpTable[i - 2];
    }
  
    return dpTable[n];
  }