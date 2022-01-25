package com.example.myk.clases;

public class claseproveedores {
    private int img;
    private String id;
    private String dnioruc;
    private String direccion;
    private String nombre;
    private String estado;

    public claseproveedores() {
    }

    public claseproveedores(int img, String id, String dnioruc, String direccion, String nombre, String estado) {
        this.img = img;
        this.id = id;
        this.dnioruc = dnioruc;
        this.direccion = direccion;
        this.nombre = nombre;
        this.estado = estado;
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

    public String getDnioruc() {
        return dnioruc;
    }

    public void setDnioruc(String dnioruc) {
        this.dnioruc = dnioruc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
