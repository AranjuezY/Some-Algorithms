class Solution:
    """
    @param color: the given color
    @return: a 7 character color that is most similar to the given color
	相似的RGB颜色
    """
    def similarRGB(self, color):
        red=int(color[1:3],16)
        green=int(color[3:5],16)
        black=int(color[5:7],16)
        r=round(red/17)*17
        g=round(green/17)*17
        b=round(black/17)*17
        return '#%02x%02x%02x' % (r, g, b)
		
	"""
    @param words: a list of string
    @return: a boolean
	有效单词词广场
    """
    def validWordSquare(self, words):
        for i in range(len(words)):
            for j in range(len(words[i])):
                if words[i][j] != words[j][i]:
                    return False
        return True
		
	"""
    @param s: A string
    @return: whether the string is a valid parentheses
	有效的括号序列
    """
    def isValidParentheses(self, s):
        stack = []
        for ch in s:
            if ch == '{' or ch == '[' or ch == '(':
                stack.append(ch)
            else:
                if not stack:
                    return False
                if ch == ']' and stack[-1] != '[' or ch == ')' and stack[-1] != '(' or ch == '}' and stack[-1] != '{':
                    return False
                stack.pop()
        return not stack
		
	"""
    @param intervals: interval list.
    @return: A new interval list.
	合并区间
    """
	"""
	class Interval(object):
		def __init__(self, start, end):
        self.start = start
        self.end = end
	"""	
    def merge(self, intervals):
        intervals=sorted(intervals,key=lambda x:x.start)
        result=[]
        for interval in intervals:
            if len(result)==0 or result[-1].end<interval.start:
                result.append(interval)
            else:
                result[-1].end = max(result[-1].end,interval.end)
        return result
		
    """
    @param digits: a number represented as an array of digits
    @return: the result
	加一
    """
    def plusOne(self, digits):
        digits=list(reversed(digits))
        carry=1
        for i in range(len(digits)):
            summ=digits[i]+carry
            digits[i]=summ%10
            carry=int(summ/10)
        if carry>0:
            digits.append(carry)
        return list(reversed(digits))

    """
    @param N: The number of integers
    @return: The number of beautiful arrangements you can construct
	Beautiful Arrangement
    """
    def countArrangement(self, N):
        return self.dfs(set(), 0, N)
        
    def dfs(self, existingNums, pos, N):
        if len(existingNums) == N:
            return 1
        numOfSolutions=0
        for i in range(1,N+1):
            if i not in existingNums and ((pos+1)%i==0 or i%(pos+1)==0):
                existingNums.add(i)
                numOfSolutions += self.dfs(existingNums, pos + 1, N)
                existingNums.remove(i)
        return numOfSolutions

    """
    @param root: the given BST
    @param k: the given k
    @return: the kth smallest element in BST
	Kth Smallest Element in a BST
    """
    def kthSmallest(self, root, k):
        stack=[]
        while root:
            stack.append(root)
            root = root.left
        for i in range(k-1):
            if not stack:
                break
            node = stack[-1]
            if not node.right:
                node = stack.pop()
                while stack and stack[-1].right == node:
                    node = stack.pop()
            else:
                node = node.right
                while node:
                    stack.append(node)
                    node = node.left
        return stack[-1].val