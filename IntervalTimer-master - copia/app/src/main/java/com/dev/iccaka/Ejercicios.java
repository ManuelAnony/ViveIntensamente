package com.dev.iccaka;

public class Ejercicios {
    private String nomejer;
    private int rep, series, idsesion, idejer;

    public Ejercicios(String nomejer, int rep, int series,int idsesion, int idejer) {
        this.nomejer = nomejer;
        this.rep = rep;
        this.series = series;
        this.idsesion = idsesion;
        this.idejer = idejer;

        //(nomejer, series, idsesion, idejer);
    }

    public Ejercicios(String nomejer) {
        this.nomejer = nomejer;
    }

    public Ejercicios() {
    }

    public String getNomejer() {

        return nomejer;
    }

    public void setNomejer(String nomejer) {

        this.nomejer = nomejer;
    }

    public int getRep() {

        return rep;
    }

    public void setRep(int rep) {

        this.rep = rep;
    }
    public int getSeries() {

        return series;
    }

    public void setSeries(int series) {

        this.series = series;
    }
    public int getIdsesion() {

        return idsesion;
    }

    public void setIdsesion(int idsesion) {

        this.idsesion = idsesion;
    }
    public int getIdejer() {

        return idejer;
    }

    public void setIdejer(int idejer) {

        this.idejer = idejer;
    }

}