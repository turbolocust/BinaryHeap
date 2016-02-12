/*
 * Copyright (C) 2016 Matthias
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
public class BinaryMaxHeapTest {

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
        Object value = "value";
        BinaryHeap<Object> instance = new BinaryMaxHeap<>();
        instance.add(value);
    }

    /**
     * Test of remove method, of class BinaryMaxHeap.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        Integer value1 = 1, value2 = 2, value3 = 3, value4 = 4;
        Integer value5 = 2, value6 = 4, value7 = 8, value8 = 5;
        BinaryHeap<Integer> instance = new BinaryMaxHeap<>(12);
        instance.add(value1);
        instance.add(value2);
        instance.add(value3);
        instance.add(value4);
        instance.add(value5);
        instance.add(value6);
        instance.add(value7);
        instance.add(value8);
        System.out.println(Arrays.toString(instance.toArray()));
        Integer expResult = 8; //largest value
        Integer result = instance.remove();
        System.out.println(Arrays.toString(instance.toArray()));
        assertEquals(expResult, result);
    }

    /**
     * Test of remove method with comparator, of class BinaryMaxHeap.
     */
    @Test
    public void testRemoveComparator() {
        System.out.println("removeComparator");
        Integer value1 = 1, value2 = 2, value3 = 3, value4 = 4;
        Integer value5 = 2, value6 = 4, value7 = 8, value8 = 5;
        BinaryHeap<Integer> instance = new BinaryMaxHeap<>(12, (Integer i1, Integer i2) -> (i1.compareTo(i2)));
        instance.add(value1);
        instance.add(value2);
        instance.add(value3);
        instance.add(value4);
        instance.add(value5);
        instance.add(value6);
        instance.add(value7);
        instance.add(value8);
        System.out.println(Arrays.toString(instance.toArray()));
        Integer expResult = 8; //largest value
        Integer result = instance.remove();
        System.out.println(Arrays.toString(instance.toArray()));
        assertEquals(expResult, result);
    }
}
