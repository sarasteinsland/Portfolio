package BubbleSort;

import Model.City;

public class UnOptimizedBubbleSort {
    public City[] sort(City[] cities) {
        boolean isSorted = false;

        while (!isSorted) {
            isSorted = true;
            for (int i = 1; i < cities.length; i++) {
                if (cities[i - 1].latitude < cities[i].latitude) {
                    City temp = cities[i - 1];
                    cities[i - 1] = cities[i];
                    cities[i] = temp;
                    isSorted = false;
                }
            }
        }

        return cities;
    }
}
