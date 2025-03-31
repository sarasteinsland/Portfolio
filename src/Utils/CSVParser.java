package Utils;

import Model.City;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVParser {
    ArrayList<City> listCities = new ArrayList<>();

    public CSVParser() {
        this.generateCities();
    }

    public void generateCities() {
        try {
            File file = new File("files/worldcities.csv");
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                City city = new City(scanner.nextLine());
                listCities.add(city);
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("failed?");
            throw new RuntimeException(e);
        }
    }

    public City[] getCities() {
        return listCities.toArray(new City[0]);
    }
}
