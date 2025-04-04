package QuickSort;

import Model.City;

import static java.util.Collections.swap;

public class QuickSort {
    public void sort(City[]cities) { sort(cities, 0, cities.length - 1);}

    public void sort(City[] cities, int low, int high){
        if (low >= 0 && low < high){
            var pivot = partition(cities, low, high);

            sort(cities, low, pivot);
            sort(cities, pivot + 1, high);
        }
    }
    int partition(City[] cities, int low, int high){
        var pivot = cities[low].latitude;
        var left = low - 1;
        var right = high + 1;

        while (true){
            do{
                left = left + 1;
            } while (cities[left].latitude < pivot);
            do{
                right = right - 1;
            } while (cities[right].latitude > pivot);

            if(left >= right){
                return right;
            }
//            swap(cities, left, right);
            City temp = cities[left];
            cities[left] = cities[right];
            cities[right] = temp;

        }
    }
}
