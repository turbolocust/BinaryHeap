/*
 * Copyright (C) 2016 Matthias Fussenegger
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package binaryHeap;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Abstract class that represents a binary heap implemented as an array
 *
 * @author Matthias Fussenegger
 * @param <T> Generic type parameter
 */
@SuppressWarnings("unchecked")
public abstract class BinaryHeap<T> {

    /**
     * Allows 32 entries, as first entry must be {@code null} in a binary heap
     */
    public static final int INITIAL_SIZE = (1 << 5) + 1;

    /**
     * The actual heap, implemented as an array to store the elements
     */
    protected T[] _heap;

    /**
     * The comparator used for comparing objects in heap
     */
    protected final Comparator<? super T> _comp;

    /**
     * Initializes a binary heap with default size, which allows 32 elements to
     * be stored. As the first element must be {@code null}, the true size is 33
     */
    protected BinaryHeap() {
        _heap = (T[]) new Object[INITIAL_SIZE];
        _comp = null;
    }

    /**
     * Initializes a new binary heap with specified size. The size specified is
     * the number of elements that can be stored in the heap. This means the
     * true size is the specified size plus one {@code size + 1}
     *
     * @param size The specified size of the heap
     */
    protected BinaryHeap(int size) {
        _heap = (T[]) new Object[size + 1];
        _comp = null;
    }

    /**
     * Initializes a binary heap with default size, which allows 32 elements to
     * be stored. As the first element must be {@code null}, the true size is 33
     *
     * @param comp The specified comparator used by heap
     */
    protected BinaryHeap(Comparator<? super T> comp) {
        _heap = (T[]) new Object[INITIAL_SIZE];
        _comp = comp;
    }

    /**
     * Initializes a new binary heap with specified size. The size specified is
     * the number of elements that can be stored in the heap. This means the
     * true size is the specified size plus one {@code size + 1}
     *
     * @param size The specified size of the heap
     * @param comp The specified comparator used by heap
     */
    protected BinaryHeap(int size, Comparator<? super T> comp) {
        _heap = (T[]) new Object[size + 1];
        _comp = comp;
    }

    /**
     * Resizes the heap. This method is currently called by child classes only
     *
     * @param newSize The new size of the heap
     * @return False if resize is not possible as new size is smaller than
     * number of stored elements. True if resize was successful
     */
    protected boolean resize(int newSize) {
        if (newSize < size()) {
            return false;
        }

        T[] newHeap = (T[]) new Object[newSize];

        for (int i = 0; i <= size(); ++i) {
            newHeap[i] = _heap[i];
        }

        _heap = newHeap;

        return true;
    }

    /**
     * Swaps two elements in the heap
     *
     * @param i The first position in heap
     * @param j The second position in heap
     */
    protected void swap(int i, int j) {
        T temp = _heap[j];
        _heap[j] = _heap[i];
        _heap[i] = temp;
    }

    /**
     * Returns the index of the last element in the heap
     *
     * @return The position of the last element in the heap
     */
    protected int indexOfLastElement() {
        for (int i = size(); i > 0; --i) {
            if (_heap[i] != null) {
                return i;
            }
        }
        return 0;
    }

    /**
     * The size of the heap, which is the size of elements that can be stored.
     * As the first element must be null, the true size of the heap is the
     * returned size plus one {@code size() + 1}
     *
     * @return The size of the heap
     */
    public int size() {
        return _heap.length - 1;
    }

    /**
     * Checks whether this heap is empty or not. Basically, it will only check
     * if a root element exists or not, as otherwise it would not be a binary
     * heap structure
     *
     * @return True if heap is empty, false otherwise
     */
    public boolean isEmpty() {
        return _heap[1] == null;
    }

    /**
     * Returns the first element of the heap without removing it
     *
     * @return The first element of the heap
     */
    public T peek() {
        return !isEmpty() ? _heap[1] : null;
    }

    /**
     * Adds a new element to the heap
     *
     * @param element The element to be added
     */
    public abstract void add(T element);

    /**
     * Removes and returns the first element of the heap
     *
     * @return The first element of the heap or null if heap is empty
     */
    public abstract T remove();

    /**
     * Shifts an element up after adding it to the heap according to its natural
     * ordering. {@code NullPointerException} will be thrown if the element to
     * be inserted is {@code null}
     *
     * @param element The element to be shifted up
     */
    protected abstract void siftUpComparable(T element);

    /**
     * Shifts an element up after adding it to the heap using the comparator of
     * this class. {@code NullPointerException} will be thrown if the element to
     * be inserted is {@code null}
     *
     * @param element The element to be shifted up
     */
    protected abstract void siftUpComparator(T element);

    /**
     * Shifts elements down after removing the first element using the natural
     * ordering of the elements in the heap. This is necessary to restore the
     * heap properties
     */
    protected abstract void siftDownComparable();

    /**
     * Shifts elements down after removing the first element using the
     * comparator of this class. This is necessary to restore the heap
     * properties
     */
    protected abstract void siftDownComparator();

    /**
     * Returns the comparator used to order the elements of this heap, or null
     * if this heap is sorted according to the natural ordering of its elements
     *
     * @return The comparator used to order this heap, or null if this heap is
     * sorted to the natural ordering of its elements
     */
    public Comparator<? super T> comparator() {
        return _comp;
    }

    /**
     * Checks whether the specified element exists at least once in this heap
     *
     * @param o The object to be checked for containment in this heap
     * @return True if element exists, false otherwise
     */
    public boolean contains(Object o) {
        for (int i = 0; i < _heap.length; ++i) {
            if (_heap[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Counts the number of nodes in this heap and returns the result. A node is
     * simply a stored element in the heap that is not null
     *
     * @return The number of nodes (elements) in this heap
     */
    public int countNodes() {
        int elementCount = 0;
        for (int i = 0; i < _heap.length; ++i) {
            if (_heap[i] != null) {
                ++elementCount;
            }
        }
        return elementCount;
    }

    /**
     * Returns the heap of this class. As the heap is already implemented as an
     * array, this method simply works like a getter. Be aware of, that the
     * returned array also contains {@code null} elements
     *
     * @return An array containing the heap structure with its elements
     */
    public Object[] toArray() {
        return _heap;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj == null || !obj.getClass().equals(this.getClass())) {
            return false;
        }

        Object[] heap = ((BinaryHeap<Object>) obj).toArray();

        return Arrays.equals(heap, _heap);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Arrays.deepHashCode(_heap);
        return hash;
    }
}
