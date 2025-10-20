package models;


import lombok.Data;

@Data
public class Informe {


    private int totalCrashes;
    private int totalEventos;
    private double tasaError;


    public void incrementTotalCrashes() {
        totalCrashes++;
    }

    public void incrementTotalEventos() {
        totalEventos++;
    }

    public void calcularTasaError() {
        if (totalCrashes + totalEventos > 0) {
            tasaError = ((double) totalCrashes / ( totalEventos)) * 100.0;
        } else {
            tasaError = 0.0;
        }
    }
}
