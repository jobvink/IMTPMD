package com.hsl.imtpmd.imtpmd;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.hsl.imtpmd.imtpmd.model.UserModel;
import com.hsl.imtpmd.imtpmd.model.UserVerplichtvakModel;

import java.util.ArrayList;

public class Hoofdfase34 extends AppCompatActivity {

    private ListView H23HoofdListview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoofdfase34);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        UserModel user = null;
        if(bundle != null)
            user = UserModel.getUser(getApplicationContext(), bundle.getString("user"));

        H23HoofdListview = (ListView) findViewById(R.id.hoofdvakkenh23);

        Log.d("Propedeuze: ", "ophalen van vakken");
        ArrayList<UserVerplichtvakModel> verplichtvakModels = UserVerplichtvakModel.propedeuze(getApplicationContext(), user);

        Log.d("propedeuze: ", "Printen van vakken");
        for (UserVerplichtvakModel vak : verplichtvakModels) {
            Log.d("Propedeuze", vak.getVerplichtvak().getNaam());
        }

        ListAdapter la = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                verplichtvakModels);

        H23HoofdListview.setAdapter(la);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Button puntenButton = (Button) findViewById(R.id.puntenButton34);

        puntenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Hoofdfase34.this, PuntenOverzicht.class));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

}
