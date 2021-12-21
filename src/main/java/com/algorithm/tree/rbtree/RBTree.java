package com.algorithm.tree.rbtree;


import com.algorithm.tree.binarytree.TreeNode;
import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

/**
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * @param <K>
 * @param <V>
 */
public class RBTree <K extends Comparable<K>, V> {


    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private RBNode root;


    private RBNode parentOf(RBNode node){
        if (node != null){
            return node.parent;
        }

        return null;
    }


    /**
     * 判断结点是否为红色
     * @param node
     * @return
     */
    private boolean isRed(RBNode node){
        if (node != null){
            return node.color == RED;
        }
        return false;

    }

    /**
     *
     * 判断结点是否为黑色
     * @param node
     * @return
     */
    private boolean isBlack(RBNode node) {
        if (node != null){
            return node.color == BLACK;
        }
        return false;
    }

    /**
     *
     * @param node
     */
    private void setRed(RBNode node){

        if (node != null){
            node.color = RED;
        }
    }


    /**
     *
     * @param node
     */
    private void setBlack(RBNode node){

        if (node != null){
            node.color = BLACK;
        }
    }

    /**
     *
     * @param node
     */
    public static void inOrderPrint(RBNode node) {

        if (node == null) {
            return;
        } else {
            Stack<RBNode> nodeStack = new Stack<>();
            nodeStack.push(node);
            while (! nodeStack.isEmpty()) {

                // 弹出父节点
                RBNode n = nodeStack.pop();
                if (n.right != null) {
                    nodeStack.push(n.right);
                }

                System.out.println(n.getValue());

                if (n.left != null) {
                    nodeStack.push(n.left);
                }

            }

        }

    }

    /**
     * 左旋方法
     * 左旋示意图: 左旋 x 结点
     *
     *
     *    p                             p
     *    |                             |
     *    x                             y
     *   / \             ---->         / \
     * lx    y                        x   ry
     *      / \                      / \
     *    ly   ry                   lx  ly
     *
     * 1. 将x的右子结点指向y的左子结点， 将y的左子结点的父结点更新为x
     * 2. 将x的父结点(不为空时)，更新y的父结点为x的父结点
     * 3. 将x的父结点更新为y，将y的左子结点更新为x
     *
     * @param x
     */
    private void leftRotate(RBNode x){

        RBNode y = x.left;
        // 1. 将x的右子结点指向y的左子结点， 将y的左子结点的父结点更新为x
        x.right = y.left;
        if (y.left != null){
            y.left.parent = x;
        }

        if (x.parent != null){
            y.parent = x.parent;
            if (x == x.parent.left){
                x.parent.left = y;
            }else {
                x.parent.left = y;
            }
        }else {
            this.root = y;
            this.root.parent = null;

        }

        x.parent = y;
        y.left = x;




    }

    /**
     * 右旋方法
     * 右旋示意图: 右旋 y 结点
     *
     *
     *    p                             p
     *    |                             |
     *    y                             y
     *   / \             ---->         / \
     *  x   ry                        x   ry
     * / \                      / \
     *lx  ly                   lx  ly
     *
     * 1. 将 y 的左子结点指向 x 的右子结点， 将 x 的右子结点的父结点更新为 y
     * 2. 将x的父结点(不为空时)，更新y的父结点为x的父结点
     * 3. 将x的父结点更新为y，将y的左子结点更新为x
     *
     * @param y
     */
    private void rightRotate(RBNode y){

        RBNode x = y.left;
        // 1. 将x的右子结点指向y的左子结点， 将y的左子结点的父结点更新为x
        y.left = x.right;
        if (x.right != null){
            x.right.parent = x;
        }

        if (y.parent != null){
            x.parent = y.parent;
            if (y == y.parent.left){
                y.parent.left = x;
            }else {
                y.parent.left = x;
            }
        }else {
            this.root = x;
            this.root.parent = null;

        }

        y.parent = x;
        x.right = y;

    }

    /**
     *
     * @param key
     * @param value
     */
    public void insert(K key, V value){

        RBNode node = new RBNode();
        node.setKey(key);
        node.setValue(value);
        node.setColor(RED);

        insert(node);

    }


    private void insert(RBNode node){

        RBNode parent = null;
        RBNode x = this.root;
        while (x != null){
            int cmp = node.key.compareTo(x.key);
            if (cmp > 0){
                x = x.right;
            }else if (cmp == 0){
                x.setValue(node.getValue());
                return;
            }else {
                x = x.left;
            }

        }

        node.parent = parent;
        if (parent != null){
            int cmp = node.key.compareTo(parent.key);
            if (cmp > 0){
                parent.right = node;
            }else {
                parent.left = node;
            }

        }else {
            this.root = node;
        }


    }
















    static class RBNode< K extends Comparable<K>, V>{
        private RBNode parent;
        private RBNode left;
        private RBNode right;

        private boolean color;
        private K key;
        private V value;

        public RBNode() {
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }




}
