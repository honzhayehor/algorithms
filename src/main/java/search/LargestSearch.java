package search;
/**
 * Implementation of searching algorithm to help finding index (or element itself) of the largest element in a list or array
 * <p>Class contains several overloaded static methods that work with Java Collection framework
 */
public class LargestSearch {
    /**
     * Search of the largest element in integer array
     * @param arr integer array. Not necessary sorted;
     * @return index of the largest element
     */
    public static int searchIdx(int[] arr) {
        //TODO: implement method using searchLargestCore() method
        return -1;
    }
    /**
     * Search of the largest element in integer array
     * @param arr integer array. Not necessary sorted;
     * @return largest element
     */
    public static int searchEl(int[] arr) {
        //TODO: implement method using searchLargestCore() method
        return -1;
    }

    public static Element search(int[] arr) {
        //TODO: implement method using searchLargestCore() method
        return new Element(1, 1);
    }

    /**
     * Private inner class that contains largest element index and value
     */
    private record Element(int index, int value) {};


    @FunctionalInterface
    private interface IntComparatorAtIndex {
        int compareAt(int index);
    }

    //TODO: Adjust to return Element class that contains idx and value.
    private static int searchLargestCore(int size, IntComparatorAtIndex cmpAt) {
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
