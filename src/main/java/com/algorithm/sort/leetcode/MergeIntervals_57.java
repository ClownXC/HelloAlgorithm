package com.algorithm.sort.leetcode;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 *
 * @author chen
 *
 *
 *
 *接着从第一个区间开始遍历，对每个区间执行如下操作：
 */
public class MergeIntervals_57 {

    /**
     *
     * @param intervals
     * @return
     */
    public static List<Interval> merge(List<Interval> intervals) {

        sortIntervals(intervals);

        int i = 0;
        while (i < intervals.size()) {

            if (i != intervals.size() - 1) {

                if (intervals.get(i).end >= intervals.get(i + 1).
                        start && intervals.get(i).end <= intervals.get(i + 1).end) {

                    int tempStart = intervals.get(i).start;
                    int tempEnd = intervals.get(i + 1).end;

                    intervals.remove(i);
                    intervals.get(i).start = tempStart;
                    intervals.get(i).end = tempEnd;

                } else if (intervals.get(i).end < intervals.get(i + 1).start) {
                    i++;
                } else if (intervals.get(i).end > intervals.get(i + 1).end) {
                    intervals.remove(i + 1);
                    i++;
                }
            } else {
                i++;
            }
        }
        return intervals;
    }

    /**
     * 从例子可以看出，两个区间若能合并，则第一个区间的右端点一定不小于第二个区间的左端点
     * 所以先把区间集合按照左端点从小到大进行排序
     * @param intervals
     */
    private static void sortIntervals(List<Interval> intervals) {
        Collections.sort(intervals, (o1, o2) -> {
            if (o1.start > o2.start)
                return 1;
            else if (o1.start < o2.start)
                return -1;
            else
                return 0;
        });
    }


    public static void main(String[] args) {

    }


}

class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }
}