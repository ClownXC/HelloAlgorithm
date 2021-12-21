package com.algorithm.string;

/**
 *
 */
public class ToLowerCase_709 {


    public static String toLowerCase(String str){

        String ans = "";
        for(int i=0; i<str.length(); i++){
            char a = str.charAt(i);
            a = tolowercase(a);
            ans += a;
        }

        return ans;

    }

    /**
     *
     * @param t
     * @return
     */
    private static char tolowercase(char t){

        if(t >= 'A' && t <= 'Z'){
            t = (char)(t+32);
        }
        return t;
    }



    public static void main(String[] args) {
        System.out.println(ToLowerCase_709.toLowerCase("GRHHRHRssasdDDD"));
    }

}
