package service;

import models.Crash;
import models.Event;
import models.Informe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InformeService {

    public Map<String, Informe> generateInformeMap(List <Crash> crashes, List<Event> events) {

        Map<String, Informe> informeMap = new HashMap<String, Informe>();

        crashes.forEach(c -> {

            String appName = c.getAppName();

            if(!informeMap.containsKey(c.getAppName())) {
                informeMap.put(appName, new Informe());
            }

            Informe informeApp = informeMap.get(appName);
            informeApp.incrementTotalCrashes();

        });

        events.forEach(e -> {
            String appName = e.getAppName();

            if(!informeMap.containsKey(appName)) {
                informeMap.put(appName, new Informe());
            }
            Informe informe = informeMap.get(appName);
            informe.incrementTotalEventos();
        });


        informeMap.forEach((appName, informe) -> {
            informe.calcularTasaError();
        });


        return informeMap;
    }
}
