package search;

import java.util.Comparator;
import java.util.List;

/**
 * Implementation of Binary search algorithm.
 * <p>Class contains several overloaded static methods that work with Java Collection framework
 */
public class BinarySearch {

    /**
     * Performs binary search on a list sorted according to the given comparator.
     *
     * @param list sorted list
     * @param key element of type T index of which needs to be found in given list
     * @param comparator comparator based on which two elements of type T can be compared
     * @return index of element or -1 if not found
     */
    public static <T> int search(List<? extends T> list, T key, Comparator<? super T> comparator) {
        return binarySearchCore(list.size(), mid -> comparator.compare(list.get(mid), key));
    }

    /**
     * Performs binary search on a sorted integer array.
     *
     * @param arr sorted integer array
     * @param key element of type int index of which needs to be found in given array
     * @return index of element or -1 if not found
     */
    public static int search(int[] arr, int key) {
        return binarySearchCore(arr.length, mid -> Integer.compare(arr[mid], key));
    }

    /**
     * Performs binary search on a sorted float array.
     *
     * @param arr sorted float array
     * @param key element of type float index of which needs to be found in given array
     * @return index of element or -1 if not found
     */
    public static int search(float[] arr, float key) {
        return binarySearchCore(arr.length, mid -> Float.compare(arr[mid], key));
    }

    /**
     * Performs binary search on a sorted double array.
     *
     * @param arr sorted double array
     * @param key element of type double index of which needs to be found in given array
     * @return index of element or -1 if not found
     */
    public static int search(double[] arr, double key) {
        return binarySearchCore(arr.length, mid -> Double.compare(arr[mid], key));
    }

    /**
     * Performs binary search on a sorted byte array.
     *
     * @param arr sorted byte array
     * @param key element of type byte index of which needs to be found in given array
     * @return index of element or -1 if not found
     */
    public static int search(byte[] arr, byte key) {
        return binarySearchCore(arr.length, mid -> Byte.compare(arr[mid], key));
    }

    @FunctionalInterface
    private interface IntComparatorAtIndex {
        int compareAt(int index);
    }

    private static int binarySearchCore(int size, IntComparatorAtIndex cmpAt) {
        int low = 0;
        int high = size - 1;

        while (low <= high) {
            int mid = low + ((high - low) >>> 1);
            int cmp = cmpAt.compareAt(mid);

            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
