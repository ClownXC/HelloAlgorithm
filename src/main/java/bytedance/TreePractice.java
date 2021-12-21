package bytedance;

import java.util.*;

public class TreeTest {


    /**
     * 二叉搜索树的后序遍历序列
     *
     * @param postorder
     * @return
     */
    public boolean verifyPostorder(int[] postorder) {
        Stack<Integer> stack = new Stack<>();
        int root = Integer.MAX_VALUE;
        for (int i = postorder.length - 1; i >= 0; i--) {
            if (postorder[i] > root) return false;
            while (!stack.isEmpty() && stack.peek() > postorder[i])
                root = stack.pop();
            stack.add(postorder[i]);
        }
        return true;
    }

    /**
     * 二叉搜索树的最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (p.val < root.val && q.val < root.val) {
                root = root.left;
            } else if (p.val > root.val && q.val > root.val) {
                root = root.right;
            } else {
                break;
            }
        }
        return root;
    }

    /**
     * 二叉树的最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        //首先要检查当前的root情况，若为null就直接返回；
        //若为p、q则是满足最近公共节点为节点本身
        if (root == null || root == p || root == q)
            return root;

        //再利用递归从根节点开始，开始向下遍历每个节点（以下两步则为具体对每一个节点的左右子树查找），由上述if语句的返回值得到这两个节点的值
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        //对于left和right节点返回的情况，如果根节点的左子树/右子树找不到最近公共节点，那么就说明在右子树/左子树当中
        if (left == null)
            return right;

        if (right == null)
            return left;
        //如果上述两个情况都不符合 则说明根节点就是最近公共节点，直接返回
        return root;
    }


    /**
     * 分裂二叉树的最大乘积
     *
     * @param root
     * @return
     */
    public int maxProduct(TreeNode root) {

        return 0;

    }


    /**
     * 先序遍历构造二叉树
     *
     * @param preorder
     * @return
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        int n = preorder.length;
        if (n == 0) return null;

        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.push(root);

        for (int i = 1; i < n; i++) {
            // take the last element of the deque as a parent
            // and create a child from the next preorder element
            TreeNode node = deque.peek();
            TreeNode child = new TreeNode(preorder[i]);
            // adjust the parent
            while (!deque.isEmpty() && deque.peek().val < child.val)
                node = deque.pop();

            // follow BST logic to create a parent-child link
            if (node.val < child.val) node.right = child;
            else node.left = child;
            // add the child into deque
            deque.push(child);
        }
        return root;
    }


    /**
     * 从二叉搜索树到更大和树
     *
     * @param root
     * @return
     */
    public TreeNode bstToGst(TreeNode root) {
        return null;

    }


    /**
     * 二叉搜索树的范围和
     *
     * @param root
     * @param L
     * @param R
     * @return
     */
    public int rangeSumBST(TreeNode root, int L, int R) {
        int ans = 0;
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                if (L <= node.val && node.val <= R)
                    ans += node.val;
                if (L < node.val)
                    stack.push(node.left);
                if (node.val < R)
                    stack.push(node.right);
            }
        }
        return ans;
    }


    TreeNode pre;
    int min = Integer.MAX_VALUE;

    /**
     * 二叉搜索树结点最小距离
     *
     * @param root
     * @return
     */
    public int minDiffInBST(TreeNode root) {
        inorder(root);
        return min;
    }

    public void inorder(TreeNode node) {
        if (node == null)
            return;

        inorder(node.left);
        if (pre != null) {
            min = Math.min(min, node.val - pre.val);
        }
        pre = node;
        inorder(node.right);
    }

    /**
     * N叉树的最大深度
     *
     * @param root
     * @return
     */
    public int maxDepth(Node root) {
        if (root == null) return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int maxDepth = 0;
        while (!queue.isEmpty()) {
            int count = queue.size();
            maxDepth++;
            while (count > 0) {
                count--;
                Node cur = queue.poll();
                for (Node node : cur.children) {
                    if (node != null)
                        queue.add(node);
                }
            }
        }
        return maxDepth;
    }


    /**
     * 二叉树的坡度
     *
     * @param root
     * @return
     */
    public int findTilt(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Stack<TreeNode> stack = new Stack<>();
        LinkedList<TreeNode> list = new LinkedList<>();
        stack.push(root);
        int p = 0;
        while (!stack.isEmpty()) {
            TreeNode treeNode = stack.pop();
            list.addFirst(treeNode);
            if (treeNode.left != null) {
                stack.push(treeNode.left);
            }
            if (treeNode.right != null) {
                stack.push(treeNode.right);
            }
        }
        for (TreeNode treeNode : list) {
            int left = treeNode.left == null ? 0 : treeNode.left.val;
            int right = treeNode.right == null ? 0 : treeNode.right.val;
            p += Math.abs(left - right);
            treeNode.val = left + right + treeNode.val;
        }
        return p;
    }


    /**
     * 合并二叉树
     *
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null)
            return t2;
        Stack<TreeNode[]> stack = new Stack<>();
        stack.push(new TreeNode[]{t1, t2});
        while (!stack.isEmpty()) {
            TreeNode[] t = stack.pop();
            if (t[0] == null || t[1] == null) {
                continue;
            }
            t[0].val += t[1].val;
            if (t[0].left == null) {
                t[0].left = t[1].left;
            } else {
                stack.push(new TreeNode[]{t[0].left, t[1].left});
            }
            if (t[0].right == null) {
                t[0].right = t[1].right;
            } else {
                stack.push(new TreeNode[]{t[0].right, t[1].right});
            }
        }
        return t1;
    }


    /**
     * 二叉树的层平均值
     *
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            long sum = 0, count = 0;
            Queue<TreeNode> temp = new LinkedList<>();
            while (!queue.isEmpty()) {
                TreeNode n = queue.remove();
                sum += n.val;
                count++;
                if (n.left != null)
                    temp.add(n.left);
                if (n.right != null)
                    temp.add(n.right);
            }
            queue = temp;
            res.add(sum * 1.0 / count);
        }
        return res;
    }


    public List<Double> averageOfLevels2(TreeNode root) {
        List<Integer> count = new ArrayList<>();
        List<Double> res = new ArrayList<>();
        average(root, 0, res, count);
        for (int i = 0; i < res.size(); i++)
            res.set(i, res.get(i) / count.get(i));
        return res;
    }

    public void average(TreeNode t, int i, List<Double> sum, List<Integer> count) {
        if (t == null)
            return;
        if (i < sum.size()) {
            sum.set(i, sum.get(i) + t.val);
            count.set(i, count.get(i) + 1);
        } else {
            sum.add(1.0 * t.val);
            count.add(1);
        }
        average(t.left, i + 1, sum, count);
        average(t.right, i + 1, sum, count);
    }


    int t;
    Map<String, Integer> trees;
    Map<Integer, Integer> count;
    List<TreeNode> ans;

    /**
     * 寻找重复的子树
     *
     * @param root
     * @return
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        t = 1;
        trees = new HashMap();
        count = new HashMap();
        ans = new ArrayList();
        lookup(root);
        return ans;
    }

    public int lookup(TreeNode node) {
        if (node == null) return 0;
        String serial = node.val + "," + lookup(node.left) + "," + lookup(node.right);
        int uid = trees.computeIfAbsent(serial, x -> t++);
        count.put(uid, count.getOrDefault(uid, 0) + 1);
        if (count.get(uid) == 2)
            ans.add(node);
        return uid;
    }


    /**
     * 最大二叉树
     */
    class Solution {
        public TreeNode constructMaximumBinaryTree(int[] nums) {
            return construct(nums, 0, nums.length);
        }

        public TreeNode construct(int[] nums, int l, int r) {
            if (l == r)
                return null;
            int max_i = max(nums, l, r);
            TreeNode root = new TreeNode(nums[max_i]);
            root.left = construct(nums, l, max_i);
            root.right = construct(nums, max_i + 1, r);
            return root;
        }

        public int max(int[] nums, int l, int r) {
            int max_i = l;
            for (int i = l; i < r; i++) {
                if (nums[max_i] < nums[i])
                    max_i = i;
            }
            return max_i;
        }
    }


    /**
     * 二叉树最大宽度
     */
    class WidthOfBinaryTree {

        public int widthOfBinaryTree(TreeNode root) {
            Queue<AnnotatedNode> queue = new LinkedList();
            queue.add(new AnnotatedNode(root, 0, 0));
            int curDepth = 0, left = 0, ans = 0;
            while (!queue.isEmpty()) {
                AnnotatedNode a = queue.poll();
                if (a.node != null) {
                    queue.add(new AnnotatedNode(a.node.left, a.depth + 1, a.pos * 2));
                    queue.add(new AnnotatedNode(a.node.right, a.depth + 1, a.pos * 2 + 1));
                    if (curDepth != a.depth) {
                        curDepth = a.depth;
                        left = a.pos;
                    }
                    ans = Math.max(ans, a.pos - left + 1);
                }
            }
            return ans;
        }

        class AnnotatedNode {
            TreeNode node;
            int depth, pos;

            AnnotatedNode(TreeNode n, int d, int p) {
                node = n;
                depth = d;
                pos = p;
            }
        }
    }


    /**
     * 二叉搜索树中的众数
     */
    class findMode {

        int maxTimes = 0;
        int thisTimes = 0;
        List<Integer> res = new LinkedList<Integer>();
        TreeNode pre = null;

        public int[] findMode(TreeNode root) {
            inOrder(root);
            int length = res.size();
            int[] rr = new int[length];
            for (int i = 0; i < length; i++) {
                rr[i] = res.get(i);
            }
            return rr;
        }

        public void inOrder(TreeNode root) {
            if (root == null) {
                return;
            }
            inOrder(root.left);
            if (pre != null && pre.val == root.val) {
                thisTimes++;
            } else {
                thisTimes = 1;
            }
            if (thisTimes == maxTimes) {
                res.add(root.val);
            } else if (thisTimes > maxTimes) {
                maxTimes = thisTimes;
                res.clear();
                res.add(root.val);
            }
            pre = root;
            inOrder(root.right);
        }
    }


    /**
     * 从前序与中序遍历序列构造二叉树
     */
    class BuildTree {

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0 || preorder.length != inorder.length) {
                return null;
            }
            return help(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
        }

        private TreeNode help(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd) {
            //递归的第一步：递归终止条件，避免死循环
            if (pStart > pEnd || iStart > iEnd) {
                return null;
            }
            //重建根节点
            TreeNode treeNode = new TreeNode(preorder[pStart]);
            int index = 0;  //index找到根节点在中序遍历的位置
            while (inorder[iStart + index] != preorder[pStart]) {
                index++;
            }
            //重建左子树
            treeNode.left = help(preorder, pStart + 1, pStart + index, inorder, iStart, iStart + index - 1);
            //重建右子树
            treeNode.right = help(preorder, pStart + index + 1, pEnd, inorder, iStart + index + 1, iEnd);
            return treeNode;

        }
    }


    /**
     * 从中序与后序遍历序列构造二叉树
     */
    class BuildTree2 {
        public TreeNode buildTree(int[] inorder, int[] postorder) {

        }
    }


    /**
     * 恢复二叉搜索树
     */
    class RecoverTree {
        public void recoverTree(TreeNode root) {

        }
    }


    /**
     * 验证二叉搜索树
     */
    class IsValidBST {
        public boolean isValidBST(TreeNode root) {
            return false;
        }
    }

    /**
     * 不同的二叉搜索树 II
     */
    class NumTrees {
        public int numTrees(int n) {
            return 0;
        }

        public List<TreeNode> generateTrees(int n) {
            return null;
        }
    }


    /**
     * 二叉树的右视图
     */
    class RightSideView {
        public List<Integer> rightSideView(TreeNode root) {
            return null;
        }
    }

    /**
     * 实现 Trie (前缀树)
     */
    class Trie {

        /**
         * Initialize your data structure here.
         */
        public Trie() {

        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            return;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            return false;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            return false;
        }
    }

    /**
     * 二叉树的所有路径
     */
    class BinaryTreePaths {
        public List<String> binaryTreePaths(TreeNode root) {
            return null;
        }
    }


    /**
     * 二叉树的序列化与反序列化
     */
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            return "";
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            return null;
        }
    }


    /**
     * 二叉树最长连续序列
     */
    class LongestConsecutive {
        public int longestConsecutive(TreeNode root) {
            return 0;

        }
    }

    /**
     * 二叉树的垂直遍历
     */
    class VerticalOrder {
        public List<List<Integer>> verticalOrder(TreeNode root) {
            return null;
        }
    }


    /**
     * 最大 BST 子树
     */
    class LargestBSTSubtree {
        public int largestBSTSubtree(TreeNode root) {
            return 0;
        }
    }


    /**
     * 删除二叉搜索树中的节点
     */
    class DeleteNode {
        public TreeNode deleteNode(TreeNode root, int key) {
            return null;
        }
    }

    /**
     * 将二叉搜索树转化为排序的双向链表
     */
    class TreeToDoublyList {
        public Node treeToDoublyList(Node root) {
            return null;
        }
    }


    /**
     * N叉树的层序遍历
     */
    class LevelOrder {
        public List<List<Integer>> levelOrder(Node root) {
            return null;
        }
    }

    /**
     * 二叉树的直径
     */
    class DiameterOfBinaryTree {
        public int diameterOfBinaryTree(TreeNode root) {
            return 0;
        }
    }


}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}

/**
 * 二叉树的垂序遍历
 */
class Solution {
    List<Location> locations;

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // Each location is a node's x position, y position, and value
        locations = new ArrayList();
        dfs(root, 0, 0);
        Collections.sort(locations);

        List<List<Integer>> ans = new ArrayList();
        ans.add(new ArrayList<Integer>());

        int prev = locations.get(0).x;

        for (Location loc : locations) {
            // If the x value changed, it's part of a new report.
            if (loc.x != prev) {
                prev = loc.x;
                ans.add(new ArrayList<Integer>());
            }

            // We always add the node's value to the latest report.
            ans.get(ans.size() - 1).add(loc.val);
        }

        return ans;
    }

    public void dfs(TreeNode node, int x, int y) {
        if (node != null) {
            locations.add(new Location(x, y, node.val));
            dfs(node.left, x - 1, y + 1);
            dfs(node.right, x + 1, y + 1);
        }
    }
}

class Location implements Comparable<Location> {
    int x, y, val;

    Location(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }

    @Override
    public int compareTo(Location that) {
        if (this.x != that.x)
            return Integer.compare(this.x, that.x);
        else if (this.y != that.y)
            return Integer.compare(this.y, that.y);
        else
            return Integer.compare(this.val, that.val);
    }
}


/**
 *
 */
class OrderUtil {


    /**
     * N 叉树的前序遍历
     *
     * @param root
     * @return
     */
    public static List<Integer> preorderN(Node root) {

        LinkedList<Node> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            Node node = stack.pollLast();
            output.add(node.val);
            Collections.reverse(node.children);
            for (Node item : node.children) {
                stack.add(item);
            }
        }
        return output;
    }


    /**
     * N 叉树的后序遍历
     *
     * @param root
     * @return
     */
    public static List<Integer> postorderN(Node root) {
        LinkedList<Node> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            Node node = stack.pollLast();
            output.addFirst(node.val);
            for (Node item : node.children) {
                if (item != null) {
                    stack.add(item);
                }
            }
        }
        return output;
    }


    /**
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {


        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;


        while (cur != null || !stack.isEmpty()) {
            //节点不为空一直压栈
            while (cur != null) {
                stack.push(cur);
                cur = cur.left; //考虑左子树
            }
            cur = stack.pop();
            ans.add(cur.val);
            cur = cur.right;
        }
        return ans;


    }


    /**
     * @param head
     * @return
     */
    public static List<Integer> preOrderTraversal(TreeNode head) {

        List<Integer> res = new ArrayList<Integer>();
        if (head == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {

            TreeNode node = stack.pop();
            res.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return res;
    }

    /**
     * N 叉树的前序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> preOrderTraversalN(Node root) {

        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        Stack<Node> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            output.add(node.val);

            Collections.reverse(node.children);
            for (Node item : node.children) {
                stack.add(item);
            }
        }
        return output;
    }


    /**
     * @param root
     */
    public List<Integer> postorderTraversal(TreeNode root) {


        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        Stack<TreeNode> treeNodes = new Stack<>();
        treeNodes.add(root);
        while (!treeNodes.isEmpty()) {

            TreeNode node = treeNodes.pop();
            output.add(node.val);
            if (node.left != null) {
                treeNodes.add(node.left);
            }
            if (node.right != null) {
                treeNodes.add(node.right);
            }
        }
        return output;
    }


    /**
     * BFS 层次遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> treeNodes = new LinkedList<>();
        treeNodes.offer(root);

        while (!treeNodes.isEmpty()) {


            int size = treeNodes.size();
            List<Integer> levelNodes = new LinkedList<>();

            for (int i = 0; i < size; i++) {

                TreeNode cur = treeNodes.poll();
                levelNodes.add(cur.val);
                if (cur.left != null) {
                    treeNodes.offer(cur.left);
                }
                if (cur.right != null) {
                    treeNodes.offer(cur.right);
                }
            }
            res.add(levelNodes);
        }


        return res;
    }


    /**
     * 二叉树的锯齿形层次遍历
     */
    class ZigzagLevelOrder {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> res = new LinkedList<>();
            if (root == null) {
                return res;
            }
            Queue<TreeNode> treeNodes = new LinkedList<>();
            treeNodes.offer(root);

            int level = 1;
            while (!treeNodes.isEmpty()) {

                List<Integer> levelNodes = new LinkedList<>();
                int size = treeNodes.size();

                for (int i = 0; i < size; i++) {

                    TreeNode cur = treeNodes.poll();
                    levelNodes.add(cur.val);
                    if (cur.left != null) {
                        treeNodes.offer(cur.left);
                    }
                    if (cur.right != null) {
                        treeNodes.offer(cur.right);
                    }
                }

                if (level % 2 == 0) {
                    Collections.reverse(levelNodes);
                }
                res.add(levelNodes);
                level++;
            }
            return res;
        }
    }

    /**
     * N 叉树的层序遍历
     */
    class LevelOrderN {

        public List<List<Integer>> levelOrder(Node root) {


            List<List<Integer>> result = new ArrayList<>();
            if (root == null) {
                return result;
            }
            Queue<Node> treeNodes = new LinkedList<>();
            treeNodes.add(root);

            while (!treeNodes.isEmpty()) {

                List<Integer> levelNodes = new LinkedList<>();
                int size = treeNodes.size();

                for (int i = 0; i < size; i++) {
                    Node node = treeNodes.poll();
                    levelNodes.add(node.val);
                    treeNodes.addAll(node.children);
                }
                result.add(levelNodes);
            }
            return result;
        }
    }

    /**
     *
     */
    class Solution {
        public boolean isSubtree(TreeNode s, TreeNode t) {
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(s);
            while (!q.isEmpty()) {
                TreeNode cur = q.poll();
                if (cur.val == t.val && isSame(cur, t))
                    return true;
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
            return false;
        }

        private boolean isSame(TreeNode s, TreeNode t) {
            if (s == null && t == null)
                return true;
            if (s == null || t == null)
                return false;
            if (s.val != t.val)
                return false;
            return isSame(s.left, t.left) && isSame(s.right, t.right);
        }
    }


    /**
     * 二叉树的垂序遍历
     */
    class Solution {
        public List<List<Integer>> verticalTraversal(TreeNode root) {

        }
    }


}


class KthSmallest {

    public int kthSmallest(TreeNode root, int k) {

        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        int res = -1;

        while (cur != null || !stack.isEmpty()) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            cur = stack.pop();
            if (--k == 0) {
                res = cur.val;
                break;
            }
            cur = cur.right;
        }
        return res;
    }
}


/**
 * 从中序与后序遍历序列构造二叉树
 */
class BuildTree {

    HashMap<Integer, Integer> memo = new HashMap<>();
    int[] post;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            memo.put(inorder[i], i);
        }
        post = postorder;
        return buildTree(0, inorder.length - 1, 0, post.length - 1);
    }

    public TreeNode buildTree(int is, int ie, int ps, int pe) {
        if (ie < is || pe < ps) return null;

        int root = post[pe];
        int ri = memo.get(root);

        TreeNode node = new TreeNode(root);
        node.left = buildTree(is, ri - 1, ps, ps + ri - is - 1);
        node.right = buildTree(ri + 1, ie, ps + ri - is, pe - 1);
        return node;
    }
}

/**
 * 从前序与中序遍历序列构造二叉树
 */
class BuildTree2 {

    HashMap<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTreeHelper(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    /**
     * @param preorder
     * @param preStart
     * @param preEnd
     * @param inorder
     * @param inStart
     * @param inEnd
     * @return
     */
    private TreeNode buildTreeHelper(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {

        if (preStart == preEnd) {
            return null;
        }

        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);


        int indexOfRoot = map.get(rootVal);
        int leftNum = indexOfRoot - inStart;
        root.left = buildTreeHelper(preorder, preStart + 1, preStart + leftNum + 1,
                inorder, inStart, indexOfRoot);
        root.right = buildTreeHelper(preorder, preStart + leftNum + 1, preEnd,
                inorder, indexOfRoot + 1, inEnd);
        return root;
    }

}


///**
// *
// */
//class LevelOrder {
//
//    public List<List<Integer>> levelOrder(TreeNode root) {
//
//        List<List<Integer>> res = new LinkedList<>();
//        if(root == null) {
//            return res;
//        }
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.offer(root);
//        while(!queue.isEmpty())
//        {
//            List<Integer> subList = new LinkedList<>();
//            int size = queue.size();
//            for(int i = 0; i < size; i++)
//            {
//                TreeNode cur = queue.poll();
//                subList.add(cur.val);
//                if(cur.left != null) {
//                    queue.offer(cur.left);
//                }
//                if(cur.right != null){
//                    queue.offer(cur.right);
//                }
//            }
//            res.add(subList);
//        }
//        return res;
//    }
//}

/**
 * 二叉树中所有距离为 K 的结点
 */
class DistanceK {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {

    }
}

/**
 * 所有可能的满二叉树
 */
class AllPossibleFBT {
    public List<TreeNode> allPossibleFBT(int N) {

    }
}

/**
 * 606. 根据二叉树创建字符串
 */
class Tree2str {
    public String tree2str(TreeNode root) {

        if (root == null) {
            return "";
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        Set<TreeNode> visited = new HashSet<>();
        StringBuilder s = new StringBuilder();

        while (!stack.isEmpty()) {

            root = stack.peek();
            if (visited.contains(root)) {
                stack.pop();
                s.append(")");
            } else {
                visited.add(root);
                s.append("(" + root.val);
                if (root.left == null && root.right != null) s.append("()");
                if (root.right != null) stack.push(root.right);
                if (root.left != null) stack.push(root.left);
            }
        }
        return s.substring(1, s.length() - 1);

    }
}

/**
 *
 */
class FlipEquiv {


    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        List<Integer> vals1 = new ArrayList();
        List<Integer> vals2 = new ArrayList();
        dfs(root1, vals1);
        dfs(root2, vals2);
        return vals1.equals(vals2);
    }

    public void dfs(TreeNode node, List<Integer> vals) {

        if (node != null) {

            vals.add(node.val);
            int L = node.left != null ? node.left.val : -1;
            int R = node.right != null ? node.right.val : -1;

            if (L < R) {
                dfs(node.left, vals);
                dfs(node.right, vals);
            } else {
                dfs(node.right, vals);
                dfs(node.left, vals);
            }

            vals.add(null);
        }
    }

}

/**
 * 二叉树最大宽度
 */
class WidthOfBinaryTree {
    public int widthOfBinaryTree(TreeNode root) {

    }
}
//
//class IsSymmetric {
//    public boolean isSymmetric(TreeNode root) {
//        if (root == null) {
//            return true;
//        }
//
//        // 实现类不能选用 ArrayDeque，因为该类的 add 方法会对添加的元素做非空检查
//        Deque<TreeNode> deque = new LinkedList<>();
//        // Deque<TreeNode> deque = new ArrayDeque<>();
//
//        deque.addFirst(root.left);
//        deque.addLast(root.right);
//
//        while (!deque.isEmpty()) {
//            TreeNode leftNode = deque.removeFirst();
//            TreeNode rightNode = deque.removeLast();
//
//            if (leftNode == null && rightNode == null) {
//                continue;
//            }
//
//            if (leftNode == null || rightNode == null) {
//                return false;
//            }
//
//            if (leftNode.val != rightNode.val) {
//                return false;
//            }
//
//            deque.addFirst(leftNode.right);
//            deque.addFirst(leftNode.left);
//            deque.addLast(rightNode.left);
//            deque.addLast(rightNode.right);
//        }
//
//        return true;
//    }
//
//}

/**
 *
 */
class AverageOfLevels {

    public List<Double> AverageOfLevels(TreeNode root) {


        List<Double> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        while (!queue.isEmpty()) {
            long sum = 0, count = 0;
            Queue<TreeNode> temp = new LinkedList<>();

            while (!queue.isEmpty()) {
                TreeNode n = queue.remove();
                sum += n.val;
                count++;
                if (n.left != null) {
                    temp.add(n.left);
                }
                if (n.right != null) {
                    temp.add(n.right);
                }
            }
            queue = temp;
            res.add(sum * 1.0 / count);

        }
        return res;
    }
}


/**
 * 不同的二叉搜索树 II
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 */
class GenerateTrees {

    public List<TreeNode> generateTrees(int n) {


    }


    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }

        List<TreeNode> list = generateTreesHelp(1, n);
        return list;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}

/**
 * 验证二叉搜索树
 */
class IsValidBST {

    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        double inorder = -Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {

            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val <= inorder) return false;
            inorder = root.val;
            root = root.right;
        }
        return true;
    }
}

/**
 * 恢复二叉搜索树
 */
class RecoverTree {

    public void recoverTree(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque();
        TreeNode x = null;
        TreeNode y = null;
        TreeNode pre = null;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.removeLast();


            if (pre != null && root.val < pre.val) {
                y = root;
                if (x == null) {
                    x = pre;
                } else {
                    break;
                }
            }
            pre = root;
            root = root.right;
        }

        swap(x, y);
    }


    private void swap(TreeNode a, TreeNode b) {
        int tmp = a.val;
        a.val = b.val;
        b.val = tmp;
    }
}

/**
 *
 */
class IsSameTree {
    public boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (q == null || p == null) return false;
        if (p.val != q.val) return false;
        return true;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (!check(p, q)) return false;

        // init deques
        ArrayDeque<TreeNode> deqP = new ArrayDeque<>();
        ArrayDeque<TreeNode> deqQ = new ArrayDeque<>();

        deqP.addLast(p);
        deqQ.addLast(q);

        while (!deqP.isEmpty()) {

            p = deqP.removeFirst();
            q = deqQ.removeFirst();

            if (!check(p, q)) return false;
            if (p != null) {
                if (!check(p.left, q.left)) return false;
                if (p.left != null) {
                    deqP.addLast(p.left);
                    deqQ.addLast(q.left);
                }
                if (!check(p.right, q.right)) return false;
                if (p.right != null) {
                    deqP.addLast(p.right);
                    deqQ.addLast(q.right);
                }
            }
        }
        return true;
    }
}


/**
 *
 */
class IsSymmetric {
    public boolean isSymmetric(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        //用队列保存节点
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        //将根节点的左右孩子放到队列中
        queue.add(root.left);
        queue.add(root.right);
        while (queue.size() > 0) {
            //从队列中取出两个节点，再比较这两个节点
            TreeNode left = queue.removeFirst();
            TreeNode right = queue.removeFirst();
            //如果两个节点都为空就继续循环，两者有一个为空就返回false
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null) {
                return false;
            }
            if (left.val != right.val) {
                return false;
            }
            //将左节点的左孩子， 右节点的右孩子放入队列
            queue.add(left.left);
            queue.add(right.right);
            //将左节点的右孩子，右节点的左孩子放入队列
            queue.add(left.right);
            queue.add(right.left);
        }

        return true;
    }
}

/**
 * 二叉树的最大深度
 */
class MaxDepth {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // bfs
        Queue<TreeNode> queue = new LinkedList<>();
        int depth = 0;
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }
        }
        return depth;
    }
}

/**
 * 将有序数组转换为二叉搜索树
 */
class SortedArrayToBST {
    int[] nums;

    public TreeNode helper(int left, int right) {
        if (left > right) return null;

        // always choose right middle node as a root
        int p = (left + right) / 2;
        if ((left + right) % 2 == 1) ++p;

        // inorder traversal: left -> node -> right
        TreeNode root = new TreeNode(nums[p]);
        root.left = helper(left, p - 1);
        root.right = helper(p + 1, right);
        return root;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        this.nums = nums;
        return helper(0, nums.length - 1);
    }

}

/**
 *
 */
class MaxPathSum {
    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        findMaxPathSum(root);
        return max;
    }

    int findMaxPathSum(TreeNode root) {
        if (root == null) return 0;

        int left = Math.max(findMaxPathSum(root.left), 0);
        int right = Math.max(findMaxPathSum(root.right), 0);

        //求的过程中考虑包含当前根节点的最大路径
        max = Math.max(max, root.val + left + right);

        //只返回包含当前根节点和左子树或者右子树的路径
        return root.val + Math.max(left, right);
    }

}

/**
 * 翻转二叉树
 */
class InvertTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        while (!queue.isEmpty()) {

            TreeNode current = queue.poll();
            TreeNode temp = current.left;
            current.left = current.right;
            current.right = temp;
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
        return root;
    }
}

/**
 * 完全二叉树的节点个数.给出一个完全二叉树，求出该树的节点个数。
 */
class CountNodes {


    private int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return getDepth(root.left) + 1;
        }
    }

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = getDepth(root.left);
        int rightHeight = getDepth(root.right);
        // 左子树是 perfect binary tree
        if (rightHeight == leftHeight) {
            // 左子树高度和右子树高度相等
            // 左子树加右子树加根节点
            //return (1 << rightHeight) - 1  + countNodes(root.right) + 1;
            return (1 << rightHeight) + countNodes(root.right);
            // 右子树是 perfect binary tree
        } else {
            // 左子树加右子树加根节点
            //return countNodes(root.left) + (1 << rightHeight) - 1 + 1;
            return countNodes(root.left) + (1 << rightHeight);
        }
    }


    // Return tree depth in O(d) time.
    public int computeDepth(TreeNode node) {
        int d = 0;
        while (node.left != null) {
            node = node.left;
            ++d;
        }
        return d;
    }

    // Last level nodes are enumerated from 0 to 2**d - 1 (left -> right).
    // Return True if last level node idx exists.
    // Binary search with O(d) complexity.
    public boolean exists(int idx, int d, TreeNode node) {
        int left = 0, right = (int) Math.pow(2, d) - 1;
        int pivot;
        for (int i = 0; i < d; ++i) {
            pivot = left + (right - left) / 2;
            if (idx <= pivot) {
                node = node.left;
                right = pivot;
            } else {
                node = node.right;
                left = pivot + 1;
            }
        }
        return node != null;
    }

    public int countNodes2(TreeNode root) {
        // if the tree is empty
        if (root == null) return 0;

        int d = computeDepth(root);
        // if the tree contains 1 node
        if (d == 0) return 1;

        // Last level nodes are enumerated from 0 to 2**d - 1 (left -> right).
        // Perform binary search to check how many nodes exist.
        int left = 1, right = (int) Math.pow(2, d) - 1;
        int pivot;
        while (left <= right) {
            pivot = left + (right - left) / 2;
            if (exists(pivot, d, root)) left = pivot + 1;
            else right = pivot - 1;
        }

        // The tree contains 2**d - 1 nodes on the first (d - 1) levels
        // and left nodes on the last level.
        return (int) Math.pow(2, d) - 1 + left;
    }


}

/**
 * 二叉树的右视图
 */
class RightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        //这个和二叉树的层序遍历区别在于,只需要保存最右边的TreeNode即可

        List<Integer> list = new ArrayList<Integer>();
        if (root == null) {
            return list;
        }
        int level = 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int level_length = queue.size();

            for (int i = 0; i < level_length; ++i) {

                if (i == level_length - 1) {
                    list.add(queue.peek().val);
                }
                TreeNode node = queue.remove();

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }


            }

            level++;
        }
        return list;

    }
}

/**
 * 二叉搜索树迭代器
 */
class BSTIterator {

    public BSTIterator(TreeNode root) {

    }

    /**
     * @return the next smallest number
     */
    public int next() {

    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {

    }
}

/**
 * 求根到叶子节点数字之和
 */
class SumNumbers {

    public int sumNumbers(TreeNode root) {

        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        Queue<Integer> numQueue = new LinkedList<Integer>();

        int res = 0;
        queue.add(root);
        numQueue.add(0);
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 把该层的都入队，同时如果遇到叶节点，计算更新
            for (int i = 0; i < size; i++) {
                root = queue.poll();

                int val = numQueue.poll() * 10 + root.val;
                if (root.left == null && root.right == null) res += val;
                if (root.left != null) {
                    queue.add(root.left);
                    numQueue.add(val);
                }
                if (root.right != null) {
                    queue.add(root.right);
                    numQueue.add(val);
                }
            }
        }
        return res;

    }


}

class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {

    }
}

/**
 * 二叉搜索树的最小绝对差
 */
class MinimumDifference {
    public int getMinimumDifference(TreeNode root) {
        int pre = -1;
        int minDif = Integer.MAX_VALUE;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;


        while (cur != null || !stack.isEmpty()) {
            //节点不为空一直压栈
            while (cur != null) {
                stack.push(cur);
                cur = cur.left; //考虑左子树
            }


            cur = stack.pop();
            if (pre != -1) {
                minDif = Math.min(minDif, cur.val - pre);
            }
            pre = cur.val;
            cur = cur.right;
        }
        return Math.abs(minDif);

    }
}

/**
 *
 */
class RangeSumBST {


    public int rangeSumBST(TreeNode root, int L, int R) {

        if (root == null) {
            return 0;
        }
        int sum = 0;
        Stack<TreeNode> stack = new Stack<>();

        while (!stack.isEmpty() || root != null) {

            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode tmp = stack.pop();
            int val = tmp.val;
            if (val >= L && val <= R) sum += val;
            root = tmp.right;

        }

        return sum;

    }
}




/**
 *  树中距离之和
 */
class LowestCommonAncestor {


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {


        Stack<TreeNode> stack = new Stack<>();
        HashMap<TreeNode, TreeNode> parent = new HashMap<>();
        stack.push(root);
        parent.put(root, null);


        //将遍历过程中每个节点的父节点保存起来
        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode cur = stack.pop();
            if (cur.left != null) {
                stack.push(cur.left);
                parent.put(cur.left, cur);
            }
            if (cur.right != null) {
                stack.push(cur.right);
                parent.put(cur.right, cur);
            }
        }

        HashSet<TreeNode> ancestors = new HashSet<>();
        // 倒着还原 p 的路径，并将每个节点加入到 set 中
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }

        // 倒着遍历 q 的路径，判断是否在 p 的路径中
        while (q != null) {
            if (ancestors.contains(q)) {
                break;
            }
            q = parent.get(q);
        }
        return q;
    }
}