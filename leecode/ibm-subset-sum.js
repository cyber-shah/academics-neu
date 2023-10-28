
/**
 * IBM Coding Challenge

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

/* This is too long, time out error
1. Sort the array in decreasing order
2. Initialize two empty subsets, A and B
3. Iterate over the sorted array
4. Check if the sum of A’s elements is greater than the sum of B’s elements: 
    4.1 If not, move elements from B to A until this condition is met.
5. Sort subset A in increasing order
*/
function subsetAIterative(arr) {
    arr.sort((a,b) => b - a);
    console.log(arr);
    // two subsets A and B
    let A = [];
    let B = [...arr];
    
    // sums to store the totals
    let sumA = 0;
    let sumB = arr.reduce((a, b) => a + b, 0);
    
    // for every item in array
    for (let i = 0; i < arr.length; i++) {
        // keep adding to A until the sumA > sumB
        if (sumA > sumB) {
            break;
        }
        else {
            A.push(arr[i]);
            sumA += arr[i];
            B.splice(B.indexOf(arr[i], 1));
            sumB -= arr[i];
        }
    }
    
    // debug statements
    // console.log("A = " + A);
    // console.log("sumA = " + sumA);
    // console.log("sumB = " + sumB);
    return A.sort((a, b) => a - b);

}


function subsetA(arr) {
    
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
