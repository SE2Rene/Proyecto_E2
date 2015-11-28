package com.example.talento.moneymanagerv4.model;

import java.io.InputStream;

/**
 * Created by Rene AMG on 25-Nov-15.
 */

public class Categorias {
    int _id;
    int imgCategoria;
    String nombre;
    float saldoLimite;

    public Categorias(int idCategoria) {
        this._id = idCategoria;
    }

    public Categorias(int idCategoria, String nombre) {
        this._id = idCategoria;
        this.nombre = nombre;
    }

    public Categorias(int idCategoria, String nombre, float saldoLimite) {
        this._id = idCategoria;
        this.nombre = nombre;
        this.saldoLimite = saldoLimite;
    }

    public Categorias(int idCategoria, String nombre, int foto, float saldoLimite) {
        this._id = idCategoria;
        this.nombre = nombre;
        this.imgCategoria = foto;
        this.saldoLimite = saldoLimite;
    }

    public int getIdCategoria() {return _id;}

    public void setIdCategoria(int idCategoria) {
        this._id = idCategoria;
    }

    public String getNombreCategoria() {
        return nombre;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombre = nombreCategoria;
    }

    public int getImgCategoria() {
        return imgCategoria;
    }

    public void setImgCategoria(int imgCategoria) {
        this.imgCategoria = imgCategoria;
    }

    public float getSaldoLimiteCategoria() {
        return saldoLimite;
    }

    public void setSaldoLimiteCategoria(float saldoLimite) {
        this.saldoLimite = saldoLimite;
    }
}
