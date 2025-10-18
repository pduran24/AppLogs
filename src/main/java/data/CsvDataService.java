package data;

import models.Crash;
import models.Event;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CsvDataService implements DataService {

    private static Logger logger = Logger.getLogger(CsvDataService.class.getName());


    @Override
    public List<Crash> findAllCrashes(String archivo) {

        var salida = new ArrayList<Crash>();

        logger.info("Iniciando lista de crashes");

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            var contenido =  br.lines().skip(1);

            contenido.forEach(line -> {
                String[] lineArray = line.split(";");
                Crash crash = new Crash();

                crash.setTimeStamp(lineArray[0]);
                crash.setAppName(lineArray[1]);
                crash.setErrorCode(lineArray[2]);
                crash.setErrorMessage(lineArray[3]);
                crash.setStackTrace(lineArray[4]);
                crash.setDeviceModel(lineArray[5]);
                crash.setOsVersion(lineArray[6]);

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

            contenido.forEach(line -> {
                String[] lineArray = line.split(";");
                Event event = new Event();
                event.setTimeStamp(lineArray[0]);
                event.setAppName(lineArray[1]);
                event.setEventType(lineArray[2]);
                event.setUserId(lineArray[3]);
                event.setSessionId(lineArray[4]);
                event.setEventData(lineArray[5]);

                salida.add(event);
            });


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return salida;
    }
}
