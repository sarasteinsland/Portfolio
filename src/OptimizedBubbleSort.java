public class OptimizedBubbleSort {
    public City[] sort(City[] cities) {
        boolean isSorted = false;
        int n = cities.length;

        while (!isSorted) {
            isSorted = true;
            for (int i = 1; i < n; i++) {
                if (cities[i - 1].latitude < cities[i].latitude) {
                    City temp = cities[i - 1];
                    cities[i - 1] = cities[i];
                    cities[i] = temp;
                    isSorted = false;
                }
            }
            n--;
        }

        for (City city : cities) {
            System.out.println(city);
        }

        return cities;
    }
}

