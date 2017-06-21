package com.hsl.imtpmd.imtpmd;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.hsl.imtpmd.imtpmd.model.UserModel;
import com.hsl.imtpmd.imtpmd.model.UserVerplichtvakModel;
import com.hsl.imtpmd.imtpmd.model.VerplichtvakModel;

import java.util.ArrayList;

public class Propedeuse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propedeuse);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        UserModel user = null;
        if(bundle != null)
            user = UserModel.getUser(getApplicationContext(), bundle.getString("user"));

        Log.d("Propedeuze: ", "ophalen van vakken");
        ArrayList<UserVerplichtvakModel> verplichtvakModels = UserVerplichtvakModel.propedeuze(getApplicationContext(), user);

        Log.d("propedeuze: ", "Printen van vakken");
        for (UserVerplichtvakModel vak : verplichtvakModels) {
            Log.d("Propedeuze", vak.getVerplichtvak().getNaam());
        }

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

}
