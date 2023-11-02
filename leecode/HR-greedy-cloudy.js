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
 * Complete the 'maximumPeople' function below.
 *
 * The function is expected to return a LONG_INTEGER.
 * The function accepts following parameters:
 *  1. LONG_INTEGER_ARRAY p
 *  2. LONG_INTEGER_ARRAY x
 *  3. LONG_INTEGER_ARRAY y
 *  4. LONG_INTEGER_ARRAY r
 */

function maximumPeople(p, x, y, r) {
    // Return the maximum number of people that will be in a sunny town after removing exactly one cloud.

}

function main() {
    const ws = fs.createWriteStream(process.env.OUTPUT_PATH);

    const n = parseInt(readLine().trim(), 10);

    const p = readLine().replace(/\s+$/g, '').split(' ').map(pTemp => parseInt(pTemp, 10));

    const x = readLine().replace(/\s+$/g, '').split(' ').map(xTemp => parseInt(xTemp, 10));

    const m = parseInt(readLine().trim(), 10);

    const y = readLine().replace(/\s+$/g, '').split(' ').map(yTemp => parseInt(yTemp, 10));

    const r = readLine().replace(/\s+$/g, '').split(' ').map(rTemp => parseInt(rTemp, 10));

    const result = maximumPeople(p, x, y, r);

    ws.write(result + '\n');

    ws.end();
}
