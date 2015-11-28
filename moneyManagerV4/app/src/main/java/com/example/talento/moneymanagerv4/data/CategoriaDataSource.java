package com.example.talento.moneymanagerv4.data;

/**
 * Created by Rene AMG on 25-Nov-15.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.talento.moneymanagerv4.model.Categorias;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDataSource {
    //private final static String TABLE_NAMEUSUARIO = "Categoria";

    private final static String TABLE_NAMECATEGORIA = "Categoria";
    private MySQLite dbHelper;
    private SQLiteDatabase db;

    public CategoriaDataSource(Context c) {
        dbHelper = new MySQLite(c);
    }

    public void addCategoria(Categorias categoria) {
        open();
        ContentValues values = new ContentValues();
        values.put("nombre", categoria.getNombreCategoria());
        values.put("saldoLimite", String.valueOf(categoria.getSaldoLimiteCategoria()));
        db.insert(TABLE_NAMECATEGORIA, null, values);
        close();
    }

    public void updateCategoria(Categorias categoria) {
        open();
        ContentValues values = new ContentValues();
        values.put("nombre", categoria.getNombreCategoria());
        values.put("saldoLimite", String.valueOf(categoria.getSaldoLimiteCategoria()));
        String where = "_id = " + categoria.getIdCategoria();
        db.update(TABLE_NAMECATEGORIA, values, where, null);
        close();
    }

    public void updateSaldoLimite(Categorias categoria) {
        open();
        ContentValues values = new ContentValues();
        values.put("saldoLimite", String.valueOf(categoria.getSaldoLimiteCategoria()));
        String where = "_id = " + categoria.getIdCategoria();
        db.update(TABLE_NAMECATEGORIA, values, where, null);
        close();
    }

    public void deleteCategoria(Categorias categoria) {
        open();
        String where = "_id = " + categoria.getIdCategoria();
        db.delete(TABLE_NAMECATEGORIA, where, null);
        close();
    }

    public List<Categorias> getAllCategorias() {
        open();
        String query = "SELECT * FROM " + TABLE_NAMECATEGORIA;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        List<Categorias> categoria = new ArrayList<Categorias>();
        while(!cursor.isAfterLast()) {

            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String nombreCategoria = cursor.getString(cursor.getColumnIndex("nombre"));
            float saldoLimite = cursor.getFloat(cursor.getColumnIndex("saldoLimite"));


            Categorias categorias = new Categorias(id,nombreCategoria,saldoLimite);
            categoria.add(categorias);
            cursor.moveToNext();
        }

        close();
        return categoria;
    }


    public Categorias getCategoria(int idCate) {
        open();
        String query = "SELECT * FROM " + TABLE_NAMECATEGORIA + " WHERE _id = " + idCate;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0 ) {
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String nombreCategoria = cursor.getString(cursor.getColumnIndex("nombre"));
            float saldoLimite = cursor.getFloat(cursor.getColumnIndex("saldoLimite"));

            close();

            Categorias categorias = new Categorias(id,nombreCategoria,saldoLimite);

            return categorias;
        }
        close();
        return null;
    }

    public double getsaldoLimite(int idCategoria) {
        open();
        String query = "SELECT saldoLimite FROM Categoria WHERE idCategoria = " + idCategoria;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        double saldoLimite = cursor.getDouble(cursor.getColumnIndex("saldoLimite"));
        close();
        return saldoLimite;
    }

    private void open() {
        db = dbHelper.getWritableDatabase();
    }
    private void close() {
        if (db.isOpen())
            db.close();;
    }

}
