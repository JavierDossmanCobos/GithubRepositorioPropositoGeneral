package com.usa.retotiendavirtual.ui.producto.model;

import java.util.List;

public class CarritoDeCompraModel {

    private String idUsuario;
    private String idSucursal;
    private String fecha;
    private long totalvalor;
    private int totalitems;
    private List<ProductoModel> productos;

    public CarritoDeCompraModel() {
    }

    public CarritoDeCompraModel(String idUsuario, String idSucursal, String fecha, long totalvalor, int totalitems, List<ProductoModel> productos) {
        this.idUsuario = idUsuario;
        this.idSucursal = idSucursal;
        this.fecha = fecha;
        this.totalvalor = totalvalor;
        this.totalitems = totalitems;
        this.productos = productos;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(String idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public long getTotalvalor() {
        return totalvalor;
    }

    public void setTotalvalor(long totalvalor) {
        this.totalvalor = totalvalor;
    }

    public int getTotalitems() {
        return totalitems;
    }

    public void setTotalitems(int totalitems) {
        this.totalitems = totalitems;
    }

    public List<ProductoModel> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoModel> productos) {
        this.productos = productos;
    }






}
