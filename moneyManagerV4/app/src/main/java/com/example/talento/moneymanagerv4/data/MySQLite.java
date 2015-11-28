package com.example.talento.moneymanagerv4.data;

/**
 * Created by Rene AMG on 25-Nov-15.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.talento.moneymanagerv4.R;

public class MySQLite extends SQLiteOpenHelper {

    final static int DATABASE_VERSION = 17;
    public static final String DATABASE_NAME = "MoneyManager.db";
    //Tabla Ingresos(Datos)
    public static final String TABLE_Ingresos = "Ingresos";
    public static final String idIngresos = "idIngreso";
    public static final String cantidadIngreso = "cantidadIngreso";
    public static final String fechaIngreso = "fechaIngreso";

    public static final String SQL_CREATE_TABLE_Ingresos = "create table " + TABLE_Ingresos + "( "+
            idIngresos + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            cantidadIngreso + " REAL NULL, " +
            fechaIngreso + " DATETIME NULL"+
            ")";

    //Tabla Categoria(Datos)
    public static final String TABLE_Categoria = "Categoria";
    public static final String _idCategoria = "_id";
    public static final String Categoria_img = "imgCategoria";
    public static final String Categoria_nombre = "nombre";
    public static final String Categoria_saldoLimite = "saldoLimite";


    //{"Comida", "Entretenimiento", "Transporte", "Salud", "Educacion","Regalo","Ropa", "Otros"};
    //Trae todas las imagenes agregadas para las categorias
    public static final int[] imageIDs = {
            R.mipmap.comida,//0 el index se pone en la precarga de categorias para que tenga la imagen dada.
            R.mipmap.entretenimiento,//1 ej. la categoria trabajo tendria el index 1
            R.mipmap.transporte,//2
            R.mipmap.salud,
            R.mipmap.educacion,
            R.mipmap.regalo,
            R.mipmap.ropa,
            R.mipmap.otros
    };

    public static String queryInsertCategoria1 = "INSERT INTO Categoria(nombre,imgCategoria,saldoLimite) VALUES ('Comida'," +imageIDs[0] + ",10)";
    public static String queryInsertCategoria2 = "INSERT INTO Categoria(nombre,imgCategoria,saldoLimite) VALUES ('Entretenimiento'," +imageIDs[1] + ",10)";
    public static String queryInsertCategoria3 = "INSERT INTO Categoria(nombre,imgCategoria,saldoLimite) VALUES ('Transporte'," +imageIDs[2] + ",10)";
    public static String queryInsertCategoria4 = "INSERT INTO Categoria(nombre,imgCategoria,saldoLimite) VALUES ('Salud'," +imageIDs[3] + ",10)";
    public static String queryInsertCategoria5 = "INSERT INTO Categoria(nombre,imgCategoria,saldoLimite) VALUES ('Educacion'," +imageIDs[4] + ",10)";
    public static String queryInsertCategoria6 = "INSERT INTO Categoria(nombre,imgCategoria,saldoLimite) VALUES ('Regalo'," +imageIDs[5] + ",10)";
    public static String queryInsertCategoria7 = "INSERT INTO Categoria(nombre,imgCategoria,saldoLimite) VALUES ('Ropa'," +imageIDs[6] + ",10)";
    public static String queryInsertCategoria8 = "INSERT INTO Categoria(nombre,imgCategoria,saldoLimite) VALUES ('Otros'," +imageIDs[7] + ",10)";

    public static String queryInsertIngreso = "INSERT INTO Ingresos(cantidadIngreso) VALUES (0)";

    public static String  queryInsertSaldo = "INSERT INTO Saldo(cantidadSaldo,diasAlCorte) VALUES (200, 10)";


    public static final String SQL_CREATE_TABLE_Categoria = "create table " + TABLE_Categoria + "( "+
            _idCategoria + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Categoria_img + " TEXT NULL, " +
            Categoria_nombre + " TEXT NOT NULL, "+
            Categoria_saldoLimite + " FLOAT "+
            ")";

    //Tabla Saldo(Datos)
    public static final String TABLE_Saldo = "Saldo";
    public static final String Saldo_idSaldo = "idSaldo";
    public static final String Saldo_cantidadSaldo = "cantidadSaldo";
    public static final String Saldo_diasAlCorte = "diasAlCorte";

    public static final String SQL_CREATE_TABLE_Saldo = "create table " + TABLE_Saldo + "("+
            Saldo_idSaldo + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            Saldo_cantidadSaldo + " REAL,"+
            Saldo_diasAlCorte + " INTEGER"+
            ")";

    //Tabla Gastos(Datos)
    public static final String TABLE_Gastos = "Gastos";
    public static final String Gastos_idGasto = "idGasto";
    public static final String Gastos_cantidadGastada = "cantidadGastada";
    public static final String Gastos_fechaGasto = "fechaGasto";
    public static final String Gastos_fechaCorte = "fechaCorte";
    public static final String Gastos_idCategoria = "idCategoria";
    public static final String Gastos_idSaldo = "idSaldo";

    public static final String SQL_CREATE_TABLE_Gastos = "create table " + TABLE_Gastos + "("+
            Gastos_idGasto + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            Gastos_cantidadGastada + " FLOAT,"+
            Gastos_fechaGasto + " DATE,"+
            Gastos_fechaCorte + " DATE,"+
            Gastos_idCategoria + " INTEGER," +
            Gastos_idSaldo + " INTEGER," +
            " FOREIGN KEY ("+Gastos_idCategoria+") REFERENCES "+TABLE_Categoria+"("+_idCategoria+"),"+
            " FOREIGN KEY ("+Gastos_idSaldo+") REFERENCES "+TABLE_Saldo+"("+Saldo_idSaldo+"));";



   // public static final String queryPreCargaCategorias = "INSERT INTO " + TABLE_Categoria + "("+ Categoria_img +", " + Categoria_nombre +") VALUES " +
  //          "("+imageIDs[0]+",'Trabajo'), ("+imageIDs[1]+",'Educación'), ("+imageIDs[2]+",'Ocio') ";

    public MySQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_Categoria);
        db.execSQL(SQL_CREATE_TABLE_Saldo);
        db.execSQL(SQL_CREATE_TABLE_Gastos);
        db.execSQL(SQL_CREATE_TABLE_Ingresos);
        db.execSQL(queryInsertSaldo);
        db.execSQL(queryInsertIngreso);
        db.execSQL(queryInsertCategoria1);
        db.execSQL(queryInsertCategoria2);
        db.execSQL(queryInsertCategoria3);
        db.execSQL(queryInsertCategoria4);
        db.execSQL(queryInsertCategoria5);
        db.execSQL(queryInsertCategoria6);
        db.execSQL(queryInsertCategoria7);
        db.execSQL(queryInsertCategoria8);
        // db.execSQL(queryPreCargaCategorias);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Saldo );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Categoria);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Gastos);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Ingresos);
        onCreate(db);
    }
}
