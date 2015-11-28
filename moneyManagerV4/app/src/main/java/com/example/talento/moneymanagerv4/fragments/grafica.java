package com.example.talento.moneymanagerv4.fragments;

import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.talento.moneymanagerv4.R;
import com.example.talento.moneymanagerv4.data.CategoriaDataSource;
import com.example.talento.moneymanagerv4.data.GastoDataSource;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by Rene AMG on 26-Nov-15.
 */
public class grafica  extends Fragment {
    public static final String TAG = "grafica";

    //private RelativeLayout mainLayout;
    private FrameLayout mainLayout;
    private PieChart mChart;

    //we are going to displaypie char for smarthphones market shares
    private float[] yData = {5,10,10,10,1,0,20,80};
    private String[] xData = {"Comida", "Entretenimiento", "Transporte", "Salud", "Educacion","Regalo","Ropa", "Otros"};;


    public static grafica newInstance(Bundle arguments)
    {
        grafica listacts = new grafica();
        if(arguments != null)
        {
            listacts.setArguments(arguments);
        }
        return listacts;
    }

    public grafica() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pie_chart, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Bundle bundle = getArguments();
        int un = bundle.getInt("id");

        GastoDataSource gastoSource = new GastoDataSource(getActivity());

        //{"Comida", "Entretenimiento", "Transporte", "Salud", "Educacion","Regalo","Ropa", "Otros"};
        yData[0] = (float) gastoSource.getGastosComida();
        yData[1] = (float) gastoSource.getGastosEntretenimiento();
        yData[2] = (float) gastoSource.getGastosTransporte();
        yData[3] = (float) gastoSource.getGastosSalud();
        yData[4] = (float) gastoSource.getGastosEducacion();
        yData[5] = (float) gastoSource.getGastosRegalo();
        yData[6] = (float) gastoSource.getGastosRopa();
        yData[7] = (float) gastoSource.getGastosOtros();


        mainLayout = (FrameLayout) rootView.findViewById(R.id.chartContainer);
        mChart = new PieChart(getActivity()); //Tiene el context.

        //add pie chart to main layout
        mainLayout.addView(mChart);
        mainLayout.setBackgroundColor(Color.WHITE);

        //configure pie chart
        mChart.setUsePercentValues(true);
        mChart.setDescription(""); //Smartphones Market Share

        //enable hole and configure
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColorTransparent(true);
        mChart.setHoleRadius(7);
        mChart.setTransparentCircleRadius(10);

        // enable rotation of the chart by touch
        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);

        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                if (e == null)
                    return;

                Toast.makeText(getActivity(),
                        xData[e.getXIndex()] + " = $" + e.getVal() + "", Toast.LENGTH_SHORT).show();//El mensaje al darle click
            }

            @Override
            public void onNothingSelected() {

            }
        });
        // add data
        addData();

        // customize legends
        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);


        return rootView;
    }

    private void addData() {
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        for (int i = 0; i < yData.length; i++)
            yVals1.add(new Entry(yData[i], i));

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < xData.length; i++)
            xVals.add(xData[i]);

        // create pie data set
        PieDataSet dataSet = new PieDataSet(yVals1, "");//Market Share
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);

        // add many colors
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);

        // instantiate pie data object now
        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.GRAY);

        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        // update pie chart
        mChart.invalidate();
    }
}
