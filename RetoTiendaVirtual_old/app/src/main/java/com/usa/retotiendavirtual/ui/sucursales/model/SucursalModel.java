package com.usa.retotiendavirtual.ui.sucursales.model;

public class SucursalModel {
    private long altitud;
    private long longitud;
    private String direccion;
    private String horaApertura;
    private String horaCierre;
    private String nonbre;
    private String telefono;
    private int imgBanner;

    public SucursalModel(int imgBanner) {
        this.imgBanner = imgBanner;
    }

    public SucursalModel(long altitud, long longitud, String direccion, String horaApertura, String horaCierre, String nonbre, String telefono, int imgBanner) {
        this.altitud = altitud;
        this.longitud = longitud;
        this.direccion = direccion;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
        this.nonbre = nonbre;
        this.telefono = telefono;
        this.imgBanner = imgBanner;
    }

    public long getAltitud() {
        return altitud;
    }

    public void setAltitud(long altitud) {
        this.altitud = altitud;
    }

    public long getLongitud() {
        return longitud;
    }

    public void setLongitud(long longitud) {
        this.longitud = longitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(String horaApertura) {
        this.horaApertura = horaApertura;
    }

    public String getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(String horaCierre) {
        this.horaCierre = horaCierre;
    }

    public String getNonbre() {
        return nonbre;
    }

    public void setNonbre(String nonbre) {
        this.nonbre = nonbre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getImgBanner() {
        return imgBanner;
    }

    public void setImgBanner(int imgBanner) {
        this.imgBanner = imgBanner;
    }

    @Override
    public String toString() {
        return "SucursalModel{" +
                "altitud=" + altitud +
                ", longitud=" + longitud +
                ", direccion='" + direccion + '\'' +
                ", horaApertura='" + horaApertura + '\'' +
                ", horaCierre='" + horaCierre + '\'' +
                ", nonbre='" + nonbre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", imgBanner=" + imgBanner +
                '}';
    }
}
