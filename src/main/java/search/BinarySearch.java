package search;

import java.util.Comparator;
import java.util.List;

/**
 * Implementation of Binary search algorithm.
 * <p>Class contains several static methods that work with Java Collection framework
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
    public static <T> int binarySearch(List<? extends T> list, T key, Comparator<? super T> comparator) {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = low + ((high - low) >>> 1);
            T midVal = list.get(mid);

            int cmp = comparator.compare(midVal, key);
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
