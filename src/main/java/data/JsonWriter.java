package data;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import models.Informe; // Asegúrate de que la ruta a tu modelo Informe es correcta

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

public class JsonWriter {

    /**
     * Escribe el mapa de informes en un fichero JSON.
     *
     * @param reportData El mapa con los datos del informe (Clave: appName, Valor: Informe).
     * @param filePath   La ruta del fichero donde se guardará el JSON (ej: "analysis_report.json").
     */

    private static Logger logger = Logger.getLogger(CsvDataReader.class.getName());

    public void writeReport(Map<String, Informe> reportData, String filePath) {
        // 1. Crear el ObjectMapper de Jackson. Es el motor que convierte de Java a JSON.
        ObjectMapper mapper = new ObjectMapper();

        // 2. Configurar el "pretty print". Esto hace que el JSON sea legible.
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            // 3. Escribir el mapa 'reportData' en el fichero. Jackson hace todo el trabajo.
            mapper.writeValue(new File(filePath), reportData);
            logger.info("Informe generado en: " + filePath);

        } catch (IOException e) {
            logger.info("Error al generar el informe JSON: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}