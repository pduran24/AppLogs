package data;

import models.Crash;
import models.Event;

import java.util.List;

public interface DataService {


    public List<Crash> findAllCrashes();
    public List<Event> findAllEvents();


}
