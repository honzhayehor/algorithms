package sort;

import java.util.Comparator;

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
    public static <T> T[] sort(T[] arr, Comparator<? super T> comparator) {
        for (int i = 0; i < arr.length; i++) {
            int smallestIdx = findSmallestIdx(arr, i, arr.length, comparator);

            if (smallestIdx != i) { // swap
                T tmp = arr[i];
                arr[i] = arr[smallestIdx];
                arr[smallestIdx] = tmp;
            }
        }
        return arr;
    }

    private static int findSmallestIdx(int[] arr, int from, int to) {
        int smallestIdx = from;
        int smallest = arr[smallestIdx];

        for (int i = from + 1; i < to; i++) {
            if (arr[i] < smallest) {
                smallest = arr[i];
                smallestIdx = i;
            }
        }
        return smallestIdx;
    }
    private static int findSmallestIdx(byte[] arr, int from, int to) {
        int smallestIdx = from;
        byte smallest = arr[smallestIdx];

        for (int i = from + 1; i < to; i++) {
            if (arr[i] < smallest) {
                smallest = arr[i];
                smallestIdx = i;
            }
        }
        return smallestIdx;
    }
    private static int findSmallestIdx(long[] arr, int from, int to) {
        int smallestIdx = from;
        long smallest = arr[smallestIdx];

        for (int i = from + 1; i < to; i++) {
            if (arr[i] < smallest) {
                smallest = arr[i];
                smallestIdx = i;
            }
        }
        return smallestIdx;
    }
    private static int findSmallestIdx(float[] arr, int from, int to) {
        int smallestIdx = from;
        float smallest = arr[smallestIdx];

        for (int i = from + 1; i < to; i++) {
            if (arr[i] < smallest) {
                smallest = arr[i];
                smallestIdx = i;
            }
        }
        return smallestIdx;
    }
    private static int findSmallestIdx(double[] arr, int from, int to) {
        int smallestIdx = from;
        double smallest = arr[smallestIdx];

        for (int i = from + 1; i < to; i++) {
            if (arr[i] < smallest) {
                smallest = arr[i];
                smallestIdx = i;
            }
        }
        return smallestIdx;
    }
    private static int findSmallestIdx(short[] arr, int from, int to) {
        int smallestIdx = from;
        short smallest = arr[smallestIdx];

        for (int i = from + 1; i < to; i++) {
            if (arr[i] < smallest) {
                smallest = arr[i];
                smallestIdx = i;
            }
        }
        return smallestIdx;
    }
    private static <T> int findSmallestIdx(T[] arr, int from, int to, Comparator<? super T> comparator) {
        if (comparator == null) throw new IllegalArgumentException("Comparator cannot be null");
        int smallestIdx = from;
        T smallest = arr[from];

        for (int i = from + 1; i < to; i++) {
            if (comparator.compare(arr[i], smallest) < 0) {
                smallest = arr[i];
                smallestIdx = i;
            }
        }
        return smallestIdx;
    }
}
