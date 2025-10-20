package data;

import models.Informe;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertiesWriter {
    Logger logger = Logger.getLogger(PropertiesWriter.class.getName());

    public void writePropertieFile(Map<String, Informe> mapReport, String filePath) {


        Properties prop = new Properties();

        for (Map.Entry<String, Informe> entry : mapReport.entrySet()) {
            String appName = entry.getKey();
            Informe informe = entry.getValue();

            prop.setProperty(appName + ".totalCrashes", String.valueOf(informe.getTotalCrashes()));
            prop.setProperty(appName + ".totalEventos", String.valueOf(informe.getTotalEventos()));
            prop.setProperty(appName + ".tasaError", String.format("%.2f", informe.getTasaError()));
        }

        try (FileWriter fw = new FileWriter(filePath)) {
            prop.store(fw, "Informe");
            logger.info("Propeties generado con exito");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
