import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVParser {
    ArrayList<City> listCities = new ArrayList<>();

    public void printFile() {
        try (Scanner scanner = new Scanner(new File("files/worldcities.csv"))) {
            System.out.println("yes");
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
}
