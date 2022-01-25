package com.example.myk.clases;

public class claseclientes {
    private int img;
    private String id;
    private String dni;
    private String nombreyapellido;
    private String codigo;
    private String telefono;
    private String telefono2;
    private String direccion;
    private String direccion2;

    public claseclientes() {
    }

    public claseclientes(int img, String id, String dni, String nombreyapellido, String codigo, String telefono, String telefono2, String direccion, String direccion2) {
        this.img = img;
        this.id = id;
        this.dni = dni;
        this.nombreyapellido = nombreyapellido;
        this.codigo = codigo;
        this.telefono = telefono;
        this.telefono2 = telefono2;
        this.direccion = direccion;
        this.direccion2 = direccion2;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombreyapellido() {
        return nombreyapellido;
    }

    public void setNombreyapellido(String nombreyapellido) {
        this.nombreyapellido = nombreyapellido;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion2() {
        return direccion2;
    }

    public void setDireccion2(String direccion2) {
        this.direccion2 = direccion2;
    }
}
