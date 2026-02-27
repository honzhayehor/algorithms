package sort;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SelectionSortTest {

    @Test
    void correctSortForIntArray1() {
        int[] arrToSort = {12, 53, 22, 63, 45, 76, 98, 1, 2, 11, 2};
        int[] expected = {1, 2, 2, 11, 12, 22, 45, 53, 63, 76, 98};
        int[] actual = SelectionSort.sort(arrToSort);

        assertArrayEquals(expected, actual);
    }

    @Test
    void correctSortForIntArray2() {
        int[] arrToSort = {3, 2, 1, 4};
        int[] expected = {1, 2, 3, 4};
        int[] actual = SelectionSort.sort(arrToSort);

        assertArrayEquals(expected, actual);
    }

    @Test
    void assertInitialArrayIsSorted() {
        int[] initialArray = {2, 5, 3, 2, 1};
        SelectionSort.sort(initialArray);
        assertArrayEquals(new int[] {1, 2, 2, 3, 5},initialArray);
    }

    @Test
    void correctSortWithObjectArray() {
        record Person(int salary){}

        Person[] people = new Person[] {
                new Person(321),
                new Person(430),
                new Person(100),
                new Person(210)
        };


        Person[] expected = new Person[] {
                new Person(100),
                new Person(210),
                new Person(321),
                new Person(430)
        };
        SelectionSort.sort(people, Comparator.comparingInt(Person::salary));

        assertArrayEquals(expected, people);
    }
}