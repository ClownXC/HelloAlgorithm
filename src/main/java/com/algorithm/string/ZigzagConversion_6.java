package com.algorithm.string;


/**
 * @author zxc
 *
 *   将字符串 "PAYPALISHIRING" 以Z字形排列成给定的行数：（下面这样的形状）
 *    P     A     H     N
 *    A  P  L  S  I  I  G
 *    Y     I     R
 *   之后按逐行顺序依次排列："PAHNAPLSIIGYIR"
 *   实现一个将字符串进行指定行数的转换的函数:
 *   string convert(string text, int nRows);
 *   convert("PAYPALISHIRING", 3) 应当返回 "PAHNAPLSIIGYIR" 。
 *
 */
public class ZigzagConversion_6 {


    /**
     *
     *
     *
     *
     * @param s
     * @return
     */
    public String convert(String s, int numRows) {

        int len = s.length();

        if (len == 0 || numRows == 0 || numRows == 1) {
            return s;
        }

        //两整列之间的差 也就是等差数列中的d
        int gap = 2 * (numRows - 1);
        String result = "";

        for (int i = 0; i < numRows; i++){

            int j = i;
            String temp = "";
            while (j < len){

                temp += s.charAt(j);
                int middle = j + 2*(numRows-i-1);

                if(i != 0 && (i != numRows-1) && middle < len){
                    temp += s.charAt(middle);
                }
                j += gap;
            }
            result += temp;
        }
        return result ;

    }




    public static void main(String[] args){


        ZigzagConversion_6 test = new ZigzagConversion_6();
        String len = test.convert("PAYPALISHIRING",3);
        System.out.println("result:------"+len);




    }

}
