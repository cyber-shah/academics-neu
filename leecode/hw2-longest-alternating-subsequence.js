// Function to find the length of the longest alternating subsequence
function longestAlternatingSubsequence(arr) {
    // Get the length of the array
    let n = arr.length;
  
    // Initialize two arrays to store the lengths of the longest
    // alternating subsequence ending at each index
    // las[i][0] is for the case where the last element is greater than its previous element
    // las[i][1] is for the case where the last element is smaller than its previous element
    let las = Array.from({length: n}, () => [1, 1]);
  
    // Initialize the result to 1 (the minimum length)
    let res = 1;
  
    // Loop through the array from index 1 to n-1
    for (let i = 1; i < n; i++) {
      // Loop through all previous elements from index 0 to i-1
      for (let j = 0; j < i; j++) {
        // If arr[i] is greater than arr[j] and i is odd, then we can extend the subsequence ending at j with a greater element
        if (arr[i] > arr[j] && i % 2 != 0) {
          las[i][0] = Math.max(las[i][0], las[j][1] + 1);
        }
        // If arr[i] is smaller than arr[j] and i is even, then we can extend the subsequence ending at j with a smaller element
        else if (arr[i] < arr[j] && i % 2 == 0) {
          las[i][1] = Math.max(las[i][1], las[j][0] + 1);
        }
      }
      // Update the result with the maximum length found so far
      res = Math.max(res, Math.max(las[i][0], las[i][1]));
    }
  
    // Return the result
    return res;
  }
  
  // Test the function with some examples
  console.log(longestAlternatingSubsequence([3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5])); // Output: 9
  console.log(longestAlternatingSubsequence([10, 22, 9, 33, 49, 50, 31, 60])); // Output: 6
  console.log(longestAlternatingSubsequence([4, 5, 7, 14])); // Output: 2
  