/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @return {TreeNode}
 */
var invertTreeRecursive = function(root) {
    /*     
    1. start at root, swap left and right
    2. recurse on left, till you reach the end
    3. recurse on right, till you reach the end
    3. return root 
    */
    // base case
    if (root === null) return null;

    // recursive case
    let temp = root.left;
    root.left = root.right;
    root.right = temp;

    invertTree(root.left);
    invertTree(root.right);
    return root;
};




/**
 * @param {TreeNode} root
 * @return {TreeNode}
 */
var invertTreeIterative = function(root) {

    // uses a queue structure - first in first out to
    // iterate over the tree

    if (root === null) {
        return null;
    }
    
    // create a new queue 
    let queue = [root];
    let current;

    // as far as there are elements in the queue
    while (queue.length > 0) {
        // get the first element in queue
        current = queue.shift();

        let temp = left;
        // Akshay Kumar flip
        current.left = right;
        current.right = temp;

        // add both left and right into the queue
        if (current.left != null) { 
            queue.push(current.left);
        }
        if (current.right != null) {
            queue.push(current.right);
        }
    }
    return root;
};
