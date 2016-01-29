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
package binaryHeap;

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
public class BinaryMinHeapTest {

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
        Object value = "value";
        BinaryMinHeap<Object> instance = new BinaryMinHeap<>();
        instance.add(value);
    }

    /**
     * Test of remove method, of class BinaryMinHeap.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        Integer value1 = 1;
        Integer value2 = 2;
        Integer value3 = 3;
        Integer value4 = 4;
        Integer value5 = 2;
        Integer value6 = 4;
        Integer value7 = 8;
        Integer value8 = 5;
        BinaryMinHeap<Integer> instance = new BinaryMinHeap<>(12);
        instance.add(value1);
        instance.add(value2);
        instance.add(value3);
        instance.add(value4);
        instance.add(value5);
        instance.add(value6);
        instance.add(value7);
        instance.add(value8);
        System.out.println(Arrays.toString(instance.toArray()));
        Integer expResult = 1; //smallest value
        Integer result = instance.remove();
        System.out.println(Arrays.toString(instance.toArray()));
        assertEquals(expResult, result);
    }

}
