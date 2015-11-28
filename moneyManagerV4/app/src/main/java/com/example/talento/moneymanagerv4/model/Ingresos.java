package com.example.talento.moneymanagerv4.model;

import java.util.Date;

/**
 * Created by Rene AMG on 26-Nov-15.
 */
public class Ingresos {

    int idIngreso;
    private double cantidadIngreso;
    private Date fechaIngreso;

    public Ingresos(int idIngreso) {
        this.idIngreso = idIngreso;
    }

    public Ingresos(int idIngreso, double cantidadIngreso) {
        this.idIngreso = idIngreso;
        this.cantidadIngreso = cantidadIngreso;
    }

    public Ingresos(double cantidadIngreso) {
        this.cantidadIngreso = cantidadIngreso;
    }

    public Ingresos(double cantidadIngreso, Date fechaIngreso) {
        this.cantidadIngreso = cantidadIngreso;
        this.fechaIngreso = fechaIngreso;
    }

    public Ingresos(int idIngreso, double cantidadIngreso, Date fechaIngreso) {
        this.idIngreso = idIngreso;
        this.cantidadIngreso = cantidadIngreso;
        this.fechaIngreso = fechaIngreso;
    }

    public int getIdIngreso() {return idIngreso;}

    public void setIdIngreso(int idIngreso) {
        this.idIngreso = idIngreso;
    }

    public double getCantidadIngreso() {
        return cantidadIngreso;
    }

    public void setCantidadIngreso(double cantidadIngreso) {
        this.cantidadIngreso = cantidadIngreso;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}
