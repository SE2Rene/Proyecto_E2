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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.talento.moneymanagerv4.R;
import com.example.talento.moneymanagerv4.data.CategoriaDataSource;
import com.example.talento.moneymanagerv4.model.Categorias;
import com.example.talento.moneymanagerv4.utils.Utils;

/**
 * Created by tecnologia on 27/11/2015.
 */
public class modificarCategoria extends Fragment {
    public static final String TAG = "modificarCategoria";
    TextView nombreCategoria;
    EditText txtSaldoLimite;
    TextView txtSaldoLimiteActual;
    Button btnEditar;
    ImageView imgViewCategoria;


    public static modificarCategoria newInstance(Bundle arguments)
    {
        modificarCategoria listacts = new modificarCategoria();
        if(arguments != null)
        {
            listacts.setArguments(arguments);
        }
        return listacts;
    }

    public modificarCategoria() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.modificar_categoria, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        nombreCategoria = (TextView) rootView.findViewById(R.id.nombreCategoria);
        txtSaldoLimite = (EditText) rootView.findViewById(R.id.txtSaldoLimite);
        txtSaldoLimiteActual = (TextView) rootView.findViewById(R.id.txtSaldoLimiteActual);
        btnEditar = (Button) rootView.findViewById(R.id.btnEditar);
        imgViewCategoria = (ImageView) rootView.findViewById(R.id.imgViewCategoria);

        Bundle b = getArguments();
        int categoriaId = b.getInt("id");

        Categorias categoriaSeleccionada = obtenerCategoria(categoriaId);
        nombreCategoria.setText(categoriaSeleccionada.getNombreCategoria() + "");
        txtSaldoLimiteActual.setText(categoriaSeleccionada.getSaldoLimiteCategoria() + "");

        if(categoriaSeleccionada.getIdCategoria() == 1){
            imgViewCategoria.setImageResource(R.mipmap.comida);
        }
        else if(categoriaSeleccionada.getIdCategoria() == 2){
            imgViewCategoria.setImageResource(R.mipmap.entretenimiento);
        } else if(categoriaSeleccionada.getIdCategoria() == 3){
            imgViewCategoria.setImageResource(R.mipmap.transporte);
        }
        else if(categoriaSeleccionada.getIdCategoria() == 4){
            imgViewCategoria.setImageResource(R.mipmap.salud);
        }
        else if(categoriaSeleccionada.getIdCategoria() == 5){
            imgViewCategoria.setImageResource(R.mipmap.educacion);
        }
        else if(categoriaSeleccionada.getIdCategoria() == 6){
            imgViewCategoria.setImageResource(R.mipmap.regalo);
        }
        else if(categoriaSeleccionada.getIdCategoria()== 7){
            imgViewCategoria.setImageResource(R.mipmap.ropa);
        }
        else if(categoriaSeleccionada.getIdCategoria() == 8){
            imgViewCategoria.setImageResource(R.mipmap.otros);
        }

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float  nuevoSaldoLimite = 0;
                if(txtSaldoLimite.getText().toString().length() > 0){
                    nuevoSaldoLimite = Float.parseFloat(txtSaldoLimite.getText().toString());
                }
                if(nuevoSaldoLimite > 0) {
                    Bundle b = getArguments();
                    int categoriaId = b.getInt("id");
                    Categorias categoriaSeleccionada = obtenerCategoria(categoriaId);
                    categoriaSeleccionada.setSaldoLimiteCategoria(nuevoSaldoLimite);
                    CategoriaDataSource catSource = new CategoriaDataSource(getActivity());
                    catSource.updateSaldoLimite(categoriaSeleccionada);
                    Utils.showToast(getActivity(), "Categoria Actualizada");
                    txtSaldoLimiteActual.setText(categoriaSeleccionada.getSaldoLimiteCategoria() + "");
                }else{//Dejo saldoLimite vacio.
                    /*Bundle b = getArguments();
                    int categoriaId = b.getInt("id");
                    Categorias categoriaSeleccionada = obtenerCategoria(categoriaId);

                    nuevoSaldoLimite = Float.parseFloat(null);


                    categoriaSeleccionada.setSaldoLimiteCategoria(nuevoSaldoLimite);
                    CategoriaDataSource catSource = new CategoriaDataSource(getActivity());
                    catSource.updateSaldoLimite(categoriaSeleccionada);
                    Utils.showToast(getActivity(), "Categoria Actualizada");
                    txtSaldoLimiteActual.setText(categoriaSeleccionada.getSaldoLimiteCategoria() + "");*/
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

}

