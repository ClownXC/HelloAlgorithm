package com.algorithm.stack.leetcode;

import com.algorithm.stack.ExpressionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author zxc
 *
 *  后缀表达式
 *
 *
 *
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
public class ReversePolishNotationDemo {





    public static void main(String[] args){
        String suffixExpression = "3 4 + 5 * 6 -";
        List<String> suffixList = ExpressionUtil.getExpressionList(suffixExpression, " ");
        long res = calculate(suffixList);
        System.out.println("res:    "+res);



    }





    public static long calculate(List<String> expressionList){

        Stack<String> stack = new Stack<>();
        for (String item : expressionList){

            if (item.matches("\\d+")){
                stack.push(item);
            }else {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = 0;

                switch (item){

                    case "+": res = num2 + num1; break;
                    case "-": res = num2 - num1; break;
                    case "*": res = num2 * num1;; break;
                    case "/": res = num2 / num1;; break;
                    default:System.out.println("没有该操作符");

                }

                stack.push("" + res);
            }

        }
        return Integer.parseInt(stack.pop());

    }


}


