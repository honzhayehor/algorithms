package sort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

    @Test
    void correctSortOfIntArray() {
        int[] arr = {43, 2, 113, 552, 12, 1};
        int[] expected = {1, 2, 12, 43, 113, 552};

        QuickSort.sort(arr, 0, arr.length - 1);
        assertArrayEquals(expected, arr);
    }

}