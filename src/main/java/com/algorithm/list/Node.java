package com.algorithm.list;

public class Node {

    public long data;
    public Node next;

    public Node(long data) {
        this.data = data;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void display() {
        System.out.print( "Node{" +"data=" + data +"}---");
    }
}
