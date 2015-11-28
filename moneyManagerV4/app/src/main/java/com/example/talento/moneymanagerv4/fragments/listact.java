package com.example.talento.moneymanagerv4.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


import com.example.talento.moneymanagerv4.R;
import com.example.talento.moneymanagerv4.adapters.categoriaAdapter;
import com.example.talento.moneymanagerv4.data.CategoriaDataSource;
import com.example.talento.moneymanagerv4.model.Categorias;
import com.example.talento.moneymanagerv4.utils.Utils;


import java.util.List;

/**
 * Created by Rene AMG on 25-Nov-15.
 */

public class listact extends Fragment {

    //ListView lstVjs;
    ListView lstCatgs;
    public static final String TAG = "listact";

    public static listact newInstance(Bundle arguments)
    {
        listact listacts = new listact();
        if(arguments != null)
        {
            listacts.setArguments(arguments);
        }
        return listacts;
    }

    public listact() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fgg_listact, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        lstCatgs = (ListView) rootView.findViewById(R.id.lstCatgs);

        Bundle bundle = getArguments();
        int un = bundle.getInt("id");

   //     Utils.showToast(getActivity(), "Categorias" + "");

      initCustomListView();

        return rootView;
    }

    private void initCustomListView() {

        List<Categorias> vjs = new CategoriaDataSource(getActivity()).getAllCategorias();
       // Utils.showToast(getActivity(), vjs.get(0).getImgCategoria() + "");
        categoriaAdapter ps = new categoriaAdapter(vjs);
        lstCatgs.setAdapter(ps);

        lstCatgs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Bundle arguments = new Bundle();
                long idPrecionado = id +1;
                arguments.putInt("id", (int)idPrecionado);

                modificarCategoria frg = modificarCategoria.newInstance(arguments);
                frg.setArguments(arguments);
                changeFragment(frg, "modificarCategoria");

            }
        });

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

}
