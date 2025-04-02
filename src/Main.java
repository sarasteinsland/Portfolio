import InsertionSort.InsertionSort;
import Utils.CSVParser;
import Model.City;
import BubbleSort.OptimizedBubbleSort;
import BubbleSort.UnOptimizedBubbleSort;
import Utils.Test;
import MergeSort.MergeSort;

import java.util.Arrays;

void main() throws InterruptedException {
    CSVParser parser = new CSVParser();
    City[] worldCities = parser.getCities();
    City[] worldCities2 = Arrays.copyOf(worldCities, worldCities.length);
    City[] worldCities3 = Arrays.copyOf(worldCities, worldCities.length);
    long start;
    long end;
    Test test = new Test();

    UnOptimizedBubbleSort unOptimizedBubbleSort = new UnOptimizedBubbleSort();
    OptimizedBubbleSort optimizedBubbleSort = new OptimizedBubbleSort();
    InsertionSort insertionSort = new InsertionSort();

    test.start();
    /*
    test.restartTimer();
    start = System.currentTimeMillis();
    System.out.println("Optimized BubbleSort starting...");
    optimizedBubbleSort.sort(worldCities);
    end = System.currentTimeMillis();
    System.out.printf("OptimizedBubbleSort sorting time: %d ms\n", (end - start));

    Thread.sleep(2000);

    test.restartTimer();
    start = System.currentTimeMillis();
    System.out.println("Unoptimized BubbleSort starting...");
    unOptimizedBubbleSort.sort(worldCities2);
    end = System.currentTimeMillis();
    System.out.printf("UnoptimizedBubbleSort sorting time: %d ms\n", (end - start));

    Thread.sleep(2000);

    start = System.currentTimeMillis();
    System.out.println("Insertion sort starting...");
    insertionSort.sort(worldCities3);
    end = System.currentTimeMillis();
    System.out.printf("Insertion sort sorting time: %d ms\n", (end - start));
    test.stop = true;
*/

    MergeSort mergeSort = new MergeSort();
    mergeSort.sort(worldCities3);
}