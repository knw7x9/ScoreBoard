package edu.isu.cs2235.structures.impl;

import edu.isu.cs2235.structures.List;

/**
 * A Singly Linked List class
 *
 * @ Katherine Wilsdon
 * @param <E> Element Type
 */
public class SinglyLinkedList<E>  implements List<E> {
    private Node<E> head;
    private Node<E> tail;
    private int count = 0;

    private class Node<E> {
        E data;
        Node<E> next;

        // Gets the information or data contained in the node
        public E getData() {
            return data;
        }

        // Sets the the next item in the list
        public void setNext(Node<E> next) {
            this.next = next;
        }

        // Gets the the next item in the list
        public Node<E> getNext() {
            return next;
        }

        // Generate a Node class
        public Node(E data) {
            this.data = data;
            next = null;
        }
    }

    /**
     * Generates a Singly Linked List
     */
    public SinglyLinkedList() { }

    /**
     * @return first element in the list or null if the list is empty.
     */
    @Override
    public E first() {
        if (head == null)
            return null;
        else
            return head.getData();
    }

    /**
     * @return last element in the list or null if the list is empty.
     */
    @Override
    public E last() {
        if (tail == null)
            return null;
        else
            return tail.getData();
    }

    /**
     * Adds the provided element to the end of the list, only if the element is
     * not null.
     *
     * @param element Element to be added to the end of the list.
     */
    @Override
    public void addLast(E element) {
        if (element == null)
            return;
        Node<E> newNode = new Node<E>(element);
        if(head != null && tail != null) {
            tail.setNext(newNode);
            tail = newNode;
        }
        else if (head == null) {
            tail = newNode;
            head = tail;
            tail.setNext(null);
        }
        count++;
    }

    /**
     * Adds the provided element to the front of the list, only if the element
     * is not null.
     *
     * @param element Element to be added to the front of the list.
     */
    @Override
    public void addFirst(E element) {
        if (element != null) {
            Node<E> temp = new Node<E>(element);
            temp.setNext(head);
            head = temp;
            if(tail == null)
                tail = head;
            count++;
        }
    }

    /**
     * Removes the element at the front of the list.
     *
     * @return Element at the front of the list, or null if the list is empty.
     */
    @Override
    public E removeFirst() {
        if (tail == null && head == null)
            return null;
        if (tail != null && head == null)
            tail = null;
        Node<E> temp = head;
        head = temp.getNext();
        temp.setNext(null);
        count--;
        return temp.getData();
    }

    /**
     * Removes the element at the end of the list.
     *
     * @return Element at the end of the list, or null if the list is empty.
     */
    @Override
    public E removeLast() {
        Node<E> temp = head;
        for (int i = 0; i < size() - 1; ++i)
            temp = temp.getNext();
        temp.setNext(null);
        E data = tail.getData();
        tail = temp;
        count--;
        return data;
    }

    /**
     * Inserts the given element into the list at the provided index. The
     * element will not be inserted if either the element provided is null or if
     * the index provided is less than 0. If the index is greater than or equal
     * to the current size of the list, the element will be added to the end of
     * the list.
     *
     * @param element Element to be added (as long as it is not null).
     * @param index Index in the list where the element is to be inserted.
     */
    @Override
    public void insert(E element, int index) {
        if (element == null || index < 0)
            return;
        if (index >= size()) {
            addLast(element);
        } else if (index == 0) {
            addFirst(element);
        } else {
            Node<E> temp = head;
            Node<E> newNode = new Node<E>(element);
            for (int i = 0; i < index - 1; ++i)
                temp = temp.getNext();

            if (size() > 2) {
                newNode.setNext(temp.getNext());
                temp.setNext(newNode);
            } else {
                newNode.setNext(tail);
                temp.setNext(newNode);
            }
            count++;
        }
    }

    /**
     * Removes the element at the given index and returns the value.
     *
     * @param index Index of the element to remove
     * @return The value of the element at the given index, or null if the index
     * is greater than or equal to the size of the list or less than 0.
     */
    @Override
    public E remove(int index) {
        if(index < 0 || index >= size())
            return null;
        else {
            if (index == 0)
                return removeFirst();
            else if (index == size() - 1)
                return removeLast();
            else {
                Node<E> temp = head;
                for (int i = 0; i < index - 1; ++i)
                    temp = temp.getNext();
                Node<E> toRemove = temp.getNext();
                temp.setNext(toRemove.getNext());
                count--;
                return toRemove.getData();
            }
        }
    }

    /**
     * Retrieves the value at the specified index. Will return null if the index
     * provided is less than 0 or greater than or equal to the current size of
     * the list.
     *
     * @param index Index of the value to be retrieved.
     * @return Element at the given index, or null if the index is less than 0
     * or greater than or equal to the list size.
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size())
            return null;
        Node<E> temp = head;
        for (int i = 0; i < index; ++i) {
            temp = temp.getNext();
        }
        return temp.getData();
    }

    /**
     * @return The current size of the list. Note that 0 is returned for an
     * empty list.
     */
    @Override
    public int size() {
        if (isEmpty())
            return 0;
        else
            return count;
    }

    /**
     * @return true if there are no items currently stored in the list, false
     * otherwise.
     */
    @Override
    public boolean isEmpty() {
        return count <= 0;
    }

    /**
     * Prints the contents of the list in a single line separating each element
     * by a space to the default System.out
     */
    @Override
    public void printList() {
        for (int i = 0; i < size(); ++i)
            System.out.println(get(i));
    }
}
