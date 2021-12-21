package com.algorithm.string;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen
 */
public class UniqueMorseCode_804 {

    public static int uniqueMorseRepresentations(String[] words){

       String morse[] = {".-","-...","-.-.","-..",".","..-.",
                "--.","....","..",".---","-.-",".-..","--","-.","---",
                ".--.","--.-",".-.","...","-","..-","...-",".--","-..-",
                "-.--","--.."};


        Set<String> concatenation = new HashSet<>();
        String str;
        for(String w : words)
        {
            str = "";
            for(char c : w.toCharArray()){
                str += morse[c - 'a'];
            }
            concatenation.add(str);
        }
        return concatenation.size();
    }

    public static void main(String[] args) {

        String[] words = {"gin", "zen", "gig", "msg"};
        System.out.println("---"+UniqueMorseCode_804.uniqueMorseRepresentations(words));
    }





}
