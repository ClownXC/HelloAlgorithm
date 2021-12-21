package cs;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class CLHLock {

    static class Node{
        boolean isLocked = true;
    }

    private volatile Node tail;
    private static final AtomicReferenceFieldUpdater<CLHLock, Node> updater =
            AtomicReferenceFieldUpdater.newUpdater(CLHLock.class, Node.class, "tail");


    public void lock(Node currentNode){
        Node preNode = updater.getAndSet(this, currentNode);
        if(preNode != null){
            while (preNode.isLocked){

            }
        }
    }


    public void unlock(Node currentNode){

        if ( !updater.compareAndSet(this, currentNode, null)){
            currentNode.isLocked = false;
        }

    }




}
