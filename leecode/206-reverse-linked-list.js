/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} head
 * @return {ListNode}
 */
var reverseList = function(head) {
    /* This is pretty slow! O(n^2)
        1. create a new linked list
        2. iterate through the original linked list
                go to the end of the original linked list
        3. add each node to the front of the new linked list
        4. return the new linked list 
    */

    /* The kid's hands approach O(n) 
        A faster way is to just change pointers ~
        Imagine a line of kids holding hands. 
        If you want to reverse the line, you just have to change the direction of the kids' hands!
    */

    let current = head;
    let prev = null;
    let next = null;

    // iterate till you reach the end
    while (current !== null) {
        // save the next node
        next = current.next;
        current.next = prev;
        
        // then we want to iterate to the next node, which is next node we saved earlier
        current = next;
        // and then we want to set the previous node to the current node
        prev = current;
    }
};