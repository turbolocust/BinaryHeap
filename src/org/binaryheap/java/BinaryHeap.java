/*
 * The MIT License
 *
 * Copyright 2017 Matthias Fussenegger
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.binaryheap.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Abstract class that represents a binary heap implemented as an array.
 *
 * @author Matthias Fussenegger
 * @param <T> Generic type parameter
 */
public abstract class BinaryHeap<T> {

    /**
     * Allows 32 entries as first entry has to be {@code null} in this binary
     * heap implementation.
     */
    public static final int INITIAL_SIZE = (1 << 5) + 1;

    /**
     * The number of elements currently stored in the heap.
     */
    protected int _size = 0;

    /**
     * The actual heap, implemented as a generic array.
     */
    protected T[] _heap;

    /**
     * The {@link Comparator} used for comparing the elements in the heap.
     */
    protected final Comparator<? super T> _comp;

    /**
     * Initializes a binary heap with default size, which allows 32 elements to
     * be stored. As the first element has to be {@code null}, the true size is
     * 33.
     */
    @SuppressWarnings("unchecked")
    protected BinaryHeap() {
        _heap = (T[]) new Object[INITIAL_SIZE];
        _comp = null;
    }

    /**
     * Initializes a new binary heap with the specified size. The size specified
     * is the number of elements that can be stored in the heap. This means that
     * the true size of the heap is the specified size plus one
     * {@code size + 1}.
     *
     * @param size The size of the heap.
     */
    @SuppressWarnings("unchecked")
    protected BinaryHeap(int size) {
        _heap = (T[]) new Object[size + 1];
        _comp = null;
    }

    /**
     * Initializes a binary heap with default size, which allows 32 elements to
     * be stored. As the first element has to be {@code null}, the true size is
     * 33.
     *
     * @param comp The {@link Comparator} used by the heap.
     */
    @SuppressWarnings("unchecked")
    protected BinaryHeap(Comparator<? super T> comp) {
        _heap = (T[]) new Object[INITIAL_SIZE];
        _comp = comp;
    }

    /**
     * Initializes a new binary heap with the specified size. The size specified
     * is the number of elements that can be stored in the heap. This means that
     * the true size of the heap is the specified size plus one
     * {@code size + 1}.
     *
     * @param size The size of the heap.
     * @param comp The {@link Comparator} used by the heap.
     */
    @SuppressWarnings("unchecked")
    protected BinaryHeap(int size, Comparator<? super T> comp) {
        _heap = (T[]) new Object[size + 1];
        _comp = comp;
    }

    /**
     * Resizes the heap. This method is currently called by child classes only.
     *
     * @param newSize The new size of the heap.
     * @return False if resize is not possible as the new size is smaller than
     * the number of stored elements. True if resizing was successful.
     */
    protected final boolean resize(int newSize) {
        if (newSize < size()) {
            return false;
        }
        @SuppressWarnings("unchecked")
        T[] newHeap = (T[]) new Object[newSize];

        for (int i = 0; i <= _size; ++i) {
            newHeap[i] = _heap[i];
        }
        _heap = newHeap;

        return true;
    }

    /**
     * Swaps two elements at the specified positions in the heap.
     *
     * @param i The position of the first element in the heap.
     * @param j The position of the second element in the heap.
     */
    protected final void swap(int i, int j) {
        T temp = _heap[j];
        _heap[j] = _heap[i];
        _heap[i] = temp;
    }

    /**
     * Returns the number of elements currently stored in the heap.
     *
     * @return The number of elements currently stored in the heap.
     */
    public final int size() {
        return _size;
    }

    /**
     * Checks whether this heap contains any elements.
     *
     * @return True if the heap is empty, false otherwise.
     */
    public final boolean isEmpty() {
        return _size == 0;
    }

    /**
     * Returns the first element of the heap without removing it.
     *
     * @return The first element of the heap.
     */
    public final T peek() {
        return !isEmpty() ? _heap[1] : null;
    }

    /**
     * Adds an element to the heap.
     *
     * @param element The element to be added.
     */
    public final void add(T element) {
        if (element == null) {
            throw new NullPointerException();
        } else if (isEmpty()) {
            _heap[1] = element;
        } else {
            if (_heap[_heap.length - 1] != null) {
                resize(_heap.length * 2);
            }
            if (_comp != null) {
                siftUpUsingComparator(element);
            } else {
                siftUpComparable(element);
            }
        }
        ++_size;
    }

    /**
     * Adds multiple elements to the heap.
     *
     * @param elements The elements to be added.
     */
    @SafeVarargs
    public final void add(T... elements) {
        for (T element : elements) {
            add(element);
        }
    }

    /**
     * Removes and returns the first element of the heap.
     *
     * @return The first element of the heap or {@code null} if heap is empty.
     */
    public final T remove() {
        if (isEmpty()) {
            return null;
        }
        T element = _heap[_size]; // right outermost leaf
        T topValue = _heap[1]; // the result to be returned
        _heap[1] = element;

        if (_comp != null) {
            siftDownUsingComparator();
        } else {
            siftDownComparable();
        }
        _heap[_size] = null; // clear reference
        --_size;

        return topValue;
    }

    /**
     * Shifts an element up after adding it to the heap according to its natural
     * ordering. A {@link NullPointerException} is thrown if the element to be
     * inserted is {@code null}.
     *
     * @param element The element to be shifted up
     */
    protected abstract void siftUpComparable(T element);

    /**
     * Shifts an element up after adding it to the heap using the
     * {@link Comparator} of this heap. A {@link NullPointerException} is thrown
     * if the element to be inserted is {@code null}.
     *
     * @param element The element to be shifted up
     */
    protected abstract void siftUpUsingComparator(T element);

    /**
     * Shifts elements down after removing the first element using the natural
     * ordering of the elements in the heap. This is necessary to restore the
     * heap properties.
     */
    protected abstract void siftDownComparable();

    /**
     * Shifts elements down after removing the first element using the
     * {@link Comparator} of this heap. This is necessary to restore the heap
     * properties.
     */
    protected abstract void siftDownUsingComparator();

    /**
     * Returns the {@link Comparator} used to order the elements of this heap,
     * or {@code null} if this heap is sorted according to the natural ordering
     * of its elements.
     *
     * @return The {@link Comparator} used to order this heap or {@code null} if
     * this heap is sorted to the natural ordering of its elements.
     */
    public final Comparator<? super T> comparator() {
        return _comp;
    }

    /**
     * Checks whether the specified element exists at least once in this heap.
     * This method will return true on the first occurrence of the specified
     * element.
     *
     * @param o The element to be found in this heap.
     * @return True if element exists, false otherwise.
     */
    public final boolean contains(Object o) {
        for (int i = 1; i < _size; ++i) {
            if (_heap[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the index of the specified element in the heap or a negative
     * {@link Integer} if either the element does not exist or is {@code null}.
     *
     * @param o The element of which to find the index.
     * @return The index of the specified element if it exists in the heap or a
     * negative {@link Integer} otherwise.
     */
    public final int indexOf(Object o) {
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
     * Removes all elements in this heap. The heap will be empty after this call
     * returns. The size of the heap will not be affected by this operation.
     */
    public final void clear() {
        if (!isEmpty()) {
            for (int i = 1; i < _size; ++i) {
                _heap[i] = null;
            }
            _size = 0;
        }
    }

    /**
     * Returns an array consisting of all the elements stored in the heap.
     *
     * @return An array consisting of all the elements stored in the heap.
     */
    public final Object[] toArray() {
        Object[] elements = new Object[_size];
        for (int i = 1; i <= _size; ++i) {
            elements[i - 1] = _heap[i];
        }
        return elements;
    }

    /**
     * Returns a list consisting of all the elements stored in the heap.
     *
     * @return A list consisting of all the elements in the heap.
     */
    public final List<T> toList() {
        List<T> elements = new ArrayList<>(_size);
        for (int i = 1; i <= _size; ++i) {
            elements.add(_heap[i]);
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
        @SuppressWarnings("unchecked")
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
