import Utils.CSVParser;
import Model.City;
import BubbleSort.OptimizedBubbleSort;
import BubbleSort.UnOptimizedBubbleSort;

void main() throws InterruptedException {
    CSVParser parser = new CSVParser();
    City[] worldCities = parser.getCities();
    City[] worldCities2 = Arrays.copyOf(worldCities, worldCities.length);
    long start;
    long end;

    UnOptimizedBubbleSort unOptimizedBubbleSort = new UnOptimizedBubbleSort();
    OptimizedBubbleSort optimizedBubbleSort = new OptimizedBubbleSort();

    start = System.currentTimeMillis();
    System.out.println("Optimized BubbleSort starting...");
    optimizedBubbleSort.sort(worldCities);
    end = System.currentTimeMillis();
    System.out.printf("OptimizedBubbleSort sorting time: %d ms\n", (end - start));

    Thread.sleep(2000);

    start = System.currentTimeMillis();
    System.out.println("Unoptimized BubbleSort starting...");
    unOptimizedBubbleSort.sort(worldCities2);
    end = System.currentTimeMillis();
    System.out.printf("UnoptimizedBubbleSort sorting time: %d ms\n", (end - start));
}