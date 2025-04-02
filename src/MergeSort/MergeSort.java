package MergeSort;

import Model.City;

import java.util.Arrays;



public class MergeSort {


    public void sort(City[]cities){
        if (cities.length > 1){
            int mid = cities.length / 2;


            City[] left = Arrays.copyOfRange(cities, 0, mid);
            City[] right = Arrays.copyOfRange(cities, mid, cities.length);

            sort(left);
            sort(right);

            merge(cities, left, right);
        }
    }

    private void merge(City[] cities, City[] left, City[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i].latitude <= right[j].latitude) {
                cities[k++] = left[i++];
            } else {
                cities[k++] = right[j++];
            }
        }

        while (i < left.length) {
            cities[k++] = left[i++];
        }

        while (j < right.length) {
            cities[k++] = right[j++];
        }
    }
}