package com.example.talento.moneymanagerv4.fragments;

import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.talento.moneymanagerv4.R;
import com.example.talento.moneymanagerv4.data.IngresoDataSource;
import com.example.talento.moneymanagerv4.model.Ingresos;
import com.example.talento.moneymanagerv4.utils.Utils;

import java.util.Date;

/**
 * Created by Talento on 25/11/2015.
 */
public class registrarIngreso extends Fragment {
    public static final String TAG = "registrarIngreso";
    EditText txtCantidad;
    TextView lblSaldoActual;
    Button btnModificar;

   public static registrarIngreso newInstance()
    {
        registrarIngreso listacts = new registrarIngreso();
        return listacts;
    }
    public registrarIngreso() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.modificar_saldo, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        txtCantidad = (EditText) rootView.findViewById(R.id.txtCantidadIngreso);
        lblSaldoActual = (TextView) rootView.findViewById(R.id.lblSaldoActual);
        btnModificar = (Button) rootView.findViewById(R.id.btnAgregarSaldo);

        initIngresoTotales();

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double cantidadIngreso = 0;

                if(txtCantidad.getText().toString().length() > 0) {
                    cantidadIngreso = Double.parseDouble(txtCantidad.getText().toString());
                }

                if(cantidadIngreso > 0){
                Date fechaIngreso = new Date();

                Ingresos usua = new Ingresos(cantidadIngreso, fechaIngreso);


                IngresoDataSource ingresoSource = new IngresoDataSource(getActivity());
                ingresoSource.addIngreso(usua);

                Utils.showToast(getActivity(), "Ingreso Registrado");
                initIngresoTotales();
                }
                else{
                    Utils.showToast(getActivity(), "No se capturó una cantidad valida");
                }
            }
        });

        return rootView;
    }

    public void initIngresoTotales(  ) {

        IngresoDataSource ingresoTotal = new IngresoDataSource(getActivity());
        float cantidadTotalIngreso = (float)(Math.floor(ingresoTotal.getIngresoTotal() * 100) / 100);
        lblSaldoActual.setText(" $" + cantidadTotalIngreso);


        //Utils.showToast(getActivity(), id + "");

    //lblSaldoActual.setText("hola" + "");

    }
}
