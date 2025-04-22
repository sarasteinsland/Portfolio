import InsertionSort.InsertionSort;
import QuickSort.QuickSort;
import Utils.CSVParser;
import Model.City;
import BubbleSort.OptimizedBubbleSort;
import BubbleSort.UnOptimizedBubbleSort;
import Utils.Timer;
import MergeSort.MergeSort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Initialiserer parser og timer
        CSVParser parser = new CSVParser();
        Timer timer = new Timer();

        // Henter byene fra CSV
        City[] worldCities = parser.getCities();


        City[] cities1 = Arrays.copyOf(worldCities, worldCities.length);
        City[] cities2 = Arrays.copyOf(worldCities, worldCities.length);
        City[] cities3 = Arrays.copyOf(worldCities, worldCities.length);
        City[] cities4 = Arrays.copyOf(worldCities, worldCities.length);
        City[] cities5 = Arrays.copyOf(worldCities, worldCities.length);
        City[] cities6 = Arrays.copyOf(worldCities, worldCities.length);

        UnOptimizedBubbleSort unOptimizedBubbleSort = new UnOptimizedBubbleSort();
        OptimizedBubbleSort optimizedBubbleSort = new OptimizedBubbleSort();
        InsertionSort insertionSort = new InsertionSort();
        MergeSort mergeSort = new MergeSort();

        QuickSort quickSortFirst = new QuickSort(QuickSort.PivotStrategy.FIRST);
        QuickSort quickSortLast = new QuickSort(QuickSort.PivotStrategy.LAST);
        QuickSort quickSortRandom = new QuickSort(QuickSort.PivotStrategy.RANDOM);

        // --- QuickSort Random ---
        System.out.println("QuickSort (Random pivot) starting...");
        timer.start();
        quickSortRandom.sort(cities1);
        timer.stopAndPrint("QuickSort (Random):");
        System.out.println("Sammenligninger: " + quickSortRandom.getComparisons());
        Thread.sleep(2000);

        // --- QuickSort First ---
        System.out.println("QuickSort (First pivot) starting...");
        timer.start();
        quickSortFirst.sort(cities6);
        timer.stopAndPrint("QuickSort (First):");
        System.out.println("Sammenligninger: " + quickSortFirst.getComparisons());
        Thread.sleep(2000);

        // --- QuickSort Last ---
        System.out.println("QuickSort (Last pivot) starting...");
        timer.start();
        quickSortLast.sort(cities5);
        timer.stopAndPrint("QuickSort (Last):");
        System.out.println("Sammenligninger: " + quickSortLast.getComparisons());
        Thread.sleep(2000);

        // --- Optimized BubbleSort ---
        System.out.println("Optimized BubbleSort starting...");
        timer.start();
        optimizedBubbleSort.sort(cities2);
        timer.stopAndPrint("Optimized BubbleSort:");
        Thread.sleep(2000);

        // --- Unoptimized BubbleSort ---
        System.out.println("Unoptimized BubbleSort starting...");
        timer.start();
        unOptimizedBubbleSort.sort(cities3);
        timer.stopAndPrint("Unoptimized BubbleSort:");
        Thread.sleep(2000);

        // --- Insertion Sort ---
        System.out.println("Insertion Sort starting...");
        timer.start();
        insertionSort.sort(cities4);
        timer.stopAndPrint("Insertion Sort:");
        Thread.sleep(2000);

        // --- MergeSort ---
        System.out.println("MergeSort starting...");
        timer.start();
        mergeSort.sort(cities5);
        timer.stopAndPrint("MergeSort:");
        System.out.println("Merges: " + mergeSort.merges);
    }
}
