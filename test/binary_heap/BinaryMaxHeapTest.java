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
public class BinaryMaxHeapTest implements Testable {

    public BinaryMaxHeapTest() {
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
     * Test of add method, of class BinaryMaxHeap.
     */
    @Test
    public void testAdd() {
        System.out.println("MAX HEAP");
        System.out.println("add");
        //java priority queue needs a comparator to fulfil maximum heap condition
        Comparator<Integer> c = (Integer i1, Integer i2) -> {
            Integer v1 = i1;
            Integer v2 = i2;
            return v2.compareTo(v1);
        };
        BinaryHeap<Integer> instance = new BinaryMaxHeap<>();
        PriorityQueue<Integer> pq = addRandomValues(200, instance, c);
        assertEquals(pq.peek(), instance.peek());
    }

    /**
     * Test of remove method, of class BinaryMaxHeap.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        //java priority queue needs a comparator to fulfil maximum heap condition
        Comparator<Integer> c = (Integer i1, Integer i2) -> {
            Integer v1 = i1;
            Integer v2 = i2;
            return v2.compareTo(v1);
        };
        BinaryHeap<Integer> instance = new BinaryMaxHeap<>();
        PriorityQueue<Integer> pq = addRandomValues(200, instance, c);
        assertEquals(pq.remove(), instance.remove());
    }

    /**
     * Test of remove method with comparator, of class BinaryMaxHeap.
     */
    @Test
    public void testRemoveComparator() {
        System.out.println("removeComparator");
        //java priority queue needs a comparator to fulfil maximum heap condition
        Comparator<Integer> c1 = (Integer i1, Integer i2) -> {
            Integer v1 = i1;
            Integer v2 = i2;
            return v1.compareTo(v2);
        };
        Comparator<Integer> c2 = (Integer i1, Integer i2) -> {
            Integer v1 = i1;
            Integer v2 = i2;
            return v2.compareTo(v1);
        };
        BinaryHeap<Integer> instance = new BinaryMaxHeap<>(c1);
        PriorityQueue<Integer> pq = addRandomValues(200, instance, c2);
        assertEquals(pq.remove(), instance.remove());
    }

}
