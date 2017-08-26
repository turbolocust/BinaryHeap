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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Matthias Fussenegger
 */
public class BinaryMinHeapTest {

    private static final int HEAP_SIZE = 1024;

    public BinaryMinHeapTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class BinaryMinHeap.
     */
    @Test
    public void testAdd() {
        System.out.println("MIN HEAP");
        System.out.println("add");
        BinaryHeap<Integer> instance = new BinaryMinHeap<>();
        PriorityQueue<Integer> pq = TestUtil.addRandomValues(HEAP_SIZE, instance);
        assertEquals(pq.peek(), instance.peek());
    }

    @Test
    public void testAddAll() {
        System.out.println("MIN HEAP");
        System.out.println("addAll");
        BinaryHeap<Integer> instance = new BinaryMinHeap<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        Integer[] values = new Integer[HEAP_SIZE];
        for (int i = 0; i < HEAP_SIZE; ++i) {
            int value = TestUtil.generateRandomValue();
            values[i] = value;
            pq.add(value);
        }

        instance.add(values);
        assertEquals(pq.peek(), instance.peek());
    }

    /**
     * Test of remove method, of class BinaryMinHeap.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        BinaryHeap<Integer> instance = new BinaryMinHeap<>();
        PriorityQueue<Integer> pq = TestUtil.addRandomValues(HEAP_SIZE, instance);
        assertEquals(pq.remove(), instance.remove());
    }

    /**
     * Test of remove method with comparator, of class BinaryMinHeap.
     */
    @Test
    public void testRemoveComparator() {
        System.out.println("removeComparator");
        Comparator<Integer> c = (Integer i1, Integer i2) -> {
            Integer v1 = i1;
            Integer v2 = i2;
            return v2.compareTo(v1);
        };
        BinaryHeap<Integer> instance = new BinaryMinHeap<>(c);
        PriorityQueue<Integer> pq = TestUtil.addRandomValues(HEAP_SIZE, instance, c);
        assertEquals(pq.remove(), instance.remove());
    }

}
