import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVParser {
    ArrayList<City> listCities = new ArrayList<>();

    public CSVParser() {
        this.generateCities();
    }

    public void generateCities() {
        try (Scanner scanner = new Scanner(new File("files/worldcities.csv"))) {
            System.out.println("Prøv på nytt !! Du klarer det!!");
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
