package com.usa.retotiendavirtual.ui.producto.model;

public class ProductoModel {
    private String nombre;
    private String descripcion;
    private long precio;
    private String urlImg;

    private String id;

    private Integer imagen;   // to be deleted

    private Integer cantidad;

    private long valor;

    private int idDb;

    public ProductoModel(){

    }

    public ProductoModel(String nombre, String descripcion, long precio, String idFirebase, Integer cantidad, long valor, int idDb) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.id = id;
        this.cantidad = cantidad;
        this.valor = valor;
        this.idDb = idDb;
    }

    public ProductoModel(String nombre, String descripcion, long precio, String urlImg, String id, Integer imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.urlImg = urlImg;
        this.id = id;
        this.imagen = imagen;
    }

    public ProductoModel(String nombre, String descripcion, long precio, String urlImg) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.urlImg = urlImg;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String uriImg) {
        this.urlImg = uriImg;
    }

    public Integer getImagen() {
        return imagen;
    }

    public void setImagen(Integer imagen) {
        this.imagen = imagen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }

    public int getIdDb() {
        return idDb;
    }

    public void setIdDb(int idDb) {
        this.idDb = idDb;
    }
}



