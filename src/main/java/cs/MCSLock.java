package cs;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class MCSLock {

    static class Node{
        Node next;
        boolean isLocked = false;

    }


    volatile Node tail;
    private static final AtomicReferenceFieldUpdater<MCSLock, Node> tailUpdater =
            AtomicReferenceFieldUpdater.newUpdater(MCSLock.class, Node.class, "tail");


    public void lock(Node currentNode){

        Node preNode = tailUpdater.getAndSet(this, currentNode);
        if(preNode != null){
            preNode.next = currentNode;
            while (currentNode.isLocked){

            }
        }



    }

    public void unlock(Node currentThreadMcsNode) {

        //UPDATER.get(this) 获取最后加入的线程的node
        //如果获取到的最后加入的node和当前node（currentThreadMcsNode）不相同，表示还有其他线程等待锁，    		 			直接修改后继者的isLocked属性。
        //相同代表当前没其他有线程等待锁，进入下面的处理
        if (tailUpdater.get(this) == currentThreadMcsNode) {//step1
            //这个时候可能会有其他线程又加入了进来，检查时候有人排在自己后面，currentThreadMcsNode.next 							表示依然没有染排在自己后面
            if (currentThreadMcsNode.next == null) { //step2
                //将 tail 设置为空，如果返回true设置成功，如果返回false，表示设置失败（其他线程加入了进										来，使得当前tail持有的节点不等于currentThreadMcsNode）
                if (tailUpdater.compareAndSet(this, currentThreadMcsNode, null)) {// //step3
                    // 设置成功返回，没有其他线程等待锁
                    return;
                } else {
                    // 突然有其他线程加入，需要检测后继者是否有值，
                    // 因为：step4执行完后，step5可能还没执行完
                    while (currentThreadMcsNode.next == null) {
                    }
                }
            }
            //修改后继者的isLocked,通知后继者结束自旋
            currentThreadMcsNode.next.isLocked = false;
            currentThreadMcsNode.next = null;// for GC
        }
    }


}
