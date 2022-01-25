package com.example.myk.clases;

public class claseproveedorescompleto {
    String id;
    String dnioruc;
    String direccion;
    String nombre;
    String estado;
    String telefono1;
    String telefono2;
    String correo;

    public claseproveedorescompleto() {
    }

    public claseproveedorescompleto(String id, String dnioruc, String direccion, String nombre, String estado, String telefono1, String telefono2, String correo) {
        this.id = id;
        this.dnioruc = dnioruc;
        this.direccion = direccion;
        this.nombre = nombre;
        this.estado = estado;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.correo = correo;
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

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
