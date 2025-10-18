package models;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Crash extends RegistroLog {

    private String errorCode;
    private String errorMessage;
    private String stackTrace;
    private String deviceModel;
    private String osVersion;



}
