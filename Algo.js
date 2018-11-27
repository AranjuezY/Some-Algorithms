/**
 * @param s: input string
 * @return: the longest palindromic substring
 */
 //最长回文子串
const longestPalindrome = function (s) {
    let dp = new Array(s.length), max = [0,0];
    for(let i = s.length-1; i>=0; i--){
        dp[i] = new Array(s.length-i);
        for(let j = i; j<s.length; j++){
            if(i === j){
                dp[i][j] = 1;
            }
            else{
                dp[i][j] = (s[i] === s[j]) && (dp[i+1][j-1] || j===i+1)
            }
            
            if(dp[i][j] && j+1-i>max[1]-max[0]){
                max = [i, j+1];
            }
        }
    }
    
    return s.substring(max[0], max[1]);
}

/**
 * @param s: the given string
 * @return: if a permutation of the string could form a palindrome
 */
 //回文排列
const canPermutePalindrome = function (s) {
    s=s.split('')
    m=new Map()
    s.map((c)=>{
        if(!m.has(c)) {
	    	m.set(c,1)
    	}else {
	    	m.set(c,m.get(c)+1)
	    }
	return m
    })
    var count=0
    m.forEach((v)=>{count= count+ v%2})
    return count<=1
}

/**
 * @param grid: a list of lists of integers
 * @return: An integer, minimizes the sum of all numbers along its path
 */
 //最小路径和
const minPathSum = function (grid) {
    var m = grid.length, n = grid[0].length
    for(let i=0; i<m; i++) {
        for(let j=0; j<n; j++){
            if(i===0 && j>0) {grid[0][j] += grid[0][j-1]}
            if(i>0 && j===0) {grid[i][0] += grid[i-1][0]}
            if(i>0 && j>0) {grid[i][j] += Math.min(grid[i-1][j],grid[i][j-1])}
        }
    }
    return grid[m-1][n-1]
}

/**
 * @param n: An integer
 * @return: An integer
 */
 //爬楼梯
const climbStairs = function (n) {
    var a=0, b=1, i=0
    if (n===0) return 0
    while(i<n) {
        let c=b
        b=a+b
        a=c
        i++
    }
    return b
}

/**
 * @param n: a positive integer
 * @param primes: the given prime list
 * @return: the nth super ugly number
 */
 //超级丑数
const nthSuperUglyNumber = function (n, primes) {
    var num = new Array(n).fill(0)
    var times = new Array(primes.length).fill(0)
	num[0] = 1
	for (let i=1; i<n; i++) {
		var min = Number.MAX_VALUE
		for (let j=0; j<primes.length; j++) {
			min = Math.min(min, primes[j]*num[times[j]])
		}
		num[i] = min
		for (let j=0; j<times.length; j++) {
			if (primes[j]*num[times[j]] === min) times[j]++
		}
	}
	return num[n-1]
}

/**
 * @param matrix: the given matrix
 * @return: True if and only if the matrix is Toeplitz
 */
 //托普利兹矩阵
const isToeplitzMatrix = function (matrix) {
    let M=matrix.length, N=matrix[0].length
    for(let i=1; i<M; i++) {
        for(let j=1; j<N; j++) {
            if (matrix[i][j] != matrix[i-1][j-1]) {
                return false
            }
        }
    }
    return true
}