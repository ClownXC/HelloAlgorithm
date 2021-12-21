package com.algorithm.tree.binarytree;

/**
 * @author zxc
 * @param <T>
 */
public class ThreadedTreeNode<T>{

    private long index;
    private T value;

    private ThreadedTreeNode leftChild;
    private ThreadedTreeNode rightChild;
    private ThreadedTreeNode parent;


    private int leftType;
    private int rightType;


    public ThreadedTreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(ThreadedTreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public ThreadedTreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(ThreadedTreeNode rightChild) {
        this.rightChild = rightChild;
    }

    public ThreadedTreeNode getParent() {
        return parent;
    }

    public void setParent(ThreadedTreeNode parent) {
        this.parent = parent;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public long getIndex() {
        return index;
    }


    public T getValue() {
        return value;
    }




    public ThreadedTreeNode(long index, T value) {
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
