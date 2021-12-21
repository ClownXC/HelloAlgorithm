package com.algorithm.tree.binarytree;

/**
 * @author zxc
 * 以中序遍历线索化二叉树
 */
public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {

        ThreadedTreeNode root = new ThreadedTreeNode(1, "A");
        ThreadedTreeNode nodeB = new ThreadedTreeNode(2, "B");
        ThreadedTreeNode nodeC = new ThreadedTreeNode(3, "C");
        ThreadedTreeNode nodeD = new ThreadedTreeNode(4, "D");
        ThreadedTreeNode nodeE = new ThreadedTreeNode(5, "E");
        ThreadedTreeNode nodeF = new ThreadedTreeNode(6, "F");

        root.setLeftChild(nodeB);
        root.setRightChild(nodeC);
        nodeB.setLeftChild(nodeD);
        nodeB.setRightChild(nodeE);

        nodeC.setLeftChild(nodeF);


        ThreadedBinaryTree binaryTree = new ThreadedBinaryTree(root);
        binaryTree.threadedNodes();
        binaryTree.threadedList();

        System.out.println("nodeE:pre----" + nodeE.getLeftChild().getValue());


    }

    
}


class ThreadedBinaryTree{

    private ThreadedTreeNode root;
    private ThreadedTreeNode pre = null;


    public ThreadedBinaryTree(ThreadedTreeNode root) {
        this.root = root;
    }








    public void threadedNodes(){
        threadedNodes(root);
    }

    private void threadedNodes(ThreadedTreeNode node){

        if (node == null){
            return;
        }

        threadedNodes(node.getLeftChild());

        if (node.getLeftChild() == null){
             node.setLeftChild(pre);
             node.setLeftType(1);
        }



        if (pre != null && pre.getRightChild() == null){
            pre.setRightChild(node);
            pre.setRightType(1);

        }
        pre = node;


        threadedNodes(node.getRightChild());

    }


    public void threadedList(){

        ThreadedTreeNode node = root;
        while (node != null){
            while (node.getLeftType() == 0){
                node = node.getLeftChild();
            }
            System.out.println(node);
            while (node.getRightType() == 1){
                node = node.getRightChild();
                System.out.println(node);
            }
            node = node.getRightChild();
        }


    }





}