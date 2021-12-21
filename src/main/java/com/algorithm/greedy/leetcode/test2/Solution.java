package com.algorithm.greedy.leetcode.test2;


import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution {


    public static void main(String[] args) {


    }


    public static int findMinArrowShots(Pair<Integer, Integer>[] points) {

        if (points.length == 0) {
            return 0;
        }

//        Arrays.sort(points.);
        int shootNum = 1;
        int shootBegin = points[0].getKey();
        int shootEnd = points[0].getValue();

        for (int i = 1; i < points.length; i++) {
            if (points[i].getLeft() <= shootEnd) {
                shootBegin = points[i].getKey();
                if (shootEnd > points[i].getValue()) {
                    shootEnd = points[i].getRight();
                }
            } else {
                shootNum++;
                shootBegin = points[i].getLeft();
                shootBegin = points[i].getRight();
            }
        }

        return shootNum;


    }

    public static boolean cmp(Pair<Integer, Integer> a, Pair<Integer, Integer> b) {
        return a.getLeft() < b.getLeft();
    }


    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length < 1) {
            return intervals.length;
        }
        Arrays.sort(intervals, (int[] a, int[] b) -> {
            return a[1] != b[1] ? a[1] - b[1] : a[0] - b[0];

        });
        ArrayList<int[]> arrayList = new ArrayList<>();
        arrayList.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= arrayList.get(arrayList.size() - 1)[1]) {
                arrayList.add(intervals[i]);
            }

        }
        return intervals.length - arrayList.size();
    }


}
