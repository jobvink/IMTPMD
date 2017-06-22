package com.hsl.imtpmd.imtpmd;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class PuntenOverzicht extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punten_overzicht);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BarChart puntenOverzicht;

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        puntenOverzicht = (BarChart) findViewById(R.id.Puntenchart);

        ArrayList<BarEntry> punten = new ArrayList<>();
        punten.add(new BarEntry(44f,0));
        punten.add(new BarEntry(24f,0));
        punten.add(new BarEntry(55f,0));
        punten.add(new BarEntry(94f,0));
        punten.add(new BarEntry(14f,0));
        punten.add(new BarEntry(14f,0));
        BarDataSet barDataSet = new BarDataSet(punten,"Punten");


        BarData theData = new BarData(barDataSet);
        puntenOverzicht.setData(theData);

        puntenOverzicht.setTouchEnabled(true);
        puntenOverzicht.setDragEnabled(true);
        puntenOverzicht.setScaleEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

}
