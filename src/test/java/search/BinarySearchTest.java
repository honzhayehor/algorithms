package search;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTest {

    record Person(int salary) {}

    List<Person> listOfPersons = List.of(new Person(100), new Person(200), new Person(300), new Person(500));

    @Test
    void findsCorrectIndexOfPersonaBySalary() {
        Person p = new Person(200);
        int index = BinarySearch.search(listOfPersons, p, Comparator.comparingInt(Person::salary));
        assertEquals(1, index);
    }

    @Test
    void returnsNegativeOneBecauseElementIsNotInList() {
        Person p = new Person(800);
        int index = BinarySearch.search(listOfPersons, p, Comparator.comparingInt(Person::salary));
        assertEquals(-1, index);
    }

    @Test
    void searchWithIntArray() {
        int[] arr = {1, 2, 3, 5, 8, 10, 123};

        int index = BinarySearch.search(arr, 5);
        assertEquals(3, index);
    }

    @Test
    void searchWithFloatArray() {
        float[] arr = {1f, 2f, 3f, 5f, 8f, 10f, 123f};
        int index = BinarySearch.search(arr, 5f);
        assertEquals(3, index);
    }

    @Test
    void searchWithDoubleArray() {
        double[] arr = {1.0, 2.0, 3.0, 5.0, 8.0, 10.0, 123.0};
        int index = BinarySearch.search(arr, 10.0);
        assertEquals(5, index);
    }

    @Test
    void searchWithByteArray() {
        byte[] arr = {1, 2, 3, 5, 8, 10, 123};
        int index = BinarySearch.search(arr, (byte) 10);
        assertEquals(5, index);
    }

    @Test
    void returnMiddleIndexIfAllElementsAreTheSame() {
        byte[] arr = {12, 12, 12, 12, 12};
        int index = BinarySearch.search(arr, (byte) 12);
        assertEquals(2, index);
    }

    @Test
    void throwsExceptionWhenSizeIsZero() {
        byte[] arr = {};
        assertThrows(IllegalArgumentException.class, () -> BinarySearch.search(arr, (byte) 2));
    }
}