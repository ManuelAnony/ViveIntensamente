package com.dev.iccaka;

public class Sesion {
    private String enfoque, fecha;
    private int idsesion, numejer;

    public Sesion(String enfoque, String fecha,int numejer, int idsesion) {
        this.enfoque = enfoque;
        this.fecha = fecha;
        this.numejer = numejer;
        this.idsesion = idsesion;
    }

    public Sesion(String enfoque, String fecha) {
        this.enfoque = enfoque;
        this.fecha = fecha;
    }

    public Sesion() {
    }

    public String getEnfoque() {
        return enfoque;
    }

    public void setEnfoque(String enfoque) {

        this.enfoque = enfoque;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public int getNumejer() {
        return numejer;
    }

    public void setNumejer(int numejer) {
        this.numejer = numejer;
    }
    public int getIdsesion() {
        return idsesion;
    }

    public void setIdsesion(int idsesion) {
        this.idsesion = idsesion;
    }

}
