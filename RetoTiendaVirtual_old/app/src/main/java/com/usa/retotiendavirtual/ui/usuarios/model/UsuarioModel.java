package com.usa.retotiendavirtual.ui.usuarios.model;

public class UsuarioModel {

    private String nombre;
    private String correo;
    private String palabraclave;
    private String fechacumple;
    private String telefono;
    private String rol;

    public UsuarioModel() {

    }

    public UsuarioModel(String nombre, String correo, String palabraclave, String fechacumple, String telefono, String rol) {
        this.nombre = nombre;
        this.correo = correo;
        this.palabraclave = palabraclave;
        this.fechacumple = fechacumple;
        this.telefono = telefono;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPalabraclave() {
        return palabraclave;
    }

    public void setPalabraclave(String palabraclave) {
        this.palabraclave = palabraclave;
    }

    public String getFechacumple() {
        return fechacumple;
    }

    public void setFechacumple(String fechacumple) {
        this.fechacumple = fechacumple;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
