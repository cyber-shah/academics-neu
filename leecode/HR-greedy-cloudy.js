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

function maximumPeople1(p, x, y, r) {
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

function maximumPeople(p, x, y, r) {
    // p = population of the town(p1, p2, ... , pi)
    // x = location of the town(x1, x2, ....., xi)
    // y = location of clouds(y1, y2, ......, yi)
    // r = range of clouds r_i is the range of cloud y_i
    let events = [];
    
    // put the towns first
    for (var i = 0; i < x.length; i++) {
        events.push({type : 'town', pos : x[i], index : i});
    }
    
    // then the clouds
    for (var i = 0; i < y.length; i++) {
        events.push({type : 'start', pos : y[i] - r[i], cloud : i});
        events.push({type : 'end', pos : y[i] + r[i], cloud : i});
    }
    
    // get the events first
    events.sort((a, b) => a.pos - b.pos || (a.type === 'town') - (b.type === 'town'));
    
    console.log(events);
    
    let cloud_count = 0;
    var active_cloud = 0;
    var cloud_score = new Array(y.length).fill(0);
    var sunny_population = 0;
    
    // for all events
    for (var i = 0; i < events.length; i++) {
        // if its a town
        if (events[i].type === 'town') {
            if (cloud_count === 1) {
                console.log("cloudcount is 1")
                cloud_score[active_cloud] += p[events[i].index];
            }
            else if (cloud_count === 0) {
                sunny_population += p[events[i].index];
                console.log("added sunny pop " + sunny_population);
            }
        }
        // if starts
        else if (events[i].type === 'start') {
            cloud_count++;
            if (cloud_count === 1) {
                active_cloud = events[i].cloud;
            }
        }
        // if ends
        else if (events[i].type === 'end') {
            cloud_count--;
            if (cloud_count === 0) {
                active_cloud = -1;
            }
        }
    }

    console.log(cloud_score);
    return sunny_population + Math.max(...cloud_score);
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
