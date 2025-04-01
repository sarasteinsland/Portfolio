package InsertionSort;

import Model.City;

public class InsertionSort {

    public void sort(City[] cities) {
        int pivot = 1;

        for (int i = pivot; i < cities.length; i++) {
            for (int x = pivot; x > 0; x--) {
                if (cities[x].latitude < cities[x - 1].latitude) {
                    City temp = cities[x];
                    cities[x] = cities[x - 1];
                    cities[x - 1] = temp;
                } else {
                    break;
                }
            }
            pivot++;
        }
    }
}
