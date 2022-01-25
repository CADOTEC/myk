package com.example.myk.clases;

public class clasepesadas {
    private int img;
    private String id;
    private String polloogallina;
    private String peso;
    private String njabas;
    private String naves;

    public clasepesadas() {
    }

    public clasepesadas(int img, String id, String polloogallina, String peso, String njabas, String naves) {
        this.img = img;
        this.id = id;
        this.polloogallina = polloogallina;
        this.peso = peso;
        this.njabas = njabas;
        this.naves = naves;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPolloogallina() {
        return polloogallina;
    }

    public void setPolloogallina(String polloogallina) {
        this.polloogallina = polloogallina;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getNjabas() {
        return njabas;
    }

    public void setNjabas(String njabas) {
        this.njabas = njabas;
    }

    public String getNaves() {
        return naves;
    }

    public void setNaves(String naves) {
        this.naves = naves;
    }
}

