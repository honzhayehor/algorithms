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

    public static ElementByte search(byte[] arr) {
        return maxCoreByte(arr.length, i -> arr[i]);
    }

    public static ElementFloat search(float[] arr) {
        return maxCoreFloat(arr.length, i -> arr[i]);
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
    public record ElementByte(int index, byte value) {}
    public record ElementFloat(int index, float value) {}

    @FunctionalInterface
    private interface IntAt { int get(int i); }

    @FunctionalInterface
    private interface LongAt { long get(int i); }

    @FunctionalInterface
    private interface DoubleAt { double get(int i); }

    @FunctionalInterface
    private interface ByteAt { byte get(int i); }

    @FunctionalInterface
    private interface FloatAt { float get(int i); }

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

    private static ElementByte maxCoreByte(int size, ByteAt at) {
        if (size == 0) throw new IllegalArgumentException("Empty array");
        int bestIdx = 0;
        byte best = at.get(0);
        for (int i = 1; i < size; i++) {
            byte v = at.get(i);
            if (v > best) {
                best = v;
                bestIdx = i;
            }
        }
        return new ElementByte(bestIdx, best);
    }

    private static ElementFloat maxCoreFloat(int size, FloatAt at) {
        if (size == 0) throw new IllegalArgumentException("Empty array");
        int bestIdx = 0;
        float best = at.get(0);
        for (int i = 1; i < size; i++) {
            float v = at.get(i);
            if (Float.compare(v, best) > 0) {
                best = v;
                bestIdx = i;
            }
        }
        return new ElementFloat(bestIdx, best);
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

    // Here comes overloaded public static methods that work with values and indexes only (So no Element class will be returned)
    /**
     * Static method that returns only index of the largest element
     * @param arr int array.
     * @return index of the largest element
     * @throws IllegalArgumentException if array is empty
     */
    public static int searchIdx(int[] arr) {
        ElementInt elementInt = search(arr);
        return elementInt.index;
    }
    /**
     * Static method that returns only value of the largest element
     * @param arr int array.
     * @return value of the largest element
     * @throws IllegalArgumentException if array is empty
     */
    public static int searchVal(int[] arr) {
        ElementInt elementInt = search(arr);
        return elementInt.value;
    }
    /**
     * Static method that returns only index of the largest element
     * @param arr double array.
     * @return index of the largest element
     * @throws IllegalArgumentException if array is empty
     */
    public static int searchIdx(double[] arr) {
        ElementDouble elementDouble = search(arr);
        return elementDouble.index;
    }
    /**
     * Static method that returns only value of the largest element
     * @param arr double array.
     * @return value of the largest element
     * @throws IllegalArgumentException if array is empty
     */
    public static double searchVal(double[] arr) {
        ElementDouble elementDouble = search(arr);
        return elementDouble.value;
    }
    /**
     * Static method that returns only index of the largest element
     * @param arr long array.
     * @return index of the largest element
     * @throws IllegalArgumentException if array is empty
     */
    public static int searchIdx(long[] arr) {
        ElementLong elementLong = search(arr);
        return elementLong.index;
    }
    /**
     * Static method that returns only value of the largest element
     * @param arr long array.
     * @return value of the largest element
     * @throws IllegalArgumentException if array is empty
     */
    public static long searchVal(long[] arr) {
        ElementLong elementLong = search(arr);
        return elementLong.value;
    }
    /**
     * Static method that returns only index of the largest element
     * @param arr byte array.
     * @return index of the largest element
     * @throws IllegalArgumentException if array is empty
     */
    public static int searchIdx(byte[] arr) {
        ElementByte elementByte = search(arr);
        return elementByte.index;
    }
    /**
     * Static method that returns only value of the largest element
     * @param arr byte array.
     * @return value of the largest element
     * @throws IllegalArgumentException if array is empty
     */
    public static byte searchVal(byte[] arr) {
        ElementByte elementByte = search(arr);
        return elementByte.value;
    }
    /**
     * Static method that returns only index of the largest element
     * @param arr float array.
     * @return index of the largest element
     * @throws IllegalArgumentException if array is empty
     */
    public static int searchIdx(float[] arr) {
        ElementFloat elementFloat = search(arr);
        return elementFloat.index;
    }
    /**
     * Static method that returns only value of the largest element
     * @param arr float array.
     * @return value of the largest element
     * @throws IllegalArgumentException if array is empty
     */
    public static float searchVal(float[] arr) {
        ElementFloat elementFloat = search(arr);
        return elementFloat.value;
    }
    /**
     * Static method that returns only index of the largest element
     * @param collection collection that contains elements.
     * @param keyExtractor Function that defines how to extract key from collection
     * @param keyComparator Comparator that defines how to compare extracted keys.
     * @return index of the largest element
     * @throws IllegalArgumentException if array is empty
     */
    public static <T, K> int searchIdx(
            Collection<T> collection,
            Function<? super T, ? extends K> keyExtractor,
            Comparator<? super K> keyComparator) {
        Element<T> element = search(collection, keyExtractor, keyComparator);
        return element.index;
    }
    /**
     * Static method that returns only value of the largest element
     * @param collection collection that contains elements.
     * @param keyExtractor Function that defines how to extract key from collection
     * @param keyComparator Comparator that defines how to compare extracted keys.
     * @return value of the largest element
     * @throws IllegalArgumentException if array is empty
     */
    public static <T, K> T searchVal(
            Collection<T> collection,
            Function<? super T, ? extends K> keyExtractor,
            Comparator<? super K> keyComparator) {
        Element<T> element = search(collection, keyExtractor, keyComparator);
        return element.value;
    }

}
