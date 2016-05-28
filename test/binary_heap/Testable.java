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

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 *
 * @author Matthias Fussenegger
 */
public interface Testable {

    /**
     * Adds random {@link Integer} values up to a maximum size to the specified
     * heap and returns a {@link PriorityQueue} with the same values
     *
     * @param size The maximum size of values to be added
     * @param heap The heap that will be filled with random values
     * @return A {@link PriorityQueue} with the same values as in the heap
     */
    default PriorityQueue<Integer> addRandomValues(int size, BinaryHeap<Integer> heap) {
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
     * heap and returns a {@link PriorityQueue} with the same values
     *
     * @param size The maximum size of values to be added
     * @param heap The heap that will be filled with random values
     * @param c The comparator used to initialize the {@link PriorityQueue}
     * @return A {@link PriorityQueue} with the same values as in the heap
     */
    default PriorityQueue<Integer> addRandomValues(int size, BinaryHeap<Integer> heap, Comparator<Integer> c) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(c);
        Random rand = new Random();
        for (int i = 0; i < size; ++i) {
            final int next = rand.nextInt();
            heap.add(next);
            pq.add(next);
        }
        return pq;
    }

}
