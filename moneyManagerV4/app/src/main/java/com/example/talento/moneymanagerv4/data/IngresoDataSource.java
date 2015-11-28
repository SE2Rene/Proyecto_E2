package com.example.talento.moneymanagerv4.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.talento.moneymanagerv4.model.Ingresos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Rene AMG on 26-Nov-15.
 */
public class IngresoDataSource {

    private final static String TABLE_NAMEINGRESOS = "Ingresos";
    private MySQLite dbHelper;
    private SQLiteDatabase db;

    public IngresoDataSource(Context c) {
        dbHelper = new MySQLite(c);
    }

    public void addIngreso(Ingresos ingreso) {
        open();
        ContentValues values = new ContentValues();
        values.put("cantidadIngreso", ingreso.getCantidadIngreso());
        values.put("fechaIngreso", String.valueOf(ingreso.getFechaIngreso()));
        db.insert(TABLE_NAMEINGRESOS, null, values);
        close();
    }

    public void deleteAllIngresos() {
        open();
        db.delete(TABLE_NAMEINGRESOS, null, null);
        close();
    }

    public double getIngresoTotal() {
        open();
        String query = "SELECT SUM(cantidadIngreso) AS ingresosTotales FROM " + TABLE_NAMEINGRESOS;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        double ingresosTotal = cursor.getDouble(cursor.getColumnIndex("ingresosTotales"));
        close();
        return ingresosTotal;
    }

    public double getIngresoTotalDisponible() {
        open();
        String query = "SELECT SUM(cantidadIngreso) AS ingresosTotales FROM " + TABLE_NAMEINGRESOS;
        Cursor cursor = db.rawQuery(query, null);

        String queryy = "SELECT SUM(cantidadGastada) AS gastoTotal FROM Gastos";
        Cursor cursorr = db.rawQuery(queryy, null);

        cursor.moveToFirst();
        cursorr.moveToFirst();

        double ingresosTotal = cursor.getDouble(cursor.getColumnIndex("ingresosTotales"));
        double gastoTotal = cursor.getDouble(cursor.getColumnIndex("gastoTotal"));

        double ingresoDisponible = ingresosTotal - gastoTotal;
        close();
        return ingresoDisponible;
    }

    private void open() {
        db = dbHelper.getWritableDatabase();
    }
    private void close() {
        if (db.isOpen())
            db.close();;
    }
}
