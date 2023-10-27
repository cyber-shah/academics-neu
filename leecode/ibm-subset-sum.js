/* 
1. Array Subsets
Given an integer array, divide the array into 2 subsets A and B while respecting the following conditions:
The intersection of A and B is null.
• The union A and B is equal to the original array.
• The number of elements in subset A is minimal.
• The sum of A's elements is greater than the sum of B's elements.
Return the subset A in increasing order where the sum of A's elements is greater than the sum of
B's elements. If more than one subset exists, return the one with the maximal sum. 
*/
{
    'use strict';

    const fs = require('fs');

    process.stdin.resume();
    process.stdin.setEncoding('utf-8');

    let inputString = '';
    let currentLine = 0;

    process.stdin.on('data', function(inputStdin) {
        inputString += inputStdin;
    });

    process.stdin.on('end', function() {
        inputString = inputString.split('\n');

        main();
    });

    function readLine() {
        return inputString[currentLine++];
    }
}

/*
 * Complete the 'subsetA' function below.
 *
 * The function is expected to return an INTEGER_ARRAY.
 * The function accepts INTEGER_ARRAY arr as parameter.
 */

function subsetA(arr) {
    // Write your code here
    arr.sort((a, b) => a - b);

    let totalSum = arr.reduce((a, b) => a + b, 0);
    let A = [];
    let B = [];
    let sumA = 0;
    let sumB = totalSum;

    // Iterate over the sorted array
    for (let i = arr.length - 1; i >= 0; i--) {
        if (sumA + arr[i] <= sumB) {
            A.push(arr[i]);
            sumA += arr[i];
            sumB -= arr[i];
        } else {
            B.push(arr[i]);
        }
    }
    
    // Sort A in ascending order and return it
    console.log("A = " + A);
    console.log("Original =" + arr);
    console.log(sumA +", " + sumB);
    
    // Sort A in ascending order and return it
    A.sort((a, b) => a - b);
    return A;

}

function main() {
    const ws = fs.createWriteStream(process.env.OUTPUT_PATH);

    const arrCount = parseInt(readLine().trim(), 10);

    let arr = [];

    for (let i = 0; i < arrCount; i++) {
        const arrItem = parseInt(readLine().trim(), 10);
        arr.push(arrItem);
    }

    const result = subsetA(arr);

    ws.write(result.join('\n') + '\n');

    ws.end();
}
