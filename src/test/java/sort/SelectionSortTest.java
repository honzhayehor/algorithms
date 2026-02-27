package sort;

import org.junit.jupiter.api.Test;

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

}