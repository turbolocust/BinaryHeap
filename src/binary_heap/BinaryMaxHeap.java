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
 * Extends BinaryHeap and offers functionality of a maximum priority queue
 *
 * @author Matthias Fussenegger
 * @param <T> Generic type parameter
 */
public class BinaryMaxHeap<T> extends BinaryHeap<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Initializes a binary maximum heap with default size, which allows 32
     * elements to be stored. As the first element must be {@code null}, the
     * true size of the heap is 33. In a maximum heap, the largest element is
     * the root element
     */
    public BinaryMaxHeap() {
        super();
    }

    /**
     * Initializes a new binary maximum heap with specified size. The size
     * specified is the number of elements that can be stored in the heap. This
     * means the true size is the specified size plus one {@code size + 1}. In a
     * maximum heap, the largest element is the root element
     *
     * @param size The specified size of the heap
     */
    public BinaryMaxHeap(int size) {
        super(size);
    }

    /**
     * Initializes a new binary maximum heap with default size, which allows 32
     * elements to be stored. As the first element must be {@code null}, the
     * true size is 33. In a maximum heap, the largest element is the root
     * element
     *
     * @param comp The specified {@code Comparator} used by heap
     */
    public BinaryMaxHeap(Comparator<? super T> comp) {
        super(comp);
    }

    /**
     * Initializes a new binary maximum heap with specified size. The size
     * specified is the number of elements that can be stored in the heap. This
     * means the true size is the specified size plus one {@code size + 1}. In a
     * maximum heap, the largest element is the root element
     *
     * @param size The specified size of the heap
     * @param comp The specified {@code Comparator} used by heap
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
            if (_heap[size()] != null) {
                resize((size() + 1) * 2);
            }
            if (_comp != null) {
                siftUpUsingComparator(element);
            } else {
                siftUpComparable(element);
            }
        }
    }

    @Override
    public T remove() {
        if (isEmpty()) {
            return null;
        }

        T element = _heap[indexOfLastElement()]; //right outermost leaf
        T topValue = _heap[1]; //the result to be returned
        _heap[1] = element;

        if (_comp != null) {
            siftDownUsingComparator();
        } else {
            siftDownComparable();
        }

        _heap[indexOfLastElement()] = null;
        return topValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void siftUpComparable(T element) {
        int i = indexOfLastElement() + 1;
        while (i > 1 && ((Comparable<? super T>) element).compareTo((T) _heap[i / 2]) > 0) {
            _heap[i] = _heap[i / 2];
            i = i / 2;
        }
        _heap[i] = element;
    }

    @Override
    protected void siftUpUsingComparator(T element) {
        int i = indexOfLastElement() + 1;
        _heap[i] = element;
        while (i > 1 && _comp.compare(element, _heap[i / 2]) > 0) {
            _heap[i] = _heap[i / 2];
            i = i / 2;
        }
        _heap[i] = element;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void siftDownComparable() {
        int i = 1, parent = i;
        int leftChild = i * 2;
        int rightChild = leftChild + 1;

        while (rightChild < _heap.length) {
            if (_heap[leftChild] != null) {
                if (((Comparable<? super T>) _heap[leftChild]).compareTo((T) _heap[parent]) > 0) {
                    parent = leftChild;
                }
            }
            if (_heap[rightChild] != null) {
                if (((Comparable<? super T>) _heap[rightChild]).compareTo((T) _heap[parent]) > 0) {
                    parent = rightChild;
                }
            }
            /*swap if child is larger than its parent*/
            if (parent != i) {
                swap(i, parent);
            } else {
                break;
            }
            i = parent;
            leftChild = i * 2;
            rightChild = leftChild + 1;
        }
    }

    @Override
    protected void siftDownUsingComparator() {
        int i = 1, parent = i;
        int leftChild = i * 2;
        int rightChild = leftChild + 1;

        while (rightChild < _heap.length) {
            if (_heap[leftChild] != null) {
                if (_comp.compare(_heap[leftChild], _heap[parent]) > 0) {
                    parent = leftChild;
                }
            }
            if (_heap[rightChild] != null) {
                if (_comp.compare(_heap[rightChild], _heap[parent]) > 0) {
                    parent = rightChild;
                }
            }
            /*swap if child is larger than its parent*/
            if (parent != i) {
                swap(i, parent);
            } else {
                break;
            }
            i = parent;
            leftChild = i * 2;
            rightChild = leftChild + 1;
        }
    }
}
