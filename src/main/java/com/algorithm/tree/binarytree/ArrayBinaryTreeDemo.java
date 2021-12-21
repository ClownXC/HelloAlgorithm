package com.algorithm.tree.binarytree;

/**
 * @author zxc
 * 二叉树顺序存储
 * 需求:给定一个数组{1, 2, 3, 4, 5, 6, 7},要求以二叉树前序遍历的方式进行遍历
 * 遍历结果应当为1,2,4,5,6,7
 */
public class ArrayBinaryTreeDemo {



    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder();
    }
}


class ArrayBinaryTree{

    private int[]arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }


    public void preOrder(){
        preOrder(0);
    }


    private void preOrder(int index){

        if (arr == null || arr.length == 0){
            System.out.println("arr is empty");
        }

        System.out.print(arr[index] + " ");

        if ( 2*index + 1 < arr.length ){
            preOrder(2*index + 1);
        }

        if ( 2*index + 2 < arr.length ){
            preOrder(2*index + 2);
        }

    }}