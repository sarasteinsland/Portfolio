package MergeSort;

import Model.City;

import java.util.Arrays;


// Kode referert til:
// https://github.com/kristiania/PG4200/blob/main/src/java/data/algorithms/sort/MergeSortAlgorithm.java
public class MergeSort {
    public int merges = 0;

    public void sort(City[] cities) {
        // Hvis arrayet har 1 eller mindre elementer er det sortert
        if (cities.length > 1) {
            // Midtpunktet i arrayet
            int mid = cities.length / 2;

            // Deler arrayet i to halvdeler; høyre og venstre del
            City[] left = Arrays.copyOfRange(cities, 0, mid);
            City[] right = Arrays.copyOfRange(cities, mid, cities.length);

            // Sorter hver halvdel rekursivt
            sort(left);
            sort(right);

            // Høyre og venstre halvdel av arrayet slås sammen
            merge(cities, left, right);
        }
    }

    // Tar inn original-arrayet samt høyre og venstre halvdel og slår dem sammen
    private void merge(City[] cities, City[] left, City[] right) {
        int i = 0, j = 0, k = 0;

        // Sammenligner elementer i høyre og venstre, setter peker i original-arrayet
        // til det minste i høyre eller venstre
        while (i < left.length && j < right.length) {
            if (left[i].latitude <= right[j].latitude) {
                cities[k++] = left[i++];
            } else {
                cities[k++] = right[j++];
            }
        }

        // Legger til resterende elementer fra venstre-array hvis det eksisterer
        while (i < left.length) {
            cities[k++] = left[i++];
        }

        // Legger til resterende elementer fra høyre-array hvis det eksisterer
        while (j < right.length) {
            cities[k++] = right[j++];
        }
        // Increment antall merge-operasjoner
        merges++;
    }
}