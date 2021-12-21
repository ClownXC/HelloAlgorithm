package com.algorithm.stack;

public class ArrayStackDemo {






    public static void main (String args[]){

        ArrayStack testStack = new ArrayStack(10);





    }





}
class ArrayStack{

    private int maxSize;
    private int[] stack;
    private int top = -1;


    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    /**
     *
     * @return
     */
    public boolean isFull(){
        return top == maxSize - 1;
    }

    /**
     *
     * @return
     */
    public boolean isEmpty(){
        return top == - 1;
    }

    /**
     *
     * @param data
     */
    public void push(int data){

        if(isFull()){
            return;
        }
        top++;
        stack[top] = data;
    }

    /**
     *
     * @return
     */
    public int pop(){

        if(isEmpty()){
            throw new RuntimeException("NULL");
        }

        int val = stack[top];
        top--;
        return val;
    }

    /**
     *
     * @return
     */
    public void list(){

        if(isEmpty()){
            throw new RuntimeException("NULL");
        }

        for(int i = top; i >= 0; i--){
            System.out.println("val:  "+i+"------"+stack[i]);
        }
    }



}


