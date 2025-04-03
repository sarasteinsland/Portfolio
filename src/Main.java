import InsertionSort.InsertionSort;
import Utils.CSVParser;
import Model.City;
import BubbleSort.OptimizedBubbleSort;
import BubbleSort.UnOptimizedBubbleSort;
import Utils.Timer;
import MergeSort.MergeSort;

import java.util.Arrays;

void main() throws InterruptedException {
    CSVParser parser = new CSVParser();
    Timer timer = new Timer();
    City[] worldCities = parser.getCities();
    City[] worldCities2 = Arrays.copyOf(worldCities, worldCities.length);
    City[] worldCities3 = Arrays.copyOf(worldCities, worldCities.length);
    City[] worldCities4 = Arrays.copyOf(worldCities, worldCities.length);

    UnOptimizedBubbleSort unOptimizedBubbleSort = new UnOptimizedBubbleSort();
    OptimizedBubbleSort optimizedBubbleSort = new OptimizedBubbleSort();
    InsertionSort insertionSort = new InsertionSort();
    MergeSort mergeSort = new MergeSort();

    timer.start();

    System.out.println("Optimized BubbleSort starting...");
    timer.interval();
    optimizedBubbleSort.sort(worldCities);
    timer.printInterval("Optimized BubbleSort:");
    Thread.sleep(2000);

    System.out.println("Unoptimized Bubble sort starting...");
    timer.interval();
    unOptimizedBubbleSort.sort(worldCities2);
    timer.printInterval("Unoptimized Bubble sort");
    Thread.sleep(2000);

    System.out.println("Insertion sort starting...");
    timer.interval();
    insertionSort.sort(worldCities3);
    timer.printInterval("Insertion sort:");
    Thread.sleep(2000);

    System.out.println("Merge sort starting...");
    timer.interval();
    mergeSort.sort(worldCities4);
    timer.printInterval("Merge sort:");

    timer.stopTimer();
}