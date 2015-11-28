package com.example.talento.moneymanagerv4.model;

/**
 * Created by Chino's Work on 25/11/2015.
 */
public class Saldos {
    int idSaldo;
    double cantidadSaldo;
    int diasAlCorte;


    public Saldos(int idSaldo) {
        this.idSaldo = idSaldo;
    }

    public Saldos(int idSaldo, double cantidadSaldo) {
        this.idSaldo = idSaldo;
        this.cantidadSaldo = cantidadSaldo;
    }

    public Saldos(double cantidadSaldo, int diasAlCorte) {
        this.diasAlCorte = diasAlCorte;
        this.cantidadSaldo = cantidadSaldo;
    }

    public Saldos(int idSaldo, double cantidadSaldo,int diasAlCorte) {
        this.idSaldo = idSaldo;
        this.cantidadSaldo = cantidadSaldo;
        this.diasAlCorte = diasAlCorte;
    }


    public int getIdSaldo() {return idSaldo;}

    public void setIdSaldo(int idSaldo) {
        this.idSaldo = idSaldo;
    }

    public double getCantidadSaldo() {return cantidadSaldo;}

    public void setCantidadSaldo(double cantidadSaldo) {
        this.cantidadSaldo = cantidadSaldo;
    }

    public int getDiasAlCorte() {return diasAlCorte;}

    public void setDiasAlCorte(int diasAlCorte) {
        this.diasAlCorte = diasAlCorte;
    }

}
