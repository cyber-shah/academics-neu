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
    // p = population of the town(p1, p2, ... , pi)
    // x = location of the town(x1, x2, ....., xi)
    // y = location of clouds(y1, y2, ......, yi)
    // r = range of clouds r_i is the range of cloud y_i
    let max_score = 0;
    let max_cloud = 0;

    // for all clouds [i]
    for (var i = 0; i < y.length; i++) {
        var start_cloud = y[i] - r[i]; 
        var end_cloud = y[i] + r[i];
        var current_score = 0;
        // for all towns [j]
        for (var j = 0; j < x.length ; j++) {
            console.log("comparing " + start_cloud + " " + x[j] + " " + end_cloud)
            if (start_cloud <= x[j] && x[j] <= end_cloud) {
                console.log("getting score, adding city " + j)
                current_score += p[j]
            }
        }
        if(current_score > max_score) {
            max_score = current_score;
            max_cloud = i;
            console.log("max score so far " + max_score);
            console.log("best cloud " + i)
        }
    }

    return max_score;
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
