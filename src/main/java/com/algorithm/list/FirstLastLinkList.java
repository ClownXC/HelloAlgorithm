package com.algorithm.list;

public class FirstLastLinkList {

    private Node firstNode;
    private Node lastNode;

    private boolean isEmpty(){
        return (firstNode == null);
    }

    /**
     *
     * @param value
     */
    public void insertAfterFirst(long value){

        Node insertNode = new Node(value);
        if(firstNode == null){
            lastNode = insertNode;
        }
        insertNode.next = firstNode.next;
        firstNode.next = insertNode;


    }
    public void insertAfterLast(long value){

        Node insertNode = new Node(value);
        if(firstNode == null){
            firstNode = insertNode;
        }else{
            lastNode.next = insertNode;
        }

        lastNode = insertNode;
    }



    public static class Node<E>{

        E data;
        Node<E> pre;
        Node<E> next;

        public Node(E data) {
            this.data = data;
        }

        public Node(E data, Node<E> nextNode) {
            this.data = data;
            this.next = nextNode;
        }

        public Node(E data, Node<E> preNode, Node<E> nextNode) {
            this.data = data;
            this.pre = preNode;
            this.next = nextNode;
        }
    }





}
