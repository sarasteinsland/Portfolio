import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class City {
    String city;
    String cityAscii;
    Double latitude;
    Double longitude;
    String country;
    String iso2;
    String iso3;
    String adminName;
    String capital;
    Double population;
    Integer id;
    public City(String data) {
        // Regex to split CSV-like format with quotes
        Pattern pattern = Pattern.compile("\"([^\"]*)\"");
        Matcher matcher = pattern.matcher(data);

        String[] fields = new String[11];
        int i = 0;

        while (matcher.find() && i < fields.length) {
            fields[i++] = matcher.group(1);
        }

        // Assign values
        this.city = fields[0];
        this.cityAscii = fields[1];
        this.latitude = Double.parseDouble(fields[2]);
        this.longitude = Double.parseDouble(fields[3]);
        this.country = fields[4];
        this.iso2 = fields[5];
        this.iso3 = fields[6];
        this.adminName = fields[7];
        this.capital = fields[8];
        this.population = parsePopulation(fields[9]);
        this.id = Integer.parseInt(fields[10]);
    }
    private Double parsePopulation(String value) {
        return ((Objects.equals(value, "")) ? 0.0 : Double.parseDouble(value));
    }
}
