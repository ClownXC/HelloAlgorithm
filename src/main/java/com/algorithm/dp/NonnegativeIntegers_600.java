package com.algorithm.dp;




public class NonnegativeIntegers_600 {

    /**
     *
     * 分情况讨论记录f[i]为000...11111(i个1）中不包含连续的1的个数
     * 当i位为0，则右面的几位可以不受当前为影响f[i-1];
     * 当i位位1，则下一位只能为0，i-2位受影响，f[i-2]；
     * 得f[i]=f[i-1]+f[i-2];
     * 接下来对于任意一个数，可以分为比如9 1001b，
     * 可以分为0000-0111 和 1000-1001两部分，
     * 第一部分可以直接得到，
     * 第二部分属于多出的部分，
     * 需要继续拆解。
     * 所以每当遇到当前位1加上f[i]，
     * 如果当前位为0，的没有多出，无影响，继续往下
     * 当发现当前为1而且下一位也为1的时候，因为下一位只能取0，所以能选取的数都比下一位为1的时候要小，所以直接返回。
     *
     *
     *
     * @param i
     * @return
     */
    public static int negativeIntegers(int i){
        return 0;
    }

    public static void main(String[] args) {

    }
}
