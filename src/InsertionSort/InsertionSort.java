package InsertionSort;

import Model.City;

// Kode referert til:
// https://github.com/kristiania/PG4200/blob/main/src/java/data/algorithms/sort/InsertionSortAlgorithm.java
public class InsertionSort {

    public void sort(City[] cities) {
        int pivot = 1; // Setter pivot som andre element da første kan regnes som allerede sortert

        for (int i = pivot; i < cities.length; i++) {
            for (int x = pivot; x > 0; x--) {
                // Sjekk om neste element er mindre enn forrige
                if (cities[x].latitude < cities[x - 1].latitude) {
                    // Bytt om på rekkefølgen dersom dette er sant
                    City temp = cities[x];
                    cities[x] = cities[x - 1];
                    cities[x - 1] = temp;
                } else {
                    // Er elementet sortert går man ut av indre loop
                    break;
                }
            }
            // Set pivot til å peke til neste element i listen
            pivot++;
        }
    }
}
