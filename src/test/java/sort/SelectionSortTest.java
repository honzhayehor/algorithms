package sort;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void correctSortOfListOfPerson() {
        record Person(int salary) {}

        List<Person> people = new ArrayList<>();
        people.add(new Person(242));
        people.add(new Person(100));
        people.add(new Person(79));
        people.add(new Person(123));

        List<Person> expected = List.of(
                new Person(79),
                new Person(100),
                new Person(123),
                new Person(242)
        );

        SelectionSort.sort(people, Comparator.comparingInt(Person::salary));
        assertEquals(expected, people);

    }

    @Test
    void throwsUnsupportedExceptionWhenUnmodifiableListProvided() {
        record Person(int salary) {}

        List<Person> people = List.of(
                new Person(242),
                new Person(100),
                new Person(100),
                new Person(79)
        );

        List<Person> expected = List.of(
                new Person(79),
                new Person(100),
                new Person(123),
                new Person(242)
        );
        assertThrows(UnsupportedOperationException.class, () -> SelectionSort.sort(people, Comparator.comparingInt(Person::salary)));
    }

    @Test
    void throwsIllegalArgumentExceptionWhenNullComparatorProvided() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(1);
        list.add(14);
        assertThrows(IllegalArgumentException.class, () -> {SelectionSort.sort(list, null);});
    }


    private record Person(int salary){};

    @ParameterizedTest
    @MethodSource("providePeople")
    void returnsCorrectResultNoMatterTheAttempts(List<Person> people) {

        List<Person> sorted =
                SelectionSort.sort(people, Comparator.comparingInt(Person::salary));

        assertSorted(sorted, Comparator.comparingInt(Person::salary));
    }
    private static <T> void assertSorted(
            List<T> list,
            Comparator<? super T> comparator) {

        for (int i = 1; i < list.size(); i++) {
            assertTrue(
                    comparator.compare(list.get(i - 1), list.get(i)) <= 0,
                    "List is not sorted at index " + i
            );
        }
    }
    private static Stream<List<Person>> providePeople() {
        Random random = new Random(42);
        return IntStream.rangeClosed(1, 20)
                .mapToObj(ignore -> generateRandomPeople(random));
    }
    private static List<Person> generateRandomPeople(Random random) {

        int minPeople = 1;
        int maxPeople = 100;

        int salaryMin = 100;
        int salaryMax = 500;

        int numberOfPeople =
                random.nextInt(maxPeople - minPeople + 1) + minPeople;

        List<Person> people = new ArrayList<>();

        for (int i = 0; i < numberOfPeople; i++) {
            int salary =
                    random.nextInt(salaryMax - salaryMin + 1) + salaryMin;

            people.add(new Person(salary));
        }

        return people;
    }
}