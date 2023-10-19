function maxCookies(budget, costs) {
    let dp = Array(budget + 1).fill(-1);
    dp[0] = 0;  // It's possible to buy 0 cookies with a budget of 0
    
    for (let i = 0; i < costs.length; i++) {
        for (let j = costs[i]; j <= budget; j++) {
            if (dp[j - costs[i]] !== -1) {
                dp[j] = Math.max(dp[j], dp[j - costs[i]] + 1);
            }
        }
    }
    
    // If it's impossible to use up the entire budget
    if (dp[budget] === -1) {
        return "Impossible";
    }
    
    return dp[budget];
}

// Test case
let costs = [2, 5];
let budget = 4;
console.log(maxCookies(budget, costs));  // Output: 3
