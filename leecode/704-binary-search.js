/**
 * Recursive approach
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var recursiveSearch = function(nums, target) {
    // variables to split the array into 2
    let start = 0;
    let end = nums.length-1;
    let mid = Math.floor((start+end)/2);
    
    // base cases
    if (nums.length === 0) return -1;
    
    if (nums.length === 1) {
        if (nums[0] === target) {
            return 0;
        } else {
            return -1;
        }
    }

    while (start < end) {
        // if target is at the mid index
        if (nums[mid] === target) {
            return mid;
        } 
        // if the target is to the left of the mid
        else if (nums[mid] > target) {
            return search(nums.slice(start, mid), target);
        }
        // if the target is to the right of the mid
        else if (nums[mid] < target) {
            let returnValue = search(nums.slice(mid+1, end+1), target);
            if (returnValue === -1) {
                return -1;
            }
            else {
                // return original `mid` + 1 + the index of the target
                return mid + 1 + returnValue;
            }
        }
    }
};


// Pointer approach (iterative)
var pointerSearch = function(nums, target) {
    let start = 0;
    let end = nums.length-1;

    while (start <= end) {
        const mid = Math.floor((start+end)/2);

        if (nums[mid] === target)
            return mid;
        // if target is to the left of mid
        else if (nums[mid] > target)
            end = mid-1;
        // if target is right of mid
        else if (nums[mid] < target)
            start = mid+1;
    }

    return -1;
};




// Pointer approach (iterative)
var fastPointerSearch = function(nums, target) {
    let start = 0;
    let end = nums.length-1;

    while (start <= end) {
        const mid = Math.floor((start+end)/2);
        const midValue = nums[mid];

        if (midValue === target)
            return mid;
        // if target is to the left of mid
        else if (midValue > target)
            end = mid-1;
        // if target is right of mid
        else
            start = mid+1;
    }

    return -1;

};


// lets test this out
let nums = [2,5];
let target = 0;
console.log(search(nums, target));