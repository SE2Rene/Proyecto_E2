package com.example.talento.moneymanagerv4.model;

/**
 * Created by Rene AMG on 25-Nov-15.
 */

import java.util.Date;

public class Gastos {

    int idGasto;
    float cantidadGastada;
    Date fechaGasto;
    Date fechaCorte;
    Categorias categoriaGasto;
    private Saldos saldoTotal;

    public Gastos(int idGasto, float cantidadGastada, Date fechaGasto, Date fechaCorte) {
        this.idGasto = idGasto;
        this.cantidadGastada = cantidadGastada;
        this.fechaGasto = fechaGasto;
        this.fechaCorte = fechaCorte;
    }

    public Gastos(float cantidadGastada, Date fechaGasto, Date fechaCorte,Categorias categoriaGasto, Saldos saldoTotal) {
        this.cantidadGastada = cantidadGastada;
        this.fechaGasto = fechaGasto;
        this.fechaCorte = fechaCorte;
        this.categoriaGasto = categoriaGasto;
        this.saldoTotal = saldoTotal;
    }


    public Gastos(int idGasto,float cantidadGastada, Date fechaGasto, Date fechaCorte, Categorias categoriaGasto) {
        this.idGasto = idGasto;
        this.cantidadGastada = cantidadGastada;
        this.fechaGasto = fechaGasto;
        this.fechaCorte = fechaCorte;
        this.categoriaGasto = categoriaGasto;
    }

    public int getIdGasto() {
        return idGasto;
    }

    public void setIdGasto(int idGasto) {
        this.idGasto = idGasto;
    }

    public float getCantidadGastada() {
        return cantidadGastada;
    }

    public void setCantidadGastada(float cantidadGastada) {
        this.cantidadGastada = cantidadGastada;
    }

    public Date getFechaGasto() {
        return fechaGasto;
    }

    public void setFechaGasto(Date fechaGasto) {
        this.fechaGasto = fechaGasto;
    }

    public Date getFechaCorte() {
        return fechaGasto;
    }

    public void setFechaCorte(Date fechaCorte) {
        this.fechaCorte = fechaCorte;
    }

    public Categorias getCategoriaGasto() {
        return categoriaGasto;
    }

    public void setCategoriaGasto(Categorias categoriaGasto) {
        this.categoriaGasto = categoriaGasto;
    }


    public Saldos getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(Saldos saldoTotal) {
        this.saldoTotal = saldoTotal;
    }
}
