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
    /*
        start with arr[i] at 0, with 1 candy
        if arr[i + 1] > arr[i] {
            then candy[i + 1] = candy[i] + 1
        }
        keep doing this recursively until you get an increasing streak
        else if arr[i+1] < arr[i] {
            if (candy[i] - 1 == 0) {
                candy[i] = candy[i] + 1;
            }
            else { 
                candy[i + 1] = candy[i] - 1
            }
        }
    */
    
    
    /**
     * [3,4]            [1,2]
     * [3,4,3]          [1,2,1]    
     * [3,4,3,2]        [1,3,2,1]
     * [3,4,3,2,1]      [1,4,3,2,1]
     * [3,4,3,2,1,4]    [1,4,3,2,1,2]
     * 
     * 
     * if next (score) < prev (score)
     *      if prev (score) 
     *      DECREASE the candy given by 1
     * else if next (score) > prev (score)
     *      INCREASE the candy given by 1
     * 
     */
    

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
