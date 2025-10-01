package BubbleSort;

import Model.City;

public class OptimizedBubbleSort {
    public City[] sort(City[] cities) {
        boolean isSorted = false;
        // Antall elementer i listen som blir redusert med én for hver loop
        int n = cities.length;

        while (!isSorted) {
            // Bytt elementene hvis første er større enn andre
            isSorted = true;
            // 'n' blir mindre for hver swap
            for (int i = 1; i < n; i++) {
                if (cities[i - 1].latitude < cities[i].latitude) {
                    City temp = cities[i - 1];
                    cities[i - 1] = cities[i];
                    cities[i] = temp;
                    // Listen er fortsatt ikke sortert
                    isSorted = false;
                }
                // Blir ingen elementer i listen byttet om, er listen sortert
            }
            n--;
        }

        return cities;
    }
}

