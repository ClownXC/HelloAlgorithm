package com.algorithm.tree.binarytree.leetcode.invertbinarytree226;

import com.algorithm.tree.binarytree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 翻转二叉树
 * @author zxc
 */
public class Solution {

    public static void main(String[] args) {

    }


    /**
     *
     * @param root
     * @return
     */
    public TreeNode invertTreeByStack(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()) {

            final TreeNode node = stack.pop();

            TreeNode left = node.leftChild;
            node.leftChild = node.rightChild;
            node.rightChild = left;

            if(node.leftChild != null) {
                stack.push(node.leftChild);
            }
            if(node.rightChild != null) {
                stack.push(node.rightChild);
            }
        }
        return root;
    }


    public TreeNode invertTreeByQueue(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (! queue.isEmpty()) {

            TreeNode node = queue.poll();

            TreeNode left = node.leftChild;
            node.leftChild = node.rightChild;
            node.rightChild = left;

            if (node.leftChild != null) {
                queue.offer(node.leftChild);
            }
            if (node.rightChild != null) {
                queue.offer(node.rightChild);
            }
        }
        return root;
    }
}
