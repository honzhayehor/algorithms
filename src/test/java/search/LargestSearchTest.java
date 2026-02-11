package search;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LargestSearchTest {
    private record Person(int salary) {};

    @Test
    void returnsPersonWithTheBiggestSalary() {
        List<Person> personList = List.of(
                new Person(120_000),
                new Person(90_000),
                new Person(200_000),
                new Person(400_000),
                new Person(120_000));

        LargestSearch.Element<Person> element =
                LargestSearch.search(personList, Person::salary, Integer::compare);

        assertEquals(3, element.index());

        int salary = element.value().salary();
        assertEquals(400_000.0, salary);
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

}