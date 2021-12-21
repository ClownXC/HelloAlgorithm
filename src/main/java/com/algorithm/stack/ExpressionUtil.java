package com.algorithm.stack;

import com.algorithm.list.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zxc
 */
public class ExpressionUtil {


    private static final Pattern digitPattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");


    public static void main(String[] args) {
        String expression = "1+((2+3)*4)-5";
        List<String> ls = getExpressionList(expression);
//        for (String s : ls){
//            System.out.println("ls: "+ s);
//        }
//
        List<String> ls2 = infix2Suffix(ls);
        for (String s : ls2){
            System.out.println("ls2: "+ s);
        }

    }


    /**
     * 中缀表达式转后缀表达式
     * @param infixItemList
     * @return
     */
    public static List<String> infix2Suffix(List<String> infixItemList){


        Stack<String> operStack = new Stack<>();
//        Stack<String> suffixStack = new Stack<>();
        List<String> resList = new ArrayList<>();



        for (String item : infixItemList){

            if(digitPattern.matcher(item).matches()){
                resList.add(item);
            }else if(item.equals("(")){
                operStack.push(item);
            }else if(item.equals(")")){

                while (! operStack.peek().equals("(")){
                    resList.add(operStack.pop());
                }
                operStack.pop();

            }else {

                while (! operStack.isEmpty()
                        && (Operation.priority(item) <= Operation.priority(operStack.peek()))){
                    resList.add(operStack.pop());
                }
                operStack.push(item);
            }

        }

        while (! operStack.isEmpty()){
            resList.add(operStack.pop());
        }

        return resList;

    }

    /**
     *
     * @param expression
     * @param s
     * @return
     */
    public static List<String> getExpressionList(String expression, String s){

        String[] expressionSpilt = expression.split(s);

        List<String> expressionList = new ArrayList<>();
        for(String item : expressionSpilt){

            expressionList.add(item);
        }

        return expressionList;
    }


    /**
     *
     * @param expression
     * @return
     */
    public static List<String> getExpressionList(String expression){


        List<String> expressionList = new ArrayList<>();
        int i = 0;
        String str = "";
        char c = ' ';

        do {
            if (( c = expression.charAt(i)) < 48 || (c = expression.charAt(i)) > 57){
                expressionList.add("" + c);
                i++;
            }else {
                str = "";
                while (i < expression.length()
                        && (c = expression.charAt(i)) >= 48
                        && (c = expression.charAt(i)) <= 57){
                    str += c;
                    i++;
                }
                expressionList.add(str);
            }
        }while (i < expression.length());

        return expressionList;
    }

}


class Operation{

    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;


    public static int priority(String oper){

        int result = 0;

        switch (oper){

            case "+": result = ADD; break;
            case "-": result = SUB; break;
            case "*": result = MUL; break;
            case "/": result = DIV; break;
            default:System.out.println("没有该操作符");

        }

        return result;


    }




}

