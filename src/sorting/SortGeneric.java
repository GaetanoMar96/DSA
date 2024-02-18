package sorting;

import java.util.*;

public class SortGeneric<T extends Comparable<T>> {

    public void insertionSort(T[] array) {
        int n = array.length;
        for (int i = 1; i < n; ++i) {
            T key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j].compareTo(key) > 0) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    public T[] quickSort(T[] array) {
        if (array.length <= 1)
            return array;
        T pivot = array[0];
        List<T> left = new ArrayList<>();
        List<T> right = new ArrayList<>();
        for (T t : array) {
            if (t.compareTo(pivot) == 0) {
                continue;
            }
            if (t.compareTo(pivot) > 0) {
                right.add(t);
            } else {
                left.add(t);
            }
        }
        T[] leftArray = quickSort(left.toArray((T[]) new Comparable[0]));
        T[] rightArray = quickSort(right.toArray((T[]) new Comparable[0]));
        List<T> merged = new ArrayList<>(Arrays.asList(leftArray));
        merged.add(pivot);
        merged.addAll(List.of(rightArray));
        return merged.toArray((T[]) new Comparable[0]);
    }
}
