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
package binary_heap;

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
     * Allows 32 entries, as first entry is {@code null} in a binary heap
     */
    public static final int INITIAL_SIZE = (1 << 5) + 1;

    /**
     * The number of objects currently stored in the heap
     */
    protected int _size = 0;

    /**
     * The actual heap, implemented as a generic array to store the objects
     */
    protected T[] _heap;

    /**
     * The {@link Comparator} used for comparing the objects in the heap
     */
    protected final Comparator<? super T> _comp;

    /**
     * Initializes a binary heap with default size, which allows 32 elements to
     * be stored. As the first element is {@code null}, the true size is 33
     */
    protected BinaryHeap() {
        _heap = (T[]) new Object[INITIAL_SIZE];
        _comp = null;
    }

    /**
     * Initializes a new binary heap with the specified size. The size specified
     * is the number of elements that can be stored in the heap. This means that
     * the true size is the specified size plus one {@code size + 1}
     *
     * @param size The size of the heap
     */
    protected BinaryHeap(int size) {
        _heap = (T[]) new Object[size + 1];
        _comp = null;
    }

    /**
     * Initializes a binary heap with default size, which allows 32 elements to
     * be stored. As the first element is {@code null}, the true size is 33
     *
     * @param comp The {@link Comparator} used by the heap
     */
    protected BinaryHeap(Comparator<? super T> comp) {
        _heap = (T[]) new Object[INITIAL_SIZE];
        _comp = comp;
    }

    /**
     * Initializes a new binary heap with the specified size. The size specified
     * is the number of elements that can be stored in the heap. This means that
     * the true size is the specified size plus one {@code size + 1}
     *
     * @param size The size of the heap
     * @param comp The {@link Comparator} used by heap
     */
    protected BinaryHeap(int size, Comparator<? super T> comp) {
        _heap = (T[]) new Object[size + 1];
        _comp = comp;
    }

    /**
     * Resizes the heap. This method is currently called by child classes only
     *
     * @param newSize The new size of the heap
     * @return False if resize is not possible as the new size is smaller than
     * number of stored elements. True if resizing was successful
     */
    protected boolean resize(int newSize) {
        if (newSize < size()) {
            return false;
        }
        T[] newHeap = (T[]) new Object[newSize];

        for (int i = 0; i <= _size; ++i) {
            newHeap[i] = _heap[i];
        }
        _heap = newHeap;

        return true;
    }

    /**
     * Swaps two elements at the specified positions in the heap
     *
     * @param i The first position in the heap
     * @param j The second position in the heap
     */
    protected void swap(int i, int j) {
        T temp = _heap[j];
        _heap[j] = _heap[i];
        _heap[i] = temp;
    }

    /**
     * Returns the number of elements currently stored in the heap
     *
     * @return The number of elements currently stored in the heap
     */
    public int size() {
        return _size;
    }

    /**
     * Checks whether this heap contains any elements
     *
     * @return True if heap is empty, false otherwise
     */
    public boolean isEmpty() {
        return _size == 0;
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
     * ordering. {@link NullPointerException} will be thrown if the element to
     * be inserted is {@code null}
     *
     * @param element The element to be shifted up
     */
    protected abstract void siftUpComparable(T element);

    /**
     * Shifts an element up after adding it to the heap using the
     * {@link Comparator} of this class. {@link NullPointerException} will be
     * thrown if the element to be inserted is {@code null}
     *
     * @param element The element to be shifted up
     */
    protected abstract void siftUpUsingComparator(T element);

    /**
     * Shifts elements down after removing the first element using the natural
     * ordering of the elements in the heap. This is necessary to restore the
     * heap properties
     */
    protected abstract void siftDownComparable();

    /**
     * Shifts elements down after removing the first element using the
     * {@link Comparator} of this class. This is necessary to restore the heap
     * properties
     */
    protected abstract void siftDownUsingComparator();

    /**
     * Returns the {@link Comparator} used to order the elements of this heap,
     * or null if this heap is sorted according to the natural ordering of its
     * elements
     *
     * @return The {@link Comparator} used to order this heap, or {@code null}
     * if this heap is sorted to the natural ordering of its elements
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
        for (int i = 1; i < _size; ++i) {
            if (_heap[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the index of the specified element in the heap or a negative
     * {@link Integer} if either the element does not exist or is {@code null}
     *
     * @param o The element of which to find the index
     * @return The index of the specified element if it exists in the heap or a
     * negative {@link Integer} otherwise
     */
    public int indexOf(Object o) {
        if (o != null) {
            for (int i = 1; i < _size; ++i) {
                if (_heap[i].equals(o)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Removes all elements in this heap. This heap will be empty after this
     * call returns
     */
    public void clear() {
        if (!isEmpty()) {
            for (int i = 1; i < _size; ++i) {
                _heap[i] = null;
            }
            _size = 0;
        }
    }

    /**
     * Returns an array consisting of all the elements stored in the heap
     *
     * @return An array consisting of all the elements stored in the heap
     */
    public Object[] toArray() {
        Object[] elements = new Object[_size];
        for (int i = 1; i <= _size; ++i) {
            elements[i - 1] = _heap[i];
        }
        return elements;
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
