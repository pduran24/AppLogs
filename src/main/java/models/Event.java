package models;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Event extends RegistroLog {


    private String eventType;
    private String userId;
    private String sessionId;
    private String eventData;


}
