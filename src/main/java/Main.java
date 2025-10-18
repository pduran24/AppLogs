import data.CsvDataService;
import data.DataService;

public class Main {
    static void main() {


        DataService ds = new CsvDataService("crashes.csv");


        ds.findAllCrashes().forEach(System.out::println);







    }
}
