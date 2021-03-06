//package com.algorithm.MyList;
//
//import java.util.*;
//import java.util.function.Consumer;
//
///**
// * Doubly-linked MyList implementation of the {@code MyList} and {@code Deque}
// * interfaces.  Implements all optional MyList operations, and permits all
// * elements (including {@code null}).
// *
// * <p>All of the operations perform as could be expected for a doubly-linked
// * MyList.  Operations that index into the MyList will traverse the MyList from
// * the beginning or the end, whichever is closer to the specified index.
// *
// * <p><strong>Note that this implementation is not synchronized.</strong>
// * If multiple threads access a linked MyList concurrently, and at least
// * one of the threads modifies the MyList structurally, it <i>must</i> be
// * synchronized externally.  (A structural modification is any operation
// * that adds or deletes one or more elements; merely setting the value of
// * an element is not a structural modification.)  This is typically
// * accomplished by synchronizing on some object that naturally
// * encapsulates the MyList.
// *
// * If no such object exists, the MyList should be "wrapped" using the
// * {@link Collections#synchronizedList Collections.synchronizedList}
// * method.  This is best done at creation time, to prevent accidental
// * unsynchronized access to the MyList:<pre>
// *   MyList MyList = Collections.synchronizedList(new LinkedList(...));</pre>
// *
// * <p>The iterators returned by this class's {@code iterator} and
// * {@code listIterator} methods are <i>fail-fast</i>: if the MyList is
// * structurally modified at any time after the iterator is created, in
// * any way except through the Iterator's own {@code remove} or
// * {@code add} methods, the iterator will throw a {@link
// * ConcurrentModificationException}.  Thus, in the face of concurrent
// * modification, the iterator fails quickly and cleanly, rather than
// * risking arbitrary, non-deterministic behavior at an undetermined
// * time in the future.
// *
// * <p>Note that the fail-fast behavior of an iterator cannot be guaranteed
// * as it is, generally speaking, impossible to make any hard guarantees in the
// * presence of unsynchronized concurrent modification.  Fail-fast iterators
// * throw {@code ConcurrentModificationException} on a best-effort basis.
// * Therefore, it would be wrong to write a program that depended on this
// * exception for its correctness:   <i>the fail-fast behavior of iterators
// * should be used only to detect bugs.</i>
// *
// * <p>This class is a member of the
// * <a href="{@docRoot}/../technotes/guides/collections/index.html">
// * Java Collections Framework</a>.
// *
// * @author  Josh Bloch
// * @see     MyList
// * @see     ArrayList
// * @since 1.2
// * @param <E> the type of elements held in this collection
// */
//
//public class LinkedList<E>
//        extends AbstractSequentialList<E>
//        implements MyList<E>, Deque<E>, Cloneable, java.io.Serializable
//{
//    transient int size = 0;
//
//    /**
//     * Pointer to first node.
//     * Invariant: (first == null && last == null) ||
//     *            (first.prev == null && first.item != null)
//     */
//    transient Node<E> first;
//
//    /**
//     * Pointer to last node.
//     * Invariant: (first == null && last == null) ||
//     *            (last.next == null && last.item != null)
//     */
//    transient Node<E> last;
//
//    /**
//     * Constructs an empty MyList.
//     */
//    public LinkedList() {
//    }
//
//    /**
//     * Constructs a MyList containing the elements of the specified
//     * collection, in the order they are returned by the collection's
//     * iterator.
//     *
//     * @param  c the collection whose elements are to be placed into this MyList
//     * @throws NullPointerException if the specified collection is null
//     */
//    public LinkedList(Collection<? extends E> c) {
//        this();
//        addAll(c);
//    }
//    private static class Node<E> {
//        E item;
//        Node<E> next;
//        Node<E> prev;
//
//        Node(Node<E> prev, E element, Node<E> next) {
//            this.item = element;
//            this.next = next;
//            this.prev = prev;
//        }
//    }
//    /**
//     * Links e as first element.
//     */
//    private void linkFirst(E e) {
//        final Node<E> f = first;
//        final Node<E> newNode = new Node<E>(null, e, f);
//        first = newNode;
//        if (f == null)
//            last = newNode;
//        else
//            f.prev = newNode;
//        size++;
//        modCount++;
//    }
//
//    /**
//     * Links e as last element.
//     */
//    void linkLast(E e) {
//        final java.util.LinkedList.Node<E> l = last;
//        final java.util.LinkedList.Node<E> newNode = new java.util.LinkedList.Node<>(l, e, null);
//        last = newNode;
//        if (l == null)
//            first = newNode;
//        else
//            l.next = newNode;
//        size++;
//        modCount++;
//    }
//
//    /**
//     * Inserts element e before non-null Node succ.
//     */
//    void linkBefore(E e, java.util.LinkedList.Node<E> succ) {
//        // assert succ != null;
//        final java.util.LinkedList.Node<E> pred = succ.prev;
//        final java.util.LinkedList.Node<E> newNode = new java.util.LinkedList.Node<>(pred, e, succ);
//        succ.prev = newNode;
//        if (pred == null)
//            first = newNode;
//        else
//            pred.next = newNode;
//        size++;
//        modCount++;
//    }
//
//    /**
//     * Unlinks non-null first node f.
//     */
//    private E unlinkFirst(java.util.LinkedList.Node<E> f) {
//        // assert f == first && f != null;
//        final E element = f.item;
//        final java.util.LinkedList.Node<E> next = f.next;
//        f.item = null;
//        f.next = null; // help GC
//        first = next;
//        if (next == null)
//            last = null;
//        else
//            next.prev = null;
//        size--;
//        modCount++;
//        return element;
//    }
//
//    /**
//     * Unlinks non-null last node l.
//     */
//    private E unlinkLast(java.util.LinkedList.Node<E> l) {
//        // assert l == last && l != null;
//        final E element = l.item;
//        final java.util.LinkedList.Node<E> prev = l.prev;
//        l.item = null;
//        l.prev = null; // help GC
//        last = prev;
//        if (prev == null)
//            first = null;
//        else
//            prev.next = null;
//        size--;
//        modCount++;
//        return element;
//    }
//
//    /**
//     * Unlinks non-null node x.
//     */
//    E unlink(java.util.LinkedList.Node<E> x) {
//        // assert x != null;
//        final E element = x.item;
//        final java.util.LinkedList.Node<E> next = x.next;
//        final java.util.LinkedList.Node<E> prev = x.prev;
//
//        if (prev == null) {
//            first = next;
//        } else {
//            prev.next = next;
//            x.prev = null;
//        }
//
//        if (next == null) {
//            last = prev;
//        } else {
//            next.prev = prev;
//            x.next = null;
//        }
//
//        x.item = null;
//        size--;
//        modCount++;
//        return element;
//    }
//
//    /**
//     * Returns the first element in this MyList.
//     *
//     * @return the first element in this MyList
//     * @throws NoSuchElementException if this MyList is empty
//     */
//    public E getFirst() {
//        final java.util.LinkedList.Node<E> f = first;
//        if (f == null)
//            throw new NoSuchElementException();
//        return f.item;
//    }
//
//    /**
//     * Returns the last element in this MyList.
//     *
//     * @return the last element in this MyList
//     * @throws NoSuchElementException if this MyList is empty
//     */
//    public E getLast() {
//        final java.util.LinkedList.Node<E> l = last;
//        if (l == null)
//            throw new NoSuchElementException();
//        return l.item;
//    }
//
//    /**
//     * Removes and returns the first element from this MyList.
//     *
//     * @return the first element from this MyList
//     * @throws NoSuchElementException if this MyList is empty
//     */
//    public E removeFirst() {
//        final java.util.LinkedList.Node<E> f = first;
//        if (f == null)
//            throw new NoSuchElementException();
//        return unlinkFirst(f);
//    }
//
//    /**
//     * Removes and returns the last element from this MyList.
//     *
//     * @return the last element from this MyList
//     * @throws NoSuchElementException if this MyList is empty
//     */
//    public E removeLast() {
//        final java.util.LinkedList.Node<E> l = last;
//        if (l == null)
//            throw new NoSuchElementException();
//        return unlinkLast(l);
//    }
//
//    /**
//     * Inserts the specified element at the beginning of this MyList.
//     *
//     * @param e the element to add
//     */
//    public void addFirst(E e) {
//        linkFirst(e);
//    }
//
//    /**
//     * Appends the specified element to the end of this MyList.
//     *
//     * <p>This method is equivalent to {@link #add}.
//     *
//     * @param e the element to add
//     */
//    public void addLast(E e) {
//        linkLast(e);
//    }
//
//    /**
//     * Returns {@code true} if this MyList contains the specified element.
//     * More formally, returns {@code true} if and only if this MyList contains
//     * at least one element {@code e} such that
//     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
//     *
//     * @param o element whose presence in this MyList is to be tested
//     * @return {@code true} if this MyList contains the specified element
//     */
//    public boolean contains(Object o) {
//        return indexOf(o) != -1;
//    }
//
//    /**
//     * Returns the number of elements in this MyList.
//     *
//     * @return the number of elements in this MyList
//     */
//    public int size() {
//        return size;
//    }
//
//    /**
//     * Appends the specified element to the end of this MyList.
//     *
//     * <p>This method is equivalent to {@link #addLast}.
//     *
//     * @param e element to be appended to this MyList
//     * @return {@code true} (as specified by {@link Collection#add})
//     */
//    public boolean add(E e) {
//        linkLast(e);
//        return true;
//    }
//
//    /**
//     * Removes the first occurrence of the specified element from this MyList,
//     * if it is present.  If this MyList does not contain the element, it is
//     * unchanged.  More formally, removes the element with the lowest index
//     * {@code i} such that
//     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>
//     * (if such an element exists).  Returns {@code true} if this MyList
//     * contained the specified element (or equivalently, if this MyList
//     * changed as a result of the call).
//     *
//     * @param o element to be removed from this MyList, if present
//     * @return {@code true} if this MyList contained the specified element
//     */
//    public boolean remove(Object o) {
//        if (o == null) {
//            for (java.util.LinkedList.Node<E> x = first; x != null; x = x.next) {
//                if (x.item == null) {
//                    unlink(x);
//                    return true;
//                }
//            }
//        } else {
//            for (java.util.LinkedList.Node<E> x = first; x != null; x = x.next) {
//                if (o.equals(x.item)) {
//                    unlink(x);
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    /**
//     * Appends all of the elements in the specified collection to the end of
//     * this MyList, in the order that they are returned by the specified
//     * collection's iterator.  The behavior of this operation is undefined if
//     * the specified collection is modified while the operation is in
//     * progress.  (Note that this will occur if the specified collection is
//     * this MyList, and it's nonempty.)
//     *
//     * @param c collection containing elements to be added to this MyList
//     * @return {@code true} if this MyList changed as a result of the call
//     * @throws NullPointerException if the specified collection is null
//     */
//    public boolean addAll(Collection<? extends E> c) {
//        return addAll(size, c);
//    }
//
//    /**
//     * Inserts all of the elements in the specified collection into this
//     * MyList, starting at the specified position.  Shifts the element
//     * currently at that position (if any) and any subsequent elements to
//     * the right (increases their indices).  The new elements will appear
//     * in the MyList in the order that they are returned by the
//     * specified collection's iterator.
//     *
//     * @param index index at which to insert the first element
//     *              from the specified collection
//     * @param c collection containing elements to be added to this MyList
//     * @return {@code true} if this MyList changed as a result of the call
//     * @throws IndexOutOfBoundsException {@inheritDoc}
//     * @throws NullPointerException if the specified collection is null
//     */
//    public boolean addAll(int index, Collection<? extends E> c) {
//        checkPositionIndex(index);
//
//        Object[] a = c.toArray();
//        int numNew = a.length;
//        if (numNew == 0)
//            return false;
//
//        java.util.LinkedList.Node<E> pred, succ;
//        if (index == size) {
//            succ = null;
//            pred = last;
//        } else {
//            succ = node(index);
//            pred = succ.prev;
//        }
//
//        for (Object o : a) {
//            @SuppressWarnings("unchecked") E e = (E) o;
//            java.util.LinkedList.Node<E> newNode = new java.util.LinkedList.Node<>(pred, e, null);
//            if (pred == null)
//                first = newNode;
//            else
//                pred.next = newNode;
//            pred = newNode;
//        }
//
//        if (succ == null) {
//            last = pred;
//        } else {
//            pred.next = succ;
//            succ.prev = pred;
//        }
//
//        size += numNew;
//        modCount++;
//        return true;
//    }
//
//    /**
//     * Removes all of the elements from this MyList.
//     * The MyList will be empty after this call returns.
//     */
//    public void clear() {
//        // Clearing all of the links between nodes is "unnecessary", but:
//        // - helps a generational GC if the discarded nodes inhabit
//        //   more than one generation
//        // - is sure to free memory even if there is a reachable Iterator
//        for (java.util.LinkedList.Node<E> x = first; x != null; ) {
//            java.util.LinkedList.Node<E> next = x.next;
//            x.item = null;
//            x.next = null;
//            x.prev = null;
//            x = next;
//        }
//        first = last = null;
//        size = 0;
//        modCount++;
//    }
//
//
//    // Positional Access Operations
//
//    /**
//     * Returns the element at the specified position in this MyList.
//     *
//     * @param index index of the element to return
//     * @return the element at the specified position in this MyList
//     * @throws IndexOutOfBoundsException {@inheritDoc}
//     */
//    public E get(int index) {
//        checkElementIndex(index);
//        return node(index).item;
//    }
//
//    /**
//     * Replaces the element at the specified position in this MyList with the
//     * specified element.
//     *
//     * @param index index of the element to replace
//     * @param element element to be stored at the specified position
//     * @return the element previously at the specified position
//     * @throws IndexOutOfBoundsException {@inheritDoc}
//     */
//    public E set(int index, E element) {
//        checkElementIndex(index);
//        java.util.LinkedList.Node<E> x = node(index);
//        E oldVal = x.item;
//        x.item = element;
//        return oldVal;
//    }
//
//    /**
//     * Inserts the specified element at the specified position in this MyList.
//     * Shifts the element currently at that position (if any) and any
//     * subsequent elements to the right (adds one to their indices).
//     *
//     * @param index index at which the specified element is to be inserted
//     * @param element element to be inserted
//     * @throws IndexOutOfBoundsException {@inheritDoc}
//     */
//    public void add(int index, E element) {
//        checkPositionIndex(index);
//
//        if (index == size)
//            linkLast(element);
//        else
//            linkBefore(element, node(index));
//    }
//
//    /**
//     * Removes the element at the specified position in this MyList.  Shifts any
//     * subsequent elements to the left (subtracts one from their indices).
//     * Returns the element that was removed from the MyList.
//     *
//     * @param index the index of the element to be removed
//     * @return the element previously at the specified position
//     * @throws IndexOutOfBoundsException {@inheritDoc}
//     */
//    public E remove(int index) {
//        checkElementIndex(index);
//        return unlink(node(index));
//    }
//
//    /**
//     * Tells if the argument is the index of an existing element.
//     */
//    private boolean isElementIndex(int index) {
//        return index >= 0 && index < size;
//    }
//
//    /**
//     * Tells if the argument is the index of a valid position for an
//     * iterator or an add operation.
//     */
//    private boolean isPositionIndex(int index) {
//        return index >= 0 && index <= size;
//    }
//
//    /**
//     * Constructs an IndexOutOfBoundsException detail message.
//     * Of the many possible refactorings of the error handling code,
//     * this "outlining" performs best with both server and client VMs.
//     */
//    private String outOfBoundsMsg(int index) {
//        return "Index: "+index+", Size: "+size;
//    }
//
//    private void checkElementIndex(int index) {
//        if (!isElementIndex(index))
//            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
//    }
//
//    private void checkPositionIndex(int index) {
//        if (!isPositionIndex(index))
//            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
//    }
//
//    /**
//     * Returns the (non-null) Node at the specified element index.
//     */
//    java.util.LinkedList.Node<E> node(int index) {
//        // assert isElementIndex(index);
//
//        if (index < (size >> 1)) {
//            java.util.LinkedList.Node<E> x = first;
//            for (int i = 0; i < index; i++)
//                x = x.next;
//            return x;
//        } else {
//            java.util.LinkedList.Node<E> x = last;
//            for (int i = size - 1; i > index; i--)
//                x = x.prev;
//            return x;
//        }
//    }
//
//    // Search Operations
//
//    /**
//     * Returns the index of the first occurrence of the specified element
//     * in this MyList, or -1 if this MyList does not contain the element.
//     * More formally, returns the lowest index {@code i} such that
//     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
//     * or -1 if there is no such index.
//     *
//     * @param o element to search for
//     * @return the index of the first occurrence of the specified element in
//     *         this MyList, or -1 if this MyList does not contain the element
//     */
//    public int indexOf(Object o) {
//        int index = 0;
//        if (o == null) {
//            for (java.util.LinkedList.Node<E> x = first; x != null; x = x.next) {
//                if (x.item == null)
//                    return index;
//                index++;
//            }
//        } else {
//            for (java.util.LinkedList.Node<E> x = first; x != null; x = x.next) {
//                if (o.equals(x.item))
//                    return index;
//                index++;
//            }
//        }
//        return -1;
//    }
//
//    /**
//     * Returns the index of the last occurrence of the specified element
//     * in this MyList, or -1 if this MyList does not contain the element.
//     * More formally, returns the highest index {@code i} such that
//     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
//     * or -1 if there is no such index.
//     *
//     * @param o element to search for
//     * @return the index of the last occurrence of the specified element in
//     *         this MyList, or -1 if this MyList does not contain the element
//     */
//    public int lastIndexOf(Object o) {
//        int index = size;
//        if (o == null) {
//            for (java.util.LinkedList.Node<E> x = last; x != null; x = x.prev) {
//                index--;
//                if (x.item == null)
//                    return index;
//            }
//        } else {
//            for (java.util.LinkedList.Node<E> x = last; x != null; x = x.prev) {
//                index--;
//                if (o.equals(x.item))
//                    return index;
//            }
//        }
//        return -1;
//    }
//
//    // Queue operations.
//
//    /**
//     * Retrieves, but does not remove, the head (first element) of this MyList.
//     *
//     * @return the head of this MyList, or {@code null} if this MyList is empty
//     * @since 1.5
//     */
//    public E peek() {
//        final java.util.LinkedList.Node<E> f = first;
//        return (f == null) ? null : f.item;
//    }
//
//    /**
//     * Retrieves, but does not remove, the head (first element) of this MyList.
//     *
//     * @return the head of this MyList
//     * @throws NoSuchElementException if this MyList is empty
//     * @since 1.5
//     */
//    public E element() {
//        return getFirst();
//    }
//
//    /**
//     * Retrieves and removes the head (first element) of this MyList.
//     *
//     * @return the head of this MyList, or {@code null} if this MyList is empty
//     * @since 1.5
//     */
//    public E poll() {
//        final java.util.LinkedList.Node<E> f = first;
//        return (f == null) ? null : unlinkFirst(f);
//    }
//
//    /**
//     * Retrieves and removes the head (first element) of this MyList.
//     *
//     * @return the head of this MyList
//     * @throws NoSuchElementException if this MyList is empty
//     * @since 1.5
//     */
//    public E remove() {
//        return removeFirst();
//    }
//
//    /**
//     * Adds the specified element as the tail (last element) of this MyList.
//     *
//     * @param e the element to add
//     * @return {@code true} (as specified by {@link Queue#offer})
//     * @since 1.5
//     */
//    public boolean offer(E e) {
//        return add(e);
//    }
//
//    // Deque operations
//    /**
//     * Inserts the specified element at the front of this MyList.
//     *
//     * @param e the element to insert
//     * @return {@code true} (as specified by {@link Deque#offerFirst})
//     * @since 1.6
//     */
//    public boolean offerFirst(E e) {
//        addFirst(e);
//        return true;
//    }
//
//    /**
//     * Inserts the specified element at the end of this MyList.
//     *
//     * @param e the element to insert
//     * @return {@code true} (as specified by {@link Deque#offerLast})
//     * @since 1.6
//     */
//    public boolean offerLast(E e) {
//        addLast(e);
//        return true;
//    }
//
//    /**
//     * Retrieves, but does not remove, the first element of this MyList,
//     * or returns {@code null} if this MyList is empty.
//     *
//     * @return the first element of this MyList, or {@code null}
//     *         if this MyList is empty
//     * @since 1.6
//     */
//    public E peekFirst() {
//        final java.util.LinkedList.Node<E> f = first;
//        return (f == null) ? null : f.item;
//    }
//
//    /**
//     * Retrieves, but does not remove, the last element of this MyList,
//     * or returns {@code null} if this MyList is empty.
//     *
//     * @return the last element of this MyList, or {@code null}
//     *         if this MyList is empty
//     * @since 1.6
//     */
//    public E peekLast() {
//        final java.util.LinkedList.Node<E> l = last;
//        return (l == null) ? null : l.item;
//    }
//
//    /**
//     * Retrieves and removes the first element of this MyList,
//     * or returns {@code null} if this MyList is empty.
//     *
//     * @return the first element of this MyList, or {@code null} if
//     *     this MyList is empty
//     * @since 1.6
//     */
//    public E pollFirst() {
//        final java.util.LinkedList.Node<E> f = first;
//        return (f == null) ? null : unlinkFirst(f);
//    }
//
//    /**
//     * Retrieves and removes the last element of this MyList,
//     * or returns {@code null} if this MyList is empty.
//     *
//     * @return the last element of this MyList, or {@code null} if
//     *     this MyList is empty
//     * @since 1.6
//     */
//    public E pollLast() {
//        final java.util.LinkedList.Node<E> l = last;
//        return (l == null) ? null : unlinkLast(l);
//    }
//
//    /**
//     * Pushes an element onto the stack represented by this MyList.  In other
//     * words, inserts the element at the front of this MyList.
//     *
//     * <p>This method is equivalent to {@link #addFirst}.
//     *
//     * @param e the element to push
//     * @since 1.6
//     */
//    public void push(E e) {
//        addFirst(e);
//    }
//
//    /**
//     * Pops an element from the stack represented by this MyList.  In other
//     * words, removes and returns the first element of this MyList.
//     *
//     * <p>This method is equivalent to {@link #removeFirst()}.
//     *
//     * @return the element at the front of this MyList (which is the top
//     *         of the stack represented by this MyList)
//     * @throws NoSuchElementException if this MyList is empty
//     * @since 1.6
//     */
//    public E pop() {
//        return removeFirst();
//    }
//
//    /**
//     * Removes the first occurrence of the specified element in this
//     * MyList (when traversing the MyList from head to tail).  If the MyList
//     * does not contain the element, it is unchanged.
//     *
//     * @param o element to be removed from this MyList, if present
//     * @return {@code true} if the MyList contained the specified element
//     * @since 1.6
//     */
//    public boolean removeFirstOccurrence(Object o) {
//        return remove(o);
//    }
//
//    /**
//     * Removes the last occurrence of the specified element in this
//     * MyList (when traversing the MyList from head to tail).  If the MyList
//     * does not contain the element, it is unchanged.
//     *
//     * @param o element to be removed from this MyList, if present
//     * @return {@code true} if the MyList contained the specified element
//     * @since 1.6
//     */
//    public boolean removeLastOccurrence(Object o) {
//        if (o == null) {
//            for (java.util.LinkedList.Node<E> x = last; x != null; x = x.prev) {
//                if (x.item == null) {
//                    unlink(x);
//                    return true;
//                }
//            }
//        } else {
//            for (java.util.LinkedList.Node<E> x = last; x != null; x = x.prev) {
//                if (o.equals(x.item)) {
//                    unlink(x);
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    /**
//     * Returns a MyList-iterator of the elements in this MyList (in proper
//     * sequence), starting at the specified position in the MyList.
//     * Obeys the general contract of {@code MyList.listIterator(int)}.<p>
//     *
//     * The MyList-iterator is <i>fail-fast</i>: if the MyList is structurally
//     * modified at any time after the Iterator is created, in any way except
//     * through the MyList-iterator's own {@code remove} or {@code add}
//     * methods, the MyList-iterator will throw a
//     * {@code ConcurrentModificationException}.  Thus, in the face of
//     * concurrent modification, the iterator fails quickly and cleanly, rather
//     * than risking arbitrary, non-deterministic behavior at an undetermined
//     * time in the future.
//     *
//     * @param index index of the first element to be returned from the
//     *              MyList-iterator (by a call to {@code next})
//     * @return a ListIterator of the elements in this MyList (in proper
//     *         sequence), starting at the specified position in the MyList
//     * @throws IndexOutOfBoundsException {@inheritDoc}
//     * @see MyList#listIterator(int)
//     */
//    public ListIterator<E> listIterator(int index) {
//        checkPositionIndex(index);
//        return new java.util.LinkedList.ListItr(index);
//    }
//
//    private class ListItr implements ListIterator<E> {
//        private java.util.LinkedList.Node<E> lastReturned;
//        private java.util.LinkedList.Node<E> next;
//        private int nextIndex;
//        private int expectedModCount = modCount;
//
//        ListItr(int index) {
//            // assert isPositionIndex(index);
//            next = (index == size) ? null : node(index);
//            nextIndex = index;
//        }
//
//        public boolean hasNext() {
//            return nextIndex < size;
//        }
//
//        public E next() {
//            checkForComodification();
//            if (!hasNext())
//                throw new NoSuchElementException();
//
//            lastReturned = next;
//            next = next.next;
//            nextIndex++;
//            return lastReturned.item;
//        }
//
//        public boolean hasPrevious() {
//            return nextIndex > 0;
//        }
//
//        public E previous() {
//            checkForComodification();
//            if (!hasPrevious())
//                throw new NoSuchElementException();
//
//            lastReturned = next = (next == null) ? last : next.prev;
//            nextIndex--;
//            return lastReturned.item;
//        }
//
//        public int nextIndex() {
//            return nextIndex;
//        }
//
//        public int previousIndex() {
//            return nextIndex - 1;
//        }
//
//        public void remove() {
//            checkForComodification();
//            if (lastReturned == null)
//                throw new IllegalStateException();
//
//            java.util.LinkedList.Node<E> lastNext = lastReturned.next;
//            unlink(lastReturned);
//            if (next == lastReturned)
//                next = lastNext;
//            else
//                nextIndex--;
//            lastReturned = null;
//            expectedModCount++;
//        }
//
//        public void set(E e) {
//            if (lastReturned == null)
//                throw new IllegalStateException();
//            checkForComodification();
//            lastReturned.item = e;
//        }
//
//        public void add(E e) {
//            checkForComodification();
//            lastReturned = null;
//            if (next == null)
//                linkLast(e);
//            else
//                linkBefore(e, next);
//            nextIndex++;
//            expectedModCount++;
//        }
//
//        public void forEachRemaining(Consumer<? super E> action) {
//            Objects.requireNonNull(action);
//            while (modCount == expectedModCount && nextIndex < size) {
//                action.accept(next.item);
//                lastReturned = next;
//                next = next.next;
//                nextIndex++;
//            }
//            checkForComodification();
//        }
//
//        final void checkForComodification() {
//            if (modCount != expectedModCount)
//                throw new ConcurrentModificationException();
//        }
//    }
//
//    private static class Node<E> {
//        E item;
//        java.util.LinkedList.Node<E> next;
//        java.util.LinkedList.Node<E> prev;
//
//        Node(java.util.LinkedList.Node<E> prev, E element, java.util.LinkedList.Node<E> next) {
//            this.item = element;
//            this.next = next;
//            this.prev = prev;
//        }
//    }
//
//    /**
//     * @since 1.6
//     */
//    public Iterator<E> descendingIterator() {
//        return new java.util.LinkedList.DescendingIterator();
//    }
//
//    /**
//     * Adapter to provide descending iterators via ListItr.previous
//     */
//    private class DescendingIterator implements Iterator<E> {
//        private final java.util.LinkedList.ListItr itr = new java.util.LinkedList.ListItr(size());
//        public boolean hasNext() {
//            return itr.hasPrevious();
//        }
//        public E next() {
//            return itr.previous();
//        }
//        public void remove() {
//            itr.remove();
//        }
//    }
//
//    @SuppressWarnings("unchecked")
//    private java.util.LinkedList<E> superClone() {
//        try {
//            return (java.util.LinkedList<E>) super.clone();
//        } catch (CloneNotSupportedException e) {
//            throw new InternalError(e);
//        }
//    }
//
//    /**
//     * Returns a shallow copy of this {@code LinkedList}. (The elements
//     * themselves are not cloned.)
//     *
//     * @return a shallow copy of this {@code LinkedList} instance
//     */
//    public Object clone() {
//        java.util.LinkedList<E> clone = superClone();
//
//        // Put clone into "virgin" state
//        clone.first = clone.last = null;
//        clone.size = 0;
//        clone.modCount = 0;
//
//        // Initialize clone with our elements
//        for (java.util.LinkedList.Node<E> x = first; x != null; x = x.next)
//            clone.add(x.item);
//
//        return clone;
//    }
//
//    /**
//     * Returns an array containing all of the elements in this MyList
//     * in proper sequence (from first to last element).
//     *
//     * <p>The returned array will be "safe" in that no references to it are
//     * maintained by this MyList.  (In other words, this method must allocate
//     * a new array).  The caller is thus free to modify the returned array.
//     *
//     * <p>This method acts as bridge between array-based and collection-based
//     * APIs.
//     *
//     * @return an array containing all of the elements in this MyList
//     *         in proper sequence
//     */
//    public Object[] toArray() {
//        Object[] result = new Object[size];
//        int i = 0;
//        for (java.util.LinkedList.Node<E> x = first; x != null; x = x.next)
//            result[i++] = x.item;
//        return result;
//    }
//
//    /**
//     * Returns an array containing all of the elements in this MyList in
//     * proper sequence (from first to last element); the runtime type of
//     * the returned array is that of the specified array.  If the MyList fits
//     * in the specified array, it is returned therein.  Otherwise, a new
//     * array is allocated with the runtime type of the specified array and
//     * the size of this MyList.
//     *
//     * <p>If the MyList fits in the specified array with room to spare (i.e.,
//     * the array has more elements than the MyList), the element in the array
//     * immediately following the end of the MyList is set to {@code null}.
//     * (This is useful in determining the length of the MyList <i>only</i> if
//     * the caller knows that the MyList does not contain any null elements.)
//     *
//     * <p>Like the {@link #toArray()} method, this method acts as bridge between
//     * array-based and collection-based APIs.  Further, this method allows
//     * precise control over the runtime type of the output array, and may,
//     * under certain circumstances, be used to save allocation costs.
//     *
//     * <p>Suppose {@code x} is a MyList known to contain only strings.
//     * The following code can be used to dump the MyList into a newly
//     * allocated array of {@code String}:
//     *
//     * <pre>
//     *     String[] y = x.toArray(new String[0]);</pre>
//     *
//     * Note that {@code toArray(new Object[0])} is identical in function to
//     * {@code toArray()}.
//     *
//     * @param a the array into which the elements of the MyList are to
//     *          be stored, if it is big enough; otherwise, a new array of the
//     *          same runtime type is allocated for this purpose.
//     * @return an array containing the elements of the MyList
//     * @throws ArrayStoreException if the runtime type of the specified array
//     *         is not a supertype of the runtime type of every element in
//     *         this MyList
//     * @throws NullPointerException if the specified array is null
//     */
//    @SuppressWarnings("unchecked")
//    public <T> T[] toArray(T[] a) {
//        if (a.length < size)
//            a = (T[])java.lang.reflect.Array.newInstance(
//                    a.getClass().getComponentType(), size);
//        int i = 0;
//        Object[] result = a;
//        for (java.util.LinkedList.Node<E> x = first; x != null; x = x.next)
//            result[i++] = x.item;
//
//        if (a.length > size)
//            a[size] = null;
//
//        return a;
//    }
//
//    private static final long serialVersionUID = 876323262645176354L;
//
//    /**
//     * Saves the state of this {@code LinkedList} instance to a stream
//     * (that is, serializes it).
//     *
//     * @serialData The size of the MyList (the number of elements it
//     *             contains) is emitted (int), followed by all of its
//     *             elements (each an Object) in the proper order.
//     */
//    private void writeObject(java.io.ObjectOutputStream s)
//            throws java.io.IOException {
//        // Write out any hidden serialization magic
//        s.defaultWriteObject();
//
//        // Write out size
//        s.writeInt(size);
//
//        // Write out all elements in the proper order.
//        for (java.util.LinkedList.Node<E> x = first; x != null; x = x.next)
//            s.writeObject(x.item);
//    }
//
//    /**
//     * Reconstitutes this {@code LinkedList} instance from a stream
//     * (that is, deserializes it).
//     */
//    @SuppressWarnings("unchecked")
//    private void readObject(java.io.ObjectInputStream s)
//            throws java.io.IOException, ClassNotFoundException {
//        // Read in any hidden serialization magic
//        s.defaultReadObject();
//
//        // Read in size
//        int size = s.readInt();
//
//        // Read in all elements in the proper order.
//        for (int i = 0; i < size; i++)
//            linkLast((E)s.readObject());
//    }
//
//    /**
//     * Creates a <em><a href="Spliterator.html#binding">late-binding</a></em>
//     * and <em>fail-fast</em> {@link Spliterator} over the elements in this
//     * MyList.
//     *
//     * <p>The {@code Spliterator} reports {@link Spliterator#SIZED} and
//     * {@link Spliterator#ORDERED}.  Overriding implementations should document
//     * the reporting of additional characteristic values.
//     *
//     * @implNote
//     * The {@code Spliterator} additionally reports {@link Spliterator#SUBSIZED}
//     * and implements {@code trySplit} to permit limited parallelism..
//     *
//     * @return a {@code Spliterator} over the elements in this MyList
//     * @since 1.8
//     */
//    @Override
//    public Spliterator<E> spliterator() {
//        return new java.util.LinkedList.LLSpliterator<E>(this, -1, 0);
//    }
//
//    /** A customized variant of Spliterators.IteratorSpliterator */
//    static final class LLSpliterator<E> implements Spliterator<E> {
//        static final int BATCH_UNIT = 1 << 10;  // batch array size increment
//        static final int MAX_BATCH = 1 << 25;  // max batch array size;
//        final java.util.LinkedList<E> MyList; // null OK unless traversed
//        java.util.LinkedList.Node<E> current;      // current node; null until initialized
//        int est;              // size estimate; -1 until first needed
//        int expectedModCount; // initialized when est set
//        int batch;            // batch size for splits
//
//        LLSpliterator(java.util.LinkedList<E> MyList, int est, int expectedModCount) {
//            this.MyList = MyList;
//            this.est = est;
//            this.expectedModCount = expectedModCount;
//        }
//
//        final int getEst() {
//            int s; // force initialization
//            final java.util.LinkedList<E> lst;
//            if ((s = est) < 0) {
//                if ((lst = MyList) == null)
//                    s = est = 0;
//                else {
//                    expectedModCount = lst.modCount;
//                    current = lst.first;
//                    s = est = lst.size;
//                }
//            }
//            return s;
//        }
//
//        public long estimateSize() { return (long) getEst(); }
//
//        public Spliterator<E> trySplit() {
//            java.util.LinkedList.Node<E> p;
//            int s = getEst();
//            if (s > 1 && (p = current) != null) {
//                int n = batch + BATCH_UNIT;
//                if (n > s)
//                    n = s;
//                if (n > MAX_BATCH)
//                    n = MAX_BATCH;
//                Object[] a = new Object[n];
//                int j = 0;
//                do { a[j++] = p.item; } while ((p = p.next) != null && j < n);
//                current = p;
//                batch = j;
//                est = s - j;
//                return Spliterators.spliterator(a, 0, j, Spliterator.ORDERED);
//            }
//            return null;
//        }
//
//        public void forEachRemaining(Consumer<? super E> action) {
//            java.util.LinkedList.Node<E> p; int n;
//            if (action == null) throw new NullPointerException();
//            if ((n = getEst()) > 0 && (p = current) != null) {
//                current = null;
//                est = 0;
//                do {
//                    E e = p.item;
//                    p = p.next;
//                    action.accept(e);
//                } while (p != null && --n > 0);
//            }
//            if (MyList.modCount != expectedModCount)
//                throw new ConcurrentModificationException();
//        }
//
//        public boolean tryAdvance(Consumer<? super E> action) {
//            java.util.LinkedList.Node<E> p;
//            if (action == null) throw new NullPointerException();
//            if (getEst() > 0 && (p = current) != null) {
//                --est;
//                E e = p.item;
//                current = p.next;
//                action.accept(e);
//                if (MyList.modCount != expectedModCount)
//                    throw new ConcurrentModificationException();
//                return true;
//            }
//            return false;
//        }
//
//        public int characteristics() {
//            return Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED;
//        }
//    }
//
//}
