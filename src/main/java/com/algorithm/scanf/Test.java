package com.algorithm.scanf;

import java.util.Arrays;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {

        Test.test2Array();




    }

    /**
     *
     */
    public static void testArray(){

        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        String[] arr = new String[len];

        for(int i = 0; i < len; i++) {
            arr[i] = scanner.next();
        }
        System.out.println(Arrays.toString(arr));

    }


    /**
     *
     */
    public static void test2Array(){

        Scanner scanner = new Scanner(System.in);

        int x = scanner.nextInt();
        int y = scanner.nextInt();

        String[][] arr = new String[x][y];

        for(int i = 0; i < x; i++) {
            for(int j = 0; j < y; j++) {
                arr[i][j] = scanner.next();
            }
        }
        System.out.println(Arrays.toString(arr));

    }
}
