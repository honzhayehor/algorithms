package sort;

public class SelectionSort {
    public static int[] sort(int[] arr) {
        int[] sortedArr = new int[arr.length];
        int[] currentArr = copyArr(arr);
        for (int i = 0; i < arr.length; i++) {
            int smallestIdx = findSmallestIdx(currentArr);
            int smallest = currentArr[smallestIdx];
            sortedArr[i] = smallest;
            currentArr = removeElement(currentArr, smallestIdx);
        }
        return sortedArr;
    }

    private static int findSmallestIdx(int[] arr) {
        int smallest_idx = 0;
        int smallest = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < smallest) {
                smallest = arr[i];
                smallest_idx = i;
            }
        }
        return smallest_idx;
    }

    private static int[] removeElement(int[] arr, int at) {
        int[] newArr = new int[arr.length-1];
        int counter = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i != at) {
                newArr[counter++] = arr[i];
            }
        }
        return newArr;
    }

    private static int[] copyArr(int[] arr) {
        int[] newArr = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }
}
