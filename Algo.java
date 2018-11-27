//
public class Algo {
    
	
	/**
     * @param x: An integer
     * @param y: An integer
     * @return: The sum of x and y 
	 *  位运算实现加法
     */
    public int xplusy(int x, int y) {
        int sum = x^y;
        int carry = (x&y)<<1;
        while(carry != 0){
			int a = sum;
			int b = carry;
			sum = a ^ b;
			carry = (a & b) << 1;  
		}
		return sum;
    }
	
	
	/**
     * @param : An integer
     * @param : An integer
     * @return: An integer denote the count of digit k in 1..n
	 * 统计数字
     */
    public int digitCounts(int k, int n) {
        int count = 0;
        for(int i=0; i<=n; i++){
            int num = i;
            while(num/10 != 0){
                if(num%10 == k) {
                    count++;
                } 
                num = num/10;
            }
            if(num == k) {
                count++;
            }
        }
        return count;
    }
	

    /**
     * @param k : description of k
     * @param nums : array of nums
     * @return: description of return
	 * 第k大元素 快速排序递归
     */
    public int kthLargestElement(int k, int[] nums) {
        return ksort(nums,0,nums.length-1,k);
    }
	//递归的关键：参数和返回值的传递
	private int ksort(int[] nums,int left,int right,int k){
		if(left >= right) return nums[right];
		int j = partition(nums,left,right);
		if(j == k-1){
			return nums[j];
		}else 
		if(j < k-1){
			return ksort(nums,j+1,right,k);
		}else {
			return ksort(nums,left,j-1,k);
		}
	}
	private int partition(int[] nums,int left,int right){
		int i = left;
		int j = right+1;
		int cmp = nums[left];
		int a;
		
		while(true){
			while(nums[++i] > cmp) if(i == right) break;
			while(nums[--j] < cmp) if(j == left) break;
			if (i >= j) break;
			a = nums[i];
			nums[i] = nums[j];
			nums[j] = a;
		}
		a = nums[left];
		nums[left] = nums[j];
		nums[j] = a;
		return j;
	}
	
	
	
	/**
	 * public class TreeNode {
	 *     public int val;
	 *     public TreeNode left, right;
	 *     public TreeNode(int val) {
	 *         this.val = val;
	 *         this.left = this.right = null;
	 *     }
	 * }
	 */


    /**
     * @param root: param root: The root of the binary search tree
     * @param k1: An integer
     * @param k2: An integer
     * @return: return: Return all keys that k1<=key<=k2 in ascending order
	 * 二叉查找树中搜索区间 中序遍历
     */
    public List<Integer> searchRange(TreeNode root, int k1, int k2) {
        List<Integer> result = new ArrayList<>();
		sth(root, k1, k2, result);
		return result;
	}
	
	private sth(TreeNode root, int k1, int k2, List<Integer> res){
		if (root == null) return;
		else
		if(root.val >= k1 && root.val <= k2){
			sth(root.left, k1, k2, res);
			res.add(root.val);
			sth(root.right, k1, k2, res);
		}else
		if(root.val < k1){
			sth(root.right, k1, k2, res);
		}else {
			sth(root.left, k1, k2, res);
		}
	}
	
	
	
	/**
     * @param amount: a total amount of money amount
     * @param coins: the denomination of each coin
     * @return: the number of combinations that make up the amount
	 * 零钱兑换 动态规划
     */
    public int change(int amount, int[] coins) {
        // write your code here
        int[] dp = new int[amount+1];
        dp[0]=1;
        for(int i = 0; i < coins.length; i++) {
            for(int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }

	
	//带最小值操作的栈
	public class MinStack {
		private Stack<Integer> stack;
		private Stack<Integer> minStack;
		public MinStack() {
        stack = new Stack<Integer>();
        minStack = new Stack<Integer>();
		}

		/**
		 * @param number: An integer
		 * @return: nothing
		 */
		public void push(int number) {
			stack.push(number);
			if(minStack.isEmpty()){
				minStack.push(number);
			}else if(number <= minStack.peek()){
				minStack.push(number);
			}
		}

		/**
		 * @return: An integer
		 */
		public int pop() {
			int p = stack.pop();
			if(p == minStack.peek()){
				minStack.pop();
			}
			return p;
		}

		/**
		 * @return: An integer
		 */
		public int min() {
			return minStack.peek();
		}
	}
	
	
	
    /*
     * @param source: source string to be scanned.
     * @param target: target string containing the sequence of characters to match
     * @return: a index to the first occurrence of target in source, or -1  if target is not part of source.
	 * 字符串查找 暴力方法
     */
    public int strStr(String source, String target) {
        // write your code here
        if(target==null||source==null)  {  
            return -1;  
        }  
        for(int i=0; i<=source.length()-target.length(); i++){
            int flag = 1;
            for(int j=0; j<target.length(); j++){
                if(target.charAt(j) != source.charAt(i+j)){
                    flag = -1;
                    break;
                }
            }
            if(flag == 1) return i;
        }
        return -1;
    }	
	
	
	
	public class Solution {
    /**
     * @param head: n
     * @return: The new head of reversed linked list.
	 * 原地翻转链表
     */
    public ListNode reverse(ListNode head) {
        // write your code here
        if(head == null) return head;
        
        ListNode p = head;
        ListNode q = head.next;
        ListNode t = null;
        
        p.next = null;
        while(q != null){
            t = q.next;
            q.next = p;
            p = q;
            q = t;
        }
        return p;
    }
		
	//计数回文子序列	
	public int countPalindSubseq(String str) {
        if(str == null || str.isEmpty()) return 0;
        int n = str.length();
        int[][] dp = new int[n][n];
        for(int len=1;len<=n;len++){
            for(int i=0;i+len-1 < n;i++){
                int j = i+len-1;
                if(i == j){
                    dp[i][j] = 1;
                }
                else if(str.charAt(i)!=str.charAt(j)){
                    dp[i][j] = dp[i+1][j] + dp[i][j-1] - dp[i+1][j-1];
                }
                else{
                    int left = i+1;
                    int right = j-1;
                    while(left <= right && str.charAt(left) != str.charAt(i)) left++;
                    while(left <= right && str.charAt(right) != str.charAt(i)) right--;
                    if(left == right){
                        dp[i][j] = dp[i+1][j-1]*2 + 1;
                    }
                    else if(left > right){
                        dp[i][j] = dp[i+1][j-1]*2 + 2;
                    }
                    else{
                        dp[i][j] = dp[i+1][j-1]*2 - dp[left+1][right-1];
                    }
                }
                if(dp[i][j] < 0){
                    //overflow
                    dp[i][j] += 1000000007;
                }else{
                    dp[i][j] %= 1000000007;
                }
            }
        }
        return dp[0][n-1];
    }
		
		
	/**
     * @param s: the given string
     * @return: all the possible states of the string after one valid move
     */
	//翻转游戏
public List<String> generatePossibleNextMoves(String s) {
    List<String> list = new ArrayList<>();
    if (list == null) {
        return list;
    }
    for (int i = 0; i < s.length() - 1; i++) {
        if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
            list.add(s.substring(0, i) + "--" + s.substring(i + 2, s.length()));
        }
    }
    return list;
}
}

	
	
string main(string s)
{
    int len = s.size();
    if (len == 1)return s;
    int longest = 1;
    int start=0;
    vector<vector<int> > dp(len,vector<int>(len));
    for (int i = 0; i < len; i++)
    {
        dp[i][i] = 1;
        if(i<len-1)
        {
            if (s[i] == s[i + 1])
            {
                dp[i][i + 1] = 1;
                start=i;
                longest=2;
            }
        }
    }
    for (int l = 3; l <= len; l++)
    {
        for (int i = 0; i+l-1 < len; i++)
        {
            int j=l+i-1;
            if (s[i] == s[j] && dp[i+1][j-1]==1)
            {
                dp[i][j] = 1;
                start=i;
                longest = l;
            }
        }
    }
    return s.substr(start,longest);
}

