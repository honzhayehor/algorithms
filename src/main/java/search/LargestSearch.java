package search;

import java.util.*;
import java.util.function.Function;

/**
 * Implementation of searching algorithm to help finding index (or element itself) of the largest element in a list or array
 * <p>Class contains several overloaded static methods that work with Java Collection framework
 */
public class LargestSearch {
    private LargestSearch() {}

    /**
     * Search of the largest element in integer array
     * @param arr integer array. Not necessarily sorted;
     * @return ElementInt that contains index of the largest value and the value itself
     */
    public static ElementInt search(int[] arr) {
        return maxCoreInt(arr.length, i -> arr[i]);
    }
    /**
     * Search of the largest element in long array
     * @param arr long array. Not necessarily sorted;
     * @return ElementLong that contains index of the largest value and the value itself
     */
    public static ElementLong search(long[] arr) {
        return maxCoreLong(arr.length, i -> arr[i]);
    }

    /**
     * Search of the largest element in double array
     * @param arr double array. Not necessarily sorted;
     * @return ElementDouble that contains index of the largest value and the value itself
     */
    public static ElementDouble search(double[] arr) {
        return maxCoreDouble(arr.length, i -> arr[i]);
    }
    /**
     * Search of the largest element in integer array
     * @param collection collection of objects (not necessarily sorted).
     * @param keyExtractor function that describes how to extract value from an object;
     * @param keyComparator comparator that describes how to compare extracted values;
     * @return <b>Element</b> that contains index of the largest element and its value
     * @throws IllegalArgumentException if collection is empty
     */
    public static <T, K> Element<T> search(
            Collection<T> collection,
            Function<? super T, ? extends K> keyExtractor,
            Comparator<? super K> keyComparator)
    {
        return searchCore(collection, keyExtractor, keyComparator );
    }

    /**
     * Inner classes that contain largest element index and value
     */
    public record Element<T>(int index, T value) {}
    public record ElementInt(int index, int value) {}
    public record ElementLong(int index, long value) {}
    public record ElementDouble(int index, double value) {}


    @FunctionalInterface
    private interface IntAt { int get(int i); }

    @FunctionalInterface
    private interface LongAt { long get(int i); }

    @FunctionalInterface
    private interface DoubleAt { double get(int i); }

    private static ElementInt maxCoreInt(int size, IntAt at) {
        if (size == 0) throw new IllegalArgumentException("Empty array");
        int bestIdx = 0;
        int best = at.get(0);
        for (int i = 1; i < size; i++) {
            int v = at.get(i);
            if (v > best) {
                best = v;
                bestIdx = i;
            }
        }
        return new ElementInt(bestIdx, best);
    }

    private static ElementLong maxCoreLong(int size, LongAt at) {
        if (size == 0) throw new IllegalArgumentException("Empty array");
        int bestIdx = 0;
        long best = at.get(0);
        for (int i = 1; i < size; i++) {
            long v = at.get(i);
            if (v > best) {
                best = v;
                bestIdx = i;
            }
        }
        return new ElementLong(bestIdx, best);
    }

    private static ElementDouble maxCoreDouble(int size, DoubleAt at) {
        if (size == 0) throw new IllegalArgumentException("Empty array");
        int bestIdx = 0;
        double best = at.get(0);
        for (int i = 1; i < size; i++) {
            double v = at.get(i);
            if (Double.compare(v, best) > 0) {
                best = v;
                bestIdx = i;
            }
        }
        return new ElementDouble(bestIdx, best);
    }

    /**
     * Search of the largest element in integer array
     * @param items collection where we want to find the largest element;
     * @param keyExtractor function that describes how to extract value from an object;
     * @param keyComparator comparator that describes how to compare extracted values;
     * @return class of type <b>Element</b> that contains index of the largest element and its value
     * @throws IllegalArgumentException if collection is empty
     */
    private static <T, K> Element<T> searchCore(
            Iterable<? extends T> items,
            Function<? super T, ? extends K> keyExtractor,
            Comparator<? super K> keyComparator
    ) {
        Iterator<? extends T> it = items.iterator();
        if (!it.hasNext()) {
            throw new IllegalArgumentException("Empty collection");
        }

        int idx = 0;
        int bestIdx = 0;
        T bestVal = it.next();
        K bestKey = keyExtractor.apply(bestVal);

        while (it.hasNext()) {
            idx++;
            T curVal = it.next();
            K curKey = keyExtractor.apply(curVal);

            if (keyComparator.compare(curKey, bestKey) > 0) {
                bestKey = curKey;
                bestVal = curVal;
                bestIdx = idx;
            }
        }
        return new Element<>(bestIdx, bestVal);
    }
}
