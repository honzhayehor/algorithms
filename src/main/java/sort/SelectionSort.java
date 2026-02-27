package sort;

public class SelectionSort {
    public static int[] sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int smallestIdx = findSmallestIdx(arr, i, arr.length);

            if (smallestIdx != i) {
                int tmp = arr[i];
                arr[i] = arr[smallestIdx];
                arr[smallestIdx] = tmp;
            }
        }
        return arr;
    }

    private static int findSmallestIdx(int[] arr, int from, int to) {
        int smallest_idx = from;
        int smallest = arr[smallest_idx];

        for (int i = from + 1; i < to; i++) {
            if (arr[i] < smallest) {
                smallest = arr[i];
                smallest_idx = i;
            }
        }
        return smallest_idx;
    }
}
