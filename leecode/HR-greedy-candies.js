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
    
    /* 
    const difference = (a,b) => a - b;

    let prev = 0, current = 1;
    let candies = new Array(n);
    // start with 1 
    candies[prev] = 1;
    console.log(candies)
    
    for (let i = 0; current < n; i++) {
        let diff = difference(arr[current], arr[prev]);
        // if increasing
        
        if (diff > 0) {
            candies[current] = candies[prev] + 1;
        }

        // if decreasing
        else if (diff < 0) {
            let new_prev = prev; let new_current = current;

            if (candies[prev] == 1) {
            // if previous is 1
                while (diff <= 0 && ((candies[new_prev] == 1) || (candies[new_prev] == candies[new_current]))) {
                    candies[new_current] = candies[prev];
                    candies[new_prev] = candies[new_prev] + 1;
                    new_prev --;
                    new_current --;
                    diff = difference(arr[new_current], arr[new_prev]);
                    console.log("Inside the while loop");
                    console.log(candies);
                }
            }
            // once this is done
            candies[current] = 1;
        }

        else {
            if (candies[prev] == 1){
                candies[current] = candies[prev] + 1;
            }
            else {
                candies[current] = candies[prev] - 1;
            }
            
        }

        prev = current;
        current = current + 1;
        console.log("Finally=");
        console.log(candies)
    }

    return candies.reduce((a,b) => a+b); 
    */

    /*
    Attempt 2 :
    loop from left to right
    if current > prev
        candies[current] = candies[prev] + 1
    if current < prev
        candies[current] = candies[prev] - 1;

    
    loop from right to left
    if current < prev 
        candies 
    
    
    */

    // everyone gets one candy at least
    let candies = new Array(n).fill(1);
    let current = 1, prev = 0;

    for (var i = 1; i < n; i++) {
        if (arr[current] > arr[prev]) {
            candies[current] = candies[prev] + 1;
        }
        current ++, prev ++;
    }

    current = n - 2;
    prev = n - 1;
    for (var i = n - 1; i = 0; i --) {
        if (arr[current] > arr[prev] && candies[current] >! candies[prev]) {
            candies[current] = candies[prev] + 1;
        } 
    }
    
    return candies.reduce((aggregator, value) => aggregator +=value, 0);
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
