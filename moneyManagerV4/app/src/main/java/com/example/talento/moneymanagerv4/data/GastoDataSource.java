package com.example.talento.moneymanagerv4.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.talento.moneymanagerv4.model.Categorias;
import com.example.talento.moneymanagerv4.model.Gastos;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Rene AMG on 25-Nov-15.
 */
public class GastoDataSource {
    private final static String TABLE_NAMEGASTO = "Gastos";
    private final static String TABLE_NAMECATEGORIA= "Categoria";
    private MySQLite dbHelper;
    private SQLiteDatabase db;

    public GastoDataSource(Context c) {
        dbHelper = new MySQLite(c);
    }

    public void addGasto(Gastos gasto) {
        open();
        ContentValues values = new ContentValues();
        values.put("cantidadGastada", gasto.getCantidadGastada() );
        values.put("fechaGasto", String.valueOf(gasto.getFechaGasto()));
        values.put("fechaCorte", String.valueOf(gasto.getFechaCorte()));
        values.put("idCategoria", gasto.getCategoriaGasto().getIdCategoria());
        //FALTA MANDAR EL IDSALDO.
        db.insert(TABLE_NAMEGASTO, null, values);
        close();
    }

    public void updateGasto(Gastos gasto) {
        open();
        ContentValues values = new ContentValues();
        values.put("cantidadGastada", gasto.getCantidadGastada() );
        values.put("fechaGasto", String.valueOf(gasto.getFechaGasto()));
        values.put("fechaCorte", String.valueOf(gasto.getFechaCorte()));
        values.put("idCategoria", gasto.getCategoriaGasto().getIdCategoria());
        //FALTA MANDAR EL IDSALDO.

        String where = "idGasto = " + gasto.getIdGasto();
        db.update(TABLE_NAMEGASTO, values, where, null);
        close();
    }


    public void deleteGasto(int idGasto) {
        open();
        String where = "idGasto = " + idGasto;
        db.delete(TABLE_NAMEGASTO, where, null);
        close();
    }

    public void deleteAllGasto() {
        open();
        db.delete(TABLE_NAMEGASTO, null, null);
        close();
    }

    public List<Gastos> getAllGastos() throws ParseException {
        open();
        String query = "SELECT * FROM " + TABLE_NAMEGASTO;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        List<Gastos> gastos = new ArrayList<Gastos>();
        while(!cursor.isAfterLast()) {

            int idGasto = cursor.getInt(cursor.getColumnIndex("idGasto"));
            float cantidadGastada = cursor.getFloat(cursor.getColumnIndex("cantidadGastada"));

            //La fecha puede tener problemas al recibirla como string, siendo date en la Base de Datos.
            String fechaGastoString = cursor.getString(cursor.getColumnIndex("fechaGasto"));
            String fechaCorteString = cursor.getString(cursor.getColumnIndex("fechaCorte"));

            DateFormat df = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
            Date fechaGasto =  df.parse(fechaGastoString);
            Date fechaCorte =  df.parse(fechaCorteString);


            int idCategoria = cursor.getInt(cursor.getColumnIndex("idCategoria"));

            Gastos gasto = new Gastos(idGasto, cantidadGastada, fechaGasto, fechaCorte);

            Categorias categoria = new Categorias(idCategoria);
            gasto.setCategoriaGasto(categoria);

            gastos.add(gasto);
            cursor.moveToNext();
        }

        close();
        return gastos;
    }

    public List<Gastos> getAllGastoByCategoria(int idCategoria) {
        open();
        String query = "SELECT * FROM " + TABLE_NAMEGASTO + " WHERE idCategoria = " + idCategoria;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        List<Gastos> gastos = new ArrayList<Gastos>();
        while(!cursor.isAfterLast()) {

            int idGasto = cursor.getInt(cursor.getColumnIndex("idGasto"));
            float cantidadGastada = cursor.getFloat(cursor.getColumnIndex("cantidadGastada"));

            //La fecha puede tener problemas al recibirla como string, siendo date en la Base de Datos.
            String fechaGastoString = cursor.getString(cursor.getColumnIndex("fechaGasto"));
            String fechaCorteString = cursor.getString(cursor.getColumnIndex("fechaCorte"));

            DateFormat df = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);

            Date fechaGasto = null;
            try {
                fechaGasto = df.parse(fechaGastoString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Date fechaCorte = null;
            try {
                fechaCorte = df.parse(fechaCorteString);
            } catch (ParseException e) {
                e.printStackTrace();
            }


            int idCategoriaa = cursor.getInt(cursor.getColumnIndex("idCategoria"));

            Gastos gasto = new Gastos(idGasto, cantidadGastada, fechaGasto, fechaCorte);

            Categorias categoria = new Categorias(idCategoriaa);
            gasto.setCategoriaGasto(categoria);

            gastos.add(gasto);
            cursor.moveToNext();
        }

        close();
        return gastos;
    }

    public double getGastosGeneral() {
        open();
        String query = "SELECT SUM(cantidadGastada) AS gastoTotal FROM " + TABLE_NAMEGASTO;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        float gastoTotal = cursor.getFloat(cursor.getColumnIndex("gastoTotal"));
        close();
        return gastoTotal;
    }

    public double getGastosComida() {
        open();
        String query = "SELECT SUM(cantidadGastada) AS gastoTotal FROM " + TABLE_NAMEGASTO + " WHERE idCategoria = 1";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        float gastoTotal = cursor.getFloat(cursor.getColumnIndex("gastoTotal"));
        close();
        return gastoTotal;
    }

    public double getGastosEntretenimiento() {
        open();
        String query = "SELECT SUM(cantidadGastada) AS gastoTotal FROM " + TABLE_NAMEGASTO + " WHERE idCategoria = 2";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        float gastoTotal = cursor.getFloat(cursor.getColumnIndex("gastoTotal"));
        close();
        return gastoTotal;
    }

    public double getGastosTransporte() {
        open();
        String query = "SELECT SUM(cantidadGastada) AS gastoTotal FROM " + TABLE_NAMEGASTO + " WHERE idCategoria = 3";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        float gastoTotal = cursor.getFloat(cursor.getColumnIndex("gastoTotal"));
        close();
        return gastoTotal;
    }


    public double getGastosSalud() {
        open();
        String query = "SELECT SUM(cantidadGastada) AS gastoTotal FROM " + TABLE_NAMEGASTO + " WHERE idCategoria = 4";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        float gastoTotal = cursor.getFloat(cursor.getColumnIndex("gastoTotal"));
        close();
        return gastoTotal;
    }

    public double getGastosEducacion() {
        open();
        String query = "SELECT SUM(cantidadGastada) AS gastoTotal FROM " + TABLE_NAMEGASTO + " WHERE idCategoria = 5";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        float gastoTotal = cursor.getFloat(cursor.getColumnIndex("gastoTotal"));
        close();
        return gastoTotal;
    }

    public double getGastosRegalo() {
        open();
        String query = "SELECT SUM(cantidadGastada) AS gastoTotal FROM " + TABLE_NAMEGASTO + " WHERE idCategoria = 6";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        float gastoTotal = cursor.getFloat(cursor.getColumnIndex("gastoTotal"));
        close();
        return gastoTotal;
    }

    public double getGastosRopa() {
        open();
        String query = "SELECT SUM(cantidadGastada) AS gastoTotal FROM " + TABLE_NAMEGASTO + " WHERE idCategoria = 7";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        float gastoTotal = cursor.getFloat(cursor.getColumnIndex("gastoTotal"));
        close();
        return gastoTotal;
    }

    public double getGastosOtros() {
        open();
        String query = "SELECT SUM(cantidadGastada) AS gastoTotal FROM " + TABLE_NAMEGASTO + " WHERE idCategoria = 8";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        float gastoTotal = cursor.getFloat(cursor.getColumnIndex("gastoTotal"));
        close();
        return gastoTotal;
    }

    public double getGastoByIdCategoria(int idCategoria) {
        open();
        String query = "SELECT SUM(cantidadGastada) AS gastoTotal FROM " + TABLE_NAMEGASTO + " WHERE idCategoria = " + idCategoria;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        float gastoTotal = cursor.getFloat(cursor.getColumnIndex("gastoTotal"));
        close();
        return gastoTotal;
    }

    private void open() {
        db = dbHelper.getWritableDatabase();
    }
    private void close() {
        if (db.isOpen())
            db.close();;
    }
}
