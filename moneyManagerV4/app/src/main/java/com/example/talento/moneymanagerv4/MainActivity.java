package com.example.talento.moneymanagerv4;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.talento.moneymanagerv4.adapters.DrawerAdapter;
import com.example.talento.moneymanagerv4.fragments.Inicio;
import com.example.talento.moneymanagerv4.fragments.grafica;
import com.example.talento.moneymanagerv4.fragments.listact;
import com.example.talento.moneymanagerv4.fragments.registrarGasto;
import com.example.talento.moneymanagerv4.fragments.registrarIngreso;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity{

    private DrawerLayout drawerLayout;
    private ListView drawerListView;
    private String[] sectionsTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawerListView = (ListView) findViewById(R.id.drawer_listview);
        sectionsTitle = getResources().getStringArray(R.array.str_array_sectionsTitle);

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(sectionsTitle));
        drawerListView.setAdapter(adapter);

      //  Inicio frg = Inicio.newInstance();
       // changeFragment(frg, "Inicio");

        drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    // Se dio click en Crear Titulo
                    Bundle arguments = new Bundle();
                    arguments.putInt("id", 1);

                    listact frg = listact.newInstance(arguments);
                    changeFragment(frg, "fgListaVj");
                } else if (position == 1) {
                    // Se dio click en Ingresar Gasto
                    registrarGasto frg = registrarGasto.newInstance();
                    changeFragment(frg, "registrarGasto");
                } else if (position == 2) {
                    // Se dio click en Agregar Saldo
                    //changeFragment(new addVj(), "fgAddVJ");
                    registrarIngreso frg = registrarIngreso.newInstance();
                    changeFragment(frg, "registrarIngreso");
                } else if (position == 3) {
                    // Se dio click en Mov
                    Bundle arguments = new Bundle();
                    arguments.putInt("id", 1);

                    grafica frg = grafica.newInstance(arguments);
                    changeFragment(frg, "grafica");
                } else if (position == 4) {
                    // Se dio click en Mov
                    Inicio frg = Inicio.newInstance();
                    changeFragment(frg, "Inicio");
                }
                drawerLayout.closeDrawers();
            }
        });

       if (savedInstanceState == null) {
             Inicio frg = Inicio.newInstance();
             changeFragment(frg, "Inicio");
        }


    }


    private void changeFragment(Fragment newFragment, String tag) {

        FragmentManager fragmentManager = getFragmentManager();

        Fragment currentFragment = fragmentManager.findFragmentByTag(tag);

        if (currentFragment != null && currentFragment.isVisible())
            return;

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.content_frame, newFragment, tag).addToBackStack(null);
        ft.commit();

    }


    public void onBackPressed() {
        //super.onBackPressed();
        //aqui mando a llamar al fragment anterior
        if(getFragmentManager().getBackStackEntryCount() > 0)
        {
            getFragmentManager().popBackStack();
        }
        else
        {
            super.onBackPressed();
        }
    }

}
