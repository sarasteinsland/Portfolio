
public class Main {
    public static void main(String[] args) {
        CSVParser parser = new CSVParser();

        UnOptimizedBubbleSort unOptimizedBubbleSort = new UnOptimizedBubbleSort();
        OptimizedBubbleSort optimizedBubbleSort = new OptimizedBubbleSort();
        optimizedBubbleSort.sort(parser.getCities());
        // unOptimizedBubbleSort.sort(parser.getCities());


    }
}