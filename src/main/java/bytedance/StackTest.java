package bytedance;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

public class StackTest {


    /**
     * 有效的括号
     */
    class IsValid {
        // Hash table that takes care of the mappings.
        private HashMap<Character, Character> mappings;

        // Initialize hash map with mappings. This simply makes the code easier to read.
        public IsValid() {
            this.mappings = new HashMap<>();
            this.mappings.put(')', '(');
            this.mappings.put('}', '{');
            this.mappings.put(']', '[');
        }

        public boolean isValid(String s) {

            Stack<Character> stack = new Stack<Character>();

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (this.mappings.containsKey(c)) {

                    char topElement = stack.empty() ? '#' : stack.pop();
                    if (topElement != this.mappings.get(c)) {
                        return false;
                    }
                } else {
                    stack.push(c);
                }
            }
            return stack.isEmpty();
        }
    }








    /**
     * 柱状图中最大的矩形
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {

    }

    /**
     *
     */
    class RemoveDuplicates {

        public String removeDuplicates(String S) {


            Stack<Character> stack = new Stack<>();
            for (Character c : S.toCharArray()) {
                if (stack.isEmpty() || stack.peek() != c) {
                    stack.push(c);
                } else if (stack.peek() == c) {
                    stack.pop();
                }
            }

            StringBuilder sb = new StringBuilder();
            for (Character c : stack) {
                sb.append(c);
            }
            return sb.toString();
        }
    }

    /**
     * 删除最外层的括号
     */
    class RemoveOuterParentheses {

        public String removeOuterParentheses(String S) {

            StringBuilder sb = new StringBuilder();
            int level = 0;
            for (char c : S.toCharArray()) {
                if (c == ')') --level;
                if (level >= 1) sb.append(c);
                if (c == '(') ++level;
            }
            return sb.toString();
        }


        public String removeOuterParentheses2(String S) {

            StringBuffer buffer = new StringBuffer();
            int level = 0;

            for (Character c: S.toCharArray()){
                if (c == ')') level--;
                if (level >= 1) buffer.append(c);
                if (c == '(') level++;
            }

            return buffer.toString();

        }


    }


    /**
     * 链表中的下一个更大节点
     */
    class NextLargerNodes {
        public int[] nextLargerNodes(ListPractice.ListNode head) {

            if (head.next == null) {
                return new int[] {0};
            }
            Stack<Integer> input = new Stack<>();
            int size = 0;
            while (head != null) {
                size ++;
                input.push(head.data);
                head = head.next;
            }

            Stack<Integer> stack = new Stack<>();
            int[] result = new int[size];

            while (!input.isEmpty()) {
                size--;
                while (!stack.isEmpty() && input.peek() >= stack.peek()) {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    result[size] = 0;
                } else {
                    result[size] = stack.peek();
                }
                stack.push(input.pop());
            }
            return result;
        }
    }

    /**
     * 简化路径
     *
     * @param path
     * @return
     */
    public String simplifyPath(String path) {


        String[] str = path.split("/");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < str.length; i++) {
            if (!stack.isEmpty() && str[i].equals("..")) {
                stack.pop();
            } else if (!str[i].equals("") && !str[i].equals(".") && !str[i].equals("..")) {
                stack.push(str[i]);
            }
        }
        if (stack.isEmpty()) {
            return "/";
        }
        String ans = stack.stream().collect(Collectors.joining("/"));
        return "/" + ans;
    }


    /**
     * 验证栈序列
     *
     * @param pushed
     * @param popped
     * @return
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {

        if (pushed.length != popped.length) {
            return false;
        }
        int index = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < pushed.length; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && stack.peek() == popped[index]) {
                stack.pop();
                index++;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 使括号有效的最少添加
     */
    class MinAddToMakeValid{

        public int minAddToMakeValid(String S) {
            int ans = 0, bal = 0;
            for (int i = 0; i < S.length(); ++i) {

                bal += S.charAt(i) == '(' ? 1 : -1;
                if (bal == -1) {
                    ans++;
                    bal++;
                }
            }

            return ans + bal;
        }
    }



    /**
     * 子数组的最小值之和
     *
     * @param A
     * @return
     */
    public int sumSubarrayMins(int[] A) {
        int MOD = 1_000_000_007;

        Stack<RepInteger> stack = new Stack();
        int ans = 0, dot = 0;
        for (int j = 0; j < A.length; ++j) {
            // Add all answers for subarrays [i, j], i <= j
            int count = 1;
            while (!stack.isEmpty() && stack.peek().val >= A[j]) {
                RepInteger node = stack.pop();
                count += node.count;
                dot -= node.val * node.count;
            }
            stack.push(new RepInteger(A[j], count));
            dot += A[j] * count;
            ans += dot;
            ans %= MOD;
        }

        return ans;
    }

    class RepInteger {
        int val, count;

        RepInteger(int v, int c) {
            val = v;
            count = c;
        }
    }


    /**
     * @param S
     * @return
     */
    public int scoreOfParentheses(String S) {
        Stack<Integer> stack = new Stack();
        stack.push(0); // The score of the current frame

        for (char c : S.toCharArray()) {
            if (c == '(')
                stack.push(0);
            else {
                int v = stack.pop();
                int w = stack.pop();
                stack.push(w + Math.max(2 * v, 1));
            }
        }

        return stack.pop();
    }


    /**
     * 柱状图中最大的矩形
     */
    class LargestRectangleArea {

        public int largestRectangleArea(int[] heights) {

            Stack<Integer> stack = new Stack<>();
            stack.push(-1);
            int maxArea = 0;
            for (int i = 0; i < heights.length; i++) {
                // 栈顶元素不是第一个元素 -1 且数组呈下降关系时，什么时候结束呢？
                // 显然是当栈顶元素为 -1 或者 heights[i] ≥ heights[stack.peek()] 跳出循环直接压栈
                while (stack.peek() != -1 && heights[i] < heights[stack.peek()]) {
                    // 将栈中的序号弹出，计算最大面积
                    maxArea = Math.max(heights[stack.pop()] * (i - stack.peek() - 1), maxArea);
                }
                stack.push(i);
            }
            while (stack.peek() != -1) {
                maxArea = Math.max(heights[stack.pop()] * (heights.length - stack.peek() - 1), maxArea);
            }
            return maxArea;
        }
    }


    /**
     * 接雨水
     */
    class Trap {


        public int trap(int[] height) {
            int sum = 0;
            int max_left = 0;
            int max_right = 0;
            int left = 1;
            int right = height.length - 2; // 加右指针进去


            for (int i = 1; i < height.length - 1; i++) {
                //从左到右更
                if (height[left - 1] < height[right + 1]) {
                    max_left = Math.max(max_left, height[left - 1]);
                    int min = max_left;
                    if (min > height[left]) {
                        sum = sum + (min - height[left]);
                    }
                    left++;
                    //从右到左更
                } else {
                    max_right = Math.max(max_right, height[right + 1]);
                    int min = max_right;
                    if (min > height[right]) {
                        sum = sum + (min - height[right]);
                    }
                    right--;
                }
            }
            return sum;
        }
    }


    /**
     * 二叉搜索树迭代器
     */
    class BSTIterator {

        public BSTIterator(TreeNode root) {

        }

        /** @return the next smallest number */
        public int next() {

        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {

        }
    }










    /**
     * 最小栈
     */
    class MinStack2 {
        private Stack<Integer> stack;
        private int min;
        /** initialize your data structure here. */
        public MinStack2() {
            stack = new Stack<>();
            min = Integer.MAX_VALUE;
        }

        public void push(int x) {
            if(x <= min ){//注意：这里要使用<=号
                stack.push(min);//在每一个min入栈之前将它前一个min入栈
                min = x;
            }
            stack.push(x);
        }

        public void pop() {
            if(stack.pop() == min){//如果取出来的是当前min，就将压在它低下的前一个min出栈
                min = stack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int min() {
            return min;
        }
    }


    /**
     * 最小栈
     */
    class MinStack {


        private Stack<Integer> dataStack;
        private Stack<Integer> minStack;
        /** initialize your data structure here. */
        public MinStack() {
            dataStack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int x) {
            dataStack.push(x);
            if (dataStack.isEmpty()){
                minStack.push(x);
            }else {
                int val = Math.min(x, minStack.peek());
                minStack.push(val);
            }
        }

        public void pop() {
            dataStack.pop();
            minStack.pop();
        }

        public int top() {
            return dataStack.peek();
        }

        public int min() {
            return minStack.pop();
        }
    }


}

class MyStack {

    private Queue<Integer> queue = new LinkedList<>();

    /**
     * Initialize your data structure here.
     */
    public MyStack() {

    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        queue.add(x);
        int size = queue.size();
        while (size > 1) {
            queue.add(queue.remove());
            size--;
        }
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        return queue.remove();
    }

    /**
     * Get the top element.
     */
    public int top() {
        return queue.peek();
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return queue.isEmpty();
    }
}


class MyQueue {

    private Stack<Integer> stackPush;
    private Stack<Integer> stackPop;

    /**
     * Initialize your data structure here.
     */
    public MyQueue() {
        stackPush = new Stack<>();
        stackPop = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        stackPush.push(x);
    }

    /**
     * 辅助方法：一次性将 stackPush 里的所有元素倒入 stackPop
     * 注意：1、该操作只在 stackPop 里为空的时候才操作，否则会破坏出队入队的顺序
     * 2、在 peek 和 pop 操作之前调用该方法
     */
    private void shift() {
        if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        shift();
        if (!stackPop.isEmpty()) {
            return stackPop.pop();
        }
        throw new RuntimeException("队列里没有元素");
    }

    /**
     * Get the front element.
     */
    public int peek() {
        shift();
        if (!stackPop.isEmpty()) {
            return stackPop.peek();
        }
        throw new RuntimeException("队列里没有元素");
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return stackPush.isEmpty() && stackPop.isEmpty();
    }
}


