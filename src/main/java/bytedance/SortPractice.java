package bytedance;

import java.util.Arrays;
import java.util.List;

public class SortPractice {


    /**
     * 数组的相对排序
     */
    class RelativeSortArray {

        public int[] relativeSortArray(int[] arr1, int[] arr2) {
            //由于arr1的可能取值为0-1000，共1001个取值，不算一个很大的取值范围，所以可以利用桶式排序
            int[] bucket = new int[1001];
            //计数每个桶有多少个数，也就是每个值在数组中有几个
            for (int num : arr1) {
                bucket[num]++;
            }
            //由于重新排序不会改变数组的长度，所以可以利用原数组，可以节省空间复杂度
            int i = 0;
            //由于排序是按照相对顺序进行排序，所以，首先根据arr2中的桶的顺序，依次从对应的桶中取数到arr1中
            //并注意，每拿出一个数，需要将对桶中的数的个数进行-1操作
            for (int num : arr2) {
                while (bucket[num]-- > 0) {
                    arr1[i++] = num;
                }
            }
            //未在arr2中的桶中的数，按照桶号升序进行输出，所以进行二次遍历
            for (int j = 0; j < 1001; ++j) {
                while (bucket[j]-- > 0) {
                    arr1[i++] = j;
                }
            }
            return arr1;
        }
    }


    /**
     * 对链表进行插入排序
     */
    class InsertionSortList {

        public ListNode insertionSortList(ListNode head) {

            ListNode dummy = new ListNode(Integer.MIN_VALUE);

            ListNode pre = dummy;
            ListNode tail = dummy;
            ListNode cur = head;

            while (cur != null) {
                if (tail.val < cur.val) {
                    tail.next = cur;
                    tail = cur;
                    cur = cur.next;
                } else {
                    ListNode tmp = cur.next;
                    tail.next = tmp;
                    while (pre.next != null && pre.next.val < cur.val) pre = pre.next;
                    cur.next = pre.next;
                    pre.next = cur;
                    pre = dummy;
                    cur = tmp;
                }
            }
            return dummy.next;
        }


        class ListNode {

            int val;
            ListNode next;

            ListNode(int val) {
                this.val = val;
            }

        }
    }


    /**
     * 颜色分类
     */
    class SortColors {


        public void sortColors(int[] nums) {

            int p0 = 0, curr = 0;
            int p2 = nums.length - 1;

            int tmp;
            while (curr <= p2) {
                if (nums[curr] == 0) {
                    tmp = nums[p0];
                    nums[p0++] = nums[curr];
                    nums[curr++] = tmp;
                } else if (nums[curr] == 2) {
                    tmp = nums[curr];
                    nums[curr] = nums[p2];
                    nums[p2--] = tmp;
                } else curr++;
            }
        }
    }


    /**
     * 排序链表
     */
    class Solution {


        public ListNode sortList(ListNode head) {

            if (head == null || head.next == null) {
                return head;
            }

            ListNode mid = findMiddle(head);
            ListNode right = sortList(mid.next);
            mid.next = null;

            ListNode left = sortList(head);

            return merge(left, right);
        }

        /**
         * 找出中间的节点
         */
        public ListNode findMiddle(ListNode node) {
            ListNode fast = node.next;
            ListNode slow = node;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }

            return slow;
        }

        /**
         * 对两组链表进行归并排序
         */
        public ListNode merge(ListNode left, ListNode right) {


            ListNode a = left;
            ListNode b = right;

            ListNode result = new ListNode(0);
            ListNode tmp = result;

            while (a != null && b != null) {

                while (a != null && a.val <= b.val) {
                    tmp.next = new ListNode(a.val);
                    tmp = tmp.next;
                    a = a.next;
                }

                while (a != null && b != null && b.val <= a.val) {
                    tmp.next = new ListNode(b.val);
                    tmp = tmp.next;
                    b = b.next;
                }
            }

            if (a != null) {
                tmp.next = a;
            } else if (b != null) {
                tmp.next = b;
            }

            return result.next;
        }




        class ListNode {

            int val;
            ListNode next;

            ListNode(int val) {
                this.val = val;
            }

        }
    }


    /**
     *
     */
    class LargestNumber {

        private StringBuilder res;

        public String largestNumber(int[] nums) {
            res = new StringBuilder();
            Arrays.stream(nums).boxed()
                    .map(x -> x.toString())
                    .sorted((x, y) -> (y + x).compareTo(x + y))
                    .forEach(x -> res.append(x));
            return res.charAt(0) == '0' ? "0" : res.toString();
        }
    }


//    /**
//     * 计算右侧小于当前元素的个数
//     */
//    class CountSmaller {
//        public List<Integer> countSmaller(int[] nums) {
//
//        }
//    }


































}










