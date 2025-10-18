import data.CsvDataService;
import data.DataService;

public class Main {
    static void main() {


        DataService ds = new CsvDataService();


        ds.findAllCrashes("crashes.csv").forEach(System.out::println);

        ds.findAllEvents("events.csv").forEach(System.out::println);


    }
}
