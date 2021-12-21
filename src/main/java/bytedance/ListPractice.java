package bytedance;

import java.util.*;

public class ListPractice {

    public static void main(String[] args) {


    }


    /**
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {


        ListNode rhead = null;

        while (head != null) {

            ListNode tmp = head.next;
            head.next = rhead;
            rhead = head;
            head = tmp;
        }

        return rhead;

    }


    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {


        ListNode dummyHead = new ListNode(0);
        ListNode p = l1;
        ListNode q = l2;
        ListNode curr = dummyHead;

        int carry = 0;
        while (p != null || q != null) {

            int x = (p != null) ? p.data : 0;
            int y = (q != null) ? q.data : 0;

            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) {
                p = p.next;
            }
            if (q != null) {
                q = q.next;
            }
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    /**
     * 给定两个非空链表来代表两个非负整数。
     * 数字最高位位于链表开始位置。它们的每个节点只存储单个数字。
     * 将这两数相加会返回一个新的链表。
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {

        Stack<Integer> stack1 = new Stack();
        Stack<Integer> stack2 = new Stack();
        ListNode node1 = l1;
        while (node1 != null) {
            stack1.push(node1.data);
            node1 = node1.next;
        }
        ListNode node2 = l2;
        while (node2 != null) {
            stack2.push(node2.data);
            node2 = node2.next;
        }
        ListNode head = null;


        int flag = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || flag != 0) {
            int value = 0;
            if (!stack1.isEmpty()) {
                value += stack1.pop();
            }

            if (!stack2.isEmpty()) {
                value += stack2.pop();
            }

            value += flag;
            ListNode node = new ListNode(value % 10);
            flag = value / 10;
            node.next = head;
            head = node;
        }
        return head;
    }


    /**
     * 删除链表中的节点
     *
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.data = node.next.data;
        node.next = node.next.next;
    }

    //------------------------------------------------------------------------------------------------------------

    /**
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode node = dummy;
        //找到需要反转的那一段的上一个节点。
        for (int i = 1; i < m; i++) {
            node = node.next;
        }
        //node.next就是需要反转的这段的起点。
        ListNode nextHead = node.next;
        ListNode next = null;
        ListNode pre = null;
        //反转m到n这一段
        for (int i = m; i <= n; i++) {
            next = nextHead.next;
            nextHead.next = pre;
            pre = nextHead;
            nextHead = next;
        }
        //将反转的起点的next指向next。
        node.next.next = next;
        //需要反转的那一段的上一个节点的next节点指向反转后链表的头结点
        node.next = pre;
        return dummy.next;
    }


    public ListNode removeNthFromEnd(ListNode head, int n) {


        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;

        for (int i = 0; i <= n + 1; i++) {
            first = first.next;
        }

        while (first != null) {
            first = first.next;
            second = second.next;
        }

        second.next = second.next.next;
        return dummy.next;


    }

    /**
     * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {

        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.next.data == current.data) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;

    }


    /**
     * 为了防止删除头结点的极端情况发生，先创建空结点dummy，使dummy指向传入的head结点。
     * 然后创建cur的指针，指向链表的头部（即dummy）。
     * 接着对cur指针迭代，因为要对比cur(cur最初始的定义指向空结点)指针的下一个结点与下下一个结点的值是否相等，为了防止产生空指针异常，故退出迭代的条件为：cur.next != null && cur.next.next != null。
     * 在迭代过程中，如果cur.next.val == cur.next.next.val说明此时有重复元素，此时创建一个临时指针temp，指向cur的下一个节点，即temp指向的第一个重复元素所在的位置。通过while循环去重，去重后，temp指向的是重复元素中的最后一个位置。最后cur.next = temp.next就实现了消除重复元素。
     * 当然，如果为发现重复元素，则直接向后迭代即可。
     * 迭代完成后，返回dummy的next。
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates2(ListNode head) {

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {

            if (cur.next.data == cur.next.next.data) {
                ListNode temp = cur.next;
                while (temp != null && temp.next != null && temp.data == temp.next.data) {
                    temp = temp.next;
                }
                cur.next = temp.next;
            } else {
                cur = cur.next;
            }

        }
        return dummy.next;
    }


    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        if (headA == null || headB == null) return null;
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != pB) {
            pA = (pA == null) ? headB : pA.next;
            pB = (pB == null) ? headA : pB.next;
        }
        return pA;
    }


    /**
     * 当用慢指针 slow 遍历列表时，让另一个指针 fast 的速度是它的两倍。
     * 当 fast 到达列表的末尾时，slow 必然位于中间。
     *
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //------------------------------------------------------------------------------------------------------------



    class IsPalindrome {

        public boolean isPalindrome(ListNode head) {
            if (head == null) return true;
            ListNode slow = findMid(head);
            if (head == slow) {//处理只有一个或两个节点的情况
                if (head.next == null || head.data == head.next.data) return true;
                else return false;
            }
            reverseList(slow);//反转链表的后半部分
            boolean result = compare(head, slow.next);
            reverseList(slow);//恢复链表原本的结构
            return result;
        }

        private ListNode findMid(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }

        private ListNode reverseList(ListNode head) {
            ListNode pre = null;
            ListNode curr = head;
            while (curr != null) {
                ListNode next = curr.next;
                curr.next = pre;
                pre = curr;
                curr = next;
            }
            return pre;
        }

        private boolean compare(ListNode one, ListNode two) {
            while (one != null && two != null) {
                if (one.data != two.data) return false;
                one = one.next;
                two = two.next;
            }
            return true;
        }
    }

    //------------------------------------------------------------------------------------------------------------


    /**
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {

        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    /**
     * 链表中包含环，请找出该链表的环的入口节点
     * 使用快慢指针，slow 一次走一步，fast一次走两步，当两个相遇时，fast指针再重新指向head
     * 此时fast一次走一步，slow一次走一步，再次相遇则为入口
     *
     * @param head
     * @return
     */
    public ListNode entryNodeOfLoop(ListNode head) {


        if (head == null || head.next == null)
            return null;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {

            slow = slow.next; //走一步
            fast = fast.next.next; //走两步

            if (slow == fast) { //第一次相遇

                fast = head;
                while (fast != slow) {
                    fast = fast.next; //相遇后走一步
                    slow = slow.next; //相遇后走一步
                }
                //第二次相遇
                if (slow == fast) {
                    return slow;
                }

            }
        }
        return null;
    }

    //------------------------------------------------------------------------------------------------------------

    /**
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);

        ListNode prev = dummy;

        while (l1 != null && l2 != null) {

            if (l1.data <= l2.data) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        prev.next = (l1 == null) ? l2 : l1;

        return dummy.next;
    }


    /**
     * @param lists
     * @return
     */
    public ListNode mergeKLists2(ListNode[] lists) {


        if (lists == null || lists.length == 0) {
            return null;
        }
        return solve(lists, 0, lists.length - 1);


    }

    //通过mid将数组一分为二，并不断缩小规模，当规模为1时返回并开始合并
    //通过合并两个链表，不断增大其规模，整体看就是不断缩小-最后不断扩大的过程
    private ListNode solve(ListNode[] lists, int begin, int end) {
        if (begin == end) {
            return lists[begin];
        }
        int mid = begin + (end - begin) / 2;
        ListNode left = solve(lists, begin, mid);
        ListNode right = solve(lists, mid + 1, end);
        return mergeTwoLists(left, right);
    }


    //------------------------------------------------------------------------------------------------------------

    public ListNode partition(ListNode head, int x) {


        ListNode before_head = new ListNode(0);
        ListNode before = before_head;
        ListNode after_head = new ListNode(0);
        ListNode after = after_head;

        while (head != null) {

            if (head.data < x) {
                before.next = head;
                before = before.next;
            } else {
                // If the original list node is greater or equal to the given x,
                // assign it to the after list.
                after.next = head;
                after = after.next;
            }

            // move ahead in the original list
            head = head.next;
        }

        after.next = null;
        before.next = after_head.next;

        return before_head.next;
    }


    //------------------------------------------------------------------------------------------------------------

    /**
     *
     */
    class Solution {
        public ListNode rotateRight(ListNode head, int k) {

            if (head == null) return null;
            if (head.next == null) return head;

            // close the linked list into the ring
            ListNode old_tail = head;
            int n = 1;
            while(old_tail.next != null){

                old_tail = old_tail.next;
                n++;
            }

            old_tail.next = head;

            ListNode new_tail = head;
            for (int i = 0; i < n - (k % n) - 1; i++) {
                new_tail = new_tail.next;
            }

            ListNode new_head = new_tail.next;

            // break the ring
            new_tail.next = null;

            return new_head;
        }
    }


    /**
     * 将奇节点放在一个链表里，偶链表放在另一个链表里。然后把偶链表接在奇链表的尾部。
     *
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {

        if (head == null) return null;
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }


    /**
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {

        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode pre = dummy;
        ListNode tail = dummy;
        ListNode cur = head;

        while (cur != null) {
            if (tail.data < cur.data) {
                tail.next = cur;
                tail = cur;
                cur = cur.next;
            } else {
                ListNode tmp = cur.next;
                tail.next = tmp;
                while (pre.next != null && pre.next.data < cur.data) pre = pre.next;
                cur.next = pre.next;
                pre.next = cur;
                pre = dummy;
                cur = tmp;
            }
        }
        return dummy.next;
    }


    /**
     * @param head
     * @return
     */
    public ListNode plusOne(ListNode head) {
        // sentinel head
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;
        ListNode notNine = sentinel;

        // find the rightmost not-nine digit
        while (head != null) {
            if (head.data != 9) notNine = head;
            head = head.next;
        }

        // increase this rightmost not-nine digit by 1
        notNine.data++;
        notNine = notNine.next;

        // set all the following nines to zeros
        while (notNine != null) {
            notNine.data = 0;
            notNine = notNine.next;
        }

        return sentinel.data != 0 ? sentinel : sentinel.next;
    }


    /**
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;

        ListNode prev = sentinel, curr = head;
        while (curr != null) {
            if (curr.data == val) prev.next = curr.next;
            else prev = curr;
            curr = curr.next;
        }
        return sentinel.next;
    }


    /**
     * 在 O(nlogn) 时间复杂度和常数级空间复杂度下，对链表进行排序。
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }


        ListNode tmp = slow.next;
        slow.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(tmp);

        ListNode h = new ListNode(0);
        ListNode res = h;

        while (left != null && right != null) {
            if (left.data < right.data) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        h.next = left != null ? left : right;
        return res.next;
    }

    /**
     * @param root
     * @param k
     * @return
     */
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode cur = root;
        int N = 0;
        while (cur != null) {
            cur = cur.next;
            N++;
        }

        int width = N / k, rem = N % k;

        ListNode[] ans = new ListNode[k];
        cur = root;
        for (int i = 0; i < k; ++i) {
            ListNode head = new ListNode(0), write = head;
            for (int j = 0; j < width + (i < rem ? 1 : 0); ++j) {
                write = write.next = new ListNode(cur.data);
                if (cur != null) cur = cur.next;
            }
            ans[i] = head.next;
        }
        return ans;
    }

    /**
     * @param head
     */
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        //1. 使用快慢指针,找出链表的中心节点。
        ListNode middle = middleNode(head);

        //2. 将原始链表按照中心链表分割为两个链表，并将右链表反转
        //2.1 原始链表：1->2->3->4->5 左链表：1->2->3 右链表：4->5
        ListNode left = head;
        ListNode right = middle.next;
        middle.next = null;

        //2.2 反转右链表
        //原始右链表：4->5 反转后：5->4
        right = reverse(right);

        //3. 合并两个链表，将右链表插入到左链表
        //左链表：1->2->3 右链表：4->5 合并后：1->5->2->4->3
        merge(left, right);
    }


    public void merge(ListNode left, ListNode right) {
        ListNode leftTemp;
        ListNode rightTemp;
        while (left.next != null && right != null) {
            //1. 保存next节点
            leftTemp = left.next;
            rightTemp = right.next;

            //2. 将右链表的第一个节点插入到左链表中
            // 左链表：1->2->3 右链表：5->4
            // 合并后的左链表：1->5->2->3
            left.next = right;
            right.next = leftTemp;

            //3. 移动left和right指针
            //左链表变为：2->3 右链表变为：4
            left = leftTemp;
            right = rightTemp;
        }
    }


    /**
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {

        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode temp = pre;


        while (temp.next != null && temp.next.next != null) {
            ListNode start = temp.next;
            ListNode end = temp.next.next;
            temp.next = end;
            start.next = end.next;
            end.next = start;
            temp = start;
        }
        return pre.next;
    }


    public ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }


    /**
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode curr = dummy;

        while (curr.next != null) {

            for (int i = 0; i < k && curr != null; i++) {
                curr = curr.next;
            }
            if (curr == null) break;
            ListNode start = pre.next;
            ListNode next = curr.next;
            curr.next = null;
            pre.next = reverse(start);
            start.next = next;
            pre = start;

            curr = pre;
        }
        return dummy.next;
    }


    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }


    /**
     * @param head
     * @return
     */
    public int[] nextLargerNodes(ListNode head) {


        int[] arr = new int[10000];
        int[] valueArr = new int[10000];
        Stack<Integer> stack = new Stack<>();
        int length = 0;
        int value;
        while (head != null) {
            value = head.data;
            valueArr[length] = value;
            while (!stack.isEmpty() && value > valueArr[stack.peek()]) {
                arr[stack.pop()] = value;
            }
            stack.add(length);
            length++;
            head = head.next;
        }
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = arr[i];
        }
        return result;
    }


    /**
     * @param head
     * @return
     */
    public int getDecimalValue(ListNode head) {
        ListNode p = head;
        int result = 0;

        while (p != null) {
            result *= 2;
            result += p.data;
            p = p.next;
        }

        return result;
    }


    /**
     * 删除排序链表中的重复元素 II
     */
    class DeleteDuplicates{
        public ListNode deleteDuplicates(ListNode head) {

            ListNode dummyNode = new ListNode(-1);
            dummyNode.next = head;

            ListNode cur = dummyNode;
            while (cur.next != null && cur.next.next != null){

                if (cur.next.data == cur.next.next.data){
                    ListNode node = cur.next;
                    while (node != null && node.next.next != null && node.next.data == node.data){
                        node = node.next;
                    }
                    cur.next = node.next;
                }

                cur = cur.next;
            }
            return dummyNode.next;
        }
     }

















    

    /**
     * 链表组件
     */
    class NumComponents {
        public int numComponents(ListNode head, int[] G) {

            Set<Integer> Gset = new HashSet();
            for (int x: G) Gset.add(x);

            ListNode cur = head;
            int ans = 0;

            while (cur != null) {
                if (Gset.contains(cur.data) &&
                        (cur.next == null || !Gset.contains(cur.next.data)))
                    ans++;
                cur = cur.next;
            }

            return ans;

        }



    }










class ListNode {

    int data;
    ListNode next;

    ListNode(int data) {
        this.data = data;
    }

}
}
