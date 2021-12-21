package com.algorithm.tree.binarytree.bst;

public class HelloBST {

    public static void main(String[] args) {

    }
}


class BSTree{

    private BSTNode root;
    public void add(BSTNode node){

        if(node == null){
            node = root;
        }else {
            root.add(node);
        }

    }




}



class BSTNode{
    int value;
    BSTNode left;
    BSTNode right;

    public BSTNode(int value) {
        this.value = value;
    }

    /**
     *
     * @param node
     */
    public void add(BSTNode node){

        if (node == null){
            return;
        }
        if (node.value < this.value){
            if (this.left == null){
                this.left = node;
            }else {
                this.left.add(node);
            }
        }else {
            if (this.right == null){
                this.right = node;
            }else {
                this.right.add(node);
            }

        }

    }



    public BSTNode search(int value){
        if (value == this.value){
            return this;
        }else if(value < this.value){
            if (this.left == null){
                return null;
            }

            return this.left.search(value);
        }else {
            if (this.right == null){
                return null;
            }

            return this.right.search(value);
        }
    }



    public void infixOrder(){

        if (this.left != null){
            this.left.infixOrder();
        }

        System.out.println(this.value);
        if (this.right != null){
            this.right.infixOrder();
        }

    }

}
