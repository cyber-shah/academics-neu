{
    'use strict';

    const fs = require('fs');

    process.stdin.resume();
    process.stdin.setEncoding('utf-8');

    let inputString = '';
    let currentLine = 0;

    process.stdin.on('data', function (inputStdin) {
        inputString += inputStdin;
    });

    process.stdin.on('end', function () {
        inputString = inputString.split('\n');

        main();
    });

    function readLine() {
        return inputString[currentLine++];
    }
}

/*
 * Complete the 'candies' function below.
 *
 * The function is expected to return a LONG_INTEGER.
 * The function accepts following parameters:
 *  1. INTEGER n = number of children in class
 *  2. INTEGER_ARRAY arr = rating of each student
 */

function candies(n, arr) {
    /**
     * [3,4]            [1,2]
     * [3,4,3]          [1,2,1]
     * 
     * [3,4,3,2]        [1,2,1,0]
     * [3,4,3,2]        [1,2,2,0]
     * ...
     * [3,4,3,2]        [1,3,2,1]
     * 
     * [3,4,3,2,1]      [1,4,3,2,1]
     * [3,4,3,2,1,4]    [1,4,3,2,1,2]
     * 
     * 
     * 
     * difference(a, b) will return the difference
     * 
     * start with a = 0, b = 1
     * if difference(a, b) > 0 
     *      then candy[b] = candy[a] + 1
     *      a = b 
     *      b = b + 1
     * else if difference(a, b) < 0
     *      if candy[a] - 1 == 0
     *          while (difference(a,b) < 0) 
     *              candy[a] = candy[a] + 1
     *              candy[b] = candy[a] - 1
     *              b = a
     *              a = a - 1
     *      once done with if and while
     *      candy[b] = candy[a] - 1
     */
    
        
    const difference = (a,b) => a - b;

    let prev = 0, current = 1;
    let candies = new Array(n);
    // start with 1 
    candies[prev] = 1;

    for (let i = 0; current < n; i++) {
        let diff = difference(arr[prev], arr[current]);
        // if increasing
        
        if (diff > 0) {
            candies[current] = candies[prev] + 1;
        }

        // if decreasing
        else {
            // if previous is 1
            if (candies[prev] == 1) {
                // let new_current = prev;
                // let new_prev = prev - 1;
                // // go back until the diff is increasing
                // while (diff <= 0) {
                //     // move back
                //     new_current = new_prev;
                //     new_prev = new_prev - 1;
                //     // add one to both
                //     candies[new_current] = candies[new_prev];
                //     candies[new_prev] = candies[new_prev] + 1;
                    
                //     diff = difference(arr[prev], arr[current]);
                // }
            }
            // once this is done
            candies[current] = candies[prev] - 1;
        }

        prev = current;
        current = current + 1;
    }

    return candies.reduce((a,b) => a+b);
}

function main() {
    const ws = fs.createWriteStream(process.env.OUTPUT_PATH);

    const n = parseInt(readLine().trim(), 10);

    let arr = [];

    for (let i = 0; i < n; i++) {
        const arrItem = parseInt(readLine().trim(), 10);
        arr.push(arrItem);
    }

    const result = candies(n, arr);

    ws.write(result + '\n');

    ws.end();
}
