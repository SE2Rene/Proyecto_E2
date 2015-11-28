package com.example.talento.moneymanagerv4.fragments;

import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.talento.moneymanagerv4.R;
import com.example.talento.moneymanagerv4.data.CategoriaDataSource;
import com.example.talento.moneymanagerv4.data.GastoDataSource;
import com.example.talento.moneymanagerv4.data.IngresoDataSource;
import com.example.talento.moneymanagerv4.data.SaldoDataSource;
import com.example.talento.moneymanagerv4.model.Categorias;
import com.example.talento.moneymanagerv4.model.Gastos;
import com.example.talento.moneymanagerv4.model.Ingresos;
import com.example.talento.moneymanagerv4.model.Saldos;
import com.example.talento.moneymanagerv4.utils.Utils;

import java.util.Date;
import java.util.List;

/**
 * Created by Rene AMG on 26-Nov-15.
 */
public class registrarGasto  extends Fragment {
    public static final String TAG = "registrarGasto";
    EditText txtCantidad;
    Button btnRegistrarGasto;
    Spinner btn_dropdown;

    public static registrarGasto newInstance()
    {
        registrarGasto listacts = new registrarGasto();
        return listacts;
    }
    public registrarGasto() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.registrar_gasto, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //txtCantidad = (EditText)getActivity().findViewById(R.id.cantidadSaldo);
        txtCantidad = (EditText) rootView.findViewById(R.id.txtCantidadGasto);
        btnRegistrarGasto = (Button) rootView.findViewById(R.id.btnRegistrarGasto);
        btn_dropdown = (Spinner) rootView.findViewById(R.id.btn_dropdown);

        String[] items = new String[]{"Comida", "Entretenimiento", "Transporte", "Salud", "Educacion","Regalo","Ropa", "Otros"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        btn_dropdown.setAdapter(adapter);

        btnRegistrarGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float  cantidadGastada = 0;
                if(txtCantidad.getText().toString().length() > 0){
                  cantidadGastada = Float.parseFloat(txtCantidad.getText().toString());
                }

                if(cantidadGastada > 0) {
                    Date fechaGasto = new Date();
                    Date fechaCorte = new Date();

                    //Se debe modificar cuando se use el spinner para categorias.
                    String texto = btn_dropdown.getSelectedItem().toString();
                    int idCategoria = categoriaTextoId(texto);

                    if (idCategoria != 9) {
                       // CategoriaDataSource catDS = new CategoriaDataSource(getActivity());

                       // double saldoLimiteCategoria =  catDS.getsaldoLimite(idCategoria);
                        GastoDataSource gastoSource = new GastoDataSource(getActivity());

                        Categorias categoriaGasto = obtenerCategoria(idCategoria);

                        double saldoLimiteCategoria = categoriaGasto.getSaldoLimiteCategoria();


                            Saldos saldoGasto = new SaldoDataSource(getActivity()).getSaldo();

                            Gastos usua = new Gastos(cantidadGastada, fechaGasto, fechaCorte, categoriaGasto, saldoGasto);


                            gastoSource.addGasto(usua);
                            double gastoActual =  gastoSource.getGastoByIdCategoria(idCategoria);

                            Utils.showToast(getActivity(), "Gasto Registrado");
                         if((gastoActual - saldoLimiteCategoria) > 0) {
                            Utils.showToast(getActivity(), "Se ha superado la cantidad limite de esta categoria");
                        }

                    } else if (idCategoria == 9) {
                        Utils.showToast(getActivity(), "No se ha seleccionado categoria");
                    }
                } else{
                    Utils.showToast(getActivity(), "No se capturó una cantidad valida");
                }
            }
        });

        return rootView;
    }

    public Categorias obtenerCategoria(int idCategoria){
        Categorias categoria = new CategoriaDataSource(getActivity()).getCategoria(idCategoria);
        return categoria;
    }

    public int categoriaTextoId(String textoCategoria){
        int idCategoria;

        if(textoCategoria == "Comida"){
            idCategoria = 1;
        }else if(textoCategoria == "Entretenimiento"){
            idCategoria = 2;
        }
        else if(textoCategoria == "Transporte"){
            idCategoria = 3;
        }
        else if(textoCategoria == "Salud"){
            idCategoria = 4;
        }
        else if(textoCategoria == "Educacion"){
            idCategoria = 5;
        }
        else if(textoCategoria == "Regalo"){
            idCategoria = 6;
        }
        else if(textoCategoria == "Ropa"){
            idCategoria = 7;
        }
        else if(textoCategoria == "Otros"){
            idCategoria = 8;
        }else { //En caso de que no se a seleccionado ni una categoria.
            idCategoria = 9;
        }

        return idCategoria;
    }
}
