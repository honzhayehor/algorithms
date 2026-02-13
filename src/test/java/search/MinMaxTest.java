package search;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static search.MinMax.Max;
import static search.MinMax.*;
import static search.MinMax.Min;

class MinMaxTest {
    @Test
    void findsLargestInIntArray(){
        int[] arr = new int[] {1, 2, 2, 123, 4, 120, 400};
        int index = Max.indexOf(arr);
        int value = Max.valueOf(arr);
        ElementInt elementInt = Max.of(arr);
        assertEquals(index, elementInt.index());
        assertEquals(value, elementInt.value());
        assertEquals(6, index);
        assertEquals(400, value);
    }
    @Test
    void findsLargestInByteArray(){
        byte[] arr = new byte[] {1, 2, 2, 123, 4, 120, 127};
        int index = Max.indexOf(arr);
        byte value = Max.valueOf(arr);
        ElementByte elementInt = Max.of(arr);
        assertEquals(index, elementInt.index());
        assertEquals(value, elementInt.value());
        assertEquals(6, index);
        assertEquals(127, value);
    }
    @Test
    void findsLargestInLongArray(){
        long[] arr = new long[] {1, 2, 2, 123, 4, 120, 400};
        int index = Max.indexOf(arr);
        long value = Max.valueOf(arr);
        ElementLong elementInt = Max.of(arr);
        assertEquals(index, elementInt.index());
        assertEquals(value, elementInt.value());
        assertEquals(6, index);
        assertEquals(400, value);
    }
    @Test
    void findsLargestInFloatArray(){
        float[] arr = new float[] {1.0f, 2.0f, 2.0f, 123.0f, 4.0f, 120.0f, 400.0f};
        int index = Max.indexOf(arr);
        float value = Max.valueOf(arr);
        ElementFloat elementInt = Max.of(arr);
        assertEquals(index, elementInt.index());
        assertEquals(value, elementInt.value());
        assertEquals(6, index);
        assertEquals(400.0f, value);
    }
    @Test
    void findsLargestInPersonCollection(){
        List< BinarySearchTest.Person> list = List.of(
                new BinarySearchTest.Person(100_000),
                new BinarySearchTest.Person(200_000),
                new BinarySearchTest.Person(123_000),
                new BinarySearchTest.Person(213_222),
                new BinarySearchTest.Person(399_000)
        );
        int index = Max.indexOf(list, BinarySearchTest.Person::salary, Integer::compare);
        int value = Max.valueOf(list, BinarySearchTest.Person::salary, Integer::compare).salary();
        Element<BinarySearchTest.Person> elementInt = Max.of(list, BinarySearchTest.Person::salary, Integer::compare);
        assertEquals(index, elementInt.index());
        assertEquals(value, elementInt.value().salary());
        assertEquals(4, index);
        assertEquals(399_000, value);
    }
}