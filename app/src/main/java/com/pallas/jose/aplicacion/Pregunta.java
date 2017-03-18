package com.pallas.jose.aplicacion;

/**
 * Created by Jose on 25/02/2017.
 */
public class Pregunta {
    private String pregunta;
    private boolean solucion;

    public boolean isSolucion() {
        return solucion;
    }

    public String getPregunta() {

        return pregunta;
    }

    public Pregunta(String pregunta, boolean solucion) {
        this.pregunta = pregunta;
        this.solucion = solucion;
    }
}
