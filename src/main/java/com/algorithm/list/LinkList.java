package com.algorithm.list;

public class LinkList {

    private Node firstNode;


    /**
     * Node{data=1}---Node{data=1}---Node{data=2}---Node{data=5}---Node{data=4}---Node{data=1}---Node{data=3}
     * @param value
     */
    public void insertAfterFirstNode(long value){

        Node node = new Node(value);
        if(firstNode == null){
            firstNode = node;
        }else{
            node.next = firstNode.next;
            firstNode.next = node;

//            node.next = firstNode;
//            firstNode = node;
        }


    }


    public Node deleteAfterFirstNode(){

        Node returnNode = firstNode.next;
        firstNode.next = returnNode.next;
        return returnNode;


    }

    /**
     *
     * @param value
     */
    public Node find(long value){
        Node currentNode = firstNode;
        while(currentNode.data != value){
            if(currentNode.next == null){
                return null;
            }
            currentNode = currentNode.next;

        }
        return currentNode;
    }

    /**
     *
     * @param value
     */
    public Node delete(long value){
        Node currentNode = firstNode;
        Node preNode = firstNode;
        while(currentNode.data != value){
            if(currentNode.next == null){
                return null;
            }

            preNode = currentNode;
            currentNode = currentNode.next;
        }
        if(currentNode == firstNode){
            firstNode = firstNode.next;
        }else{
            preNode.next = currentNode.next;
        }

        return currentNode;
    }





    /**
     *
     */
    public void display(){
        Node currentNode = firstNode;
        while(currentNode!= null){
            currentNode.display();
            currentNode = currentNode.next;
        }
    }


}
