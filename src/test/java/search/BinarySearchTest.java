package search;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTest {

    record Person(int salary) {};

    List<Person> listOfPersons = List.of(new Person(100), new Person(200), new Person(300), new Person(500));

    @Test
    void findsCorrectIndexOfPersonBySalary() {
        Person p = new Person(200);
        int index = BinarySearch.binarySearch(listOfPersons, p, Comparator.comparingInt(Person::salary));
        assertEquals(1, index);
    }

    @Test
    void returnsNegativeOneBecauseElementIsNotInList() {
        Person p = new Person(800);
        int index = BinarySearch.binarySearch(listOfPersons, p, Comparator.comparingInt(Person::salary));
        assertEquals(-1, index);
    }

}