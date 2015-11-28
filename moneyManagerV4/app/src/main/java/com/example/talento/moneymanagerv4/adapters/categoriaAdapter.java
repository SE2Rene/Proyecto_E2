package com.example.talento.moneymanagerv4.adapters;

import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.talento.moneymanagerv4.R;
import com.example.talento.moneymanagerv4.model.Categorias;

import java.util.List;

/**
 * Created by Rene AMG on 25-Nov-15.
 */
public class categoriaAdapter extends BaseAdapter {
    List<Categorias> catList;

    public categoriaAdapter(List<Categorias> catList) {
        this.catList = catList;
    }

    @Override
    public int getCount() {
        return catList.size();
    }

    @Override
    public Object getItem(int position) {
        return catList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        ViewGroup item; //representa la interfaz grafica

        if(convertView != null) //verifica si ya se inflo la vista
        {
            item = (ViewGroup) convertView;//si ya lo inflo, solo le pasa la vista de nuevo al item
        }
        else
        {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext()); //infla por primera vez el xml
            item = (ViewGroup) inflater.inflate(R.layout.cat, null);
        }

        TextView lblNomCategoria = (TextView) item.findViewById(R.id.lblNomCategoria); //inicializa la variable con el label
        TextView lblLimiteCategoria = (TextView) item.findViewById(R.id.lblLimiteCategoria);
        ImageView imageView4 = (ImageView) item.findViewById(R.id.imageView4);

        Categorias categoria = (Categorias) getItem(position); //obtiene los elementos

        lblNomCategoria.setText(categoria.getNombreCategoria()); //escribe el nombre
        lblLimiteCategoria.setText("$" + categoria.getSaldoLimiteCategoria());

       // imageView4.setImageResource(R.mipmap.comida);

        if(categoria.getIdCategoria() == 1){
            imageView4.setImageResource(R.mipmap.comida);
        }
        else if(categoria.getIdCategoria() == 2){
            imageView4.setImageResource(R.mipmap.entretenimiento);
        } else if(categoria.getIdCategoria() == 3){
            imageView4.setImageResource(R.mipmap.transporte);
        }
        else if(categoria.getIdCategoria() == 4){
            imageView4.setImageResource(R.mipmap.salud);
        }
        else if(categoria.getIdCategoria() == 5){
            imageView4.setImageResource(R.mipmap.educacion);
        }
        else if(categoria.getIdCategoria() == 6){
            imageView4.setImageResource(R.mipmap.regalo);
        }
        else if(categoria.getIdCategoria()== 7){
            imageView4.setImageResource(R.mipmap.ropa);
        }
        else if(categoria.getIdCategoria() == 8){
            imageView4.setImageResource(R.mipmap.otros);
        }


        //lblID.setText(categoria.getIdCategoria()+"");
        final int idvj = categoria.getIdCategoria();

        //setonclickListener();
       /* btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int verbor = 0;
                if(misjuegos==1)
                {
                    verbor = 1;
                }
                else
                {
                    verbor = 0;
                }

                Intent i = new Intent(parent.getContext(), detalle.class);
                i.putExtra("idvj", idvj);
                i.putExtra("verborrar", verbor);
                parent.getContext().startActivity(i);
            }
        });*/

        return item;
    }


}

