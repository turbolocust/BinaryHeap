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

import java.io.Serializable;
import java.util.Comparator;

/**
 * Extends BinaryHeap and offers functionality of a maximum priority queue.
 *
 * @author Matthias Fussenegger
 * @param <T> Generic type parameter
 */
public class BinaryMaxHeap<T> extends BinaryHeap<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Initializes a binary maximum heap with default size, which allows 32
     * elements to be stored. As the first element is {@code null}, the true
     * size of the heap is 33. In a maximum heap, the largest element is the
     * root element
     */
    public BinaryMaxHeap() {
        super();
    }

    /**
     * Initializes a new binary maximum heap with the specified size. The size
     * specified is the number of elements that can be stored in the heap. This
     * means that the true size is the specified size plus one {@code size + 1}.
     * In a maximum heap, the largest element is the root element
     *
     * @param size The size of the heap
     */
    public BinaryMaxHeap(int size) {
        super(size);
    }

    /**
     * Initializes a new binary maximum heap with default size, which allows 32
     * elements to be stored. As the first element is {@code null}, the true
     * size is 33. In a maximum heap, the largest element is the root element
     *
     * @param comp The {@link Comparator} used by heap
     */
    public BinaryMaxHeap(Comparator<? super T> comp) {
        super(comp);
    }

    /**
     * Initializes a new binary maximum heap with the specified size. The size
     * specified is the number of elements that can be stored in the heap. This
     * means that the true size is the specified size plus one {@code size + 1}.
     * In a maximum heap, the largest element is the root element
     *
     * @param size The size of the heap
     * @param comp The {@link Comparator} used by heap
     */
    public BinaryMaxHeap(int size, Comparator<? super T> comp) {
        super(size, comp);
    }

    @Override
    public void add(T element) {
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

    @Override
    public T remove() {
        if (isEmpty()) {
            return null;
        }
        T element = _heap[_size]; //right outermost leaf
        T topValue = _heap[1]; //the result to be returned
        _heap[1] = element;

        if (_comp != null) {
            siftDownUsingComparator();
        } else {
            siftDownComparable();
        }
        _heap[_size] = null;
        --_size;

        return topValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void siftUpComparable(T element) {
        int i = _size + 1;
        while (i > 1 && ((Comparable<? super T>) element).compareTo(_heap[i / 2]) > 0) {
            _heap[i] = _heap[i / 2];
            i = i / 2;
        }
        _heap[i] = element;
    }

    @Override
    protected void siftUpUsingComparator(T element) {
        int i = _size + 1;
        while (i > 1 && _comp.compare(element, _heap[i / 2]) > 0) {
            _heap[i] = _heap[i / 2];
            i = i / 2;
        }
        _heap[i] = element;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void siftDownComparable() {
        int parent = 1, child = parent * 2;
        while (parent * 2 < _size) {
            if (child + 1 < _size) {
                if (((Comparable<? super T>) _heap[child + 1]).compareTo(_heap[child]) > 0) {
                    ++child; //right child is larger than left one
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
    protected void siftDownUsingComparator() {
        int parent = 1, child = parent * 2;
        while (parent * 2 < _size) {
            if (child + 1 < _size) {
                if (_comp.compare(_heap[child + 1], _heap[child]) > 0) {
                    ++child; //right child is larger than left one
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
