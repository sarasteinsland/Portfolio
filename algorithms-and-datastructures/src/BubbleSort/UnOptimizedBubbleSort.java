package BubbleSort;

import Model.City;

public class UnOptimizedBubbleSort {
    public City[] sort(City[] cities) {
        // Anta at listen ikke er sortert
        boolean isSorted = false;

        // Kjør loopen mens listen ikke er sortert
        while (!isSorted) {
            isSorted = true;
            for (int i = 1; i < cities.length; i++) {
                // Bytt elementene hvis første er større enn andre
                if (cities[i - 1].latitude < cities[i].latitude) {
                    City temp = cities[i - 1];
                    cities[i - 1] = cities[i];
                    cities[i] = temp;
                    // Listen er fortsatt ikke sortert
                    isSorted = false;
                }
                // Blir ingen elementer i listen byttet om er listen sortert
            }
        }

        return cities;
    }
}
