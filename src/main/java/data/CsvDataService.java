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

    private String archivo;


    private static Logger logger = Logger.getLogger(CsvDataService.class.getName());

    public CsvDataService(String archivo) {
        this.archivo = archivo;
    }




    @Override
    public List<Crash> findAllCrashes() {

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
    public List<Event> findAllEvents() {
        return List.of();
    }
}
