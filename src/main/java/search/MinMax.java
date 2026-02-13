package search;

import java.util.*;
import java.util.function.Function;
/**
 * Class that contains static methods for searching largest or smallest value in given list/array
 */
public class MinMax {

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

    //Private enum that is helps with control flow (">" or "<" to be used when comparing values)
    private enum Mode { MAX, MIN }

    /**
     * @param size size of given array
     * @param at functional interface that specifies how to get element at certain index
     * @param m Mode which should be used in that method (MAX or MIN for finding max or min value respectively)
     * @return ElementInt record that contains biggest/smallest element's index and value
     * @throws IllegalArgumentException in case if empty array is provided.
     */
    private static ElementInt coreInt(int size, IntAt at, Mode m) {
        if (size == 0) throw new IllegalArgumentException("Empty array");
        int bestIdx = 0;
        int best = at.get(0);
        for (int i = 1; i < size; i++) {
            int v = at.get(i);
            if ((m == Mode.MAX && v > best) || (m == Mode.MIN && v < best)) {
                best = v;
                bestIdx = i;
            }
        }
        return new ElementInt(bestIdx, best);
    }
    /**
     * @param size size of given array
     * @param at functional interface that specifies how to get element at certain index
     * @param m Mode which should be used in that method (MAX or MIN for finding max or min value respectively)
     * @return ElementLong record that contains biggest/smallest element's index and value
     * @throws IllegalArgumentException in case if empty array is provided.
     */
    private static ElementLong coreLong(int size, LongAt at, Mode m) {
        if (size == 0) throw new IllegalArgumentException("Empty array");
        int bestIdx = 0;
        long best = at.get(0);
        for (int i = 1; i < size; i++) {
            long v = at.get(i);
            if ((m == Mode.MAX && v > best) || (m == Mode.MIN && v < best)) {
                best = v;
                bestIdx = i;
            }
        }
        return new ElementLong(bestIdx, best);
    }
    /**
     * @param size size of given array
     * @param at functional interface that specifies how to get element at certain index
     * @param m Mode which should be used in that method (MAX or MIN for finding max or min value respectively)
     * @return ElementDouble record that contains biggest/smallest element's index and value
     * @throws IllegalArgumentException in case if empty array is provided.
     */
    private static ElementDouble coreDouble(int size, DoubleAt at, Mode m) {
        if (size == 0) throw new IllegalArgumentException("Empty array");
        int bestIdx = 0;
        double best = at.get(0);
        for (int i = 1; i < size; i++) {
            double v = at.get(i);
            if ((m == Mode.MAX && v > best) || (m == Mode.MIN && v < best)) {
                best = v;
                bestIdx = i;
            }
        }
        return new ElementDouble(bestIdx, best);
    }
    /**
     * @param size size of given array
     * @param at functional interface that specifies how to get element at certain index
     * @param m Mode which should be used in that method (MAX or MIN for finding max or min value respectively)
     * @return ElementByte record that contains biggest/smallest element's index and value
     * @throws IllegalArgumentException in case if empty array is provided.
     */
    private static ElementByte coreByte(int size, ByteAt at, Mode m) {
        if (size == 0) throw new IllegalArgumentException("Empty array");
        int bestIdx = 0;
        byte best = at.get(0);
        for (int i = 1; i < size; i++) {
            byte v = at.get(i);
            if ((m == Mode.MAX && v > best) || (m == Mode.MIN && v < best)) {
                best = v;
                bestIdx = i;
            }
        }
        return new ElementByte(bestIdx, best);
    }
    /**
     * @param size size of given array
     * @param at functional interface that specifies how to get element at certain index
     * @param m Mode which should be used in that method (MAX or MIN for finding max or min value respectively)
     * @return ElementFloat record that contains biggest/smallest element's index and value
     * @throws IllegalArgumentException in case if empty array is provided.
     */
    private static ElementFloat coreFloat(int size, FloatAt at, Mode m) {
        if (size == 0) throw new IllegalArgumentException("Empty array");
        int bestIdx = 0;
        float best = at.get(0);
        for (int i = 1; i < size; i++) {
            float v = at.get(i);
            if ((m == Mode.MAX && v > best) || (m == Mode.MIN && v < best)) {
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
     * @param m Mode which should be used in that method (MAX or MIN for finding max or min value respectively)
     * @return Element of type T record that contains biggest/smallest element's index and value
     * @throws IllegalArgumentException if collection is empty
     */
    private static <T, K> Element<T> coreObject(
            Iterable<? extends T> items,
            Function<? super T, ? extends K> keyExtractor,
            Comparator<? super K> keyComparator,
            Mode m
    ) {
        Iterator<? extends T> it = items.iterator();
        if (!it.hasNext()) {
            throw new IllegalArgumentException("Empty collection");
        }
        Comparator<? super K> effective =
                (m == Mode.MAX)
                        ? keyComparator
                        : keyComparator.reversed();

        int idx = 0;
        int bestIdx = 0;
        T bestVal = it.next();
        K bestKey = keyExtractor.apply(bestVal);

        while (it.hasNext()) {
            idx++;
            T curVal = it.next();
            K curKey = keyExtractor.apply(curVal);

            if (effective.compare(curKey, bestKey) > 0) {
                bestKey = curKey;
                bestVal = curVal;
                bestIdx = idx;
            }
        }
        return new Element<>(bestIdx, bestVal);
    }

    public static final class Max {
        private Max() {}
        /**
         * Search the largest element in given array
         * @param arr integer array. Not necessarily sorted;
         * @return ElementInt that contains index of the largest value and the value itself
         * @throws IllegalArgumentException if the given array is empty
         */
        public static ElementInt of(int[] arr) {
            return coreInt(arr.length, i -> arr[i], Mode.MAX);
        }
        /**
         * Search the largest element in given array
         * @param arr integer array. Not necessarily sorted;
         * @return ElementLong that contains index of the largest value and the value itself
         * @throws IllegalArgumentException if the given array is empty
         */
        public static ElementLong of(long[] arr) {
            return coreLong(arr.length, i -> arr[i], Mode.MAX);
        }
        /**
         * Search the largest element in given array
         * @param arr integer array. Not necessarily sorted;
         * @return ElementDouble that contains index of the largest value and the value itselfa
         * @throws IllegalArgumentException if the given array is empty
         */
        public static ElementDouble of(double[] arr) {
            return coreDouble(arr.length, i -> arr[i], Mode.MAX);
        }
        /**
         * Search the largest element in given array
         * @param arr integer array. Not necessarily sorted;
         * @return ElementByte that contains index of the largest value and the value itself
         * @throws IllegalArgumentException if the given array is empty
         */
        public static ElementByte of(byte[] arr) {
            return coreByte(arr.length, i -> arr[i], Mode.MAX);
        }
        /**
         * Search the largest element in given array
         * @param arr integer array. Not necessarily sorted;
         * @return ElementFloat that contains index of the largest value and the value itself
         * @throws IllegalArgumentException if the given array is empty
         */
        public static ElementFloat of(float[] arr) {
            return coreFloat(arr.length, i -> arr[i], Mode.MAX);
        }
        /**
         * Search the largest element in given array
         * @param collection collection of objects (not necessarily sorted).
         * @param keyExtractor function that describes how to extract value from an object;
         * @param keyComparator comparator that describes how to compare extracted values;
         * @return Element that contains index of the largest element and its value
         * @throws IllegalArgumentException if collection is empty
         */
        public static <T, K> Element<T> of(
                Collection<T> collection,
                Function<? super T, ? extends K> keyExtractor,
                Comparator<? super K> keyComparator)
        {
            return coreObject(collection, keyExtractor, keyComparator, Mode.MAX);
        }

        // Here comes overloaded public static methods that work with values and indexes only (So no Element class will be returned)
        /**
         * Static method that returns only index of the largest element
         * @param arr int array.
         * @return index of the largest element
         * @throws IllegalArgumentException if array is empty
         */
        public static int indexOf(int[] arr) {
            ElementInt elementInt = of(arr);
            return elementInt.index();
        }
        /**
         * Static method that returns only value of the largest element
         * @param arr int array.
         * @return value of the largest element
         * @throws IllegalArgumentException if array is empty
         */
        public static int valueOf(int[] arr) {
            ElementInt elementInt = of(arr);
            return elementInt.value();
        }
        /**
         * Static method that returns only index of the largest element
         * @param arr double array.
         * @return index of the largest element
         * @throws IllegalArgumentException if array is empty
         */
        public static int indexOf(double[] arr) {
            ElementDouble elementDouble = of(arr);
            return elementDouble.index();
        }
        /**
         * Static method that returns only value of the largest element
         * @param arr double array.
         * @return value of the largest element
         * @throws IllegalArgumentException if array is empty
         */
        public static double valueOf(double[] arr) {
            ElementDouble elementDouble = of(arr);
            return elementDouble.value();
        }
        /**
         * Static method that returns only index of the largest element
         * @param arr long array.
         * @return index of the largest element
         * @throws IllegalArgumentException if array is empty
         */
        public static int indexOf(long[] arr) {
            ElementLong elementLong = of(arr);
            return elementLong.index();
        }
        /**
         * Static method that returns only value of the largest element
         * @param arr long array.
         * @return value of the largest element
         * @throws IllegalArgumentException if array is empty
         */
        public static long valueOf(long[] arr) {
            ElementLong elementLong = of(arr);
            return elementLong.value();
        }
        /**
         * Static method that returns only index of the largest element
         * @param arr byte array.
         * @return index of the largest element
         * @throws IllegalArgumentException if array is empty
         */
        public static int indexOf(byte[] arr) {
            ElementByte elementByte = of(arr);
            return elementByte.index();
        }
        /**
         * Static method that returns only value of the largest element
         * @param arr byte array.
         * @return value of the largest element
         * @throws IllegalArgumentException if array is empty
         */
        public static byte valueOf(byte[] arr) {
            ElementByte elementByte = of(arr);
            return elementByte.value();
        }
        /**
         * Static method that returns only index of the largest element
         * @param arr float array.
         * @return index of the largest element
         * @throws IllegalArgumentException if array is empty
         */
        public static int indexOf(float[] arr) {
            ElementFloat elementFloat = of(arr);
            return elementFloat.index();
        }
        /**
         * Static method that returns only value of the largest element
         * @param arr float array.
         * @return value of the largest element
         * @throws IllegalArgumentException if array is empty
         */
        public static float valueOf(float[] arr) {
            ElementFloat elementFloat = of(arr);
            return elementFloat.value();
        }
        /**
         * Static method that returns only index of the largest element
         * @param collection collection that contains elements.
         * @param keyExtractor Function that defines how to extract key from collection
         * @param keyComparator Comparator that defines how to compare extracted keys.
         * @return index of the largest element
         * @throws IllegalArgumentException if array is empty
         */
        public static <T, K> int indexOf(
                Collection<T> collection,
                Function<? super T, ? extends K> keyExtractor,
                Comparator<? super K> keyComparator) {
            Element<T> element = of(collection, keyExtractor, keyComparator);
            return element.index();
        }
        /**
         * Static method that returns only value of the largest element
         * @param collection collection that contains elements.
         * @param keyExtractor Function that defines how to extract key from collection
         * @param keyComparator Comparator that defines how to compare extracted keys.
         * @return value of the largest element
         * @throws IllegalArgumentException if array is empty
         */
        public static <T, K> T valueOf(
                Collection<T> collection,
                Function<? super T, ? extends K> keyExtractor,
                Comparator<? super K> keyComparator) {
            Element<T> element = of(collection, keyExtractor, keyComparator);
            return element.value();
        }
    }

    public static final class Min {
        private Min() {}
        /**
         * Search the smallest element in given array
         * @param arr integer array. Not necessarily sorted;
         * @return ElementInt that contains index of the smallest value and the value itself
         * @throws IllegalArgumentException if the given array is empty
         */
        public static ElementInt of(int[] arr) {
            return coreInt(arr.length, i -> arr[i], Mode.MIN);
        }
        /**
         * Search the smallest element in given array
         * @param arr integer array. Not necessarily sorted;
         * @return ElementLong that contains index of the smallest value and the value itself
         * @throws IllegalArgumentException if the given array is empty
         */
        public static ElementLong of(long[] arr) {
            return coreLong(arr.length, i -> arr[i], Mode.MIN);
        }
        /**
         * Search the smallest element in given array
         * @param arr integer array. Not necessarily sorted;
         * @return ElementDouble that contains index of the smallest value and the value itself
         * @throws IllegalArgumentException if the given array is empty
         */
        public static ElementDouble of(double[] arr) {
            return coreDouble(arr.length, i -> arr[i], Mode.MIN);
        }
        /**
         * Search the smallest element in given array
         * @param arr integer array. Not necessarily sorted;
         * @return ElementByte that contains index of the smallest value and the value itself
         * @throws IllegalArgumentException if the given array is empty
         */
        public static ElementByte of(byte[] arr) {
            return coreByte(arr.length, i -> arr[i], Mode.MIN);
        }
        /**
         * Search the smallest element in given array
         * @param arr integer array. Not necessarily sorted;
         * @return ElementFloat that contains index of the smallest value and the value itself
         * @throws IllegalArgumentException if the given array is empty
         */
        public static ElementFloat of(float[] arr) {
            return coreFloat(arr.length, i -> arr[i], Mode.MIN);
        }
        /**
         * Search the smallest element in given array
         * @param collection collection of objects (not necessarily sorted).
         * @param keyExtractor function that describes how to extract value from an object;
         * @param keyComparator comparator that describes how to compare extracted values;
         * @return Element that contains index of the smallest element and its value
         * @throws IllegalArgumentException if collection is empty
         */
        public static <T, K> Element<T> of(
                Collection<T> collection,
                Function<? super T, ? extends K> keyExtractor,
                Comparator<? super K> keyComparator)
        {
            return coreObject(collection, keyExtractor, keyComparator, Mode.MIN);
        }

        // Here comes overloaded public static methods that work with values and indexes only (So no Element class will be returned)
        /**
         * Static method that returns only index of the smallest element
         * @param arr int array.
         * @return index of the smallest element
         * @throws IllegalArgumentException if array is empty
         */
        public static int indexOf(int[] arr) {
            ElementInt elementInt = of(arr);
            return elementInt.index();
        }
        /**
         * Static method that returns only value of the smallest element
         * @param arr int array.
         * @return value of the smallest element
         * @throws IllegalArgumentException if array is empty
         */
        public static int valueOf(int[] arr) {
            ElementInt elementInt = of(arr);
            return elementInt.value();
        }
        /**
         * Static method that returns only index of the smallest element
         * @param arr double array.
         * @return index of the smallest element
         * @throws IllegalArgumentException if array is empty
         */
        public static int indexOf(double[] arr) {
            ElementDouble elementDouble = of(arr);
            return elementDouble.index();
        }
        /**
         * Static method that returns only value of the smallest element
         * @param arr double array.
         * @return value of the smallest element
         * @throws IllegalArgumentException if array is empty
         */
        public static double valueOf(double[] arr) {
            ElementDouble elementDouble = of(arr);
            return elementDouble.value();
        }
        /**
         * Static method that returns only index of the smallest element
         * @param arr long array.
         * @return index of the smallest element
         * @throws IllegalArgumentException if array is empty
         */
        public static int indexOf(long[] arr) {
            ElementLong elementLong = of(arr);
            return elementLong.index();
        }
        /**
         * Static method that returns only value of the smallest element
         * @param arr long array.
         * @return value of the smallest element
         * @throws IllegalArgumentException if array is empty
         */
        public static long valueOf(long[] arr) {
            ElementLong elementLong = of(arr);
            return elementLong.value();
        }
        /**
         * Static method that returns only index of the smallest element
         * @param arr byte array.
         * @return index of the smallest element
         * @throws IllegalArgumentException if array is empty
         */
        public static int indexOf(byte[] arr) {
            ElementByte elementByte = of(arr);
            return elementByte.index();
        }
        /**
         * Static method that returns only value of the smallest element
         * @param arr byte array.
         * @return value of the smallest element
         * @throws IllegalArgumentException if array is empty
         */
        public static byte valueOf(byte[] arr) {
            ElementByte elementByte = of(arr);
            return elementByte.value();
        }
        /**
         * Static method that returns only index of the smallest element
         * @param arr float array.
         * @return index of the smallest element
         * @throws IllegalArgumentException if array is empty
         */
        public static int indexOf(float[] arr) {
            ElementFloat elementFloat = of(arr);
            return elementFloat.index();
        }
        /**
         * Static method that returns only value of the smallest element
         * @param arr float array.
         * @return value of the smallest element
         * @throws IllegalArgumentException if array is empty
         */
        public static float valueOf(float[] arr) {
            ElementFloat elementFloat = of(arr);
            return elementFloat.value();
        }
        /**
         * Static method that returns only index of the smallest element
         * @param collection collection that contains elements.
         * @param keyExtractor Function that defines how to extract key from collection
         * @param keyComparator Comparator that defines how to compare extracted keys.
         * @return index of the smallest element
         * @throws IllegalArgumentException if array is empty
         */
        public static <T, K> int indexOf(
                Collection<T> collection,
                Function<? super T, ? extends K> keyExtractor,
                Comparator<? super K> keyComparator) {
            Element<T> element = of(collection, keyExtractor, keyComparator);
            return element.index();
        }
        /**
         * Static method that returns only value of the smallest element
         * @param collection collection that contains elements.
         * @param keyExtractor Function that defines how to extract key from collection
         * @param keyComparator Comparator that defines how to compare extracted keys.
         * @return value of the smallest element
         * @throws IllegalArgumentException if array is empty
         */
        public static <T, K> T valueOf(
                Collection<T> collection,
                Function<? super T, ? extends K> keyExtractor,
                Comparator<? super K> keyComparator) {
            Element<T> element = of(collection, keyExtractor, keyComparator);
            return element.value();
        }
    }
}
