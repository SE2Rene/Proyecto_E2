package com.example.talento.moneymanagerv4.fragments;

import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.talento.moneymanagerv4.R;
import com.example.talento.moneymanagerv4.data.GastoDataSource;
import com.example.talento.moneymanagerv4.data.IngresoDataSource;

/**
 * Created by tecnologia on 27/11/2015.
 */
public class Inicio  extends Fragment {
    public static final String TAG = "Inicio";
    TextView txtIngresosTotal, txtGastosGeneral, txtComida, txtEntretenimiento,txtTransporte;
    TextView txtSalud, txtEducacion, txtRegalo, txtRopa, txtOtros;
    Button btnBorrar;
    ImageView imgComida, imgEntretenimiento, imgTransporte, imgSalud, imgEducacion, imgRegalo, imgRopa, imgOtros;

    public static Inicio newInstance()
    {
        Inicio listacts = new Inicio();
        return listacts;
    }
    public Inicio() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.inicio, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        txtIngresosTotal = (TextView) rootView.findViewById(R.id.txtIngresosTotal);
        txtGastosGeneral = (TextView) rootView.findViewById(R.id.txtGastosGeneral);
        btnBorrar = (Button) rootView.findViewById(R.id.btnBorrar);

        txtComida = (TextView) rootView.findViewById(R.id.txtComida);
        txtEntretenimiento = (TextView) rootView.findViewById(R.id.txtEntretenimiento);
        txtTransporte = (TextView) rootView.findViewById(R.id.txtTransporte);
        txtSalud = (TextView) rootView.findViewById(R.id.txtSalud);
        txtEducacion = (TextView) rootView.findViewById(R.id.txtEducacion);
        txtRegalo = (TextView) rootView.findViewById(R.id.txtRegalo);
        txtRopa = (TextView) rootView.findViewById(R.id.txtRopa);
        txtOtros = (TextView) rootView.findViewById(R.id.txtOtros);

        imgComida = (ImageView) rootView.findViewById(R.id.imgComida);
        imgEntretenimiento = (ImageView) rootView.findViewById(R.id.imgEntretenimiento);
        imgTransporte = (ImageView) rootView.findViewById(R.id.imgTransporte);
        imgSalud = (ImageView) rootView.findViewById(R.id.imgSalud);
        imgEducacion = (ImageView) rootView.findViewById(R.id.imgEducacion);
        imgRegalo = (ImageView) rootView.findViewById(R.id.imgRegalo);
        imgRopa = (ImageView) rootView.findViewById(R.id.imgRopa);
        imgOtros = (ImageView) rootView.findViewById(R.id.imgOtros);

        final GastoDataSource gastoSource = new GastoDataSource(getActivity());

        txtComida.setText("$" +gastoSource.getGastosComida());
        txtEntretenimiento.setText("$" +gastoSource.getGastosEntretenimiento());
        txtTransporte.setText("$" +gastoSource.getGastosTransporte());
        txtSalud.setText("$" +gastoSource.getGastosSalud());
        txtEducacion.setText("$" +gastoSource.getGastosEducacion());
        txtRegalo.setText("$" +gastoSource.getGastosRegalo());
        txtRopa.setText("$" +gastoSource.getGastosRopa());
        txtOtros.setText("$" + gastoSource.getGastosOtros());

        imgComida.setImageResource(R.mipmap.comida);
        imgEntretenimiento.setImageResource(R.mipmap.entretenimiento);
        imgTransporte.setImageResource(R.mipmap.transporte);
        imgSalud.setImageResource(R.mipmap.salud);
        imgEducacion.setImageResource(R.mipmap.educacion);
        imgRegalo.setImageResource(R.mipmap.regalo);
        imgRopa.setImageResource(R.mipmap.ropa);
        imgOtros .setImageResource(R.mipmap.otros);

        final IngresoDataSource ingresoTotal = new IngresoDataSource(getActivity());
        txtIngresosTotal.setText("$"+ingresoTotal.getIngresoTotal());
        txtGastosGeneral.setText("$"+gastoSource.getGastosGeneral());



        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingresoTotal.deleteAllIngresos();
                gastoSource.deleteAllGasto();

                txtIngresosTotal.setText("$" + ingresoTotal.getIngresoTotal());
                txtGastosGeneral.setText("$" + gastoSource.getGastosGeneral());

                txtComida.setText("$" +gastoSource.getGastosComida());
                txtEntretenimiento.setText("$" +gastoSource.getGastosEntretenimiento());
                txtTransporte.setText("$" +gastoSource.getGastosTransporte());
                txtSalud.setText("$" +gastoSource.getGastosSalud());
                txtEducacion.setText("$" +gastoSource.getGastosEducacion());
                txtRegalo.setText("$" +gastoSource.getGastosRegalo());
                txtRopa.setText("$" +gastoSource.getGastosRopa());
                txtOtros.setText("$" + gastoSource.getGastosOtros());

            }
        });

        return rootView;
    }
}
