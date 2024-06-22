/**
 * Your implementation of a circular singly linked list.
 *
 * @author Dom Fernando
 * @userid
 * @version 1.0
 */
public class SinglyLinkedList<T> {
    // Do not add new instance variables or modify existing ones.
    private LinkedListNode<T> head;
    private int size;

    /**
     * Adds the element to the index specified.
     *
     * Adding to indices 0 and {@code size} should be O(1), all other cases are
     * O(n).
     *
     * @param index the requested index for the new element
     * @param data the data for the new element
     * @throws IndexOutOfBoundsException if index is negative or
     * index > size
     * @throws IllegalArgumentException if data is null
     */
    public void addAtIndex(int index, T data) {
        if (data == null) {
            throw new IllegalArgumentException("data cannot be null");

        } else if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("not a valid index");
        }

        LinkedListNode<T> temp = new LinkedListNode<>(data);
        LinkedListNode<T> curr = head;
        if (size == 0) {
            // Case where list is empty

            head = temp;
            head.setNext(head);
        } else if (index == 0) {
            // case where we add to the very front
            LinkedListNode<T> newNode = new LinkedListNode<>(head.getData(), head.getNext());
            head.setData(data);
            head.setNext(newNode);

        } else if (index == size) {
            // case where we add to the very end
            LinkedListNode<T> newNode = new LinkedListNode<>(head.getData(), head.getNext());
            newNode.setData(head.getData());
            head.setNext(newNode);
            head.setData(data);
            head = head.getNext();
        } else {
            // case where we add to the middle of list
            for (int i = 0; i < index - 1 ; i++ ) {
                curr = curr.getNext();
            }
            temp.setNext(curr.getNext());
            curr.setNext(temp);

        }
        size ++;
    }

    /**
     * Adds the element to the front of the list.
     *
     * Must be O(1) for all cases.
     *
     * @param data the data for the new element
     * @throws IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        addAtIndex(0, data);
    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be O(1) for all cases.
     *
     * @param data the data for the new element
     * @throws IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        addAtIndex(size, data);
    }

    /**
     * Removes and returns the element from the index specified.
     *
     * Removing from index 0 should be O(1), all other cases are O(n).
     *
     * @param index the requested index to be removed
     * @return the data formerly located at index
     * @throws IndexOutOfBoundsException if index is negative or
     * index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be in range (0, size - 1) ");
        }
        T removedData;
        if (index == 0) {
            removedData = head.getData();
            if (size == 1) {
                head = null;
            } else {
                head.setData(head.getNext().getData());
                head.setNext(head.getNext().getNext());
            }
        } else if (index == size - 1) {
            LinkedListNode<T> curr = head;
            while (curr.getNext().getNext() != head) {
                curr = curr.getNext();
            }
            removedData = curr.getNext().getData();
            curr.setNext(head);
        } else {
            LinkedListNode<T> curr = head;
            for (int i = 0; i < index - 1; i++) {
                curr = curr.getNext();
            }
            removedData = curr.getNext().getData();
            curr.setNext(curr.getNext().getNext());

        }
        size--;
        return removedData;
    }

    /**
     * Removes and returns the element at the front of the list. If the list is
     * empty, return {@code null}.
     *
     * Must be O(1) for all cases.
     *
     * @return the data formerly located at the front, null if empty list
     */
    public T removeFromFront() {
        return removeAtIndex(0);
    }

    /**
     * Removes and returns the element at the back of the list. If the list is
     * empty, return {@code null}.
     *
     * Must be O(n) for all cases.
     *
     * @return the data formerly located at the back, null if empty list
     */
    public T removeFromBack() {
        return removeAtIndex(size - 1);
    }

    /**
     * Removes the last copy of the given data from the list.
     *
     * Must be O(n) for all cases.
     *
     * @param data the data to be removed from the list
     * @return the removed data occurrence from the list itself (not the data
     * passed in), null if no occurrence
     * @throws IllegalArgumentException if data is null
     */
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }

        if (head == null) { // List is empty
            return null;
        }

        LinkedListNode<T> current = head;
        LinkedListNode<T> lastOccurrence = null;
        LinkedListNode<T> prevToLastOccurrence = null;
        LinkedListNode<T> prev = null;

        do {
            if (current.getData().equals(data)) {
                lastOccurrence = current;
                prevToLastOccurrence = prev;
            }
            prev = current;
            current = current.getNext();
        } while (current != head);

        if (lastOccurrence == null) { // Data not found
            return null;
        }

        T removedData = lastOccurrence.getData();

        // Removing the head (last occurrence is head)
        if (lastOccurrence == head) {
            if (head.getNext() == head) { // Only one element in the list
                head = null;
            } else {
                head.setData(head.getNext().getData());
                head.setNext(head.getNext().getNext());
            }
        } else { // Removing a non-head node
            prevToLastOccurrence.setNext(lastOccurrence.getNext());
        }

        size--;
        return removedData;
    }

    /**
     * Returns the element at the specified index.
     *
     * Getting index 0 should be O(1), all other cases are O(n).
     *
     * @param index the index of the requested element
     * @return the object stored at index
     * @throws IndexOutOfBoundsException if index < 0 or
     * index >= size
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index not in range (0, size)");
        } else if (index == 0) {
            return head.getData();
        } else {
            LinkedListNode<T> curr = head;
            for (int i = 0; i < index; i++) {
                curr = curr.getNext();
            }
            return curr.getData();
        }
    }

    /**
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return an array of length {@code size} holding all of the objects in
     * this list in the same order
     */
    public Object[] toArray() {
        Object[] arr = new Object[size];
        LinkedListNode<T> curr = head;
        for (int i = 0; i < size ; i++) {
            T data = curr.getData();
            arr[i] = data;
            curr = curr.getNext();
        }
        return arr;
    }

    /**
     * Returns a boolean value indicating if the list is empty.
     *
     * Must be O(1) for all cases.
     *
     * @return true if empty; false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the list of all data.
     *
     * Must be O(1) for all cases.
     */
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Returns the number of elements in the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }

    /**
     * Returns the head node of the linked list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return node at the head of the linked list
     */
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }
}