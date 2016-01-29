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
     * Initializes a new binary maximum heap with default size, which allows 64
     * elements to be stored. As the first element must be null, the true size
     * is 65. In the maximum heap, the largest element is at the first position
     */
    public BinaryMaxHeap() {
        super();
    }

    /**
     * Initializes a new binary maximum heap with specified size. The size
     * specified is the number of elements that can be stored in heap. This
     * means the true size is the specified size plus one (size + 1). In the
     * maximum heap, the largest element is at the first position
     *
     * @param size The user defined size of the heap
     */
    public BinaryMaxHeap(int size) {
        super(size);
    }

    /**
     * Initializes a new binary maximum heap with default size, which allows 64
     * elements to be stored. As the first element must be null, the true size
     * is 65. In the maximum heap, the largest element is at the first position
     *
     * @param comp The user defined comparator used by heap
     */
    public BinaryMaxHeap(Comparator<? super T> comp) {
        super(comp);
    }

    /**
     * Initializes a new binary maximum heap with specified size. The size
     * specified is the number of elements that can be stored in heap. This
     * means the true size is the specified size plus one (size + 1). In the
     * maximum heap, the largest element is at the first position
     *
     * @param size The user defined size of the heap
     * @param comp The user defined comparator used by heap
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
            if (_heap[size()] != null || _heap[size() - 1] != null) {
                resize((size() * 2) + 1);
            }
            if (_comp != null) {
                siftUpComparator(element);
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
            siftDownComparator();
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
        _heap[i] = element;
        while (i > 1) {
            if (_heap[i / 2] != null) {
                if (((Comparable<? super T>) _heap[i / 2]).compareTo((T) _heap[i]) < 0) {
                    swap(i, i / 2);
                }
            } else {
                swap(i, i / 2);
            }
            i = i / 2;
        }
    }

    @Override
    protected void siftUpComparator(T element) {
        int i = indexOfLastElement() + 1;
        _heap[i] = element;
        while (i > 1) {
            if (_heap[i / 2] != null) {
                if (_comp.compare(_heap[i / 2], _heap[i]) < 0) {
                    swap(i, i / 2);
                }
            } else {
                swap(i, i / 2);
            }
            i = i / 2;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void siftDownComparable() {
        int i = 1;
        int leftChild = i * 2;
        int rightChild = i * 2 + 1;

        while (i * 2 < _heap.length) {
            if (_heap[leftChild] != null && _heap[rightChild] != null) {
                if (((Comparable<? super T>) _heap[i]).compareTo((T) _heap[leftChild]) >= 0
                        && ((Comparable<? super T>) _heap[i]).compareTo((T) _heap[rightChild]) >= 0) {
                    break;
                }
                if (((Comparable<? super T>) _heap[leftChild]).compareTo((T) _heap[rightChild]) < 0) {
                    swap(i, rightChild);
                } else if (((Comparable<? super T>) _heap[leftChild]).compareTo((T) _heap[rightChild]) > 0) {
                    swap(i, leftChild);
                }
            } else {
                break;
            }
            i *= 2;
            leftChild = i * 2;
            rightChild = i * 2 + 1;
        }
    }

    @Override
    protected void siftDownComparator() {
        int i = 1;
        int leftChild = i * 2;
        int rightChild = i * 2 + 1;

        while (i * 2 < _heap.length) {
            if (_heap[leftChild] != null && _heap[rightChild] != null) {
                if (_comp.compare(_heap[i], _heap[leftChild]) >= 0
                        && _comp.compare(_heap[i], _heap[rightChild]) >= 0) {
                    break;
                }
                if (_comp.compare(_heap[leftChild], _heap[rightChild]) < 0) {
                    swap(i, rightChild);
                } else if (_comp.compare(_heap[leftChild], _heap[rightChild]) > 0) {
                    swap(i, leftChild);
                }
            } else {
                break;
            }
            i *= 2;
            leftChild = i * 2;
            rightChild = i * 2 + 1;
        }
    }
}
