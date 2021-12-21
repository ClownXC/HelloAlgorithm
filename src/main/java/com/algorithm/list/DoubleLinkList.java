package com.algorithm.list;

public class DoubleLinkList {


    private Node firstNode;


    public void insertAfterFirst(long value){





    }



    public static class Node<E>{
        E data;
        Node<E> pre;
        Node<E> next;

        public Node(E data) {
            this.data = data;
        }

        public Node(E data, Node<E> pre, Node<E> next) {
            this.data = data;
            this.pre = pre;
            this.next = next;
        }
    }

}
