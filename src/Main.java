import Utils.CSVParser;
import Model.City;
import BubbleSort.OptimizedBubbleSort;
import BubbleSort.UnOptimizedBubbleSort;

void main() throws InterruptedException {
    CSVParser parser = new CSVParser();
    City[] worldCities = parser.getCities();
    long start;
    long end;

    UnOptimizedBubbleSort unOptimizedBubbleSort = new UnOptimizedBubbleSort();
    OptimizedBubbleSort optimizedBubbleSort = new OptimizedBubbleSort();

    start = System.currentTimeMillis();
    optimizedBubbleSort.sort(worldCities);
    end = System.currentTimeMillis();
    System.out.printf("BubbleSort.OptimizedBubbleSort: %d ms\n", (end - start));

    Thread.sleep(2000);

    start = System.currentTimeMillis();
    unOptimizedBubbleSort.sort(worldCities);
    end = System.currentTimeMillis();
    System.out.printf("BubbleSort.UnOptimizedBubbleSort: %d ms\n", (end - start));
}