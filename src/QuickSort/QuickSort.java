package QuickSort;

import Model.City;
import java.util.Random;

// Kode referert til:
// https://github.com/kristiania/PG4200/blob/main/src/java/data/algorithms/sort/QuickSortAlgorithm.java
public class QuickSort {
    public enum PivotStrategy {
        FIRST,
        LAST,
        RANDOM
    }

    private final PivotStrategy strategy;
    private int comparisons = 0;

    public QuickSort(PivotStrategy strategy) {
        this.strategy = strategy;
    }

    public int getComparisons() {
        return comparisons;
    }

    public void sort(City[] cities) {
        comparisons = 0;
        sort(cities, 0, cities.length - 1);
    }

    private void sort(City[] cities, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(cities, low, high);
            sort(cities, low, pivotIndex);
            sort(cities, pivotIndex + 1, high);
        }
    }

    private int partition(City[] cities, int low, int high) {
        choosePivot(cities, low, high);
        double pivot = cities[low].latitude;
        int left = low - 1;
        int right = high + 1;

        while (true) {
            do {
                left++;
                comparisons++;
            } while (cities[left].latitude < pivot);

            do {
                right--;
                comparisons++;
            } while (cities[right].latitude > pivot);

            if (left >= right) {
                return right;
            }

            City temp = cities[left];
            cities[left] = cities[right];
            cities[right] = temp;
        }
    }

    private void choosePivot(City[] cities, int low, int high) {
        switch (strategy) {
            case LAST -> swap(cities, low, high);
            case RANDOM -> {
                int randomIndex = new Random().nextInt(high - low + 1) + low;
                swap(cities, low, randomIndex);
            }

        }
    }

    private void swap(City[] cities, int i, int j) {
        City temp = cities[i];
        cities[i] = cities[j];
        cities[j] = temp;
    }
}
