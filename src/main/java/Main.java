import data.CsvDataReader;
import data.JsonWriter;
import data.PropertiesWriter;
import models.Crash;
import models.Event;
import models.Informe;
import service.DataService;
import service.InformeService;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Main {
    static void main() {


        DataService ds = new CsvDataReader();
        Logger logger =  Logger.getLogger(Main.class.getName());

        List<Crash> crashes =  ds.findAllCrashes("crashes.csv");

        List<Event> events = ds.findAllEvents("events.csv");

        logger.info("-> Datos de crashes y eventos leídos.");

        // PARTE 2: PROCESAR DATOS (Capa de Servicio)
        InformeService informeService = new InformeService();
        Map<String, Informe> reportData = informeService.generateInformeMap(crashes, events);
        logger.info("-> Datos procesados y métricas calculadas.");

        // PARTE 3: ESCRIBIR INFORME JSON (Capa de Datos)
        JsonWriter jsonWriter = new JsonWriter();
        jsonWriter.writeReport(reportData, "analysis_report.json");

        // Archivo properties
        var properties = new PropertiesWriter();
        properties.writePropertieFile(reportData, "analisys_report_properties.properties");

    }
}
