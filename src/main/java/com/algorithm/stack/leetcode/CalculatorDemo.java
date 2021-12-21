package com.algorithm.stack.leetcode;

/**
 * @author zxc
 *
 * 使用栈完成中缀表达式的计算思路：
 * 1. 通过一个 index 值（索引）遍历表达式
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
public class CalculatorDemo {


    public static void main(String[] args){


        String expression = "7+2*6-4";


        ArrayStack numStack = new ArrayStack(10);
        ArrayStack operStack = new ArrayStack(10);


        int index = 0;

        int num1;
        int num2;
        int res;
        int oper = 0;
        char v = ' ';
        StringBuffer keepnum = new StringBuffer();



        while (index < expression.length()){

            v = expression.charAt(index);
            if(operStack.isOper(v)){

                if(!operStack.isEmpty()){

                    if(operStack.priority(v) <= operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();

                        res = numStack.cal(num1, num2, oper);
                        numStack.push(res);
                        operStack.push(oper);

                    }else {
                        operStack.push(oper);
                    }

                }else {
                    operStack.push(oper);
                }

            }else {

                keepnum.append(v);
                if (index == expression.length() - 1){
                    numStack.push(Integer.parseInt(keepnum.toString()));
                }else {
                    if(! Character.isDigit(index + 1)){
                        numStack.push(Integer.parseInt(keepnum.toString()));
                    }
                    keepnum.delete(0, keepnum.length());
                }

            }
            index ++;
        }

        while (! operStack.isEmpty()){

            res = numStack.cal(numStack.pop(), numStack.pop(), operStack.pop());
            numStack.push(res);

        }


        numStack.list();
        System.out.println(numStack.pop());


    }



}





class ArrayStack<T>{

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


    /**
     *
     * @return
     */
    public int peek(){

        if(isEmpty()){
            throw new RuntimeException("NULL");
        }

        return stack[top];
    }


    /**
     *
     * @return
     */
    public boolean isOper(char oper){

        return oper == '*' || oper == '/' || oper == '+' || oper == '-';
    }


    /**
     * 返回值越大优先级越高
     * @param oper
     * @return
     */
    public int priority(int oper){

        if(oper == '*' || oper == '/'){
            return 1;
        }else if(oper == '+' || oper == '-'){
            return 0;
        }else{
            return -1;
        }


    }

    /**
     *
     * @param num1
     * @param num2
     * @param oper
     * @return
     */
    public int cal(int num1, int num2, int oper){

        int res  = 0;
        switch (oper){

            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '/':
                res = num2 / num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            default:
                break;

        }

        return res;

    }






}
