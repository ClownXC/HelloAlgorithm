package com.algorithm.tree.binarytree;

/**
 * @author zxc
 * @param <T>
 */
public class TreeNode<T>{

    private long index;
    private T value;

    public TreeNode leftChild;
    public TreeNode rightChild;
    public TreeNode parent;

    public long getIndex() {
        return index;
    }


    public T getValue() {
        return value;
    }


    public TreeNode getParent() {
        return parent;
    }


    public TreeNode(long index, T value) {
        this.index = index;
        this.value = value;
        this.leftChild = null;
        this.rightChild = null;

    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "index=" + index +
                ", value=" + value +
                '}';
    }
}
