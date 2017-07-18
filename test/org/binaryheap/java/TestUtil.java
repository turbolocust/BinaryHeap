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

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 *
 * @author Matthias Fussenegger
 */
public class TestUtil {

    /**
     * Adds random {@link Integer} values up to a maximum size to the specified
     * heap and returns a {@link PriorityQueue} with the same values.
     *
     * @param size The maximum size of values to be added.
     * @param heap The heap that will be filled with random values.
     * @return A {@link PriorityQueue} with the same values as in the heap.
     */
    public static PriorityQueue<Integer> addRandomValues(int size, BinaryHeap<Integer> heap) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Random rand = new Random();
        for (int i = 0; i < size; ++i) {
            final int next = rand.nextInt();
            heap.add(next);
            pq.add(next);
        }
        return pq;
    }

    /**
     * Adds random {@link Integer} values up to a maximum size to the specified
     * heap and returns a {@link PriorityQueue} with the same values.
     *
     * @param size The maximum size of values to be added.
     * @param heap The heap that will be filled with random values.
     * @param c The comparator used to initialize the {@link PriorityQueue}.
     * @return A {@link PriorityQueue} with the same values as in the heap.
     */
    public static PriorityQueue<Integer> addRandomValues(int size, BinaryHeap<Integer> heap, Comparator<Integer> c) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(c);
        Random rand = new Random();
        for (int i = 0; i < size; ++i) {
            final int next = rand.nextInt();
            heap.add(next);
            pq.add(next);
        }
        return pq;
    }

    /**
     * Returns a random {@link Integer}.
     *
     * @return A random {@link Integer}.
     */
    public static int generateRandomValue() {
        return new Random().nextInt();
    }
}
