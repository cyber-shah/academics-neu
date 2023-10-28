/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} head
 * @return {number}
 */
var getDecimalValueSlow = function(head) {
    let arr = [];
    let decimal = 0;


    let current = head;
    while (current != null) {
        arr.push(current.val);
        current = current.next;
    }

    arr.reverse();

    for (let i = 0; i < arr.length; i++) {
        let number = arr[i] * Math.pow(2, i);
        decimal += number;
    }
    console.log(arr);
    return decimal;
};




var getDecimalValue = function(head) {
    let decimal = 0;

    // O(n) time
    let i = -1;
    let current = head;
    while (current != null) {
        i++;
        current = current.next;
    }
    current = head;

    // O(n) time
    for (let j = i; j > -1; j--) {
        decimal += current.val * Math.pow(2, j);
        current = current.next;
    }


    return decimal;
};