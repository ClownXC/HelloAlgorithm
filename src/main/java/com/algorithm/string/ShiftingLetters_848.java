package com.algorithm.string;

public class ShiftingLetters_848 {


    /**
     *
     * @param S
     * @param shifts
     * @return
     */
    public static String shifting(String S, int[] shifts) {

        char[] chars = S.toCharArray();

        for (int i = shifts.length - 2; i >= 0; --i) {
            shifts[i] = (shifts[i] + shifts[i + 1]) % 26;
        }
        for (int i = 0; i < shifts.length; ++i) {
            chars[i] = (char)((chars[i] - 'a' + shifts[i]) % 26 + 'a');
        }
        return String.valueOf(chars);
    }





    public static void main(String[] args) {

        int[] shifts = {3, 5, 9};
        System.out.println("---: "+ShiftingLetters_848.shifting("abc", shifts));

    }
}
