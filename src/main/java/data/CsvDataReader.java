package data;

import models.Crash;
import models.Event;
import service.DataService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class CsvDataReader implements DataService {

    private static Logger logger = Logger.getLogger(CsvDataReader.class.getName());

    @Override
    public List<Crash> findAllCrashes(String archivo) {

        var salida = new ArrayList<Crash>();

        logger.info("Iniciando lista de crashes");

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            var contenido =  br.lines().skip(1);
            logger.info("Iniciando parseo de lines");
            contenido.forEach(line -> {
                List<String> lineArray = parseCsvLines(line);
                Crash crash = new Crash();

                crash.setTimeStamp(lineArray.get(0));
                crash.setAppName(lineArray.get(1));
                crash.setErrorCode(lineArray.get(2));
                crash.setErrorMessage(lineArray.get(3));
                crash.setStackTrace(lineArray.get(4));
                crash.setDeviceModel(lineArray.get(5));
                crash.setOsVersion(lineArray.get(6));

                salida.add(crash);
            });


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return salida;


    }

    @Override
    public List<Event> findAllEvents(String archivo) {

        var salida = new ArrayList<Event>();

        logger.info("Iniciando lista de events");

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            var contenido =  br.lines().skip(1);

            logger.info("Iniciando parseo de lines");
            contenido.forEach(line -> {
                List<String> lineArray = parseCsvLines(line); // devuelve los campos bien separados


                Event event = new Event();
                event.setTimeStamp(lineArray.get(0));
                event.setAppName(lineArray.get(1));
                event.setEventType(lineArray.get(2));
                event.setUserId(lineArray.get(3));
                event.setSessionId(lineArray.get(4));
                event.setEventData(lineArray.get(5));

                salida.add(event);
            });


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return salida;
    }


    private List<String> parseCsvLines(String linea) {

        List<String> campos = new ArrayList<>();
        StringBuilder camposTexto = new StringBuilder();
        boolean comillas = false;

        for (int i = 0; i < linea.length(); i++) {
            char c = linea.charAt(i);

            if (c == '"') {
                comillas = !comillas;
            } else if (c == ';' && !comillas) {
                campos.add(camposTexto.toString());
                camposTexto.setLength(0);
            } else {
                camposTexto.append(c);
            }
        }

        campos.add(camposTexto.toString());
        return campos;
    }




}
