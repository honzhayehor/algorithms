package search;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LargestSearchTest {
    private record Person(int salary) {}

    @Test
    void returnsPersonWithTheBiggestSalary() {
        List<Person> personList = List.of(
                new Person(120_000),
                new Person(90_000),
                new Person(200_000),
                new Person(400_000),
                new Person(120_000),
                new Person(120_000),
                new Person(90_000),
                new Person(200_000),
                new Person(423_111),
                new Person(294_222));

        LargestSearch.Element<Person> element;
        element = LargestSearch.search(personList, Person::salary, Integer::compare);

        assertEquals(8, element.index());

        int salary = element.value().salary();
        assertEquals(423_111.0, salary);
    }

    @Test
    void returnsLargestIntFromIntArray() {
        int arr[] = new int[] {1, 2, 23, 53, 7, 213, 4567, 23};
        LargestSearch.ElementInt elementInt = LargestSearch.search(arr);
        assertEquals(6, elementInt.index());
        assertEquals(4567, elementInt.value());
    }

    @Test
    void returnsLargestDoubleFromDoubleArray() {
        double arr[] = new double[] {1, 2, 23, 53, 7, 213, 4567, 23};
        LargestSearch.ElementDouble elementInt = LargestSearch.search(arr);
        assertEquals(6, elementInt.index());
        assertEquals(4567, elementInt.value());
    }

    @Test
    void ifAllElementsTheSameReturnsFirstOccurrence() {
        int arr[] = new int[] {12, 12, 12, 12, 12, 12, 12};
        LargestSearch.ElementInt elementInt = LargestSearch.search(arr);
        assertEquals(0, elementInt.index());
        assertEquals(12, elementInt.value());
    }

    @Test
    void returnsLargestByteInByteArray() {
        byte arr[] = new byte[] {1, 23, 53, 7, 23};
        LargestSearch.ElementByte elementByte = LargestSearch.search(arr);
        assertEquals(2, elementByte.index());
        assertEquals(53, elementByte.value());
    }

    @Test
    void returnsLargestLongInLongArray() {
        long arr[] = new long[] {1, 23, 53, 7, 23};
        LargestSearch.ElementLong elementLong = LargestSearch.search(arr);
        assertEquals(2, elementLong.index());
        assertEquals(53, elementLong.value());
    }

    @Test
    void returnLargestFloatInFloatArray() {
        float arr[] = new float[] {1f, 23f, 53f, 7f, 23f};
        LargestSearch.ElementFloat elementFloat = LargestSearch.search(arr);
        assertEquals(2, elementFloat.index());
        assertEquals(53, elementFloat.value());
    }
}