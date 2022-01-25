package com.example.myk.clases;

public class clasecompra {
    private int img;
    private String id;
    private String nombre;
    private String fecha;

    public clasecompra() {
    }

    public clasecompra(int img, String id, String nombre, String fecha) {
        this.img = img;
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
