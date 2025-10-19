package data;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import models.Informe; // Asegúrate de que la ruta a tu modelo Informe es correcta

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

public class JsonWriter {
    

    private static Logger logger = Logger.getLogger(CsvDataReader.class.getName());

    public void writeReport(Map<String, Informe> reportData, String filePath) {

        ObjectMapper mapper = new ObjectMapper();


        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {

            mapper.writeValue(new File(filePath), reportData);
            logger.info("Informe generado en: " + filePath);

        } catch (IOException e) {
            logger.info("Error al generar el informe JSON: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}