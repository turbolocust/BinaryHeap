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

import java.io.Serializable;
import java.util.Comparator;

/**
 * Extends BinaryHeap and offers functionality of a maximum priority queue.
 *
 * @author Matthias Fussenegger
 * @param <T> Generic type parameter
 */
public final class BinaryMaxHeap<T> extends BinaryHeap<T> implements Serializable {

    private static final long serialVersionUID = -5868352871111438769L;

    /**
     * Initializes a binary maximum heap with default size, which allows 32
     * elements to be stored. As the first element has to be {@code null}, the
     * true size of the heap is 33. In a maximum heap, the largest element is
     * the root element.
     */
    public BinaryMaxHeap() {
        super();
    }

    /**
     * Initializes a new binary maximum heap with the specified size. The size
     * specified is the number of elements that can be stored in the heap. This
     * means that the true size of the heap is the specified size plus one
     * {@code size + 1}. In a maximum heap, the largest element is the root
     * element.
     *
     * @param size The size of the heap.
     */
    public BinaryMaxHeap(int size) {
        super(size);
    }

    /**
     * Initializes a new binary maximum heap with default size, which allows 32
     * elements to be stored. As the first element has to be {@code null}, the
     * true size is 33. In a maximum heap, the largest element is the root
     * element.
     *
     * @param comp The {@link Comparator} used by the heap.
     */
    public BinaryMaxHeap(Comparator<? super T> comp) {
        super(comp);
    }

    /**
     * Initializes a new binary maximum heap with the specified size. The size
     * specified is the number of elements that can be stored in the heap. This
     * means that the true size of the heap is the specified size plus one
     * {@code size + 1}. In a maximum heap, the largest element is the root
     * element.
     *
     * @param size The size of the heap.
     * @param comp The {@link Comparator} used by the heap.
     */
    public BinaryMaxHeap(int size, Comparator<? super T> comp) {
        super(size, comp);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected final void siftUpComparable(T element) {
        int i = _size + 1;
        while (i > 1 && ((Comparable<? super T>) element).compareTo(_heap[i / 2]) > 0) {
            _heap[i] = _heap[i / 2];
            i = i / 2;
        }
        _heap[i] = element;
    }

    @Override
    protected final void siftUpUsingComparator(T element) {
        int i = _size + 1;
        while (i > 1 && _comp.compare(element, _heap[i / 2]) > 0) {
            _heap[i] = _heap[i / 2];
            i = i / 2;
        }
        _heap[i] = element;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected final void siftDownComparable() {
        int parent = 1, child = parent * 2;
        while (parent * 2 < _size) {
            if (child + 1 < _size) {
                if (((Comparable<? super T>) _heap[child + 1]).compareTo(_heap[child]) > 0) {
                    ++child; // right child is larger than left one
                }
            }
            /*swawp if child is larger than its parent*/
            if (((Comparable<? super T>) _heap[child]).compareTo(_heap[parent]) > 0) {
                swap(child, parent);
                parent = child;
                child = parent * 2;
            } else {
                break;
            }
        }
    }

    @Override
    protected final void siftDownUsingComparator() {
        int parent = 1, child = parent * 2;
        while (parent * 2 < _size) {
            if (child + 1 < _size) {
                if (_comp.compare(_heap[child + 1], _heap[child]) > 0) {
                    ++child; // right child is larger than left one
                }
            }
            /*swawp if child is larger than its parent*/
            if (_comp.compare(_heap[child], _heap[parent]) > 0) {
                swap(child, parent);
                parent = child;
                child = parent * 2;
            } else {
                break;
            }
        }
    }
}
