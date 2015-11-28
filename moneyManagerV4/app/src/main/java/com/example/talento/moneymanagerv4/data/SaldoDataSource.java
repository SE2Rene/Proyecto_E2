package com.example.talento.moneymanagerv4.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.talento.moneymanagerv4.model.Categorias;
import com.example.talento.moneymanagerv4.model.Saldos;

/**
 * Created by Talento on 25/11/2015.
 */
public class SaldoDataSource {

    private final static String TABLE_NAMESALDO = "Saldo";
    private MySQLite dbHelper;
    private SQLiteDatabase db;

    public SaldoDataSource(Context c) {
        dbHelper = new MySQLite(c);
    }

    public void updateSaldo(Saldos saldo) {
        open();
        ContentValues values = new ContentValues();
        values.put("cantidadSaldo", String.valueOf(saldo.getCantidadSaldo()));
        String where = "idSaldo = " + saldo.getIdSaldo();
        db.update(TABLE_NAMESALDO, values, where, null);
        close();
    }

    public Saldos getSaldo() {
        open();
        String query = "SELECT * FROM " + TABLE_NAMESALDO + " WHERE idSaldo = 1" ;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0 ) {
            int id = cursor.getInt(cursor.getColumnIndex("idSaldo"));
            int diasAlCorte = cursor.getInt(cursor.getColumnIndex("diasAlCorte"));
            double cantidadSaldo = cursor.getDouble(cursor.getColumnIndex("cantidadSaldo"));

            close();

            Saldos saldo = new Saldos(id,cantidadSaldo,diasAlCorte);

            return saldo;
        }
        close();
        return null;
    }

    public void addSaldo(Saldos saldo) {
        open();
        ContentValues values = new ContentValues();
        values.put("diasAlCorte", saldo.getDiasAlCorte());
        values.put("cantidadSaldo", saldo.getCantidadSaldo());
        db.insert(TABLE_NAMESALDO, null, values);
        close();
    }

    private void open() {
        db = dbHelper.getWritableDatabase();
    }
    private void close() {
        if (db.isOpen())
            db.close();;
    }
}
